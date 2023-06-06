
import java.util.Random;

public class QLearning {

    // Q Learning Utils
    private final double ALPHA = 0.1;
    private final double GAMMA = 0.9;
    private final int MAX_EPOCH = 200;
    private final int GRID_SIZE = 3;
    private final int ACTIONS_SIZE = 4;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private double[][] qTable = new double[GRID_SIZE*GRID_SIZE][ACTIONS_SIZE];
    private int[][] actions = new int[ACTIONS_SIZE][2];
    private int stateI;
    private int stateJ;

    public QLearning() {
        actions = new int[][]{
                {0,-1}, //G
                {0,1}, //D
                {1,0}, //B
                {-1,0} //H
        };

        grid = new int[][]{
                {0,0,1},
                {0,-1,0},
                {0,0,0}
        };
    }

    public void run(){
        int it = 0;
        resetState();
        int currentState;
        int nextState;

        while (it<30){
            currentState = stateI*GRID_SIZE + stateJ;
            int act = chooseAction(currentState);
            nextState = executeAction(act);

            System.out.println(stateI + " " + stateJ + " " + grid[stateI][stateJ]);

            it++;
        }
    }

    private void resetState(){
        stateI = 2;
        stateJ = 0;
    }

    private int chooseAction(double eps){
        Random random = new Random();
        double bestQ = 0;
        int act = 0;
        if(random.nextDouble() < eps){
            // Exploration
            act = random.nextInt(ACTIONS_SIZE);
        }else{
            // Exploiatation
            int st = stateI*GRID_SIZE + stateJ;
            for (int i = 0; i < ACTIONS_SIZE; i++) {
                if(qTable[st][i] > bestQ){
                    bestQ = qTable[st][i];
                    act = i;
                }
            }
        }
        return act;
    }

    private int executeAction(int act){
        stateI = Math.max(0, Math.min(actions[act][0]+stateI, 2));
        stateJ = Math.max(0, Math.min(actions[act][1]+stateJ, 2));
        return stateI*GRID_SIZE + stateJ;
    }
}