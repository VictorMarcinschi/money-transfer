package transfer.domain;

import java.time.Clock;

public interface Command<REQ, RES> {

    CommandResult<RES> execute(REQ request, Clock systemClock);
}
