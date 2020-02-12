package transfer.moneytransfer.rest;

import java.time.Clock;
import java.time.LocalDateTime;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import transfer.testutil.TestHttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTransferSteps extends SubmitTransferSteps {

    private static final String RETRIEVE_TRANSFER_URL = "/transfers/%s/retrievals";

    protected Object registerReceiverServicePartnerRequest;
    protected String receiverPartnerIdentifier;
    protected String retrieveTransferApiBasePath;

    private RetrieveTransferRequest request;

    protected TestHttpResponse<RetrieveTransferResponse> retrieveTransferResponse;

    @Given("a new service partner $identifier is onboarded for $months months with the API at $apiBasePath")
    public void givenNewServicePartnerOnboarded(@Named("identifier") String identifier,
            @Named("months") int months, @Named("apiBasePath") String apiBasePath) {

        retrieveTransferApiBasePath = apiBasePath;
        registerReceiverServicePartnerRequest = createRegsiterServicePartnerRequest(identifier, months, apiBasePath);
        var response = client.post(REGISTER_PARTNER_URL, registerReceiverServicePartnerRequest, String.class);
        receiverPartnerIdentifier = response.getResponse();
    }

    @Given("one if its end user submits a retrieval for a transfer")
    public void givenTransferRetrieval() {
    }

    @When("the service partner forwards the retrieve transfer request to the service API gateway")
    public void whenRetrieveTransferRequest() {
        request = new RetrieveTransferRequest(receiverPartnerIdentifier);
    }

    @When("the service API GW forwards the retrieve transfer request to the money transfer service")
    public void whenRetrieveTransferRequestSent() {
        retrieveTransferResponse = client.post(RETRIEVE_TRANSFER_URL, request, RetrieveTransferResponse.class,
                submitTransferResponse.getResponse().getTransferIdentifier());
    }

    @Then("the money transfer service sends a retrieve transfer response")
    public void thenRetrieveTransferResponse() {
        assertThat(retrieveTransferResponse.getRawResponse()).isNotNull();
        assertThat(retrieveTransferResponse.getResponse()).isNotNull();
    }

    @Then("the retrieve transfer response status is $status")
    public void thenRetrieveTransferResponseStatus(@Named("status") int status) {
        assertThat(retrieveTransferResponse.getRawResponse())
                .extracting(raw -> raw.code())
                .isEqualTo(status);
    }

    @Then("it contains a timestamp representing the retrieval confirmation due by time set in the future")
    public void thenRetrieveTransferResponseHasConfirmationDueBy() {
        assertThat(retrieveTransferResponse.getResponse())
                .extracting(r -> r.getConfirmationDueBy())
                .isNotNull();

        assertThat(retrieveTransferResponse.getResponse().getConfirmationDueBy())
                .isAfter(LocalDateTime.now(Clock.systemUTC().getZone()));
    }

    @Then("it contains a secret code to use for retrieval confirmation")
    public void thenRetrieveTransferResponseHasConfirmationCode() {
        assertThat(retrieveTransferResponse.getResponse().getConfirmationCode())
                .isNotEmpty();
    }

    @Then("it contains the secret code delivery method")
    public void thenRetrieveTransferResponseHasCodeDeliveryMethod() {
        assertThat(retrieveTransferResponse.getResponse().getSendCodeVia())
                .isEqualTo(submitTransferResponse.getResponse().getSendNotificationVia());
    }

    @Then("it contains the receiving user requisite that matches the one in the submitted transfer")
    public void thenRetrieveTransferResponseHasReceiverIdentifier() {
        assertThat(retrieveTransferResponse.getResponse().getSendCodeTo())
                .isEqualTo(submitTransferResponse.getResponse().getSendNotificationTo());
    }
}
