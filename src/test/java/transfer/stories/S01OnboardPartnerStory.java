package transfer.stories;

import transfer.LifecycleSteps;
import transfer.jbehave.AbstractStory;
import transfer.partner.rest.OnboardPartnerSteps;

public class S01OnboardPartnerStory extends AbstractStory {

    @Override
    public Object[] steps() {
        return new Object[]{new LifecycleSteps(), new OnboardPartnerSteps()};
    }

    @Override
    protected String story() {
        return "stories/partner/onboard_partner.story";
    }
}
