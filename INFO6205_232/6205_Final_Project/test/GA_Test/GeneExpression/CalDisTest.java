/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA_Test.GeneExpression;

import GA.GA;
import static com.sun.org.apache.regexp.internal.RETest.test;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author 15142087777的AW
 */
public class CalDisTest {
    
    @Test
    public void calDisTest1() throws Exception
    {
        double[][] cityPosition = {{0,1},{0,2},{0,3}};
    
        GA tspProblem = new GA();
        tspProblem.positionToDistance(cityPosition);
        
        double[][] a;
        a = tspProblem.getDistance();
        
        int[] test1 = {0,1,2};
        int result1 = tspProblem.calDis(test1);
        
        int[] test2 = {2,0,1};
        int result2 = tspProblem.calDis(test2);
        
        assertEquals(4, result1);
        assertEquals(4, result2);
    }
}
