package transfer.domain;

import java.time.Clock;

import lombok.RequiredArgsConstructor;
import transfer.validation.Validator;

@RequiredArgsConstructor
public class ValidatedCommand<REQ, RES> implements Command<REQ, RES> {

    private final Command<REQ, RES> command;
    private final Validator<REQ> validator;

    @Override
    public CommandResult<RES> execute(REQ request, Clock systemClock) {
        var validationResult = validator.validate(request);
        if (validationResult.isValid()) {
            return command.execute(request, systemClock);
        }
        return CommandResult.<RES>builder()
                .validationResult(validationResult)
                .build();
    }
}
