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

*Include any design diagrams prepared during sprint planning (user interface, component diagram, component/state/hierarchy, etc.) with a short paragraph for each.

Epics planned for this sprint.

* User: I want to view my trip in other tools.
* User: I want to plan trips worldwide.
* User: I want trip planning to be fast.
* User: I want trip planning to be fast.
* User: I want to design a trip from scratch so I can stop using the other tool.
* User: I want to make and save changes to the trip.
* User: I'd like even shorter trips Epic
* User: I want to choose what information is displayed in the itinerary and map.
* TripCo: All code must be tested

## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | *total* | *total* |
| Tasks |  *total*   | *total* | 
| Story Points |  *total*  | *total* | 

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.*

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *date* | *#task, ...* | *#task, ...* | *none* | 

*Add a new row for the scrum session after each lecture. *

## Review

*An introductory paragraph describing the overall results of the sprint.*

#### Completed Epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *## epic title: comments*
* 

#### Incomplete Epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *## epic title: explanation*
*

#### What Went Well

*Describe what went well during the sprint in general terms followed by a more detailed list.*

* *something*
*

#### Problems Encountered and Resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *something*
*

## Retrospective

*An introductory paragraph for your retrospective.*

#### What we changed this sprint

*Articulate specifically what you will do differently based on the retrospective from the previous sprint before the sprint starts.*

#### What we did well

*Articulate what went well at the end of the sprint.*

#### What we need to work on

*Articulate things you could improve at the end of the sprint.*

#### What we will change next sprint 

*Articulate the one thing you will change for the next sprint and how you will accomplish that.*
