Service partner submits a money transfer on behalf of an end user onboarded in its systems

Narrative:
In order to enable end users to submit money transfers
As a service partner
I want to be able to forward money transfer requests to the money transfer service

Scenario: An end user submits a money transfer via one of the onboarded service partners

GivenStories: stories/partner/onboard_partner.story

Given a service partner enabled end user submits a money transfer
And the end user's email is goldman@bank.com
And the end user's phone number is 911
And the receiving user is identified by EMAIL
And the receiving user's identifier is sachs@bank.com
And the transfer is in USD
And the transfer amount is 120.00
And the transfer is due to be retrieved in 5 days

When the service partner sends a request to the service API gateway to submit the transfer
And the service API GW forwards the request to the money transfer service

Then the money transfer service sends a response
And the response status is 201
And the response body contains the sending end user's identifier assigned by the service
And the response body contains an identifier for the submitted transfer
