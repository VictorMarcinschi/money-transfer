package transfer.rest;

public interface Command<REQ, RES> {

    CommandResult<RES> execute(REQ request);
}
