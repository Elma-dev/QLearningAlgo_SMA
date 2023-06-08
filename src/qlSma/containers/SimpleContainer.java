package qlSma.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import qlSma.agents.MasterAgent;
import qlSma.agents.QAgent;


public class SimpleContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST,"localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController mainAgent=null;
        for (int i=0;i< 3;i++){
            mainAgent = agentContainer.createNewAgent("agent"+String.valueOf(i), QAgent.class.getName(), new Object[]{});
            mainAgent.start();
        }

        mainAgent=agentContainer.createNewAgent("masterAgent", MasterAgent.class.getName(),new Object[]{});
        mainAgent.start();

    }
}
