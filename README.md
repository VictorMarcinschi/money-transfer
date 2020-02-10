# Money Transfer Service

##Description
Microservice responsible for receiving money transfer submissions from end users using e-mail or phone number
through a service partner (bank, payment processor etc.) and assisting their retrieval by receiving end users through the same or any
other service partner.
<br/>
The microservice receives any request from an API Gateway, which communicates directly with service
partners and also integrates the Money Transfer Service's other components, having an orchestration role.

##Flows

### Onboard Service Partner
**POST */partners***
<br/>
A new service partner is enabled to send and receive money transfers from on behalf of end users.
The Money Transfer Service's Onboarding processor initiates the flow once all Partner KYC procedures
are finished successfully.

**Note**
<br/>
*Onboarding processor is an external microservice.*

### Submit Money Transfer
**POST */transfers***
<br/>
An end user sends a money transfer through a service partner. The Service registers the transfer and the sending end
user's details, if this is the first interaction with the Service. Then it responds with an identifier assigned to the
transfer, by which it can be retrieved. The response is then forwarded to the service partner.

### Retrieve Money Transfer
**POST */transfers/{TRANSFER_IDENTIFIER}/retrievals***
<br/>
An end user requests the retrieval of a money transfer.
 * A service partner, on behalf of the user, sends the transfer identifier, as well as its own identifier
 * The API Gateway forwards the request to the Service
 * The Service creates a retrieval record for the transfer and assigns it a retrieval secret code
 * The Service responds to the API Gateway with the retrieval confirmation due time, secret code, code delivery method
 (email or phone) and the receiving user's details, as per the data submitted in the transfer initially.
 * The API Gateway responds to the partner with the confirmation due time
 * The API Gateway sends to the SMS or Email delivery service the secret code and the email or phone number, respectively
 
### Confirm Money Transfer Retrieval
**PATCH */transfers/{TRANSFER_IDENTIFIER}/retrievals/{SERVICE_PARTNER}***
<br/>
Once a receiving end user gets the delivered secret code, the retrieval can be confirmed and the money withdrawn.

 * The end user through the service partner confirms the retrieval
 * The service partner forwards the secret code along with the receiving user's full details to the Service's API Gateway
 * The Service verifies the secret code, and if it is correct and the retrieval was confirmed before it's confirmation due time,
 the Service response to the API Gateway with a JSON message containing **Approval** information, as well as data about the
 confirmed transfer and retrieval
 * If the Service responds with an approval, the API Gateway forwards the information about the
  confirmed transfer and retrieval to the service partner and the **Approval** data to the Money Processing Service
  to initiate delivery to the receiver
  
## Tech stack
Runtime: Java 11
<br/>
Build tool: Maven
<br/>
Database: in-memory H2 Database
<br/>
IoC Container: Dagger 2
<br/>
ORM Framework: Reladomo
<br/>
Rest Framework: Spark Java (uses embedded Jetty server)
<br/>
Testing Framework: JUnit 4 and JBehave (tests WIP)
###
  
## Building & Running
Make sure you have Maven and Java 11 installed and added to PATH.
```shell script
cd <project_directory>
mvn clean package
java -jar target\money-transfer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
By default, the embedded server starts at port **8080**.
<br/>
**Please note:** any property from the ```src/main/resources/application.properties``` file can be 
overriden by specifying system properties:

```shell script
java -jar -Dserver.port=9090 target\money-transfer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Running demo requests in Postman
Install latest version of Postman  and import the ```Money Transfer Service Demo.postman_collection.json``` collection 
from the project root directory. The 4 requests included demonstrate the described flows.
