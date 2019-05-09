/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA_Test.Evolution;

import GA.GA;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author 15142087777的AW
 */
public class MutationTest {
            
    @Test
    public void mutationTest1()
    {
        GA ga = new GA(30,48,10000,0.5f,0.5f);
        
        ga.generateCity();
        ga.run();
        
        double a = (ga.getCountm()*100);
        
        assertEquals(1 , a/50 , 0.1);
    }
    
    @Test
    public void mutationTest2()
    {
        GA ga = new GA(30,48,10000,0.5f,0.0f);
        
        ga.generateCity();
        ga.run();
        
        int a = (int) (ga.getCountm()*10);
        
        assertEquals(10,a);
    }
    
    @Test
    public void mutationTest3()
    {
        GA ga = new GA(30,48,10000,0.5f,1.0f);
        
        ga.generateCity();
        ga.run();
        
        int a = (int) (ga.getCountm()*100);
        
    }
}
