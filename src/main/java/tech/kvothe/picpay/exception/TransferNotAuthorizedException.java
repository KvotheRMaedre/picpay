package tech.kvothe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("Transfer not authorized");
        problemDetail.setDetail("This transfer was not authorized by the authorization service.");
        return problemDetail;
    }
}
