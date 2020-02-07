package transfer.domain;

public interface Command<REQ, RES> {

    CommandResult<RES> execute(REQ request);
}
