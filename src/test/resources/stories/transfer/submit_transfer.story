Service partner submits a money transfer on behalf of an end user onboarded in its systems

Narrative:
In order to enable end users to submit money transfers
As a service partner
I want to be able to forward money transfer requests to the money transfer service
So that the money transfer service can register them and notify their receivers

Scenario: An end user submits a money transfer via one of the onboarded service partners

GivenStories: stories/partner/onboard_partner.story

Given an end user with the email address goldman@bank.com and phone number 911 submits a money transfer
And the transfer is in USD
And the transfer amount is 120.00
And the receiving user is identified by EMAIL
And the receiving user's identifier is sachs@bank.com
And the transfer is due to be retrieved in 5 days

When the service partner sends a request to the service API gateway to submit the transfer
And the service API GW forwards the request to the money transfer service

Then the money transfer service sends a submit transfer response
And the submit transfer response status is 201
And it contains the sending end user's identifier assigned by the service
And it contains an identifier for the submitted transfer
And it contains a retrieval due-by date equal to the one received from the request
And it contains a notification-via attribute matching the receiving user's identifying attribute from the request
And it contains a notification-to identifier matching the receiving user's identifier from the request
