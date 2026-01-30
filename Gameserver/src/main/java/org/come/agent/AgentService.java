package org.come.agent;

import java.util.List;

public interface AgentService
{
    List<Agent> selectAll();
    
    Agent selectById(String p0);
    
    Boolean deleteById(String p0);
    
    Agent selectByUserName(String p0);
    
    Boolean addAgent(Agent p0);
    
    Boolean upAgent(Agent p0);
    
    Boolean upAgentPwd(Agent p0);
    
    Boolean upAgentXyAndJf(Agent p0);
}
