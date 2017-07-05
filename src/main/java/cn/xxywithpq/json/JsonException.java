package cn.xxywithpq.json;

/**
 * Created by panqian on 2017/7/5.
 */
public class JsonException extends RuntimeException {

    public JsonException() {
        super();
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonException(String message) {
        super(message);
    }
}
