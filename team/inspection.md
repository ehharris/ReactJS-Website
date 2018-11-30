# Inspection - Team *T10* 
 
| Inspection | Details |
| ----- | ----- |
| Subject | Database.java |
| Meeting | 11/30, 10am, Lecture |
| Checklist | *reference, URL, etc.* |

### Roles

| Name | Preparation Time |
| ---- | ---- |
| Antonio Segovia Maldonado | 1 hour |
| Tyler Dansby | 10 minutes |
| Eli Harris | 15 minutes |

### Problems found

| file:line | problem | hi/med/low | who found | github#  |
| --- | --- | :---: | :---: | --- |
| Database.java:112 | Potential malformed query: ie.: if heliport, small airport, and Canada are filters, it would return results any query that had a heliport OR small airport OR Canada. It should return if heliport OR small airport AND if in Canada | hi | Antonio | #403 |
| Database.java:122 | Commented out line that sorted the results. Search results are not currently sorted. | low | Antonio | #404 |
| Database.java:81 | Does not check for valid filter structure. Important with interop | low | Antonio | |
| Database.java:86-113 | Repeating Code | med | Eli | #396 |
| Database.java:84 | Nested for-loop increases complexity | med | Eli | #400 |
| Database.java:73 | Add externl methods for build query | low | Eli | #396 |
| Trip.java:312 | Too many repeated methods with one calculation each, change to single method with mutiple calculations. |med | Nick| |
| Itinerary.jsx:78 | Render method currently exceeds the allowed line count | hi | Tyler | |
 
