package transfer.validation.rules;

import transfer.validation.ValidationError;
import transfer.validation.ValidationRule;

public abstract class AbstractFieldValidationRule<E, F> implements ValidationRule<E> {

    private final int httpCode;
    private final FieldExtractor<E, F> extractor;
    protected final String fieldName;

    public AbstractFieldValidationRule(int httpCode, FieldExtractor<E, F> extractor, String fieldName) {
        this.httpCode = httpCode;
        this.extractor = extractor;
        this.fieldName = fieldName;
    }

    protected F extract(E from) {
        return extractor.extract(from);
    }

    protected abstract FieldError getFieldError();

    @Override
    public ValidationError getError() {
        return getFieldError().toValidationError(httpCode, fieldName);
    }
}
