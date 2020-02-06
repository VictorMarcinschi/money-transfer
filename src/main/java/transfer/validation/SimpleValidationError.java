package transfer.validation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleValidationError implements ValidationError {

    private final int httpStatus;
    private final String message;

    @Override
    public int httpStatus() {
        return httpStatus;
    }

    @Override
    public String message() {
        return message;
    }
}
