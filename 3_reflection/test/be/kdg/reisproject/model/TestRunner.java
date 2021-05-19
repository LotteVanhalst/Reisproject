package be.kdg.reisproject.model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author Lotte Vanhalst
 * @version 1.0 9/04/2019 20:31
 */
public class TestRunner {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(TestSuite.class);

        System.out.println("Failure: " + result.getFailureCount());
        for (Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println("Successful: " + result.wasSuccessful());
        System.out.println("Aantal testcases: " + result.getRunCount());
        System.out.println("Tijd: " + result.getRunTime() + " millisec");
    }
}
