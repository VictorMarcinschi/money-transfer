package transfer.moneytransfer.rest;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import transfer.partner.rest.OnboardPartnerSteps;

public class SubmitTransferSteps extends OnboardPartnerSteps {

    @Given("an end user submits a money transfer")
    public void givenMoneyTransfer() {
    }

    @Given("the end user's email is goldman@bank.com")
    public void givenSenderEmail() {
    }

    @Given("the end user's phone number is 911")
    public void givenSenderPhone() {
    }

    @Given("the receiving user is identified by EMAIL")
    public void givenReceiverAttribute() {
    }

    @Given("the receiving user's identifier is sachs@bank.com")
    public void givenReceiverIdentifier() {
    }

    @Given("the transfer is in USD")
    public void givenTransferCurrency() {
    }
    
    @Given("the transfer amount is 120.00")
    public void givenTransferAmount() {
    }

    @Given("the transfer is due to be retrieved in 5 days")
    public void givenTransferValidity() {
    }

    @When("the service partner sends a request to the service API gateway to submit the transfer")
    public void whenSubmitTransferRequest() {
    }
    
    @When("the service API GW forwards the request to the money transfer service")
    public void whenSubmitTransferRequestSent() {
    }

    @Then("the money transfer service sends a response")
    public void thenSubmitTransferResponse() {
    }

    @Then("the response status is 201")
    public void thenSubmitTransferResponseHasStatus() {
    }

    @Then("it contains the sending end user's identifier assigned by the service")
    public void thenSubmitTransferResponseHasSenderIdentifier() {
    }

    @Then("it contains an identifier for the submitted transfer")
    public void thenSubmitTransferResponseHasTransferIdentifier() {
    }
}
