package GA;

import java.util.Arrays;
import java.util.Random;


public class GA {
    private int scale = 1000;// All possible solutions (The scale of species)
    private int cityNum = 50; // The number of cities(The length of chromosome)
    private int cityPositionScale = 500;
    private int getNum = 65535; //The number scale will use when getting genes, getting selection, getting crossover and getting mutation

    private double[][] distance = new double[cityNum][cityNum];

    private int[][] parents = new int[scale][cityNum];// The parents population, row means each individual(each path), column means how the path goes
    private int[][] childs = new int[scale][cityNum];// The childs' population

    private int[] fitness = new int[scale];
    private float[] P = new float[scale];// The possibility of each individual

    private float Pc = 0.5f; // Chance to get crossover, set 50%
    private float Pm = 0.5f;// Chance to get mutation, set 50%
    private double countc;// Times to get crossover
    private double countm;// Times to get mutation

    private int MAX_GEN = 10000; // Max generation
    private int currentG = 0;// The number of current generation
    private int bestGeneration = 0;
    private int bestLength = Integer.MAX_VALUE;
    private int[] bestPath = new int[cityNum + 1];

    private Random random = new Random();
    
    
    public GA(int scale, int cityNum ,int MAX_GEN, float Pc, float Pm){
        this.scale = scale;
        this.cityNum = cityNum;
        this.MAX_GEN = MAX_GEN;
        this.Pc = Pc;
        this.Pm = Pm;
        
        this.distance = new double[cityNum][cityNum];

        this.bestPath = new int[cityNum + 1]; 

        this.parents = new int[scale][cityNum];
        
        this.childs = new int[scale][cityNum];

        this.fitness = new int[scale];

        this.P = new float[scale];
    }

    public GA() {
        
    }
    
    public void positionToDistance(double[][] cityPosition) {
        cityNum = cityPosition.length;
        
        for(int i=0;i<cityNum;i++)
        {
            for(int j=i;j<cityNum;j++)
            {
                float dis=(float)Math.sqrt(Math.pow((cityPosition[i][0] - cityPosition[j][0]),2) + Math.pow((cityPosition[i][1] - cityPosition[j][1]),2));

                distance[i][j]=dis;
                distance[j][i]=distance[i][j];
            }
        } 
    }
    
