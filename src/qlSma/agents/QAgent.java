package qlSma.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;



public class QAgent extends Agent {


    private QLearning qLearning;
    private int maze[][];
    private boolean stop = false;

    @Override
    protected void setup() {

        maze =new int[][]{
                { 0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0, -1,  0},
                { 0,  0,  0,  0,  0,  0},
                {-1,  0, -1,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  1},

        };

        double[][] qTable = new double[QLearningUtils.GRID_SIZE* QLearningUtils.GRID_SIZE][QLearningUtils.ACTIONS_SIZE];
        int actions[][]= new int[][]{
                {0,-1}, //L
                {0,1}, //R
                {1,0}, //B
                {-1,0} //T
        };
        qLearning = qLearning = new QLearning(maze ,qTable,actions );



        qLearning.resetState();
        addBehaviour(new CyclicBehaviour() {
            int iteration = 0;
            int currentState;
            int nextState;
            ACLMessage message = new ACLMessage();

            @Override
            public void action() {

                if(maze[qLearning.getStateI()][qLearning.getStateJ()] == -1){
                    sendState("wall#"+qLearning.getStateI()+"#"+qLearning.getStateJ());

                }else if(maze[qLearning.getStateI()][qLearning.getStateJ()] == 1){
                    sendState("goal#"+qLearning.getStateI()+"#"+qLearning.getStateJ());
                    qLearning.resetState();
                }else{
                    sendState("path#"+qLearning.getStateI()+"#"+qLearning.getStateJ());
                }

                currentState = qLearning.getStateI()*6 + qLearning.getStateJ();
                int action = qLearning.chooseAction(currentState);
                nextState = qLearning.executeAction(action);
                int bestAction = qLearning.chooseAction(0);
                qLearning.updateQValue(currentState, action, maze[qLearning.getStateI()][qLearning.getStateJ()],nextState, bestAction);

                sendState("agent#"+qLearning.getStateI()+"#"+qLearning.getStateJ());
            }
            private void sendState(String msg){
                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                message.setContent(msg);
                AID receiver = new AID("masterAgent", AID.ISLOCALNAME);
                message.addReceiver(receiver);
                send(message);
            }
        });
    }



}
