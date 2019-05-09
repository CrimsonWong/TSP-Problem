/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA_Test.InitialGroup;

import GA.GA;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author 15142087777的AW
 */
public class InitialTest {
    
    @Test
    public void initialTest1()
    {
         double[][] cityPosition={
                    {1304,        2312},{3639,        1315},         
                {4177,        2244},{3712,        1399},            
                {3488,        1535},{3326,        1556},         
                {3238,        1229},{4196,        1004},         
                {4312,         790},{4386,         570},
                {3007,        1970},{2562,        1756},
                {2788,        1491},{2381,        1676},
                {1332,         695},{3715,        1678},
                {3918,        2179},{4061,        2370},
                {3780,        2212},{3676,        2578},
                {4029,        2838},{4263,        2931},
                {3429,        1908},{3507,        2367},
                {3394,        2643},{3439,        3201},
                {2935,        3240},{3140,        3550},
                {2545,        2357},{2778,        2826},
                {2370,        2975}};/*citynum = 31,optimal value = 14700*/
    
        GA tspProblem = new GA(30,31,10000,0.5f,0.5f);
        tspProblem.positionToDistance(cityPosition);
        
        tspProblem.initGroup();
        
        int[][] oldPopulation = tspProblem.getParents();
        boolean rightFlag = true;
        
        for(int i =0; i<30; i++)
        {
            for(int j =0; j<31; j++)
            {
                for(int k =j+1; k<31; k++)
                {
                    if(oldPopulation[i][j] == oldPopulation[i][k])
                    {
                        rightFlag = false;
                        System.out.println(Arrays.toString(oldPopulation[i]));
                        System.out.println(i);
                        System.out.println(j);
                    }
                }
            }
        }
        
        Assert.assertTrue(rightFlag);
    }
}
