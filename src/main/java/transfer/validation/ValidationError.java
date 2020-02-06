package transfer.validation;

public interface ValidationError {

    int httpStatus();

    String message();
}
