package org.come.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CollectionExample
{
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    
    public CollectionExample() {
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
        
        public Criteria andColidIsNull() {
            this.addCriterion("COLID is null");
            return (Criteria)this;
        }
        
        public Criteria andColidIsNotNull() {
            this.addCriterion("COLID is not null");
            return (Criteria)this;
        }
        
        public Criteria andColidEqualTo(BigDecimal value) {
            this.addCriterion("COLID =", value, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidNotEqualTo(BigDecimal value) {
            this.addCriterion("COLID <>", value, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidGreaterThan(BigDecimal value) {
            this.addCriterion("COLID >", value, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("COLID >=", value, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidLessThan(BigDecimal value) {
            this.addCriterion("COLID <", value, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("COLID <=", value, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidIn(List<BigDecimal> values) {
            this.addCriterion("COLID in", values, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidNotIn(List<BigDecimal> values) {
            this.addCriterion("COLID not in", values, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("COLID between", value1, value2, "colid");
            return (Criteria)this;
        }
        
        public Criteria andColidNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("COLID not between", value1, value2, "colid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidIsNull() {
            this.addCriterion("SALEID is null");
            return (Criteria)this;
        }
        
        public Criteria andSaleidIsNotNull() {
            this.addCriterion("SALEID is not null");
            return (Criteria)this;
        }
        
        public Criteria andSaleidEqualTo(BigDecimal value) {
            this.addCriterion("SALEID =", value, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidNotEqualTo(BigDecimal value) {
            this.addCriterion("SALEID <>", value, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidGreaterThan(BigDecimal value) {
            this.addCriterion("SALEID >", value, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("SALEID >=", value, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidLessThan(BigDecimal value) {
            this.addCriterion("SALEID <", value, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("SALEID <=", value, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidIn(List<BigDecimal> values) {
            this.addCriterion("SALEID in", values, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidNotIn(List<BigDecimal> values) {
            this.addCriterion("SALEID not in", values, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("SALEID between", value1, value2, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andSaleidNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("SALEID not between", value1, value2, "saleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidIsNull() {
            this.addCriterion("ROLEID is null");
            return (Criteria)this;
        }
        
        public Criteria andRoleidIsNotNull() {
            this.addCriterion("ROLEID is not null");
            return (Criteria)this;
        }
        
        public Criteria andRoleidEqualTo(BigDecimal value) {
            this.addCriterion("ROLEID =", value, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidNotEqualTo(BigDecimal value) {
            this.addCriterion("ROLEID <>", value, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidGreaterThan(BigDecimal value) {
            this.addCriterion("ROLEID >", value, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("ROLEID >=", value, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidLessThan(BigDecimal value) {
            this.addCriterion("ROLEID <", value, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("ROLEID <=", value, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidIn(List<BigDecimal> values) {
            this.addCriterion("ROLEID in", values, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidNotIn(List<BigDecimal> values) {
            this.addCriterion("ROLEID not in", values, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("ROLEID between", value1, value2, "roleid");
            return (Criteria)this;
        }
        
        public Criteria andRoleidNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("ROLEID not between", value1, value2, "roleid");
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
