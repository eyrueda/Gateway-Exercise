# Solution for Gateway project using Spring boot framework and H2 in memory Database.

### Conditions
Programming language: Java
Framework: Spring Boot
Database: MySQL or in-memory
Automated build: Apache Maven

### Description
This sample project is managing gateways - master devices that control multiple peripheral devices.
Your task is to create a REST service (JSON/HTTP) for storing information about these gateways and their associated devices. This information must be stored in the database.
When storing a gateway, any field marked as “to be validated” must be validated and an error returned if it is invalid. Also, no more that 10 peripheral devices are allowed for a gateway.
The service must also offer an operation for displaying information about all stored gateways (and their devices) and an operation for displaying details for a single gateway. Finally, it must be possible to add and remove a device from a gateway.

Each gateway has:
• a unique serial number (string),
• human-readable name (string),
• IPv4 address (to be validated),
• multiple associated peripheral devices.
Each peripheral device has:
• a UID (number),
• vendor (string),
• date created,
• status - online/offline.

### Other considerations

No User Interface is necessary for the solution.
Provide an automated build.
Provide basic unit tests (if you have time).

## Solution:
https://github.com/eyrueda/Gateway-Exercise/blob/main/Solution%20for%20Gateway%20project.pdf

