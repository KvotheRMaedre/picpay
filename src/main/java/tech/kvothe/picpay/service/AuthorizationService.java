package tech.kvothe.picpay.service;

import org.springframework.stereotype.Service;
import tech.kvothe.picpay.client.AuthorizationClient;
import tech.kvothe.picpay.dto.TransferDTO;
import tech.kvothe.picpay.entity.Transfer;
import tech.kvothe.picpay.exception.PicPayException;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer) {
        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()){
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }
}
