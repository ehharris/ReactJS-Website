# Sprint 3 - t10 - Error 404: Team Name Not Found

## Goal

### Build shorter trips!
### Sprint Leader: *Tyler Dansby*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint3.md).
* Version in pom.xml should be `<version>3.0.0</version>`.
* Increment deployed for demo and testing as server-3.0.jar.
* Increment release `v3.0` created on GitHub with appropriate version number and name.
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
#### Continuous Integration
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

For sprint 3 we plan to improve on and finish what we added in sprint 2, such as implementing a map that shows where your trip takes you, an itinerary that shows destinations and distances for all legs of a trip, and implementing our file upload button. We also plan to introduce some new things like adding user specific attributes to the trip allowing users to only see certain parts of their trip at a time and adding more tests to our code to assure a smooth user experience.

![User Interface Sketch](/team/user_interface_plan_sketch.jpg "Sprint 3 User Interface Plan Sketch")

This is a plan for our user interface.  
The plan for the user interface is to organize the form fields in an appealing manner. Compared to our previous sprint deployment, we plan to add the "User Defined" option under Select Units. If we have time, the server-port configuration will be reduced to only one line with two inputs. We will also add the file upload button, and right under it, there will be the options to select or enter attribute/value pairs for the itinerary. The itinerary should change depending on which attribute/value pairs are chosen. From there, the submit button from the previous deployment will be replaced with a "Plan!" button. There will be a button to shorten the trip distances and another that shortens it even further. Then, we plan to have the map and itinerary dynamically update as the user updates it.

![Component Diagram Sketch](/team/sprint3_component_hierarchy.png "Sprint 3 Component Hierarchy Sketch")

This is a sketch of our Component Hieracrchy. 
Not much has been added to this for this sprint except for an optimization component. This component will work with the trip component to optimize the trip distances to either a short, shorter, or shortest trip and then display the content on the map/itinerary when it's done.

![Server Hierarchy Sketch](/team/sprint3_server_hierarchy.png "Sprint 3 User Server Hierarchy Sketch")

This is a sketch of the Server Hierarchy for Sprint 3. 
Like the component diagram, only one thing has changed for this sprint. The "Search" function will search for destinations from a database and reccomend certain destinations for the client to add.


*Include any design diagrams prepared during sprint planning (user interface, component diagram, component/state/hierarchy, etc.) with a short paragraph or each.

Epics planned for this sprint.

* User: I want a map and itinerary for my trip
* User: I want my trips to be shorter
* User: I'd like even shorter trips
* User: I want to choose what information is displayed in the itinerary and map.
* User: I want to design a trip from scratch so I can stop using the other tool.
* User: I want to make and save changes to the trip.
* TripCo: All code must be tested.


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | 7 | 2 |
| Tasks |  23   | 24 | 
| Story Points |  41  | 39 | 

With sprint 3, we are focusing on improving our incremental uploads and in turn, our burndown chart. To do this, we have split up the epics initially into as many tasks as we could, resulting in double the amount of planned story points as sprint 2. This will help us keep track of where we are on a day-to-day basis, rather than eyeballing based on one task per epic, as some of our epics were in sprint 2.

We did better on this sprint, but still have much room for improvement. We created a lot of tasks as the sprint went on which is why our planned and completed tasks do not allign very well. 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| 10-03-18 | N/A | #90, #88, #133, #166 | N/A | 
| 10-05-18 | #172 #173 | #90, #88, #133, #166 #162 | N/A | 
| 10-08-18 | #172 #173 #133 | #159 #88 #148 #195 | No Meeting Today | 
| 10-10-18 | #172 #173 #133 #159 #88 #148 #195 | #72 #90 #210 | N/A | 
| 10-12-18 | #172 #173 #133 #159 #88 #148 #195 #72 #90 #210 #162 #201 | #166 | N/A | 
| 10-15-18 | #172 #173 #133 #159 #88 #148 #195 #72 #90 #210 #162 #201 #166 | #218 | N/A | 
| 10-17-18 | #172 #173 #133 #159 #88 #148 #195 #72 #90 #210 #162 #201 #166 #218 | #167 #160 | N/A | 


## Review

Overall, we learned some valuable lessons this sprint. We improved a lot with our burndown chart, but the amount of tasks and story points that rolled over from sprint 3 proved too much for us to finish. We had good communication, and got help when needed, but we simply didn't put in enough work during this sprint. 

#### Completed Epics in Sprint Backlog 

We weren't able to complete many of the Sprint 3 epics, but we did finish up Sprint 2 and made progress with all other epics. Our main focus was finishing up the map and itinerary.

* User: I want a map and itinerary for my trip
* User: I want my trips to be shorter.

#### Incomplete Epics in Sprint Backlog 

Unfortunately, we weren't able to implement the ability to change the trip, save changes to it, and many of the itinerary related sprint 3 tasks.

* User: I'd like even shorter trips
* User: I want to choose what information is displayed in the itinerary and map.
* User: I want to design a trip from scratch so I can stop using the other tool.
* User: I want to make and save changes to the trip. 
* TripCo: All code must be tested.

#### What Went Well

We had good communication regarding when things would be done. We let each other know of issues we were having and sought help from friends and TA's when we needed to. 

* Itinerary now displays correctly and we added options to include the longitude and latitude when seeing destinations
* Database search is now functional 

#### Problems Encountered and Resolutions

In general, the two largest issues we faced as a team were time management and deployments. We were behind during this sprint due to leftover Sprint 2 epics, which took the large amount of time to complete. We also did not deploy after large edits and added functionality to the website.

* Final server deployment was incomplete as the Travis CI queue was clogged towards the beginning of the last hour of the sprint deadline. 
* A few functions failed towards the end due to lack of time and testing.
* A few classes are missing test classes.


## Retrospective

This sprint was somewhat of a learning experience for us. We underestimated the amount of work we needed to get done for this sprint but we completed what was missing from sprint 2 and added some of the epics from sprint 3. This sprint has made us come to understand that we need to get better at our time management skills and at estimating how long each task will take. In the future we will be giving much more attention to planning and have a greater commitment to working on this more incrementally.  

#### What we changed this sprint

After meeting with Dave Matthews concerning our less than average score on github and zenhub etiquette we took significant efforts to fix our score. We focused more on incremental development and how we uploaded. We focused on creating smaller tasks, assigning them properly, and putting them in progress before working on them/uploading them. We also put more effort into testing, although not perfect, was significantly imoroved from the last sprint.

#### What we did well

Our burndown chart this sprint was significantly better than our last one and showed good incremental progress even though we had some big leaps towards the end. Our teammate interaction and communcation was positive and went well like previous sprints. We also assigned tasks well based on our own skills and abilities. As a team, we helped eachother often when needed and met up to work together. 

#### What we need to work on

It would benefit the group greatly if we all worked more in the first two weeks. Keeping up with the idea of test driven development and clean code would benefit our teams grade and work experience. We also need to prevent these "big bangs" at the end of the sprints to better benefit our code climate and test integreation. 

We need to begin deploying a build everytime we complete an epic or large function that would have been demoed at the end of the sprint. We also need to create test classes for every new class we create and begin creating test cases for the client-side code. We will also need to focus on our time management heavily since we still have several sprint three epics to complete.

#### What we will change next sprint 

For the next sprint, we will meet for longer on the first day to plan everything out accordingly and discuss what each epic entails in depth. This should improve our time management skills since we will have a clearer idea as to what needs to be done and how long it will take. 
