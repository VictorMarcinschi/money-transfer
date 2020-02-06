package transfer.validation.rules;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import transfer.validation.ValidationError;
import transfer.validation.ValidationRule;

public class NonNullFieldRule<E> extends AbstractStructuralValidationRule<E, Object> {

    private final String fieldName;

    public NonNullFieldRule(int index, FieldExtractor<E, Object> extractor, int httpCode, String fieldName) {
        super(index, extractor, httpCode);
        this.fieldName = fieldName;
    }

    @Override
    public boolean passes(E entity) {
        return Optional.ofNullable(getExtractor().extract(entity)).isPresent();
    }

    @Override
    protected GenericError getGenericError() {
        return GenericError.NULL_VALUE;
    }

    @Override
    protected Object[] getErrorMessageArgs() {
        return new Object[]{fieldName};
    }
}
