package tech.kvothe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWalletType extends PicPayException{

    @Override
    public ProblemDetail toProblemDetail() {
        var problemaDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemaDetail.setTitle("The payer wallet type isn't allow to transfer.");
        return problemaDetail;
    }
}
