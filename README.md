
To get started with the travel agency system, a driver is defined in the TravelAgencyMain class. Here are the below options exposed via driver to create/map the different entities in the system. 

_
Press
1 : createPassenger

2 : createActivity

3 : createItineary

4: createDestination

5: createTravelPackage

6: registerPassengerForActivity

7: addActivityToDestination

8: removeActivityFromDestination

9: printTravelPackageDetails

10: printPassengerDetails

11: removeDestinationFromItineary

12: addDestinationToItineary
_

Now to start the system, here are the sequence of steps that needs to be followed:

Create a bunch of activities by pressing 2 and provide the required details like name, description, max seats, cost. As a result of this operation, an activity id will be generated. Provide destination id as -1 as we haven’t yet created the destination.
Create the destination object by pressing 4 and provide the destination name. Here also a unique id is generated that will uniquely identify the destination.
Once the activities and destination are created, press 7 to map the activity to destination by providing activity and destination id.
Press 3 to create an itinerary entity in the system. 
Press 12 to map itinerary to the required destinations using entity ids. 
Press 5 to create the travel package in the system and provide the relevant itinerary id. 
Press 1 to Create the passenger object by providing information like Passenger name, mobile number and package id for which user has shown interest to register.
After a user is registered, the next step is to register for an activity. Press 6 to provide passenger and activity id. 
As part of above registration, users might get successfully registered for an activity based on the seats availability or balance of the user or it will throw a relevant error. To validate, press 10/9 to print the passenger or travel package details. 


**Assumptions**: 

A user can’t register for multiple travel packages at once. 
Once a user signs up for a package, the user can’t unregister from the package.
All entity data is stored in in-memory cache using hashmaps. There is no external usage of file/db systems. 
No functionality exists to increase the balance of the passenger wallet. 

Things which could have been done better:

Unit test cases could be more exhaustive. 
Concurrency / race conditions are not handled, although it can be achieved by using ConcurrentHashMap as data structures or also using synchronised blocks in the critical sections of the code like generating entity ids etc. 
Custom exceptions could have been created, right now I have used the standard Exception class to throw any data validation errors. 
