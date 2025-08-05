package tech.kvothe.picpay.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.kvothe.picpay.dto.TransferDTO;
import tech.kvothe.picpay.entity.Transfer;
import tech.kvothe.picpay.service.TransferService;

@RestController
@RequestMapping("/v1/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO request) {
        var transfer = transferService.transfer(request);
        return ResponseEntity.ok(transfer);
    }
}
