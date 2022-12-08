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
 - The proyect will use Java 11, Maven, Spring and Webclient
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
2. Documentation: 
3. Services:

    **Sync Contacts*
    - Endpoint: GET request on `/sync`
    - This service synchronize all the contacts with a list in mailchimp
    - Parameters: 
      - listId: the listId to sync with. if there is no listId will be use the default
   one in the properties 
      - addRandomEmail=true, if the service reject the mock contact, this parameter will add an random character to the mail
    - Response Example:
    ```tsx
    {
    "synced_contacts": 2,
    "contacts": [
        {
            "first_name": "Michelle",
            "last_name": "Gaylord",
            "email": "Kirk.Fritsch32@hotmail.comj"
        },
        {
            "first_name": "Deborah",
            "last_name": "Schinner",
            "email": "Corbin.Abshire432@yahoo.comj"
        }
   }
    ```
   **Get Members from a List*
    - Endpoint: GET request on `/member-list`
    - This service get all the members from a list
    - Parameters:
        - listId: the listId to sync with. if there is no listId will be use the default
          one in the properties
    - Response Example:
    ```tsx
    {
    "total_items": 1,
    "list_id": "877d1aee05",
    "members": [
        {
            "id": "0c6dadf080de9a68153bde1c87e10da0",
            "email_address": "luisaanaliagarin@gmail.com",
            "unique_email_id": "132c92124b",
            "contact_id": "21fda2653af7353dcf878fac36986598",
            "full_name": "Luisa Analía Garín",
            "web_id": 417297,
            "email_type": "html",
            "status": "subscribed",
            "consents_to_one_to_one_messaging": true
            }
   }
    ```
   **Clean Members from a List*
    - Endpoint: DELETE request on `/clean`
    - This service can clean all the member from a list.
    - Parameters:
        - listId: the listId to sync with. if there is no listId will be use the default
          one in the properties
    - Response Example:
    ```tsx
    {
    "member_delete": 25
    }
    ```
   
   **Get all Lists* 
    - Endpoint: GET request on `/all-list`
    - This service is useful to get all the list from an account
    - Response Example:
    ```tsx
    {
    "lists": [
        {
            "id": "877d1aee05",
            "web_id": 1233,
            "name": "27463502",
            "contact": {
                "company": "27463502",
                "address1": "Luis Maria Campos 5370",
                "address2": "",
                "city": "Villa Bosch",
                "state": "Buenos Aires",
                "zip": "1682",
                "country": "AR",
                "phone": ""
            },
            "permission_reminder": "You are receiving this email because you opted in via our website.",
            "campaign_defaults": {
                "from_name": "Luisa Analía",
                "from_email": "luisaanaliagarin@gmail.com",
                "subject": "",
                "language": "en"
            },
            "list_rating": 0,
            "subscribe_url_short": "http://eepurl.com/ifJMXL",
            "subscribe_url_long": "https://gmail.us21.list-manage.com/subscribe?u=ef90be867176a407af6c806d9&id=877d1aee05",
            "beamer_address": "us21-97f22ccee0-9384831c8a@inbound.mailchimp.com",
            "visibility": "prv",
            "modules": [],
            "stats": {
                "avg_open_rate": 0,
                "avg_click_rate": 0
            }
        }
    ],
    "total_items": 1,
    "constraints": {
        "may_create": false,
        "max_instances": 1,
        "current_total_instances": 1
    }
}
    ```