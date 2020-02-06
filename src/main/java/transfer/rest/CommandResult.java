package transfer.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import transfer.validation.ValidationResult;

@RequiredArgsConstructor
@Getter
public class CommandResult<RES> {

    private final ValidationResult validationResult;
    private final RES result;

    public boolean isSuccessful() {
        return validationResult.isValid();
    }
}
