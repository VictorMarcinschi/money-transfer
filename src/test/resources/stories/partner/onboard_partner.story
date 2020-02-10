Notify the money transfer service about the onboarding of a new partner

Narrative:
In order to enable a partner to submit money transfers from end users
As a partner onboarding processor
I want to register a newly onboarded partner with the money transfer service

Scenario: A new partner has completed the onboarding process and is registered with the money transfer service
Given the onboarding processor completes the onboarding of a new partner
And assigns it the identifier MANIFORPPL
And the partner's KYC profile expires in 6 months
And the partner specifies its API base path at https://transfers.maniforppl.com/api

When the onboarding processor sends a request to the money transfer service to register the partner

Then the money transfer service sends a onboard partner response
And the onboard partner response status is 201
And the response body is the identifier of the partner from the sent request