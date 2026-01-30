package org.come.agent;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.AgentMapper;

public class AgentServiceImpl implements AgentService
{
    private AgentMapper agentMapper;
    
    public AgentServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.agentMapper = (AgentMapper)ctx.getBean("agentMapper");
    }
    
    @Override
    public List<Agent> selectAll() {
        return this.agentMapper.selectAll();
    }
    
    @Override
    public Agent selectById(String id) {
        return this.agentMapper.selectById(id);
    }
    
    @Override
    public Boolean deleteById(String id) {
        return this.agentMapper.deleteById(id);
    }
    
    @Override
    public Agent selectByUserName(String userName) {
        return this.agentMapper.selectByUserName(userName);
    }
    
    @Override
    public Boolean addAgent(Agent agent) {
        return this.agentMapper.addAgent(agent);
    }
    
    @Override
    public Boolean upAgentPwd(Agent agent) {
        return this.agentMapper.upAgentPwd(agent);
    }
    
    @Override
    public Boolean upAgentXyAndJf(Agent agent) {
        return this.agentMapper.upAgentXyAndJf(agent);
    }
    
    @Override
    public Boolean upAgent(Agent agent) {
        return this.agentMapper.upAgent(agent);
    }
}
