package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RewardHallExample
{
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    
    public RewardHallExample() {
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
        
        public Criteria andGoodstableIsNull() {
            this.addCriterion("Goodstable is null");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableIsNotNull() {
            this.addCriterion("Goodstable is not null");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableEqualTo(String value) {
            this.addCriterion("Goodstable =", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableNotEqualTo(String value) {
            this.addCriterion("Goodstable <>", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableGreaterThan(String value) {
            this.addCriterion("Goodstable >", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableGreaterThanOrEqualTo(String value) {
            this.addCriterion("Goodstable >=", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableLessThan(String value) {
            this.addCriterion("Goodstable <", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableLessThanOrEqualTo(String value) {
            this.addCriterion("Goodstable <=", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableLike(String value) {
            this.addCriterion("Goodstable like", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableNotLike(String value) {
            this.addCriterion("Goodstable not like", value, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableIn(List<String> values) {
            this.addCriterion("Goodstable in", values, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableNotIn(List<String> values) {
            this.addCriterion("Goodstable not in", values, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableBetween(String value1, String value2) {
            this.addCriterion("Goodstable between", value1, value2, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodstableNotBetween(String value1, String value2) {
            this.addCriterion("Goodstable not between", value1, value2, "goodstable");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumIsNull() {
            this.addCriterion("Goodnum is null");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumIsNotNull() {
            this.addCriterion("Goodnum is not null");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumEqualTo(BigDecimal value) {
            this.addCriterion("Goodnum =", value, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumNotEqualTo(BigDecimal value) {
            this.addCriterion("Goodnum <>", value, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumGreaterThan(BigDecimal value) {
            this.addCriterion("Goodnum >", value, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("Goodnum >=", value, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumLessThan(BigDecimal value) {
            this.addCriterion("Goodnum <", value, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("Goodnum <=", value, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumIn(List<BigDecimal> values) {
            this.addCriterion("Goodnum in", values, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumNotIn(List<BigDecimal> values) {
            this.addCriterion("Goodnum not in", values, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("Goodnum between", value1, value2, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodnumNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("Goodnum not between", value1, value2, "goodnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceIsNull() {
            this.addCriterion("Goodprice is null");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceIsNotNull() {
            this.addCriterion("Goodprice is not null");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceEqualTo(BigDecimal value) {
            this.addCriterion("Goodprice =", value, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceNotEqualTo(BigDecimal value) {
            this.addCriterion("Goodprice <>", value, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceGreaterThan(BigDecimal value) {
            this.addCriterion("Goodprice >", value, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("Goodprice >=", value, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceLessThan(BigDecimal value) {
            this.addCriterion("Goodprice <", value, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("Goodprice <=", value, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceIn(List<BigDecimal> values) {
            this.addCriterion("Goodprice in", values, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceNotIn(List<BigDecimal> values) {
            this.addCriterion("Goodprice not in", values, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("Goodprice between", value1, value2, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andGoodpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("Goodprice not between", value1, value2, "goodprice");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdIsNull() {
            this.addCriterion("Role_ID is null");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdIsNotNull() {
            this.addCriterion("Role_ID is not null");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdEqualTo(BigDecimal value) {
            this.addCriterion("Role_ID =", value, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdNotEqualTo(BigDecimal value) {
            this.addCriterion("Role_ID <>", value, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdGreaterThan(BigDecimal value) {
            this.addCriterion("Role_ID >", value, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("Role_ID >=", value, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdLessThan(BigDecimal value) {
            this.addCriterion("Role_ID <", value, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("Role_ID <=", value, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdIn(List<BigDecimal> values) {
            this.addCriterion("Role_ID in", values, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdNotIn(List<BigDecimal> values) {
            this.addCriterion("Role_ID not in", values, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("Role_ID between", value1, value2, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andRoleIdNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("Role_ID not between", value1, value2, "roleId");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeIsNull() {
            this.addCriterion("ThrowTime is null");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeIsNotNull() {
            this.addCriterion("ThrowTime is not null");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeEqualTo(Date value) {
            this.addCriterion("ThrowTime =", value, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeNotEqualTo(Date value) {
            this.addCriterion("ThrowTime <>", value, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeGreaterThan(Date value) {
            this.addCriterion("ThrowTime >", value, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeGreaterThanOrEqualTo(Date value) {
            this.addCriterion("ThrowTime >=", value, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeLessThan(Date value) {
            this.addCriterion("ThrowTime <", value, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeLessThanOrEqualTo(Date value) {
            this.addCriterion("ThrowTime <=", value, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeIn(List<Date> values) {
            this.addCriterion("ThrowTime in", values, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeNotIn(List<Date> values) {
            this.addCriterion("ThrowTime not in", values, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeBetween(Date value1, Date value2) {
            this.addCriterion("ThrowTime between", value1, value2, "throwtime");
            return (Criteria)this;
        }
        
        public Criteria andThrowtimeNotBetween(Date value1, Date value2) {
            this.addCriterion("ThrowTime not between", value1, value2, "throwtime");
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
