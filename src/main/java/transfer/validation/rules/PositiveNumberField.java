package transfer.validation.rules;

import java.math.BigDecimal;

public class PositiveNumberField<E> extends AbstractFieldValidationRule<E, BigDecimal> {

    public PositiveNumberField(int index, int httpCode, FieldExtractor<E, BigDecimal> extractor, String fieldName) {
        super(index, httpCode, extractor, fieldName);
    }

    @Override
    protected FieldError getFieldError() {
        return FieldError.NON_POSITIVE_NUMBER;
    }

    @Override
    public boolean passes(E entity) {
        return extract(entity).compareTo(BigDecimal.ZERO) > 0;
    }
}
