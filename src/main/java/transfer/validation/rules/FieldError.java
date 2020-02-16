package transfer.validation.rules;

import transfer.validation.SimpleValidationError;
import transfer.validation.ValidationError;

enum FieldError {
    
    NULL_VALUE("%s is required but not provided"),
    BLANK_VALUE("%s is required and should not be blank"),
    NON_POSITIVE_NUMBER("%s should be a positive number"),
    NON_FUTURE_DATE("%s should be in future");

    private final String messageFormat;

    FieldError(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    ValidationError toValidationError(int httpStatus, Object... messageArgs) {
        return new SimpleValidationError(httpStatus, String.format(messageFormat, messageArgs));
    }
}
