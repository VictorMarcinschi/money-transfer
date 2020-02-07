package transfer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import transfer.validation.ValidationError;
import transfer.validation.ValidationResult;

@RequiredArgsConstructor
@Getter
@Builder
public class CommandResult<RES> {

    private final ValidationResult validationResult;
    private final RES value;

    public boolean isSuccessful() {
        return validationResult.isValid();
    }

    public ValidationError getError() {
        return validationResult.getError().get();
    }
}
