package transfer.validation.rules;

import java.util.Optional;

public class NonNullFieldRule<E> extends AbstractFieldValidationRule<E, Object> {

    public NonNullFieldRule(int index, int httpCode, FieldExtractor<E, Object> extractor, String fieldName) {
        super(index, httpCode, extractor, fieldName);
    }

    @Override
    public boolean passes(E entity) {
        return Optional.ofNullable(extract(entity)).isPresent();
    }

    @Override
    protected FieldError getFieldError() {
        return FieldError.NULL_VALUE;
    }
}
