# MBTA-demo

Java Spring boot application to call MBTA Rest api 
- swagger  - https://api-v3.mbta.com/docs/swagger/index.html
- MBTA map - https://www.mbta.com/schedules/subway

main class src/main/java/code/DemoApplication.java

# Run
- if use IntelliJ: run 'Spring Boot' coniguration/run for *code.DemoApplication*

initally app was written using Java 17, but could be run with the lower versions (adjust pom.xml)

# Output
```
=== Question 1 - 'subway' routes 'long names'
Red Line,Mattapan Trolley,Orange Line,Green Line B,Green Line C,Green Line D,Green Line E,Blue Line

=== Question 2 - min/max stops in routes, intersections
1. route with the most stops  :  Green Line B         stops: 23
2. route with the fewest stops:  Mattapan Trolley     stops: 8
3. --- connecting station            routes at this stop
 3.0 . Ashmont                    :  Red Line; Mattapan Trolley
 3.1 . North Station              :  Green Line E; Green Line D; Orange Line
 3.2 . Government Center          :  Blue Line; Green Line C; Green Line B; Green Line E; Green Line D
 3.3 . Hynes Convention Center    :  Green Line C; Green Line B; Green Line D
 3.4 . Haymarket                  :  Green Line E; Green Line D; Orange Line
 3.5 . Boylston                   :  Green Line C; Green Line B; Green Line E; Green Line D
 3.6 . State                      :  Blue Line; Orange Line
 3.7 . Kenmore                    :  Green Line C; Green Line B; Green Line D
 3.8 . Copley                     :  Green Line C; Green Line B; Green Line E; Green Line D
 3.9 . Park Street                :  Red Line; Green Line C; Green Line B; Green Line E; Green Line D
 3.10. Downtown Crossing          :  Red Line; Orange Line
 3.11. Arlington                  :  Green Line C; Green Line B; Green Line E; Green Line D

=== Question 3 - connect two stations
Harvard to Quincy Center -> Red Line
Harvard to Ruggles -> Red Line, Orange Line
Central to Airport -> Red Line, Green Line C, Blue Line
invalid START stop name provided (not found...): xxxx
invalid END stop name provided (not found...): invalid stop name
Davis to Kendall/MIT -> Red Line
Ashmont to Arlington -> Red Line, Green Line B
Copley to Cedar Grove -> Green Line B, Red Line, Mattapan Trolley

```

# Algorith to find connection (MBTA lines) between two stops - Question 3

We are dealing with the graph - vertex - stop; edge - line

To find connecting lines we do NOT need actually full graph traverse (can use java guava or other packages)

Use simplified model with edges and child edges only:

 the algo - connect stops stop1 and stop2:
 
  0. fill mini graph with edges - no need to have inner stops, only lines and intersecting lines
  1. get lines (edges) for of the stop1 - line[] lines1 - usually one line, but could be set (like Downtown Crossing: Orange, Red Line)
                          and for stop2 - line[] lines2
  2. (for each line1 in lines1) check if line2 (from lines2) == lin1 OR if line2 belongs line1.child lines
     collect connection path along the way.
     
     if line1!=line2 and line1.children do not contain line2 - recurse: repeat for all line1.children
```
  Code map:
  call rest api           code.helper.Rest - uses simple http get. can switch to RestTemplate with mono/flux if needed
  init all datasets       Question3() constructor
  main recursion method   Question3::findRouteConnection
                          Question3::question_3_connectStops(String station1, String station2) - main test method for question 3

```
