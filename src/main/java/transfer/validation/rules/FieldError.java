package transfer.validation.rules;

import transfer.validation.SimpleValidationError;
import transfer.validation.ValidationError;

enum FieldError {
    
    NULL_VALUE("Field '%s' is required but not provided"),
    BLANK_VALUE("Field '%s' is required and should not be blank"),
    NON_POSITIVE_NUMBER("Field '%s' should be a positive number");

    private final String messageFormat;

    FieldError(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    ValidationError toValidationError(int httpStatus, Object... messageArgs) {
        return new SimpleValidationError(httpStatus, String.format(messageFormat, messageArgs));
    }
}
