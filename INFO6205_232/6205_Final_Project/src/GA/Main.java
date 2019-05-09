package GA;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Start....");
        GA ga = new GA();
        ga.generateCity();
        ga.run();
    }


}
