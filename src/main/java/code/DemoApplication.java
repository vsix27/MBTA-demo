package code;

import code.service.Question1;
import code.service.Question2;
import code.service.Question3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    private static Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) throws Exception {
        log.info("--- main" );

        // Question 1 - "subway" routes “long names”
        //Red Line,Mattapan Trolley,Orange Line,Green Line B,Green Line C,Green Line D,Green Line E,Blue Line
        System.out.println("\n=== Question 1 - 'subway' routes 'long names'");
        new Question1().question_1_serverFilter();

        // Question 2
        System.out.println("\n=== Question 2 - min/max stops in routes, intersections");
        new Question2().question_2_additionalInfo();

        // MBTA map https://cdn.mbta.com/sites/default/files/2022-03/3-21-2022-subway-map-union-square-branch.pdf
        // Question 3
        /**
         * we are dealing with the graph - vertex - stop; edge - line
         * to find connecting lines we do NOT need actually full graph traverse (can use java guava or other packages)
         *
         * can use simplified model with edges and child edges only:
         *
         * the algo - connect stops stop1 and stop2:
         *  0. fill mini graph with edges - no need to have inner stops, only lines and intersecting lines
         *  1. get lines (edges) for of the stop1 - line[] lines1 - usually one line, but could be set (like Downtown Crossing: Orange, Red Line)
         *                          and for stop2 - line[] lines2
         *  2. (for each line1 in lines1) check if line2 (from lines2) == lin1 OR if line2 belongs line1.child lines
         *     collect connection path along the way
         *     if line1!=line2 and line1.children do not contain line2 - recurse: repeat for all line1.children
         *
         *  Code map:
         *  call rest api           code.helper.Rest - uses simple http get. can switch to RestTemplate with mono/flux if needed
         *  init all datasets       Question3() constructor
         *  main recursion method   Question3::findRouteConnection
         *                          Question3::question_3_connectStops(String station1, String station2) - main test method for question 3
         */
        System.out.println("\n=== Question 3 - connect two stations");
        Question3 q3 = new Question3();
//        q3.question_3_connectStops("Harvard", "Quincy Center", ""); // Red Line - same route
//        q3.question_3_connectStops("Harvard", "Ruggles", "");       // Red Line, Orange Line
//        q3.question_3_connectStops("Central", "Airport", "");       // Red Line, Green Line C, Blue Line  ... others available - see commented extended
//        q3.question_3_connectStops("xxxx", "invalid stop name", ""); // negative case
//        q3.question_3_connectStops("Davis", "Kendall/MIT", "");  // 1. Davis to Kendall/MIT -> Red Line
//        q3.question_3_connectStops("Ashmont", "Arlington", "");  // 2. Ashmont to Arlington -> Red Line, Green Line B
//        q3.question_3_connectStops("Copley", "Cedar Grove", ""); // Green Line B, Red Line, Mattapan Trolley

//        The Red Line is unavailable (track fire!), can you get from Coolidge Corner to Mattapan?
//        The Blue Line is unavailable (police incident), can you get from Suffolk Downs to Davis?
//        The Green Line E is unavailable, can you get from Harvard to Haymarket?

        //q3.question_3_connectStops("Coolidge Corner", "Mattapan", "Red Line");
        //q3.question_3_connectStops("Suffolk Downs", "Davis", "Blue Line");
        q3.question_3_connectStops("Harvard", "Haymarket", "Green Line E");
        q3.question_3_connectStops("Harvard", "Haymarket", "Green Line D; Green Line E");
        /**
         * from Coolidge Corner to Mattapan: invalid START stop name provided (not found...) or route is closed: Coolidge Corner; closed routes: [Red Line]
         * from Suffolk Downs to Davis: invalid END stop name provided (not found...) or route is closed: Davis; closed routes: [Blue Line]
         * from Harvard to Haymarket: invalid START stop name provided (not found...) or route is closed: Harvard; closed routes: [Green Line E]
         *
         * Question 4
         * Add support for an unavailable train route. In cases where a train route is down, the program should list the
         * alternate routes or return a message stating that no route exists between these two stops.
         *
         * We will provide information about which train route is down, no change is needed to the MBTA API integration.
         *
         * Examples:
         *
         The Red Line is unavailable (track fire!), can you get from Coolidge Corner to Mattapan?
         The Blue Line is unavailable (police incident), can you get from Suffolk Downs to Davis?
         The Green Line E is unavailable, can you get from Harvard to Haymarket?
         */

    }

}
