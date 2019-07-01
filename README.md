# walmart
Use the command java -jar walmart-assignment-0.0.1-SNAPSHOT.jar

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
