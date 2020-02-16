package transfer.validation.rules;

import java.time.Clock;
import java.time.LocalDateTime;

public class DateInFutureFieldRule<E> extends AbstractFieldValidationRule<E, LocalDateTime> {

    private final Clock clock;

    public DateInFutureFieldRule(int httpCode, FieldExtractor<E, LocalDateTime> extractor, String fieldName,
            Clock clock) {

        super(httpCode, extractor, fieldName);
        this.clock = clock;
    }

    @Override
    protected FieldError getFieldError() {
        return FieldError.NON_FUTURE_DATE;
    }

    @Override
    public boolean passes(E entity) {
        return extract(entity).isAfter(LocalDateTime.now(clock));
    }
}
