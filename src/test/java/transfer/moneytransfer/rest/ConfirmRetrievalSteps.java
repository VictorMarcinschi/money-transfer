package transfer.moneytransfer.rest;

import java.time.Clock;
import java.time.LocalDateTime;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import transfer.moneytransfer.model.RetrievalStatus;
import transfer.testutil.TestHttpResponse;
import transfer.user.rest.EndUserDetails;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmRetrievalSteps extends RetrieveTransferSteps {

    private static final String CONFIRM_RETRIEVAL_URL = "/transfers/%s/retrievals/%s";

    private ConfirmRetrievalRequest.ConfirmRetrievalRequestBuilder requestBuilder;

    private String partnerIdentifier;
    private String transferIdentifier;

    private String email;
    private String phone;

    private TestHttpResponse<ConfirmRetrievalResponse> confirmRetrievalResponse;

    private LocalDateTime requestSentAt;

    @Given("an end user submits a retrieval confirmation with a correct secret code")
    public void givenConfirmRetrieval() {
        requestBuilder = ConfirmRetrievalRequest.builder()
                .confirmationCode(retrieveTransferResponse.getResponse().getConfirmationCode());
    }

    @Given("it contains the identifier of the transfer to be retrieved")
    public void givenConfirmRetrievalTransferIdentifier() {
        transferIdentifier = submitTransferResponse.getResponse().getTransferIdentifier();
    }

    @Given("the service partner enriches it with its own identifier")
    public void givenConfirmRetrievalPartnerIdentifier() {
        partnerIdentifier = receiverPartnerIdentifier;
    }

    @Given("the service partner enriches it with the user's email $receiverEmail")
    public void givenConfirmRetrievalReceiverEmail(@Named("receiverEmail") String email) {
        this.email = email;
    }

    @Given("the service partner enriches it with the user's phone $receiverPhone")
    public void givenConfirmRetrievalReceiverPhone(@Named("receiverPhone") String phone) {
        this.phone = phone;
    }

    @When("the service partner forwards the confirm retrieval request to the service API gateway")
    public void whenConfirmRetrievalRequest() {
        requestBuilder.receiverDetails(new EndUserDetails(email, phone));
    }

    @When("the service API GW forwards the confirm retrieval request to the money transfer service")
    public void whenConfirmRetrievalRequestSent() {
        requestSentAt = LocalDateTime.now(Clock.systemUTC().getZone());
        confirmRetrievalResponse = client.patch(CONFIRM_RETRIEVAL_URL, requestBuilder.build(),
                ConfirmRetrievalResponse.class, transferIdentifier, partnerIdentifier);
    }

    @Then("the money transfer service sends a confirm retrieval response")
    public void thenConfirmRetrievalResponse() {
        assertThat(confirmRetrievalResponse.getRawResponse()).isNotNull();
        assertThat(confirmRetrievalResponse.getResponse()).isNotNull();
    }

    @Then("the confirm retrieval response status is $status")
    public void thenConfirmRetrievalResponseStatus(@Named("status") int status) {
        assertThat(confirmRetrievalResponse.getRawResponse())
                .extracting(raw -> raw.code())
                .isEqualTo(status);
    }

    @Then("it contains a receiver identifier for the service partner to use in further requests involving the user")
    public void thenConfirmRetrievalResponseHasReceiverIdentifier() {
        assertThat(confirmRetrievalResponse.getResponse().getReceiverIdentifier())
                .isNotNull();
    }

    @Then("it contains the identifier of the confirmed transfer")
    public void thenConfirmRetrievalResponseHasTransferIdentifier() {
        assertThat(confirmRetrievalResponse.getResponse().getTransferIdentifier())
                .isEqualTo(submitTransferResponse.getResponse().getTransferIdentifier());
    }

    @Then("it contains a retrieval status of $retrievalStatus")
    public void thenConfirmRetrievalResponseHasRetrievalStatus(@Named("retrievalStatus") RetrievalStatus status) {
        assertThat(confirmRetrievalResponse.getResponse().getRetrievalStatus())
                .isSameAs(status);
    }

    @Then("it contains a retrieval approval for the ledger to register the associated transaction")
    public void thenConfirmRetrievalResponseHasApproval() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval())
                .isNotNull();
    }

    @Then("the approval contains the identifier of the confirmed transfer")
    public void thenConfirmRetrievalApprovalHasTransferIdentifier() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval())
                .extracting(appr -> appr.getTransferIdentifier())
                .isEqualTo(transferIdentifier);
    }

    @Then("the approval contains the identifier of the service partner")
    public void thenConfirmRetrievalApprovalHasPartnerIdentifier() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval())
                .extracting(appr -> appr.getPartnerIdentifier())
                .isEqualTo(partnerIdentifier);
    }

    @Then("the approval contains the service partner's api base path to notify it about the transaction details")
    public void thenConfirmRetrievalApprovalHasPartnerApiBasePath() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval())
                .extracting(appr -> appr.getPartnerApiBasePath())
                .isEqualTo(retrieveTransferApiBasePath);
    }

    @Then("the approval contains the identifier of the transfer receiver")
    public void thenConfirmRetrievalApprovalHasReceiverIdentifier() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval())
                .extracting(appr -> appr.getReceiverIdentifier())
                .isEqualTo(confirmRetrievalResponse.getResponse().getReceiverIdentifier());
    }

    @Then("the approval contains a timestamp representing the time the retrieval was confirmed")
    public void thenConfirmRetrievalApprovalHasConfirmedAtDateTime() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval().getConfirmedAt())
                .isAfter(requestSentAt)
                .isBefore(LocalDateTime.now(Clock.systemUTC().getZone()));
    }

    @Then("the approval contains the channel the receiver used to confirm the retrieval")
    public void thenConfirmRetrievalApprovalHasConfirmedVia() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval())
                .extracting(appr -> appr.getConfirmedVia())
                .isSameAs(retrieveTransferResponse.getResponse().getSendCodeVia());
    }

    @Then("the approval contains the currency code of the transfer")
    public void thenConfirmRetrievalApprovalHasCurrencyCode() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval())
                .extracting(appr -> appr.getCurrencyCode())
                .isEqualTo(submitTransferCurrencyCode);
    }

    @Then("the approval contains the amount of transfer")
    public void thenConfirmRetrievalApprovalHasAmount() {
        assertThat(confirmRetrievalResponse.getResponse().getApproval().getAmount())
                .isEqualByComparingTo(submitTransferAmount);
    }
}
