package org.come.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SellLianghaoAucExample
{
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    
    public SellLianghaoAucExample() {
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
        
        public Criteria andIdIsNull() {
            this.addCriterion("ID is null");
            return (Criteria)this;
        }
        
        public Criteria andIdIsNotNull() {
            this.addCriterion("ID is not null");
            return (Criteria)this;
        }
        
        public Criteria andIdEqualTo(BigDecimal value) {
            this.addCriterion("ID =", value, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdNotEqualTo(BigDecimal value) {
            this.addCriterion("ID <>", value, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdGreaterThan(BigDecimal value) {
            this.addCriterion("ID >", value, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("ID >=", value, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdLessThan(BigDecimal value) {
            this.addCriterion("ID <", value, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("ID <=", value, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdIn(List<BigDecimal> values) {
            this.addCriterion("ID in", values, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdNotIn(List<BigDecimal> values) {
            this.addCriterion("ID not in", values, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("ID between", value1, value2, "id");
            return (Criteria)this;
        }
        
        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("ID not between", value1, value2, "id");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdIsNull() {
            this.addCriterion("BUY_ROLE_ID is null");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdIsNotNull() {
            this.addCriterion("BUY_ROLE_ID is not null");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdEqualTo(BigDecimal value) {
            this.addCriterion("BUY_ROLE_ID =", value, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdNotEqualTo(BigDecimal value) {
            this.addCriterion("BUY_ROLE_ID <>", value, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdGreaterThan(BigDecimal value) {
            this.addCriterion("BUY_ROLE_ID >", value, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("BUY_ROLE_ID >=", value, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdLessThan(BigDecimal value) {
            this.addCriterion("BUY_ROLE_ID <", value, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("BUY_ROLE_ID <=", value, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdIn(List<BigDecimal> values) {
            this.addCriterion("BUY_ROLE_ID in", values, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdNotIn(List<BigDecimal> values) {
            this.addCriterion("BUY_ROLE_ID not in", values, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("BUY_ROLE_ID between", value1, value2, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andBuyRoleIdNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("BUY_ROLE_ID not between", value1, value2, "buyRoleId");
            return (Criteria)this;
        }
        
        public Criteria andAucPointIsNull() {
            this.addCriterion("AUC_POINT is null");
            return (Criteria)this;
        }
        
        public Criteria andAucPointIsNotNull() {
            this.addCriterion("AUC_POINT is not null");
            return (Criteria)this;
        }
        
        public Criteria andAucPointEqualTo(BigDecimal value) {
            this.addCriterion("AUC_POINT =", value, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointNotEqualTo(BigDecimal value) {
            this.addCriterion("AUC_POINT <>", value, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointGreaterThan(BigDecimal value) {
            this.addCriterion("AUC_POINT >", value, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("AUC_POINT >=", value, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointLessThan(BigDecimal value) {
            this.addCriterion("AUC_POINT <", value, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("AUC_POINT <=", value, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointIn(List<BigDecimal> values) {
            this.addCriterion("AUC_POINT in", values, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointNotIn(List<BigDecimal> values) {
            this.addCriterion("AUC_POINT not in", values, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("AUC_POINT between", value1, value2, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andAucPointNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("AUC_POINT not between", value1, value2, "aucPoint");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoIsNull() {
            this.addCriterion("LIANGHAO is null");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoIsNotNull() {
            this.addCriterion("LIANGHAO is not null");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoEqualTo(String value) {
            this.addCriterion("LIANGHAO =", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoNotEqualTo(String value) {
            this.addCriterion("LIANGHAO <>", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoGreaterThan(String value) {
            this.addCriterion("LIANGHAO >", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoGreaterThanOrEqualTo(String value) {
            this.addCriterion("LIANGHAO >=", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoLessThan(String value) {
            this.addCriterion("LIANGHAO <", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoLessThanOrEqualTo(String value) {
            this.addCriterion("LIANGHAO <=", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoLike(String value) {
            this.addCriterion("LIANGHAO like", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoNotLike(String value) {
            this.addCriterion("LIANGHAO not like", value, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoIn(List<String> values) {
            this.addCriterion("LIANGHAO in", values, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoNotIn(List<String> values) {
            this.addCriterion("LIANGHAO not in", values, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoBetween(String value1, String value2) {
            this.addCriterion("LIANGHAO between", value1, value2, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andLianghaoNotBetween(String value1, String value2) {
            this.addCriterion("LIANGHAO not between", value1, value2, "lianghao");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeIsNull() {
            this.addCriterion("BUY_TIME is null");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeIsNotNull() {
            this.addCriterion("BUY_TIME is not null");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeEqualTo(String value) {
            this.addCriterion("BUY_TIME =", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeNotEqualTo(String value) {
            this.addCriterion("BUY_TIME <>", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeGreaterThan(String value) {
            this.addCriterion("BUY_TIME >", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeGreaterThanOrEqualTo(String value) {
            this.addCriterion("BUY_TIME >=", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeLessThan(String value) {
            this.addCriterion("BUY_TIME <", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeLessThanOrEqualTo(String value) {
            this.addCriterion("BUY_TIME <=", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeLike(String value) {
            this.addCriterion("BUY_TIME like", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeNotLike(String value) {
            this.addCriterion("BUY_TIME not like", value, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeIn(List<String> values) {
            this.addCriterion("BUY_TIME in", values, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeNotIn(List<String> values) {
            this.addCriterion("BUY_TIME not in", values, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeBetween(String value1, String value2) {
            this.addCriterion("BUY_TIME between", value1, value2, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andBuyTimeNotBetween(String value1, String value2) {
            this.addCriterion("BUY_TIME not between", value1, value2, "buyTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeIsNull() {
            this.addCriterion("AUC_END_TIME is null");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeIsNotNull() {
            this.addCriterion("AUC_END_TIME is not null");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeEqualTo(String value) {
            this.addCriterion("AUC_END_TIME =", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeNotEqualTo(String value) {
            this.addCriterion("AUC_END_TIME <>", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeGreaterThan(String value) {
            this.addCriterion("AUC_END_TIME >", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeGreaterThanOrEqualTo(String value) {
            this.addCriterion("AUC_END_TIME >=", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeLessThan(String value) {
            this.addCriterion("AUC_END_TIME <", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeLessThanOrEqualTo(String value) {
            this.addCriterion("AUC_END_TIME <=", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeLike(String value) {
            this.addCriterion("AUC_END_TIME like", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeNotLike(String value) {
            this.addCriterion("AUC_END_TIME not like", value, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeIn(List<String> values) {
            this.addCriterion("AUC_END_TIME in", values, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeNotIn(List<String> values) {
            this.addCriterion("AUC_END_TIME not in", values, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeBetween(String value1, String value2) {
            this.addCriterion("AUC_END_TIME between", value1, value2, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andAucEndTimeNotBetween(String value1, String value2) {
            this.addCriterion("AUC_END_TIME not between", value1, value2, "aucEndTime");
            return (Criteria)this;
        }
        
        public Criteria andStatusIsNull() {
            this.addCriterion("STATUS is null");
            return (Criteria)this;
        }
        
        public Criteria andStatusIsNotNull() {
            this.addCriterion("STATUS is not null");
            return (Criteria)this;
        }
        
        public Criteria andStatusEqualTo(Short value) {
            this.addCriterion("STATUS =", value, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusNotEqualTo(Short value) {
            this.addCriterion("STATUS <>", value, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusGreaterThan(Short value) {
            this.addCriterion("STATUS >", value, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            this.addCriterion("STATUS >=", value, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusLessThan(Short value) {
            this.addCriterion("STATUS <", value, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusLessThanOrEqualTo(Short value) {
            this.addCriterion("STATUS <=", value, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusIn(List<Short> values) {
            this.addCriterion("STATUS in", values, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusNotIn(List<Short> values) {
            this.addCriterion("STATUS not in", values, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusBetween(Short value1, Short value2) {
            this.addCriterion("STATUS between", value1, value2, "status");
            return (Criteria)this;
        }
        
        public Criteria andStatusNotBetween(Short value1, Short value2) {
            this.addCriterion("STATUS not between", value1, value2, "status");
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
