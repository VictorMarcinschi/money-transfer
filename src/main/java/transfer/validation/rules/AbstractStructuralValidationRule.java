package transfer.validation.rules;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import transfer.validation.ValidationError;
import transfer.validation.ValidationRule;

public abstract class AbstractStructuralValidationRule<E, F> implements ValidationRule<E> {

    private final int index;
    private final FieldExtractor<E, F> extractor;
    private final int httpCode;

    public AbstractStructuralValidationRule(int index, FieldExtractor<E, F> extractor, int httpCode) {
        this.index = index;
        this.extractor = extractor;
        this.httpCode = httpCode;
    }

    @Override
    public int index() {
        return index;
    }

    protected FieldExtractor<E, F> getExtractor() {
        return extractor;
    }

    protected abstract GenericError getGenericError();

    protected abstract Object[] getErrorMessageArgs();

    @Override
    public ValidationError getError() {
        return getGenericError().toValidationError(httpCode, getErrorMessageArgs());
    }
}
