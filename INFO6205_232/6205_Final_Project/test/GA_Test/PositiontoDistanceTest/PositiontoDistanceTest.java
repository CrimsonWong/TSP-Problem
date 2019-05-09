/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA_Test.PositiontoDistanceTest;

import GA.GA;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author 15142087777的AW
 */
public class PositiontoDistanceTest {
    
    @Test
    public void positiontoDistanceTest()
    {
        double[][] cityPosition = {{3,0},{0,4}};
        
        GA tspProblem = new GA(30,2,10000,0.5f,0.5f);
        
        tspProblem.positionToDistance(cityPosition);
        
        double[][] a = tspProblem.getDistance();
        
        Assert.assertEquals(a[0][1] , a[1][0], 3);
        Assert.assertEquals(5, a[0][1], 3);
    }
    
}
