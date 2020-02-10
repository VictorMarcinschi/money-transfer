Service partner submits a money transfer retrieval request on behalf of an end user onboarded in its systems

Narrative:
In order to enable end users to retrieve money transfers
As a service partner
I want to be able to forward retrieval requests to the money transfer service

Scenario: An end user wants to retrieve a money transfer via one of the onboarded service partners

GivenStories: stories/partner/submit_transfer.story

Given a service partner end user submits a transfer retrieval request

When the service partner sends a request to the service API gateway to retrieve the transfer
And the service API GW forwards the request to the money transfer service

Then the money transfer service sends a response
And the response status is 201
And the response body contains the sending end user's identifier assigned by the service
And the response body contains an identifier for the submitted transfer
