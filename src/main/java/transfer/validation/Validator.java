package transfer.validation;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Validator<E> {

    private final List<ValidationRule<E>> rules;

    public ValidationResult validate(E entity) {
        ValidationResult result = new ValidationResult();
        for (ValidationRule<E> rule : rules) {
            if (!rule.passes(entity)) {
                result.fail(rule.getError());
                return result;
            }
        }
        return result;
    }
}
