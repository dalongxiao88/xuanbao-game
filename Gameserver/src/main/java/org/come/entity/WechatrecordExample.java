package org.come.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WechatrecordExample
{
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    
    public WechatrecordExample() {
        this.oredCriteria = new ArrayList<>();
    }
    
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    public String getOrderByClause() {
        return this.orderByClause;
    }
    
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
    
    public boolean isDistinct() {
        return this.distinct;
    }
    
    public List<Criteria> getOredCriteria() {
        return this.oredCriteria;
    }
    
    public void or(Criteria criteria) {
        this.oredCriteria.add(criteria);
    }
    
    public Criteria or() {
        Criteria criteria = this.createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }
    
    public Criteria createCriteria() {
        Criteria criteria = this.createCriteriaInternal();
        if (this.oredCriteria.size() == 0) {
            this.oredCriteria.add(criteria);
        }
        return criteria;
    }
    
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }
    
    public void clear() {
        this.oredCriteria.clear();
        this.orderByClause = null;
        this.distinct = false;
    }
    
    protected abstract static class GeneratedCriteria
    {
        protected List<Criterion> criteria;
        
        protected GeneratedCriteria() {
            this.criteria = new ArrayList<>();
        }
        
        public boolean isValid() {
            return this.criteria.size() > 0;
        }
        
        public List<Criterion> getAllCriteria() {
            return this.criteria;
        }
        
        public List<Criterion> getCriteria() {
            return this.criteria;
        }
        
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            this.criteria.add(new Criterion(condition));
        }
        
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            this.criteria.add(new Criterion(condition, value));
        }
        
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            this.criteria.add(new Criterion(condition, value1, value2));
        }
        
        public Criteria andChatIdIsNull() {
            this.addCriterion("CHAT_ID is null");
            return (Criteria)this;
        }
        
        public Criteria andChatIdIsNotNull() {
            this.addCriterion("CHAT_ID is not null");
            return (Criteria)this;
        }
        
        public Criteria andChatIdEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_ID =", value, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdNotEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_ID <>", value, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdGreaterThan(BigDecimal value) {
            this.addCriterion("CHAT_ID >", value, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_ID >=", value, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdLessThan(BigDecimal value) {
            this.addCriterion("CHAT_ID <", value, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_ID <=", value, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdIn(List<BigDecimal> values) {
            this.addCriterion("CHAT_ID in", values, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdNotIn(List<BigDecimal> values) {
            this.addCriterion("CHAT_ID not in", values, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("CHAT_ID between", value1, value2, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatIdNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("CHAT_ID not between", value1, value2, "chatId");
            return (Criteria)this;
        }
        
        public Criteria andChatMesIsNull() {
            this.addCriterion("CHAT_MES is null");
            return (Criteria)this;
        }
        
        public Criteria andChatMesIsNotNull() {
            this.addCriterion("CHAT_MES is not null");
            return (Criteria)this;
        }
        
        public Criteria andChatMesEqualTo(String value) {
            this.addCriterion("CHAT_MES =", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesNotEqualTo(String value) {
            this.addCriterion("CHAT_MES <>", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesGreaterThan(String value) {
            this.addCriterion("CHAT_MES >", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesGreaterThanOrEqualTo(String value) {
            this.addCriterion("CHAT_MES >=", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesLessThan(String value) {
            this.addCriterion("CHAT_MES <", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesLessThanOrEqualTo(String value) {
            this.addCriterion("CHAT_MES <=", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesLike(String value) {
            this.addCriterion("CHAT_MES like", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesNotLike(String value) {
            this.addCriterion("CHAT_MES not like", value, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesIn(List<String> values) {
            this.addCriterion("CHAT_MES in", values, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesNotIn(List<String> values) {
            this.addCriterion("CHAT_MES not in", values, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesBetween(String value1, String value2) {
            this.addCriterion("CHAT_MES between", value1, value2, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatMesNotBetween(String value1, String value2) {
            this.addCriterion("CHAT_MES not between", value1, value2, "chatMes");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidIsNull() {
            this.addCriterion("CHAT_SENDID is null");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidIsNotNull() {
            this.addCriterion("CHAT_SENDID is not null");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_SENDID =", value, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidNotEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_SENDID <>", value, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidGreaterThan(BigDecimal value) {
            this.addCriterion("CHAT_SENDID >", value, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_SENDID >=", value, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidLessThan(BigDecimal value) {
            this.addCriterion("CHAT_SENDID <", value, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_SENDID <=", value, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidIn(List<BigDecimal> values) {
            this.addCriterion("CHAT_SENDID in", values, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidNotIn(List<BigDecimal> values) {
            this.addCriterion("CHAT_SENDID not in", values, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("CHAT_SENDID between", value1, value2, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatSendidNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("CHAT_SENDID not between", value1, value2, "chatSendid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidIsNull() {
            this.addCriterion("CHAT_GETID is null");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidIsNotNull() {
            this.addCriterion("CHAT_GETID is not null");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_GETID =", value, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidNotEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_GETID <>", value, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidGreaterThan(BigDecimal value) {
            this.addCriterion("CHAT_GETID >", value, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_GETID >=", value, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidLessThan(BigDecimal value) {
            this.addCriterion("CHAT_GETID <", value, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("CHAT_GETID <=", value, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidIn(List<BigDecimal> values) {
            this.addCriterion("CHAT_GETID in", values, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidNotIn(List<BigDecimal> values) {
            this.addCriterion("CHAT_GETID not in", values, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("CHAT_GETID between", value1, value2, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andChatGetidNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("CHAT_GETID not between", value1, value2, "chatGetid");
            return (Criteria)this;
        }
        
        public Criteria andTimeIsNull() {
            this.addCriterion("TIME is null");
            return (Criteria)this;
        }
        
        public Criteria andTimeIsNotNull() {
            this.addCriterion("TIME is not null");
            return (Criteria)this;
        }
        
        public Criteria andTimeEqualTo(String value) {
            this.addCriterion("TIME =", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeNotEqualTo(String value) {
            this.addCriterion("TIME <>", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeGreaterThan(String value) {
            this.addCriterion("TIME >", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            this.addCriterion("TIME >=", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeLessThan(String value) {
            this.addCriterion("TIME <", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeLessThanOrEqualTo(String value) {
            this.addCriterion("TIME <=", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeLike(String value) {
            this.addCriterion("TIME like", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeNotLike(String value) {
            this.addCriterion("TIME not like", value, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeIn(List<String> values) {
            this.addCriterion("TIME in", values, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeNotIn(List<String> values) {
            this.addCriterion("TIME not in", values, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeBetween(String value1, String value2) {
            this.addCriterion("TIME between", value1, value2, "time");
            return (Criteria)this;
        }
        
        public Criteria andTimeNotBetween(String value1, String value2) {
            this.addCriterion("TIME not between", value1, value2, "time");
            return (Criteria)this;
        }
    }
    
    public static class Criteria extends GeneratedCriteria
    {
        protected Criteria() {
        }
    }
    
    public static class Criterion
    {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;
        
        public String getCondition() {
            return this.condition;
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public Object getSecondValue() {
            return this.secondValue;
        }
        
        public boolean isNoValue() {
            return this.noValue;
        }
        
        public boolean isSingleValue() {
            return this.singleValue;
        }
        
        public boolean isBetweenValue() {
            return this.betweenValue;
        }
        
        public boolean isListValue() {
            return this.listValue;
        }
        
        public String getTypeHandler() {
            return this.typeHandler;
        }
        
        protected Criterion(String condition) {
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }
        
        protected Criterion(String condition, Object value, String typeHandler) {
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List) {
                this.listValue = true;
            }
            else {
                this.singleValue = true;
            }
        }
        
        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }
        
        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }
        
        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
