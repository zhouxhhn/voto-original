package bjl.core.exception;

/**
 * 已存在异常
 * <p>
 * Created by pengyi on 2016/3/3.
 */
public class ExistException extends RuntimeException {

    public ExistException() {
    }

    public ExistException(String message) {
        super(message);
    }

    public ExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistException(Throwable cause) {
        super(cause);
    }

}
