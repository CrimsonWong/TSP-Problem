package GA_Test.AlgorithmTest;

/*
 * Copyright (c) 2017. Phasmid Software
 */



import GA.GA;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class GASolveTest {
    
    @Test
    public void gaSSolveTest1() throws Exception {
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
        
        GA tspProblem = new GA();
        tspProblem.positionToDistance(cityPosition);
        
        tspProblem.run();
        
        int a = tspProblem.getBestLength();
        int b = 14700;
        
        //System.out.println(a);
        assertEquals(1, a/b, 0.3);
    }

    @Test
    public void gaSSolveTest2() throws Exception {
//        double[][] cityPosition={{11003.611100, 42102.500000},
//                                {11108.611100, 42373.888900},
//                                {11133.333300, 42885.833300},
//                                {11155.833300, 42712.500000},
//                                {11183.333300, 42933.333300},
//                                {11297.500000, 42853.333300},
//                                {11310.277800, 42929.444400},
//                                {11416.666700, 42983.333300},
//                                {11423.888900, 43000.277800},
//                                {11438.333300, 42057.222200},
//                                {11461.111100, 43252.777800},
//                                {11485.555600, 43187.222200},
//                                {11503.055600, 42855.277800},
//                                {11511.388900, 42106.388900},
//                                {11522.222200, 42841.944400},
//                                {11569.444400, 43136.666700},
//                                {11583.333300, 43150.000000},
//                                {11595.000000, 43148.055600},
//                                {11600.000000, 43150.000000},
//                                {11690.555600, 42686.666700},
//                                {11715.833300, 41836.111100},
//                                {11751.111100, 42814.444400},
//                                {11770.277800, 42651.944400},
//                                {11785.277800, 42884.444400},
//                                {11822.777800, 42673.611100},
//                                {11846.944400, 42660.555600},
//                                {11963.055600, 43290.555600},
//                                {11973.055600, 43026.111100},
//                                {12058.333300, 42195.555600},
//                                {12149.444400, 42477.500000},
//                                {12286.944400, 43355.555600},
//                                {12300.000000, 42433.333300},
//                                {12355.833300, 43156.388900},
//                                {12363.333300, 43189.166700},
//                                {12372.777800, 42711.388900},
//                                {12386.666700, 43334.722200},
//                                {12421.666700, 42895.555600},
//                                {12645.000000, 42973.333300}};/*citynum = 38,optimal value = 6656,DJ38 Djibouti */
        double[][] cityPosition={
                {60,200},{180,200},{80,180},{140,180},
                {20,160},{100,160},{200,160},{140,140},
                {40,120},{100,120},{180,100},{60,80},
                {120,80},{180,60},{20,40},{100,40},
                {200,40},{20,20},{60,20},{160,20}};//20个城市（最优解:870）
        
        GA tspProblem = new GA();
        tspProblem.positionToDistance(cityPosition);
        
        tspProblem.run();
        
        int s1 = tspProblem.getBestLength();
        int s0 = 870;
        
        assertEquals(1, s1 / s0, 0.35);
    }
}