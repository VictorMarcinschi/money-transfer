package transfer.domain;

import java.time.Clock;

import com.gs.fw.common.mithra.MithraManagerProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionalCommand<REQ, RES> implements Command<REQ, RES> {

    private final Command<REQ, RES> command;

    @Override
    public CommandResult<RES> execute(REQ request, Clock systemClock) {
        return MithraManagerProvider.getMithraManager()
                .executeTransactionalCommand(tx -> command.execute(request, systemClock));
    }
}
