package org.come.until;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContextAware;

public class MybatisUntil implements ApplicationContextAware, DisposableBean
{
    private static ApplicationContext applicationContext;
    private static Logger logger;
    
    public static ApplicationContext getApplicationContext() {
        if (MybatisUntil.applicationContext == null) {
            MybatisUntil.applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        }
        return MybatisUntil.applicationContext;
    }
    
    @Override
    public void destroy() throws Exception {
    }
    
    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
    }
    
    static {
        MybatisUntil.applicationContext = null;
        MybatisUntil.logger = LoggerFactory.getLogger(MybatisUntil.class);
    }
}
