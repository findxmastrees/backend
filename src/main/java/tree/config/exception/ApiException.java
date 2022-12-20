package tree.config.exception;

import lombok.Getter;

@Getter
public class ApiException  extends RuntimeException {
    private ExceptionEnum error;
    private String msg;

    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
        this.msg = e.getMessage();
    }
    public ApiException(ExceptionEnum e, String msg) {
        super(e.getMessage());
        this.error = e;
        this.msg= msg;
    }
}
