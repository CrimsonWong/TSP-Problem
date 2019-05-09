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
public class SelectBestTest /*select best individual with best fitness and add it to bestTour and firstline of parentPopulation*/
{
    @Test
    public void selectBestTest() throws Exception
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
        
        GA tspProblem = new GA(30, 31, 10000, 0.5f, 0.5f);
        tspProblem.positionToDistance(cityPosition);
        
        tspProblem.initGroup();
        //System.out.println(tspProblem.getCityNum());
        
        int[] fitness = new int[30];
        
        /*Calculate fitness manualy*/
        int[][] parentPopulation = tspProblem.getParents();
        for (int k = 0; k < 30; k++) {
            fitness[k] = tspProblem.calDis(parentPopulation[k]);
        }
        
        tspProblem.setFitness(fitness);
        
        tspProblem.selectBest();
        
        Arrays.sort(fitness);
//        for (int k = 0; k <30; k++) {;
//            System.out.println(k);
//            System.out.println(fitness[k]);
//        }
        int fitnessMIN = fitness[0];
        
        int[][] newPopulation = tspProblem.getChilds();
        int a = tspProblem.calDis(newPopulation[0]);
        
        
        assertEquals(fitnessMIN,a);
        
        int bestTour[] = tspProblem.getBestPath();
        int b[] = new int[31];
        
        for(int k = 0; k <31; k++)
        {
            b[k] = bestTour[k];
        }
//        System.out.println(tspProblem.getCityNum());
//        System.out.println(Arrays.toString(b));
//        System.out.println(Arrays.toString(b));
//        System.out.println(Arrays.toString(newPopulation[0]));
        
        assertEquals(Arrays.toString(b) , Arrays.toString(newPopulation[0]));
    }
    
}
