package tech.kvothe.picpay.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.kvothe.picpay.dto.TransferDTO;
import tech.kvothe.picpay.entity.Transfer;
import tech.kvothe.picpay.entity.Wallet;
import tech.kvothe.picpay.exception.InsufficientBalanceToTransferException;
import tech.kvothe.picpay.exception.TransferNotAllowedForWalletType;
import tech.kvothe.picpay.exception.TransferNotAuthorizedException;
import tech.kvothe.picpay.exception.WalletNotFoundException;
import tech.kvothe.picpay.repository.TransferRepository;
import tech.kvothe.picpay.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;

    public TransferService(TransferRepository transferRepository,
                           WalletRepository walletRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService) {
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transfer transfer(TransferDTO request) {
        var walletPayee = walletRepository.findById(request.payee())
                .orElseThrow(()-> new WalletNotFoundException(request.payee()));

        var walletPayer = walletRepository.findById(request.payer())
                .orElseThrow(()-> new WalletNotFoundException(request.payer()));

        validateTransfer(request, walletPayer);

        walletPayer.debit(request.value());
        walletPayee.credit(request.value());

        var transfer = new Transfer(request.value(), walletPayee, walletPayer);

        walletRepository.save(walletPayer);
        walletRepository.save(walletPayee);

        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO request, Wallet walletPayer) {
        if (!walletPayer.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletType();
        }

        if (!walletPayer.isBalanceEnoughFor(request.value())) {
            throw new InsufficientBalanceToTransferException();
        }

        if (authorizationService.isAuthorized(request)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
