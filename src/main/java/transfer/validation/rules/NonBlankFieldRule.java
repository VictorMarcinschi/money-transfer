package transfer.validation.rules;

import org.apache.commons.lang3.StringUtils;

public class NonBlankFieldRule<E> extends AbstractFieldValidationRule<E, String> {

    public NonBlankFieldRule(int httpCode, FieldExtractor<E, String> extractor, String fieldName) {
        super(httpCode, extractor, fieldName);
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
