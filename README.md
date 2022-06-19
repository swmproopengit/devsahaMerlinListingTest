**Listing RestAPI**

[Maintainer: Saha Merlin](https://www.linkedin.com/in/merlin-saha/)

Code Structure
![Code Structure](https://github.com/swmproopengit/devsahaMerlinListingTest/blob/main/codeStructure.png?raw=true)

Test Structure
![Test Structure](https://github.com/swmproopengit/devsahaMerlinListingTest/blob/main/testStructure.png?raw=true)

Installation:
- Required: Java 11 & maven installed
- Clone this repository
- Move to project directory
- To build the project Run: mvn clean install
  - A jar file will be available here target/ListingRestAPI-0.0.1.jar
    ![Build](https://github.com/swmproopengit/devsahaMerlinListingTest/blob/main/build.png?raw=true)
  - For test, Run: mvn test
    ![Test](https://github.com/swmproopengit/devsahaMerlinListingTest/blob/main/test.png?raw=true)
- You don't need BD configuration here because i use H2Database

To test this, you will need [Postman](https://www.postman.com/downloads/) or Another tools
![Postman](https://github.com/swmproopengit/devsahaMerlinListingTest/blob/main/postman.png?raw=true)

- API URL: http://localhost:8081
- Documentation URL: http://localhost:8081/swagger-ui.html
  ![Documentations](https://github.com/swmproopengit/devsahaMerlinListingTest/blob/main/documention.png?raw=true)

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
