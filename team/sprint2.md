# Sprint 2 - t10 - Error 404: Team Name Not Found

## Goal

### A mobile, responsive map and itinerary!
### Sprint Leader: Nick Kaliher

## Definition of Done

* Sprint Review and Restrospectives completed (sprint2.md).
* Version in pom.xml should be `<version>2.0.0</version>`.
* Increment deployed for demo and testing.
* Increment release `v2.0` created on GitHub with appropriate version number and name.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order.
* Unit tests are fully automated.
#### Configuration Management
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration
* Continuous integration successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

For Sprint 2 we expect to complete all of the epics in our backlog as well as maintain clean and efficient code in the process. Working on the project in small increments each day will help us achieve this goal and do it in a timely manner. As always, this will require good communication between all team members to decide the best times to meet during this Sprint.

* ## Tripco: All clients and servers must interoperate!
    * This requires an adherence to the TFFI specification.
    * Each client must include a configuration option to change to server:port used for RESTful services.
* ## TripCo: The solution must be responsive for mobile devices.
    * Mobile first! The solution should be designed/optimized for a mobile environment, but still work well in a desktop    environment. Not the other way around.
    * This is an ongoing issue that may need to be reflected in the README and sprint2.md. 
* ## User: I want to supply my own units for the distances.
    * An arbitrary unit of measure should be able to be defined to use in the itinerary.
* ## TripCo: All code shall be clean!
    * Adhere to the Google style guide for Java and JavaScript.
    * Write maintainable code.
    * Read the book.
    * Amends to the sprint2.md and README.md will be necessary to stay adaptive. 
* ## User:I want a map and itinerary for my trip
    * Trips in the state of Colorado are tested on another tool.
    * The tool produces a file that conforms to the TFFI trip object.
    * The trip is always a round trip.
    * Show a map and itinerary for the trip in the file that can be viewed on a phone.
    
During the discussion of our plan, we agreed to divide the workload under each epic into tasks that we believed would take under one hour each to complete. If we find that one task will take longer than we expected, we will divide the task further. Additionally, we will add or adjust tasks as needed while the project progresses. We will maintain good communication and regularly check in with each other on our progress regularly.

## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | 5 | 1 |
| Tasks |   18   | 9 | 
| Story Points |  37  | 20 | 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| 9/14/18 | N/A | #93 | N/A |
| 9/17/18 | N/A | #85 #93 #84 | N/A |
| 9/19/18 | #51 #75 #100 | #101 #85 #93 #84 #95 | N/A |
| 9/21/18 | #51 #75 #100 #93 #89 #85 #101 #97 | #84 #114 | N/A |
| 9/24/18 | #51 #75 #100 #93 #89 #85 #101 #97 | #84 #114 | Couldn't meet today |
| 9/26/18 | #51 #75 #100 #93 #89 #85 #101 #97 | #88 #90 #84 #114 | N/A |

## Review

Overall, this sprint was a good learning experience. There was a lot of new things to get accustomed to, such as TravisCl and CodeClimate, as well as this being the first real sprint. We did much better as a team than the first sprint, but still have lots of room for improvement. Unforunately, we just weren't able to complete as much as we had hoped due to 

#### Completed epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*
Our current code has the updated TFFI spec, which allows for oteroperability and calculating distances for trips to function as they should. It also has a map of Colorado and an area to upload files; however, these aren't fully functional yet.

* User: I want to supply my own units for distances

#### Incomplete epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*
We were unable to complete the itinerary, and the legs of the trip are not drawn on the map. Some of these sprints were mostly completed, but not enough to actually say so. 

* TripCo: The solution must be responsive for mobile devices.
* Tripco: All clients and servers must interoperate!
* User: I want a map and itinerary for my trip
* TripCo: All code shall be clean!

#### What went well

Teamwork went very well for this sprint. We all helped each other when necessary, and the tasks were initally divided up to each persons strength. We also adapted very well to new things we learned during the sprint.

* Teamwork
* Adaptability
* Communication was good.

#### Problems encountered and resolutions

Our main issues were with getting started most of the time. With little to nothing to go off of, it would take us a while sometimes to even begin to understand the concepts. This wasted valuable time in the end, and our uncompleted epics reflects this.

* Understanding the big picture and how everything will work together to achieve that.
* Wasting time on less important tasks.
* Simply not devoting enough time to the project.

## Retrospective

*An introductory paragraph for your retrospective.*

#### What we changed this sprint

*Articulate specifically what you will do differently based on the retrospective from the previous sprint before the sprint starts.*

#### What we did well

*Articulate what went well at the end of the sprint.*

#### What we need to work on

*Articulate things you could improve at the end of the sprint.*

#### What we will change next sprint 

*Articulate at the end of the sprint.  Focus on one of things you need to work on.*

