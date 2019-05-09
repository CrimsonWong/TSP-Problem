/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA_Test.Selection;

import GA.GA;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author 15142087777的AW
 */
public class CountRateTest /*have fitness[scale] and want Pi[scale],which can implement the most fitness[i] has the best Delta pi[i]*/
{
    @Test
    public void countRateTest1() throws Exception
    {
        int[] fitness = {2, 1, 4, 4};
        GA tspProblem = new GA();
        
        tspProblem.setFitness(fitness);
        tspProblem.setScale(4);
        
        tspProblem.countRate();
        
        float[] pi = tspProblem.getP();
        System.out.println(pi[0]);
        System.out.println(pi[1]);
        System.out.println(pi[2]);
        System.out.println(pi[3]);
        
        assertEquals(0.875, pi[2], 3);
    }
}
