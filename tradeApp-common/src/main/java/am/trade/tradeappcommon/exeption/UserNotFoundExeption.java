package am.trade.tradeappcommon.exeption;

public class UserNotFoundExeption extends Exception {
    public UserNotFoundExeption() {
    }

    public UserNotFoundExeption(String message) {
        super(message);
    }

    public UserNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundExeption(Throwable cause) {
        super(cause);
    }

    public UserNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
