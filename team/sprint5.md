# Sprint 5 - t10 - Error 404: Team Name Not Found

## Goal

### Wrap It Up!
### Sprint Leader: Nick Kaliher

## Definition of Done

* Sprint Review and Restrospectives completed (sprint5.md).
* Version in pom.xml should be `<version>5.0.0</version>`.
* Increment deployed for demo and testing as server-5.0.jar on the production server.
* Increment release `v5.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code Climate maintainability of A or B.
* Code adheres to Google style guides for Java and JavaScript.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* All tests pass.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration / Delivery
* Travis successfully builds and tests on all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

With this final sprint, we first hope to quickly finish the few epics that were leftover from sprint 4. We nearly completed sprint 4, so we should be left with a lot of time afterwards to focus our attention on delivering a complete and well-presented product by the end of this sprint. To accomplish this, we will collablorate with each other to finish epics in a timely manner, just as we did with the world map in sprint 4. Our main focuses will be the user interface, searching and adding places worldwide, the interactive map, and even shorter trips and trip planning times.

#### Diagrams

![Component Hierarchy](/team/Sprint4_Component_Hierarchy.png "Sprint 4 Component Hierarchy Sketch")

##### -Component Hierarchy

![Server Hierarchy](/team/Sprint4_Server_Hierarchy.png "Sprint 4 Server Hierarchy Sketch")

##### -Server Hierarchy

Our diagrams haven't changed since Sprint 4 because we expect the overall structure of the frontend and backend to remain relatively unchanged. As for the user interface, we are going to continue the same general structure for that as well, continuing where we left of with it in Sprint 4.

##### Epics planned for this sprint:

* User: I want to choose what information is displayed in the itinerary and map.
* User: I want to view my trip in other tools.
* User: I want to plan trips worldwide.
* User: Make the system easier to use.
* User: I want my options remembered so I don't have to fix them all the time.
* User: I want to know who to thank for this application.
* User: I want an interactive map.
* User: I want trip planning to be fast.
* User: I want the shortest trips possible.


## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | 9 | 5 |
| Tasks | 15 | 27 | 
| Story Points | 33 | 44 |

We are at a solid number of epics, tasks, and story points here for a start. We could definitely spread the work out more, but we won't really know until we start getting into these epics and tasks that we currently have to see what they really entail. This is how it usually goes for us, and it works out nicely in the end.

We managed to complete more than half of the epics that we had planned to finish. In terms of our story points and tasks, we nearly doubled both of those in the progress. Although this is apparently fairly normal among teams, it is still an indication of poor planning and shows that we could still improve in that aspect of scrum.


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| 11/14/18 | *none* | #354 #355 | *none* | 
| 11/16/18 | #354 #355 | #358 | *none* | 
| 11/26/18 | #354 #355 #358 | #368 #369 #370 #376 | *Unable to meet this week* |
| 11/28/18 | #354 #355 #358 #368 #369 #370 #376 | #351 #349 | *Unable to meet this week* |
| 11/30/18 | Did on paper in class |
| 12/3/18 | #354 #355 #358 #368 #369 #370 #376 #349 #414 #413 #412 #392 #383 #382 | #351 #267 | *none* |
| 12/05/18 | Did on paper in class |

## Review

Overall, this sprint went as well as we could have hoped. We got a fair amount done and are pleased with the final state of our trip planning application. Of course improvements could still be made, but for the most part, our website is user friendly and does what it was set out to accomplish for anyone wishing to plan a trip.

#### Completed Epics in Sprint Backlog 

The final version of this sprint includes updates mainly regarding the user experience and optimization. These were heavily focused on due to the state that they were in prior to this sprint. We felt that we needed to improve these and get them to a level everyone was satisfied with.

* User: I want the shortest trips possible.
* User: I want to choose what information is displayed in the itinerary and map.
* User: Make the system easier to use.
* User: I want to know who to thank for this application.
* User: I want my options remembered so I don't have to fix them all the time.

#### Incomplete Epics in Sprint Backlog 

We were unable to implement an interactive map, the ability to save KML, or the lines on the static map wrapping on to the opposite sides of the Earth. In addition to these, we weren't able to implement concurrency for optimization, or a client-side representation of the search filters.

* User: I want to view my trip in other tools.: explanation
* User: I want an interactive map.
* User: I want to plan trips worldwide.
* User: I want trip planning to be fast.

#### What Went Well

Our GitHub, CodeClimate, and test coverage were the best parts of this sprint for us. Once again we got better as a team and our communication was better than previous sprints. 

* Fixing CodeClimate issues
* Test Coverage
* Teamwork
* Communication
* GitHub usage

#### Problems Encountered and Resolutions

We didn't have many major issues in this sprint. It was mainly just minor things that just didn't make any sense to us as to how they wouldn't be working properly, which is why we were forced to go to office hours so much.

* Optimization implementation: Went to office hours as often as possible to get help in solving an issue with being off by about 0.006% on 3-opt as well as a similar issue with nearest neighbor. Changing control flow solved this issue.
* Map not showing on Black Bottle: Also went to office hours for assistance.
* Permentant interop with PROD due to hardcoded port and deployment on DEV:  Went to office hours to get assistance in figuring out the problem with this. 

## Retrospective
Although not much has changed in the process in which we develop our project, our progress on optimization and U.I. definetly showed. The understanding of our team as a whole and our capabilities greatly improved. We put a lot of effort this sprint into testing and Code Climate. Although certain areas of Github etiiquette and backlog prioriziation were lacking; the sprint really turned out well and provides the user with a adequate experience. 

#### What we changed this sprint

In this sprint, we were unable to start as early as the previous sprints but the efforts put came to fuition towards the last two weeks. We lacked in certain Github areas but worked hard to complete the tasks at hand. Time managment for certain parts of the team improved as tasks were completed with good clean code and efficiency. We focused on testing more towards the end and the process of cleaning up the code. 

#### What we did well

Throughout this sprint our team improved in communication, development aspects, and CodeClimate. This sprint we really came to an understanding of everyones skill sets and motivations. Our team now feels very knowledgable in the GitHub aspects of development. We were able to complete the highest level of optimization at reasonable run times and enhanced the optimization experience. Our test coverage may not of reached the best standard of 70% but we were able to get to a benchmark through more coverage on the client side. 

#### What we need to work on

Even though there where improvements among team moral, there were still certain communication issues and other development aspects. While our teamwork generally improved there were still issues of prioritization among the team and we were unable to complete certain simple tasks due to this. We reached a certain level for testing but did not exceed in this asepct. There were some GitHub areas that could be improved; like backlog maintenence and prioritzation. 

#### What we will change next sprint 

A large area of improvement for our team would be test coverage and certain aspects of github etiquette. Our backlog organization was lacking and could have been followed better. Next sprint we will bring up our test coverage and follow a more procedural method with the backlog. Communicating throughout the sprint is critical and certain improvements on this aspect will need to be made for optimial time management and efficiency. 
