Service partner submits a money transfer retrieval confirmation request on behalf of an end user onboarded in its systems

Narrative:
In order to enable end users to confirm a money transfers retrieval
As a service partner
I want to be able to forward retrieval confirmation requests to the money transfer service

Scenario: An end user wants to confirm a money transfer retrieval via one of the onboarded service partners

GivenStories: stories/partner/retrieve_transfer.story

Given an end user submits a retrieval confirmation with the secret code received from the money transfer service
And it contains the identifier of the transfer to be retrieved
And the service partner enriches it with its own identifier
And the service partner enriches it with the user's email sachs@bank.com
And the service partner enriches it with the user's phone 112

When the service partner forwards the confirm retrieval request to the service API gateway
And the service API GW forwards the confirm retrieval request to the money transfer service

Then the money transfer service sends a confirm retrieval response
And the confirm retrieval response status is 202
And it contains a receiver identifier for the service partner to use in further requests involving the user
And it contains the identifier of the confirmed transfer
And it contains the retrieval status
And it contains a retrieval approval for the ledger to register the associated transaction
And the approval contains the identifier of the confirmed transfer
And the approval contains the identifier of the service partner
And the approval contains the service partner's api base path to notify it about the transaction details
And the approval contains the identifier of the transfer receiver
And the approval contains a timestamp representing the time the retrieval was confirmed
And the approval contains the channed the receiver used to confirm the retrieval
And the approval contains the currency code of the transfer
And the approval contains the amount of transfer

