# CS 320 Software Testing Portfolio

This repository contains selected coursework from CS 320: Software Testing, Automation, and Quality Assurance at Southern New Hampshire University. The files demonstrate how I used Java and JUnit tests to check requirements and think about software quality.

## Portfolio Artifacts

**Project One – Contact Service**

- [Contact.java](Contact.java) – A contact object with an unchangeable ID and validation for names, phone numbers, and addresses.
- [ContactService.java](ContactService.java) – An in-memory service for adding, updating, retrieving, and deleting contacts.
- [ContactTest.java](ContactTest.java) – JUnit tests for contact fields and invalid input.
- [ContactServiceTest.java](ContactServiceTest.java) – JUnit tests for adding, updating, deleting, and rejecting invalid changes.

**Project Two – Summary and Reflections**

- [Summary and Reflections Report](CS320_Project_Two_Summary_and_Reflections_Verified.docx) – A reflection on the testing approach, techniques, quality, and lessons from the projects.

## Reflection

### How can I ensure that my code, program, or software is functional and secure?

I can start by checking each requirement and writing tests for both normal and unexpected inputs. In the contact service, I tested conditions such as duplicate IDs, phone numbers that are not exactly ten digits, and updates with invalid values. It is also important to make sure a failed update does not leave the data partly changed. Input validation and regular regression testing help reduce defects, although a production application would also need additional security measures such as access controls and protection for stored information.

### How do I interpret user needs and incorporate them into a program?

I begin by turning the customer's description into specific behaviors that can be implemented and tested. For example, the requirements for this project stated that a contact ID could not change, a phone number had to be exactly ten digits, and only certain contact fields could be updated. Those details guided both the class design and my test cases. Comparing the finished behavior with the original requirements is a useful way to avoid assuming that something works simply because the program runs.

### How do I approach designing software?

I try to separate responsibilities so that each part of the program has a clear purpose. The Contact class stores and validates an individual contact, while ContactService manages the collection of contacts and the operations performed on them. I used an in-memory map to organize records by ID and kept the validation checks close to the data they protect. Building tests alongside the code also makes it easier to catch mistakes early and make changes without breaking existing behavior.
