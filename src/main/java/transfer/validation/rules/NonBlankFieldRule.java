package transfer.validation.rules;

import org.apache.commons.lang3.StringUtils;

public class NonBlankFieldRule<E> extends AbstractStructuralValidationRule<E, String> {

    private final String fieldName;

    public NonBlankFieldRule(int index, FieldExtractor<E, String> extractor, int httpCode, String fieldName) {
        super(index, extractor, httpCode);
        this.fieldName = fieldName;
    }

    @Override
    protected GenericError getGenericError() {
        return GenericError.BLANK_VALUE;
    }

    @Override
    protected Object[] getErrorMessageArgs() {
        return new Object[]{fieldName};
    }

    @Override
    public boolean passes(E entity) {
        return StringUtils.isNotBlank(getExtractor().extract(entity));
    }
}
