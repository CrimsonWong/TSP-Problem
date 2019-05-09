/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA_Test.Selection;

import GA.GA;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author 15142087777的AW
 */
public class RouletteSelect /*each newpopulation comes from old population*/
{
    @Test
    public void rouletteSelect1() throws Exception
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
        
        tspProblem.rouletteSelect();
        int[][] newPopulation = tspProblem.getChilds();
        int[][] oldPopulation = tspProblem.getParents();
        
//        ArrayList <int[]> a = new ArrayList <int[]>();
//        for(int i = 0; i < 30; i++)
//        {
//            a.add(oldPopulation[i]);
//        }
        ArrayList <String> a = new ArrayList <String>();
        for(int i = 0; i < 30; i++)
        {           
            a.add(Arrays.toString(oldPopulation[i]));
        
        }
        
        for(int i = 1; i < 30; i++)
        {
            Assert.assertTrue(a.contains(Arrays.toString(newPopulation[i])));
        }
    }
    
}
