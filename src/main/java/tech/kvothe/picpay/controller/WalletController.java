package tech.kvothe.picpay.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.kvothe.picpay.dto.WalletDTO;
import tech.kvothe.picpay.entity.Wallet;
import tech.kvothe.picpay.service.WalletService;

@RestController
@RequestMapping("/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid WalletDTO request) {
        var wallet = walletService.createWallet(request);
        return ResponseEntity.ok(wallet);
    }
}
