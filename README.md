**360 Agency Listing RestAPI Test**

[Maintainer: Saha Merlin](https://www.linkedin.com/in/merlin-saha/)

Installation:
- Required: Java 11 & maven installed
- Clone this repository
- Move to project directory
- To build the project Run: mvn clean install
  - A jar file will be available here target/360agency-listing-0.0.1.jar
- For test, Run: mvn test
- You don't need BD configuration here because i use H2Database

To test this, you will need [Postman](https://www.postman.com/downloads/) or Another tools

- API URL: http://localhost:8081
- Documentation URL: http://localhost:8081/swagger-ui.html

1. Endpoints:
- Create New Dealer: http://localhost:8081/api/v1/dealers/save
- Get Dealer Details: http://localhost:8081/api/v1/dealers/{dealerId}
  - Constrains: Dealer must exist
- Get Dealer Limit: http://localhost:8081/api/v1/dealers/limits/{dealerId}
  - Constrains: Dealer must exist
- Update Dealer limit: http://localhost:8081/api/v1/dealers/limits/{dealerId}?listingLimit={limit}
  - Constrains: Dealer must exist

- Create New Listing: http://localhost:8081/api/v1/listing/save
  - Constrains: Dealer must exist
- Update Listing: http://localhost:8081/api/v1/listing/update/{id}
  - Constrains: Dealer & Listing must exist
- Get Dealer Listing By Status: http://localhost:8081/api/v1/listing/listing_by_state/{dealerId}/DRAFT?page=0&size=2
  - Constrains: Dealer must exist
- Publish Listing: http://localhost:8081/api/v1/listing/publish/{id}
  - Constrains: Listing must exist
- UnPublish Listing: http://localhost:8081/api/v1/listing/un_publish/{id}

2. Exception:
  - ListingNotFoundException
  - DealerNotFoundException
  - DealerLimitException
  - UnKnowException