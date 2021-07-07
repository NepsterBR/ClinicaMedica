package exceptions;

public class NoClientException extends RuntimeException {
    public NoClientException(String messagem) { super(messagem); }
}
