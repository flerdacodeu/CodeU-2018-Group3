package test.edu.codeU.assignment6;

import edu.codeU.assignment6.RearrangingCars;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RearrangingCarsTest {
    RearrangingCars rearrangingCars;

    @Test
    public void checkTheCorrectRearrangingThreeCars(){
        int n = 3;
        int startCars[] = {1, 2, 4};
        int finalCars[] = {2, 3, 1};
        rearrangingCars = new RearrangingCars(n, startCars, finalCars);
        List<String> expectedMoves = new ArrayList<>();
        expectedMoves.add("Car 2 from 2 to 3");
        expectedMoves.add("Car 1 from 1 to 2");
        expectedMoves.add("Car 3 from 4 to 1");
        List<String> actualMoves = rearrangingCars.rearrangeCars();
        Assert.assertEquals(expectedMoves, actualMoves);
    }

    @Test
    public void checkTheCorrectRearrangingTwoCars(){
        int n = 2;
        int startCars[] = {1, 2};
        int finalCars[] = {1, 2};
        rearrangingCars = new RearrangingCars(n, startCars, finalCars);
        List<String> expectedMoves = new ArrayList<>();
        List<String> actualMoves = rearrangingCars.rearrangeCars();
        Assert.assertEquals(expectedMoves, actualMoves);
    }

    @Test
    public void checkTheCorrectRearranginOneCar(){
        int n = 1;
        int startCars[] = {2};
        int finalCars[] = {1};
        rearrangingCars = new RearrangingCars(n, startCars, finalCars);
        List<String> expectedMoves = new ArrayList<>();
        expectedMoves.add("Car 1 from 2 to 1");
        List<String> actualMoves = rearrangingCars.rearrangeCars();
        Assert.assertEquals(expectedMoves, actualMoves);
    }
}
