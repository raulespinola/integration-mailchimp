# Service Integration Trio Challenge Technical Design

This service is a challenge for Trio

# Background

the main goal of this proyect are:
- Testing my coding capabilities
- Integrate two services: a mock api service and mailchimp api.

# Technical Approaches

- For this proyect the aproach are the following:
  - Create the main proyect with the basic coding for maven, spring, logging, documentation and communication
  - Code the clients for communicate with mock api and mailchimp using webclient libraries.
  - Integrate the providers clients services with a main service to syncronice the contacts from one service to another.
  - Add the Controller to receive the request for syncronice and response with the actual contact which was synchronice with mainlchimp service.


# Technical Design

## Main Proyect #1

- **Background:** This proyect integrate two services a mock api service and the mailchimp service.
- **Base Libraries:**
 - The proyect will use Java 17, Maven, Spring and Webclient
 - **Communication:**
    - **Clients** (MockApi and MailChimp): 
      - Both will be creating using WebClient Library, with a Service Provider as Interface and DTO classes for exchange data with the service
 - **Service Integration:**
   - The Service will integrate both clients adapters and synchronize both service to update mailchimp service with the mockapi service
 - **Rest Controller:**
   - The service will expose a basic GET endpoint "/sync" to synchronize both services
 - **Quality Code**
   - For increase the quality for the proyect, i use sonar link and sonarqube service for check static code.
   - Add Unit Test for check business logic using Junit 5.

Example:

1. Server starts
2. Server receives the GET request on `/sync`
3. Server responds to this structure

```tsx
{
	success: true,
	message: "Hello World!"
}
```

## Files

Describe the files that you are going to change in a **human language.** Don't code here!

### filepath

Describe in **human language** what you must do. Sometimes, providing a prototype code helps everybody to get your solution.

### [Example] ./routes.js

We should apply the new `hello-world` endpoint using Express:

```tsx
routes.get("/hello", (req, res) => {
	return res.send({ success: true, message: "Hello World!"})
});
```