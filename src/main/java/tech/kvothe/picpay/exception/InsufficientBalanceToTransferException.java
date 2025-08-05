package tech.kvothe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBalanceToTransferException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetails = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetails.setTitle("Insufficient balance for this transfer");
        problemDetails.setDetail("You can't transfer a value bigger than your current balance.");
        return problemDetails;
    }
}
