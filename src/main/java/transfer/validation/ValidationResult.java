package transfer.validation;

import lombok.Getter;

import java.util.Optional;

@Getter
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
