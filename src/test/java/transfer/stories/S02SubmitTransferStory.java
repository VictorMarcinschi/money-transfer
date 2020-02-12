package transfer.stories;

import transfer.LifecycleSteps;
import transfer.jbehave.AbstractStory;
import transfer.moneytransfer.rest.SubmitTransferSteps;

public class S02SubmitTransferStory extends AbstractStory {

    @Override
    public Object[] steps() {
        return new Object[]{new LifecycleSteps(), new SubmitTransferSteps()};
    }

    @Override
    protected String story() {
        return "stories/transfer/submit_transfer.story";
    }
}
