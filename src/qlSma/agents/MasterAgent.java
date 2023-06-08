package qlSma.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class MasterAgent extends Agent {


    @Override
    protected void setup() {

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message=receive();
                if(message!=null){

                    String content = message.getContent();
                    String[] items = content.split("#");
                    String state = items[0];
                    int i = Integer.parseInt(items[1]);
                    int j = Integer.parseInt(items[2]);
                    System.out.println(content);
                    System.out.println("--------------------------------------------------");

                }else {
                    block();
                }
            }
        });
    }

}
