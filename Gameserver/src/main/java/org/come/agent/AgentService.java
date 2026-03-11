package org.come.agent;

import java.util.List;

/**
 * 代理后台服务接口。
 */
public interface AgentService
{
    List<Agent> selectAll();
    
    Agent selectById(String id);
    
    Boolean deleteById(String id);
    
    Agent selectByUserName(String userName);
    
    Boolean addAgent(Agent agent);
    
    Boolean upAgent(Agent agent);
    
    Boolean upAgentPwd(Agent agent);
    
    Boolean upAgentXyAndJf(Agent agent);
}
