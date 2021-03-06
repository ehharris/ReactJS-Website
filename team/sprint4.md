# Sprint 4 - t10 - Error 404: Team Name Not Found

## Goal

### Interactive Maps and Shorter Trips!
### Sprint Leader: Antonio Segovia Maldonado

## Definition of Done

* Sprint Review and Restrospectives completed (sprint4.md).
* Version in pom.xml should be `<version>4.0.0</version>`.
* Increment deployed for demo and testing as server-4.0.jar on the production server.
* Increment release `v4.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration / Delivery
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

For Sprint 4, our main goal is to complete the sprint 3 epics that we were unable to finish. We hope to complete all of these within the first half of this sprint. After that, we expect to finish as many of the required sprint 4 epics as possible. We recognize the amount of work rolling over from the previous sprint and are trying to be realistic with what we can fully accomplish with this one. More specifically, we want to improve our testing, itinerary, map, and overall user experience of the website.

![User Interface Sketch](/team/user_interface_plan_sketch.jpg "Sprint 4 User Interface Plan Sketch")

Our user interface plan is similar to how we planned it in Sprint 3.

![Component Hierarchy](/team/Sprint4_Component_Hierarchy.png "Sprint 4 Component Hierarchy Sketch")

For components we've added display options to Itinerary and have also eliminated the need to pass values into the map component.

![Server Hierarchy](/team/Sprint4_Server_Hierarchy.png "Sprint 4 Server Hierarchy Sketch")

We've updated the hierarcy with the calls to '/map', otherwise the backend will be similar to Sprint 3

Epics planned for this sprint.

* User: I want to view my trip in other tools.
* User: I want to plan trips worldwide.
* User: I want trip planning to be fast.
* User: I want to design a trip from scratch so I can stop using the other tool.
* User: I want to make and save changes to the trip.
* User: I'd like even shorter trips Epic
* User: I want to choose what information is displayed in the itinerary and map.
* TripCo: All code must be tested

## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | 7 | 2 |
| Tasks |  31   | 31 | 
| Story Points |  42  | 37 | 

Our team plans to add more tasks as the sprint goes along. Previously we had about 10-20 extra story points added after the plan and we ended up completing more story points then planned as well. 

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| 10/22/18 | *none* | #72 #158 #252 | Not able to meet | 
| 10/24/18 | #72 #158 #252 | #276 #278 | *none* | 
| 10/26/18 | #72 #158 #252 #276 #278 | #173 | *none* | 
| 10/29/18 | #72 #158 #252 #276 #278 #173 | #266 #273 #178 | Not able to meet |
| 10/31/18 | #72 #158 #252 #276 #278 #173 #283 #175 #266 #181 | #266 #273 #178 | *none* |
| 11/2/18 | #72 #158 #252 #276 #278 #173 #283 #175 #266 #181 #266 #273 #178 | #268 #261 | *none* |
| 11/5/18 | #72 #158 #252 #276 #278 #173 #283 #175 #266 #181 #266 #273 #178 #268 #261 #301 #303 | #165 #253 #306 | *none* |
| 11/7/18 | #72 #158 #252 #276 #278 #173 #283 #175 #266 #181 #266 #273 #178 #268 #261 #301 #303 #165 #253 #306 | #271 #234 #314 #318 #320 #325 | *none* |


## Review

This sprint went relatively well on our tasks completed and burndown chart. We have shown progress over the past sprints and our percentage of tasks completed as well as our incremental deveopment skills have all improved. We still were not on the line with our burndown nor one hundred percent in our completion; but with the plans made for improvement, our team is bound for an even better sprint next time around. 

#### Completed Epics in Sprint Backlog 

The main things that we were able to fully implement this sprint was the new svg world map, an improved nearest neighbor algorithm as well as an efficient 2-opt, an overhaul on the UI of our entire website, and the search/add functionality.

* User: I'd like even shorter trips
* User: I want to design a trip from scratch so I can stop using the other tool.
* TripCo: All code must be tested.

#### Incomplete Epics in Sprint Backlog 

Unfortunately, we weren't able to implement the filters for searching, the kml map, and the additional itinerary options from the database.

* User: I want to make and save changes to the trip.
* User: I want to choose what information is displayed in the itinerary and map.
* User: I want to plan trips worldwide.
* User: I want to view my trip in other tools.

#### What Went Well

* Planning stage
* Burndown chart
* Overall teamwork and communication
* Incremental development and pulls.
* Test coverage.
* Fixing CodeClimate issues

#### Problems Encountered and Resolutions

* Issues with nearest neighbor and 2-opt. This took many hours and lots of help from Andy and Dave Matthews to fix. Minor changes to indicies fixed the main issue in the end.
* Issues with getting accurate latitude and longitude on the world map. Required us to all help out to quickly solve the issue, which ended up being a problem with the formula that converted latitude and longitude to pixels.
* Our search function had a lot of bugs on the client-side. It took a lot of time to fix those issues, and so instead of attempting to finish it completely, we decided to polish and test what we had.

## Retrospective

Our team had a bit of a struggle in the start of this sprint, but through communication and changes within our team, we came out looking better. Lack of communication was an issue in the beginning, but after meeting and expressing our own ideas, we came to a resolution which bettered the team overall. 

#### What we changed this sprint

We did a much better job of managing our time this sprint. This was a goal from the previous sprint after struggling the last day of that sprint. Coming together as a team and getting a better understanding of how long each task would take really improved our burndown chart. We managed to not add any major code sections the last day of this sprint and instead focused on our CodeClimate smells and duplication, as well as our test coverage.
We also improved on working as a team on tasks as opposed to taking a "Divide and Conquer" approach, especially when we worked on large and complicated parts of the code.

#### What we did well

Although our had a rough patch, we came out the conflict better as a team. We each worked and did our part. Our team properly assigned each task and each member was sufficiently challenged and work kept equal throughout. We helped eachother a lot the last week to wrap up any issues we were having. Everything just seemed to come together towards the end, even though not everything was finished there was many improvements- not only to the site and backend, but also to the team's morale. 

#### What we need to work on

There is still a need for more incremental development throughout the team. Our burndown chart was possibly the best its ever been but it was still lacking in areas. Putting more consistent work into this class instead of cram sessions sould be benefiical not just for our grade but for our teamwork and communication as well. Working more on code climate and styling before uploading would also greatly increase our code climate score and improve our code base. 

#### What we will change next sprint 

With what we learned with our interpersonal conflicts and communication improvements, our next sprint is bound to be our best. We will communicate our own actions better and work towards better team collaboration. We will strive to upload more incrementally and improve our burndown chart. We will also make sure to produce polished features on the site as opposed to pushing incomplete features. We will work more on the user expereience as we have started to do this sprint.
