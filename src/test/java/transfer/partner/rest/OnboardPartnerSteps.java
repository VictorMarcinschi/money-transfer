package transfer.partner.rest;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import transfer.testutil.TestHttpClient;
import transfer.testutil.TestHttpResponse;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OnboardPartnerSteps {

    private static final String REGISTER_URL = "/partners";

    protected TestHttpClient client;

    protected String partnerIdentifier;
    protected int kycValidityMonths;
    protected String apiBasePath;

    protected TestHttpResponse<String> onboardPartnerResponse;

    @Given("the onboarding processor completes the onboarding of a new partner")
    public void givenPartnerOnboardingComplete() {
        client = new TestHttpClient();
    }

    @Given("assigns it the identifier $parnerIdentifier")
    public void givenPartnerIdentifier(@Named("parnerIdentifier") String partnerIdentifier) {
        this.partnerIdentifier = partnerIdentifier;
    }

    @Given("the partner's KYC profile expires in $months months")
    public void givenKycExpiry(@Named("months") int months) {
        this.kycValidityMonths = months;
    }

    @Given("the partner specifies its API base path at $apiBasePath")
    public void givenApiBasePath(@Named("apiBasePath") String apiBasePath) {
        this.apiBasePath = apiBasePath;
    }

    @When("the onboarding processor sends a request to the money transfer service to register the partner")
    public void whenRegisterRequestSent() {
        var request = RegisterServicePartnerRequest.builder()
                .identifier(partnerIdentifier)
                .kycExpiry(LocalDate.now().plusMonths(kycValidityMonths))
                .apiBasePath(apiBasePath)
                .build();

        onboardPartnerResponse = client.post(REGISTER_URL, request, String.class);
    }

    @Then("the money transfer service sends a onboard partner response")
    public void thenResponseSent() {
        assertThat(Optional.ofNullable(onboardPartnerResponse.getRawResponse())).isPresent();
        assertThat(Optional.ofNullable(onboardPartnerResponse.getResponse())).isPresent();
    }

    @Then("the onboard partner response status is $status")
    public void thenResponseStatus(@Named("status") int status) {
        assertThat(onboardPartnerResponse.getRawResponse())
                .extracting(r -> r.code())
                .isEqualTo(status);
    }

    @Then("the response body is the identifier of the partner from the sent request")
    public void thenResponseBody() {
        assertThat(onboardPartnerResponse).extracting(r -> r.getResponse())
                .isEqualTo(partnerIdentifier);
    }
}
