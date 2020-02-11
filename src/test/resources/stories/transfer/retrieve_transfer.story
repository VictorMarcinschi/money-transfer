Service partner submits a money transfer retrieval request on behalf of an end user onboarded in its systems

Narrative:
In order to enable end users to retrieve money transfers
As a service partner
I want to be able to forward retrieval requests to the money transfer service

Scenario: An end user wants to retrieve a money transfer via one of the onboarded service partners

GivenStories: stories/transfer/submit_transfer.story

Given a new service partner OFFSHRBANK is onboarded for 1 months with the API at http://offshore.pn/api
And one if its end user submits a retrieval for a transfer

When the service partner forwards the retrieve transfer request to the service API gateway
And the service API GW forwards the retrieve transfer request to the money transfer service

Then the money transfer service sends a retrieve transfer response
And the retrieve transfer response status is 201
And it contains a timestamp representing the retrieval confirmation due by time set in the future
And it contains a secret code to use for retrieval confirmation
And it contains the secret code delivery method
And it contains the receiving user requisite that matches the one in the submitted transfer
