package transfer.moneytransfer.rest;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import transfer.moneytransfer.model.UserAttribute;
import transfer.partner.rest.OnboardPartnerSteps;
import transfer.testutil.TestHttpResponse;
import transfer.user.rest.EndUserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SubmitTransferSteps extends OnboardPartnerSteps {

    private static final String SUBMIT_TRANSFER_URL = "/transfers";

    protected String senderEmail;
    protected String senderPhone;
    protected String currencyCode;
    protected BigDecimal amount;
    protected UserAttribute receiverAttribute;
    protected String receiverIdentifier;
    protected LocalDate retrievalExpiry;

    private SubmitTransferRequest request;
    protected TestHttpResponse<SubmitTransferResponse> submitTransferResponse;

    @Given("an end user with the email address $email and phone number $phone submits a money transfer")
    public void givenSenderEmail(@Named("email") String email, @Named("phone") String phone) {
        this.senderEmail = email;
        this.senderPhone = phone;
    }

    @Given("the transfer is in $currencyCode")
    public void givenTransferCurrency(@Named("currencyCode") String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Given("the transfer amount is $amount")
    public void givenTransferAmount(@Named("amount") BigDecimal amount) {
        this.amount = amount;
    }

    @Given("the receiving user is identified by $attribute")
    public void givenReceiverAttribute(@Named("attribute") UserAttribute attribute) {
        this.receiverAttribute = attribute;
    }

    @Given("the receiving user's identifier is $email")
    public void givenReceiverIdentifier(@Named("email") String email) {
        this.receiverIdentifier = email;
    }

    @Given("the transfer is due to be retrieved in $days days")
    public void givenTransferValidity(@Named("days") int days) {
        this.retrievalExpiry = LocalDate.now().plusDays(days);
    }

    @When("the service partner sends a request to the service API gateway to submit the transfer")
    public void whenSubmitTransferRequest() {
        request = SubmitTransferRequest.builder()
                .partnerIdentifier(partnerIdentifier)
                .senderDetails(new EndUserDetails(senderEmail, senderPhone))
                .receiverAttribute(receiverAttribute)
                .receiver(receiverIdentifier)
                .currencyCode(currencyCode)
                .amount(amount)
                .retrievalExpiry(retrievalExpiry)
                .build();
    }
    
    @When("the service API GW forwards the submit transfer request to the money transfer service")
    public void whenSubmitTransferRequestSent() {
        submitTransferResponse = client.post(SUBMIT_TRANSFER_URL, request, SubmitTransferResponse.class);
    }

    @Then("the money transfer service sends a submit transfer response")
    public void thenSubmitTransferResponse() {
        assertThat(submitTransferResponse.getRawResponse()).isNotNull();
        assertThat(submitTransferResponse.getResponse()).isNotNull();
    }

    @Then("the submit transfer response status is $status")
    public void thenSubmitTransferResponseHasStatus(@Named("status") int status) {
        assertThat(submitTransferResponse.getRawResponse())
                .extracting(raw -> raw.code())
                .isEqualTo(status);
    }

    @Then("it contains the sending end user's identifier assigned by the service")
    public void thenSubmitTransferResponseHasSenderIdentifier() {
        assertThat(submitTransferResponse.getResponse())
                .extracting(r -> r.getSenderIdentifier())
                .isNotNull();
    }

    @Then("it contains an identifier for the submitted transfer")
    public void thenSubmitTransferResponseHasTransferIdentifier() {
        assertThat(submitTransferResponse.getResponse())
                .extracting(r -> r.getTransferIdentifier())
                .isNotNull();
    }

    @Then("it contains a retrieval due-by date equal to the one received from the request")
    public void thenSubmitTransferResponseHasRetrievalDueByAsInRequest() {
        assertThat(submitTransferResponse.getResponse())
                .extracting(r -> r.getRetrievalDueBy())
                .usingDefaultComparator()
                .isEqualTo(retrievalExpiry);
    }

    @Then("it contains a notification-via attribute matching the receiving user's identifying attribute from the request")
    public void thenSubmitTransferResponseHasNotificationViaAsReceiverInRequest() {
        assertThat(submitTransferResponse.getResponse())
                .extracting(r -> r.getSendNotificationVia())
                .isSameAs(receiverAttribute);
    }

    @Then("it contains a notification-to identifier matching the receiving user's identifier from the request")
    public void thenSubmitTransferResponseHasNotificationToAsReceiverInRequest() {
        assertThat(submitTransferResponse.getResponse())
                .extracting(r -> r.getSendNotificationTo())
                .isEqualTo(receiverIdentifier);
    }
}