    public void generateCity() {
        
        int[] x = new int[cityNum];
        int[] y = new int[cityNum];

        for (int i = 0; i < cityNum; i++) {
            x[i] = random.nextInt(cityPositionScale);
            y[i] = random.nextInt(cityPositionScale);
        }

        for (int i = 0; i < cityNum - 1; i++) {
            distance[i][i] = 0; 
            for (int j = i + 1; j < cityNum; j++) 
            {
                double rij = Math
                        .sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j])
                                * (y[i] - y[j]))); 
                
                int tij = (int) Math.round(rij);
                if (tij < rij) {
                    distance[i][j] = tij + 1;
                    distance[j][i] = distance[i][j];
                } 
                else 
                {
                    distance[i][j] = tij;
                    distance[j][i] = distance[i][j];
                }
            }
        }
        distance[cityNum - 1][cityNum - 1] = 0;

    }

    public void initGroup() {
        int i, j, k;
        for (k = 0; k < scale; k++) {// speices
            parents[k][0] = random.nextInt(65535) % cityNum;
            for (i = 1; i < cityNum;) {// chromosome
                parents[k][i] = random.nextInt(65535) % cityNum;
                for (j = 0; j < i; j++) {
                    if (parents[k][i] == parents[k][j]) {
                        break;
                    }
                }
                if (j == i) {
                    i++;
                }
            }
        }

    }
    
    public int calDis(int[] chromosome) {
        int len = 0;
        for (int i = 1; i < cityNum; i++) {
            len += distance[chromosome[i - 1]][chromosome[i]];
        }
        len += distance[chromosome[cityNum - 1]][chromosome[0]];//The first city
        return len;
    } /*Run Test*/

    public void countRate() {
        int d;
        double totalFitness = 0;

        double[] tempf = new double[scale];

        for (d = 0; d < scale; d++) {
            tempf[d] = 1.0 / fitness[d];
            totalFitness += tempf[d];
        }

        P[0] = (float) (tempf[0] / totalFitness);
        for (d = 1; d < scale; d++) {
            P[d] = (float) (tempf[d] / totalFitness + P[d-1]);
        }


    } /*Run Test*/

    public void selectBest() {
        int maxid;
        int maxFitness;

        maxid = 0;
        maxFitness = fitness[0];
        for (int i = 1; i < scale; i++) {
            if (maxFitness > fitness[i]) {
                maxFitness = fitness[i];
                maxid = i;
            }
        }

        if (bestLength > maxFitness) {
            bestLength = maxFitness;
            bestGeneration = currentG;
            for (int j = 0; j < cityNum; j++) {
                bestPath[j] = parents[maxid][j];
            }
        }

        for (int a = 0; a < cityNum; a++) {
            childs[0][a] = parents[maxid][a];
        }
    } /*Run Test*/

    public void rouletteSelect() {
        int i, j, selectId;
        for (i = 1; i < scale; i++) {
            float ranResult = (float) (random.nextInt(getNum) % 1000 / 1000.0);
            
            for (j = 0; j < scale; j++) {
                if (P[j] >= ranResult) {
                    break;
                }
            }
            if(j == scale) {
                selectId = j-1;
            }
            else{
                selectId = j;
            }


            for (int a = 0; a < cityNum; a++) {               
                childs[i][a] = parents[selectId][a];
            }
        }

    } /*Run Test*/

    public void Crossover(int g1, int g2) {
        int i, j, k, flag;
        int ran1, ran2;
        int temp;
        int[] Gh1 = new int[cityNum];
        int[] Gh2 = new int[cityNum];
        

        ran1 = random.nextInt(getNum) % cityNum;
        ran2 = random.nextInt(getNum) % cityNum;
        while (ran1 == ran2) {
            ran2 = random.nextInt(getNum) % cityNum;
        }

        if (ran1 > ran2) {
            temp = ran1;
            ran1 = ran2;
            ran2 = temp;
        }

        //Put genes in Chromosome1 into Chromosome2
        for (i = 0, j = ran2; j < cityNum; i++, j++) {
            Gh2[i] = childs[g1][j];
        }

        flag = i;// The start point of the original gene of Chromosome2

        for (k = 0, j = flag; j < cityNum;){
            Gh2[j] = childs[g2][k++];
            for (i = 0; i < flag; i++) {
                if (Gh2[i] == Gh2[j]) {
                    break;
                }
            }
            if (i == flag) {
                j++;
            }
        }

        flag = ran1;
        for (k = 0, j = 0; k < cityNum;) { 

            Gh1[j] = childs[g1][k++];
            for (i = 0; i < flag; i++) {
                if (childs[g2][i] == Gh1[j]) {
                    break;
                }
            }
            if (i == flag) {
                j++;
            }
        }

        flag = cityNum - ran1;

        for (i = 0, j = flag; j < cityNum; j++, i++) {
            Gh1[j] = childs[g2][i];
        }
        // Put back
        for (i = 0; i < cityNum; i++) {
            childs[g1][i] = Gh1[i];
            childs[g2][i] = Gh2[i];
        }
    } /*Run Test*/

    public void Mutation(int g) {
        int ran1, ran2, temp;
        ran1 = random.nextInt(getNum) % cityNum;
        ran2 = random.nextInt(getNum) % cityNum;
        while (ran1 == ran2) {
            ran2 = random.nextInt(getNum) % cityNum;
        }
        temp = childs[g][ran1];
        childs[g][ran1] = childs[g][ran2];
        childs[g][ran2] = temp;
        
    } /*Run Test*/

    public void evolution() {
        selectBest();
        rouletteSelect();

        float rate1 = random.nextFloat();
        if (rate1 > Pc) {
            int id1 = random.nextInt(scale);
            int id2 = random.nextInt(scale);
            while (id1 == id2) id2 = random.nextInt(scale);
            Crossover(id1, id2);
            countc++;
        }

        float rate2 = random.nextFloat();
        if (rate2 > Pm) {
            int id3 = random.nextInt(scale);
            Mutation(id3);
            countm++;
        }

    } /*Run Test*/

    public void run() {
        int i;
        int k;

        initGroup();
        
        for (k = 0; k < scale; k++) {
            fitness[k] = calDis(parents[k]);
            
        }
        
        countRate();
        System.out.println("The Init Group :");
        for (k = 0; k < scale; k++) {
            for (i = 0; i < cityNum; i++) {
                System.out.print(parents[k][i] + ",");
            }
            System.out.println();
            System.out.println("Fitness: " + fitness[k] + " " + P[k]);
        }

        for (currentG = 0; currentG < MAX_GEN; currentG++) {
            evolution();
            for (k = 0; k < scale; k++) {
                for (i = 0; i < cityNum; i++) {
                    parents[k][i] = childs[k][i];
                }
            }
            for (k = 0; k < scale; k++) {
                fitness[k] = calDis(parents[k]);
            }
            countRate();
        }

        System.out.println("The Last Group: ");
        for (k = 0; k < scale; k++) {
            for (i = 0; i < cityNum; i++) {
                System.out.print(parents[k][i] + ",");
            }
            System.out.println();
            System.out.println("Fitness: " + fitness[k] + "," + P[k]);
        }

        System.out.println("BestGeneration:");
        System.out.println(bestGeneration);
        System.out.println("BestLength:");
        System.out.println(bestLength);
        System.out.println("BestPath:");
        for (i = 0; i < cityNum; i++) {
            System.out.print(bestPath[i] + ",");
        }

    } /*Run Test*/

    /* Below is written for the test*/
    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getCityNum() {
        return cityNum;
    }

    public void setCityNum(int cityNum) {
        this.cityNum = cityNum;
    }

    public int getMAX_GEN() {
        return MAX_GEN;
    }

    public void setMAX_GEN(int MAX_GEN) {
        this.MAX_GEN = MAX_GEN;
    }

    public int getBestGeneration() {
        return bestGeneration;
    }

    public void setBestGeneration(int bestGeneration) {
        this.bestGeneration = bestGeneration;
    }

    public int getBestLength() {
        return bestLength;
    }

    public void setBestLength(int bestLength) {
        this.bestLength = bestLength;
    }

    public int[] getBestPath() {
        return bestPath;
    }

    public void setBestPath(int[] bestPath) {
        this.bestPath = bestPath;
    }

    public int[][] getParents() {
        return parents;
    }

    public void setParents(int[][] parents) {
        this.parents = parents;
    }

    public int[][] getChilds() {
        return childs;
    }

    public void setChilds(int[][] childs) {
        this.childs = childs;
    }

    public int[] getFitness() {
        return fitness;
    }

    public void setFitness(int[] fitness) {
        this.fitness = fitness;
    }

    public float[] getP() {
        return P;
    }

    public void setP(float[] P) {
        this.P = P;
    }

    public float getPc() {
        return Pc;
    }

    public void setPc(float Pc) {
        this.Pc = Pc;
    }

    public float getPm() {
        return Pm;
    }

    public void setPm(float Pm) {
        this.Pm = Pm;
    }

    public int getCurrentG() {
        return currentG;
    }

    public void setCurrentG(int currentG) {
        this.currentG = currentG;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public double[][] getDistance() {
        return distance;
    }

    public void setDistance(double[][] distance) {
        this.distance = distance;
    }

    public double getCountc() {
        return countc/MAX_GEN;
    }

    public void setCountc(int countc) {
        this.countc = countc;
    }

    public double getCountm() {
        return countm/MAX_GEN;
    }

    public void setCountm(int countm) {
        this.countm = countm;
    }

}
