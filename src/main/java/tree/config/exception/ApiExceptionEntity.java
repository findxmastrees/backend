package tree.config.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@ToString
public class ApiExceptionEntity {
    private String errorCode;
    private Object errorMessage;

    @Builder
    public ApiExceptionEntity(HttpStatus status, String errorCode, Object errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Builder
    public ApiExceptionEntity(HttpStatus status, String errorCode, Map<String, String> errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
