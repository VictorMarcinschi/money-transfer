package transfer.validation;

import java.util.Optional;

public class ValidationResult {

    private Optional<ValidationError> error;

    public ValidationResult() {
        error = Optional.empty();
    }

    public void fail(ValidationError cause) {
        error = Optional.of(cause);
    }

    public boolean isValid() {
        return error.isEmpty();
    }
}
