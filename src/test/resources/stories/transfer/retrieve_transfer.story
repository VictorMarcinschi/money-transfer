Service partner submits a money transfer retrieval request on behalf of an end user onboarded in its systems

Narrative:
In order to enable end users to retrieve money transfers
As a service partner
I want to be able to forward retrieval requests to the money transfer service

Scenario: An end user wants to retrieve a money transfer via one of the onboarded service partners

GivenStories: stories/partner/submit_transfer.story

Given a new service partner OFFSHRBANK is onboarded for 1 month with the API at http://offshore.pn/api
And one if its end user submits a retrieval for a transfer

When the service partner forwards the request to the service API gateway
And the service API GW forwards the request to the money transfer service

Then the money transfer service sends a response
And the response status is 201
And it contains a timestamp representing the retrieval confirmation due by time
And it contains a secret code to use for retrieval confirmation
And it contains the secret code delivery method
And it contains the end user requisite that matches the one in the submitted transfer
