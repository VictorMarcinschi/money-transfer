package transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import transfer.validation.ValidationError;
import transfer.validation.ValidationResult;

@AllArgsConstructor
@Getter
@Builder
public class CommandResult<RES> {

    @Builder.Default
    private ValidationResult validationResult = new ValidationResult();
    private final RES value;

    public boolean isSuccessful() {
        return validationResult.isValid();
    }

    public ValidationError getError() {
        return validationResult.getError().get();
    }
}
