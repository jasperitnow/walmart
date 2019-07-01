# walmart

#Technical Requirements

* The matching service implementation should be written in Java preferably using a messaging solution
* The solution and tests should build and execute entirely via the command line using either Maven or Gradle as the build tool
* A README file should be included in your submission that documents your assumptions and includes instructions for building the solution and executing the tests
* Implementation mechanisms such as disk-based storage, a REST API, and a front-end GUI are welcomed but not required
* Your solution will be reviewed by engineers that you will be working with if you join the Walmart Technology team. We are
interested in seeing how you design, code, and test software.
* You will need to implement the following interface. The design of the Technician and Criteria object is entirely up to you

#Implementation

There are 4 processes creating the Issues in a fixed intervals.
There will be N number of consumers (Technisians ) working on the issues.

How this works:

  * Issue generator generates the issues 
  * There are N threads monitering the issues Queue
  * Verifies the Issues Queue, gets the first Issue, matches based on the preferences set by issue
     1. First checks if the first preffered technisian available then assign the issue
     2. If the first preffered technisian is not available, it assigns to the second preffered technician.
     3. If no technisian is available , the process waits for the next available technisian
  * Once the technisian is assigned, the process will continue to monitor the completion 
  * This will be a never ending process that continuesly monitors the issues.

#How to run :
Use the command java -jar walmart-assignment-0.0.1-SNAPSHOT.jar
