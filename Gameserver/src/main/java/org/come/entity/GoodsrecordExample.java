package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GoodsrecordExample
{
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    
    public GoodsrecordExample() {
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
        
        public Criteria andGridIsNull() {
            this.addCriterion("GRID is null");
            return (Criteria)this;
        }
        
        public Criteria andGridIsNotNull() {
            this.addCriterion("GRID is not null");
            return (Criteria)this;
        }
        
        public Criteria andGridEqualTo(Integer value) {
            this.addCriterion("GRID =", value, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridNotEqualTo(Integer value) {
            this.addCriterion("GRID <>", value, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridGreaterThan(Integer value) {
            this.addCriterion("GRID >", value, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridGreaterThanOrEqualTo(Integer value) {
            this.addCriterion("GRID >=", value, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridLessThan(Integer value) {
            this.addCriterion("GRID <", value, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridLessThanOrEqualTo(Integer value) {
            this.addCriterion("GRID <=", value, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridIn(List<Integer> values) {
            this.addCriterion("GRID in", values, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridNotIn(List<Integer> values) {
            this.addCriterion("GRID not in", values, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridBetween(Integer value1, Integer value2) {
            this.addCriterion("GRID between", value1, value2, "grid");
            return (Criteria)this;
        }
        
        public Criteria andGridNotBetween(Integer value1, Integer value2) {
            this.addCriterion("GRID not between", value1, value2, "grid");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeIsNull() {
            this.addCriterion("RECORDTYPE is null");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeIsNotNull() {
            this.addCriterion("RECORDTYPE is not null");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeEqualTo(Integer value) {
            this.addCriterion("RECORDTYPE =", value, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeNotEqualTo(Integer value) {
            this.addCriterion("RECORDTYPE <>", value, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeGreaterThan(Integer value) {
            this.addCriterion("RECORDTYPE >", value, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeGreaterThanOrEqualTo(Integer value) {
            this.addCriterion("RECORDTYPE >=", value, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeLessThan(Integer value) {
            this.addCriterion("RECORDTYPE <", value, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeLessThanOrEqualTo(Integer value) {
            this.addCriterion("RECORDTYPE <=", value, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeIn(List<Integer> values) {
            this.addCriterion("RECORDTYPE in", values, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeNotIn(List<Integer> values) {
            this.addCriterion("RECORDTYPE not in", values, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeBetween(Integer value1, Integer value2) {
            this.addCriterion("RECORDTYPE between", value1, value2, "recordtype");
            return (Criteria)this;
        }
        
        public Criteria andRecordtypeNotBetween(Integer value1, Integer value2) {
            this.addCriterion("RECORDTYPE not between", value1, value2, "recordtype");
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
        
        public Criteria andOtherroleIsNull() {
            this.addCriterion("OTHERROLE is null");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleIsNotNull() {
            this.addCriterion("OTHERROLE is not null");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleEqualTo(BigDecimal value) {
            this.addCriterion("OTHERROLE =", value, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleNotEqualTo(BigDecimal value) {
            this.addCriterion("OTHERROLE <>", value, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleGreaterThan(BigDecimal value) {
            this.addCriterion("OTHERROLE >", value, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleGreaterThanOrEqualTo(BigDecimal value) {
            this.addCriterion("OTHERROLE >=", value, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleLessThan(BigDecimal value) {
            this.addCriterion("OTHERROLE <", value, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleLessThanOrEqualTo(BigDecimal value) {
            this.addCriterion("OTHERROLE <=", value, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleIn(List<BigDecimal> values) {
            this.addCriterion("OTHERROLE in", values, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleNotIn(List<BigDecimal> values) {
            this.addCriterion("OTHERROLE not in", values, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("OTHERROLE between", value1, value2, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andOtherroleNotBetween(BigDecimal value1, BigDecimal value2) {
            this.addCriterion("OTHERROLE not between", value1, value2, "otherrole");
            return (Criteria)this;
        }
        
        public Criteria andGoodsIsNull() {
            this.addCriterion("GOODS is null");
            return (Criteria)this;
        }
        
        public Criteria andGoodsIsNotNull() {
            this.addCriterion("GOODS is not null");
            return (Criteria)this;
        }
        
        public Criteria andGoodsEqualTo(String value) {
            this.addCriterion("GOODS =", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsNotEqualTo(String value) {
            this.addCriterion("GOODS <>", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsGreaterThan(String value) {
            this.addCriterion("GOODS >", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsGreaterThanOrEqualTo(String value) {
            this.addCriterion("GOODS >=", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsLessThan(String value) {
            this.addCriterion("GOODS <", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsLessThanOrEqualTo(String value) {
            this.addCriterion("GOODS <=", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsLike(String value) {
            this.addCriterion("GOODS like", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsNotLike(String value) {
            this.addCriterion("GOODS not like", value, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsIn(List<String> values) {
            this.addCriterion("GOODS in", values, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsNotIn(List<String> values) {
            this.addCriterion("GOODS not in", values, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsBetween(String value1, String value2) {
            this.addCriterion("GOODS between", value1, value2, "goods");
            return (Criteria)this;
        }
        
        public Criteria andGoodsNotBetween(String value1, String value2) {
            this.addCriterion("GOODS not between", value1, value2, "goods");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeIsNull() {
            this.addCriterion("RECORDTIME is null");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeIsNotNull() {
            this.addCriterion("RECORDTIME is not null");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeEqualTo(Date value) {
            this.addCriterion("RECORDTIME =", value, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeNotEqualTo(Date value) {
            this.addCriterion("RECORDTIME <>", value, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeGreaterThan(Date value) {
            this.addCriterion("RECORDTIME >", value, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeGreaterThanOrEqualTo(Date value) {
            this.addCriterion("RECORDTIME >=", value, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeLessThan(Date value) {
            this.addCriterion("RECORDTIME <", value, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeLessThanOrEqualTo(Date value) {
            this.addCriterion("RECORDTIME <=", value, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeIn(List<Date> values) {
            this.addCriterion("RECORDTIME in", values, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeNotIn(List<Date> values) {
            this.addCriterion("RECORDTIME not in", values, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeBetween(Date value1, Date value2) {
            this.addCriterion("RECORDTIME between", value1, value2, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andRecordtimeNotBetween(Date value1, Date value2) {
            this.addCriterion("RECORDTIME not between", value1, value2, "recordtime");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumIsNull() {
            this.addCriterion("GOODSNUM is null");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumIsNotNull() {
            this.addCriterion("GOODSNUM is not null");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumEqualTo(Integer value) {
            this.addCriterion("GOODSNUM =", value, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumNotEqualTo(Integer value) {
            this.addCriterion("GOODSNUM <>", value, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumGreaterThan(Integer value) {
            this.addCriterion("GOODSNUM >", value, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumGreaterThanOrEqualTo(Integer value) {
            this.addCriterion("GOODSNUM >=", value, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumLessThan(Integer value) {
            this.addCriterion("GOODSNUM <", value, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumLessThanOrEqualTo(Integer value) {
            this.addCriterion("GOODSNUM <=", value, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumIn(List<Integer> values) {
            this.addCriterion("GOODSNUM in", values, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumNotIn(List<Integer> values) {
            this.addCriterion("GOODSNUM not in", values, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumBetween(Integer value1, Integer value2) {
            this.addCriterion("GOODSNUM between", value1, value2, "goodsnum");
            return (Criteria)this;
        }
        
        public Criteria andGoodsnumNotBetween(Integer value1, Integer value2) {
            this.addCriterion("GOODSNUM not between", value1, value2, "goodsnum");
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
