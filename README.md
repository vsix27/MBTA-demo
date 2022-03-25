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
