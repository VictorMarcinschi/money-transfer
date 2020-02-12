package transfer.stories;

import transfer.LifecycleSteps;
import transfer.jbehave.AbstractStory;
import transfer.moneytransfer.rest.ConfirmRetrievalSteps;

public class S04ConfirmRetrievalStory extends AbstractStory {

    @Override
    public Object[] steps() {
        return new Object[]{new LifecycleSteps(), new ConfirmRetrievalSteps()};
    }

    @Override
    protected String story() {
        return "stories/transfer/confirm_retrieval.story";
    }
}
