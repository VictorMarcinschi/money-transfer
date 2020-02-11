package transfer.partner.stories;

import transfer.LifecycleSteps;
import transfer.jbehave.AbstractStory;
import transfer.moneytransfer.rest.RetrieveTransferSteps;
import transfer.moneytransfer.rest.SubmitTransferSteps;

public class RetrieveTransferStory extends AbstractStory {

    @Override
    public Object[] steps() {
        return new Object[]{new LifecycleSteps(), new RetrieveTransferSteps()};
    }

    @Override
    protected String story() {
        return "stories/transfer/retrieve_transfer.story";
    }
}
