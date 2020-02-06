package transfer.validation.rules;

import transfer.validation.SimpleValidationError;
import transfer.validation.ValidationError;

enum GenericError {
    
    NULL_VALUE("Field '%s' is required but not provided"),
    BLANK_VALUE("Field '%s' is required and should not be blank");

    private final String messageFormat;

    GenericError(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    ValidationError toValidationError(int httpStatus, Object... messageArgs) {
        return new SimpleValidationError(httpStatus, String.format(messageFormat, messageArgs));
    }
}
