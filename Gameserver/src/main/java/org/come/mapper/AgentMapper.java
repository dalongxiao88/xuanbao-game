package org.come.mapper;

import org.come.agent.Agent;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
/**
 * 代理后台 Mapper。
 */
public interface AgentMapper
{
    List<Agent> selectAll();
    
    Agent selectById(String id);
    
    Boolean deleteById(String id);
    
    Agent selectByUserName(String userName);
    
    Boolean addAgent(Agent agent);
    
    Boolean upAgentPwd(Agent agent);
    
    Boolean upAgent(Agent agent);
    
    Boolean upAgentXyAndJf(Agent agent);
}
