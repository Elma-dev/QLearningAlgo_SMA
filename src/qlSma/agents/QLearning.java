package qlSma.agents;

import java.util.Arrays;
import java.util.Random;

public class QLearning {

    private int[][] grid ;
    private double[][] qTable ;
    private int[][] actions;
    private int stateI;
    private int stateJ;

    public QLearning() {}
    public QLearning(int[][] grid, double[][] qTable, int[][] actions) {
        this.actions = actions;
        this.qTable = qTable;
        this.grid = grid;
    }
    public void resetState(){
        stateI = 0;
        stateJ = 0;
    }

    public int chooseAction(double eps){
        Random random = new Random();
        double bestQ = 0;
        int act = 0;
        if(random.nextDouble() < eps){
            // Exploration
            act = random.nextInt(QLearningUtils.ACTIONS_SIZE);
        }else{
            // Exploiatation
            int st = stateI* QLearningUtils.GRID_SIZE + stateJ;
            for (int i = 0; i < QLearningUtils.ACTIONS_SIZE; i++) {
                if(qTable[st][i] > bestQ){
                    bestQ = qTable[st][i];
                    act = i;
                }
            }
        }
        return act;
    }
    public int executeAction(int act){
        stateI = Math.max(0, Math.min(actions[act][0]+stateI, QLearningUtils.GRID_SIZE-1));
        stateJ = Math.max(0, Math.min(actions[act][1]+stateJ, QLearningUtils.GRID_SIZE-1));
//        System.out.println("Action : ("+stateI+" , "+stateJ+")");
        return stateI* QLearningUtils.GRID_SIZE + stateJ;
    }
    public boolean isFinished(){
        return grid[this.stateI][this.stateJ] == 1;
    }

    public void showResult(){
        System.out.println(">>>>>>>>>>> QTable");
        for(double []line: qTable){
            System.out.println(Arrays.toString(line));
        }
        resetState();
        while (!isFinished()){
            int act = chooseAction(0);
            System.out.println("State : "+(stateI* QLearningUtils.GRID_SIZE+stateJ)+" action : "+act);
            executeAction(act);
        }
        System.out.println("Final State: "+(stateI* QLearningUtils.GRID_SIZE+stateJ));
    }

    public void updateQValue(int state, int action, double reward, int nextState , int bestAction) {
        double currentQValue = qTable[state][action];
        double maxNextQValue = qTable[nextState][bestAction];
        double newQValue = currentQValue + QLearningUtils.ALPHA * (reward + QLearningUtils.GAMMA * maxNextQValue - currentQValue);
        qTable[state][action] = newQValue;
    }

    public int[][] getGrid() {
        return grid;
    }

    public double[][] getqTable() {
        return qTable;
    }

    public int[][] getActions() {
        return actions;
    }

    public int getStateI() {
        return stateI;
    }

    public int getStateJ() {
        return stateJ;
    }
}
