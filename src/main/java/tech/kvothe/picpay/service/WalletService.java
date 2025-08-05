package tech.kvothe.picpay.service;

import org.springframework.stereotype.Service;
import tech.kvothe.picpay.dto.WalletDTO;
import tech.kvothe.picpay.entity.Wallet;
import tech.kvothe.picpay.exception.WalletDataAlreadyExistsException;
import tech.kvothe.picpay.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(WalletDTO dto) {

        validateWallet(dto);

        return walletRepository.save(dto.toWallet());
    }

    private void validateWallet(WalletDTO dto) {
        var wallet = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (wallet.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists.");
        }
    }
}
