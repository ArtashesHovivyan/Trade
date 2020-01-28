package am.trade.tradeappcommon.exeption;

public class CategoryNotFoundExeption extends Exception {
    public CategoryNotFoundExeption() {
    }

    public CategoryNotFoundExeption(String message) {
        super(message);
    }

    public CategoryNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundExeption(Throwable cause) {
        super(cause);
    }

    public CategoryNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
