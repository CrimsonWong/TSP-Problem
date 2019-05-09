/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA_Test.Evolution;

import GA.GA;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author 15142087777的AW
 */
public class CrossoverTest {
        
    @Test
    public void crossoverTest1()
    {
        GA ga = new GA(30,48,10000,0.5f,0.0f);
        
        ga.generateCity();
        ga.run();
        
        double a = (ga.getCountc()*100);
        
        assertEquals(1 , a/50 , 0.1);
    }
    
    @Test
    public void crossoverTest2()
    {
        GA ga = new GA(30,48,10000,0.0f,0.0f);
        
        ga.generateCity();
        ga.run();
        
        int a = (int) (ga.getCountc()*10);
        
        assertEquals(10,a);
    }
    
    @Test
    public void crossoverTest3()
    {
        GA ga = new GA(30,48,10000,1.0f,0.0f);
        
        ga.generateCity();
        ga.run();
        
        int a = (int) (ga.getCountc()*100);
        
        assertEquals(0,a);
    }
}
