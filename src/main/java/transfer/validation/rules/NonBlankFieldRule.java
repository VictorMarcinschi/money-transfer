package transfer.validation.rules;

import org.apache.commons.lang3.StringUtils;

public class NonBlankFieldRule<E> extends AbstractFieldValidationRule<E, String> {

    public NonBlankFieldRule(int index, FieldExtractor<E, String> extractor, int httpCode, String fieldName) {
        super(index, httpCode, extractor, fieldName);
    }

    @Override
    protected FieldError getFieldError() {
        return FieldError.BLANK_VALUE;
    }


    @Override
    public boolean passes(E entity) {
        return StringUtils.isNotBlank(extract(entity));
    }
}
