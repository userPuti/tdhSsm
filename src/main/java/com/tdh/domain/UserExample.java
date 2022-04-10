package com.tdh.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andYhidIsNull() {
            addCriterion("YHID is null");
            return (Criteria) this;
        }

        public Criteria andYhidIsNotNull() {
            addCriterion("YHID is not null");
            return (Criteria) this;
        }

        public Criteria andYhidEqualTo(String value) {
            addCriterion("YHID =", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidNotEqualTo(String value) {
            addCriterion("YHID <>", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidGreaterThan(String value) {
            addCriterion("YHID >", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidGreaterThanOrEqualTo(String value) {
            addCriterion("YHID >=", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidLessThan(String value) {
            addCriterion("YHID <", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidLessThanOrEqualTo(String value) {
            addCriterion("YHID <=", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidLike(String value) {
            addCriterion("YHID like", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidNotLike(String value) {
            addCriterion("YHID not like", value, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidIn(List<String> values) {
            addCriterion("YHID in", values, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidNotIn(List<String> values) {
            addCriterion("YHID not in", values, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidBetween(String value1, String value2) {
            addCriterion("YHID between", value1, value2, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhidNotBetween(String value1, String value2) {
            addCriterion("YHID not between", value1, value2, "yhid");
            return (Criteria) this;
        }

        public Criteria andYhxmIsNull() {
            addCriterion("YHXM is null");
            return (Criteria) this;
        }

        public Criteria andYhxmIsNotNull() {
            addCriterion("YHXM is not null");
            return (Criteria) this;
        }

        public Criteria andYhxmEqualTo(String value) {
            addCriterion("YHXM =", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmNotEqualTo(String value) {
            addCriterion("YHXM <>", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmGreaterThan(String value) {
            addCriterion("YHXM >", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmGreaterThanOrEqualTo(String value) {
            addCriterion("YHXM >=", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmLessThan(String value) {
            addCriterion("YHXM <", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmLessThanOrEqualTo(String value) {
            addCriterion("YHXM <=", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmLike(String value) {
            addCriterion("YHXM like", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmNotLike(String value) {
            addCriterion("YHXM not like", value, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmIn(List<String> values) {
            addCriterion("YHXM in", values, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmNotIn(List<String> values) {
            addCriterion("YHXM not in", values, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmBetween(String value1, String value2) {
            addCriterion("YHXM between", value1, value2, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhxmNotBetween(String value1, String value2) {
            addCriterion("YHXM not between", value1, value2, "yhxm");
            return (Criteria) this;
        }

        public Criteria andYhklIsNull() {
            addCriterion("YHKL is null");
            return (Criteria) this;
        }

        public Criteria andYhklIsNotNull() {
            addCriterion("YHKL is not null");
            return (Criteria) this;
        }

        public Criteria andYhklEqualTo(String value) {
            addCriterion("YHKL =", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklNotEqualTo(String value) {
            addCriterion("YHKL <>", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklGreaterThan(String value) {
            addCriterion("YHKL >", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklGreaterThanOrEqualTo(String value) {
            addCriterion("YHKL >=", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklLessThan(String value) {
            addCriterion("YHKL <", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklLessThanOrEqualTo(String value) {
            addCriterion("YHKL <=", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklLike(String value) {
            addCriterion("YHKL like", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklNotLike(String value) {
            addCriterion("YHKL not like", value, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklIn(List<String> values) {
            addCriterion("YHKL in", values, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklNotIn(List<String> values) {
            addCriterion("YHKL not in", values, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklBetween(String value1, String value2) {
            addCriterion("YHKL between", value1, value2, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhklNotBetween(String value1, String value2) {
            addCriterion("YHKL not between", value1, value2, "yhkl");
            return (Criteria) this;
        }

        public Criteria andYhxbIsNull() {
            addCriterion("YHXB is null");
            return (Criteria) this;
        }

        public Criteria andYhxbIsNotNull() {
            addCriterion("YHXB is not null");
            return (Criteria) this;
        }

        public Criteria andYhxbEqualTo(String value) {
            addCriterion("YHXB =", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbNotEqualTo(String value) {
            addCriterion("YHXB <>", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbGreaterThan(String value) {
            addCriterion("YHXB >", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbGreaterThanOrEqualTo(String value) {
            addCriterion("YHXB >=", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbLessThan(String value) {
            addCriterion("YHXB <", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbLessThanOrEqualTo(String value) {
            addCriterion("YHXB <=", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbLike(String value) {
            addCriterion("YHXB like", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbNotLike(String value) {
            addCriterion("YHXB not like", value, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbIn(List<String> values) {
            addCriterion("YHXB in", values, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbNotIn(List<String> values) {
            addCriterion("YHXB not in", values, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbBetween(String value1, String value2) {
            addCriterion("YHXB between", value1, value2, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhxbNotBetween(String value1, String value2) {
            addCriterion("YHXB not between", value1, value2, "yhxb");
            return (Criteria) this;
        }

        public Criteria andYhbmIsNull() {
            addCriterion("YHBM is null");
            return (Criteria) this;
        }

        public Criteria andYhbmIsNotNull() {
            addCriterion("YHBM is not null");
            return (Criteria) this;
        }

        public Criteria andYhbmEqualTo(String value) {
            addCriterion("YHBM =", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmNotEqualTo(String value) {
            addCriterion("YHBM <>", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmGreaterThan(String value) {
            addCriterion("YHBM >", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmGreaterThanOrEqualTo(String value) {
            addCriterion("YHBM >=", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmLessThan(String value) {
            addCriterion("YHBM <", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmLessThanOrEqualTo(String value) {
            addCriterion("YHBM <=", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmLike(String value) {
            addCriterion("YHBM like", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmNotLike(String value) {
            addCriterion("YHBM not like", value, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmIn(List<String> values) {
            addCriterion("YHBM in", values, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmNotIn(List<String> values) {
            addCriterion("YHBM not in", values, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmBetween(String value1, String value2) {
            addCriterion("YHBM between", value1, value2, "yhbm");
            return (Criteria) this;
        }

        public Criteria andYhbmNotBetween(String value1, String value2) {
            addCriterion("YHBM not between", value1, value2, "yhbm");
            return (Criteria) this;
        }

        public Criteria andCsrqIsNull() {
            addCriterion("CSRQ is null");
            return (Criteria) this;
        }

        public Criteria andCsrqIsNotNull() {
            addCriterion("CSRQ is not null");
            return (Criteria) this;
        }

        public Criteria andCsrqEqualTo(String value) {
            addCriterion("CSRQ =", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotEqualTo(String value) {
            addCriterion("CSRQ <>", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqGreaterThan(String value) {
            addCriterion("CSRQ >", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqGreaterThanOrEqualTo(String value) {
            addCriterion("CSRQ >=", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqLessThan(String value) {
            addCriterion("CSRQ <", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqLessThanOrEqualTo(String value) {
            addCriterion("CSRQ <=", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqLike(String value) {
            addCriterion("CSRQ like", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotLike(String value) {
            addCriterion("CSRQ not like", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqIn(List<String> values) {
            addCriterion("CSRQ in", values, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotIn(List<String> values) {
            addCriterion("CSRQ not in", values, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqBetween(String value1, String value2) {
            addCriterion("CSRQ between", value1, value2, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotBetween(String value1, String value2) {
            addCriterion("CSRQ not between", value1, value2, "csrq");
            return (Criteria) this;
        }

        public Criteria andDjrqIsNull() {
            addCriterion("DJRQ is null");
            return (Criteria) this;
        }

        public Criteria andDjrqIsNotNull() {
            addCriterion("DJRQ is not null");
            return (Criteria) this;
        }

        public Criteria andDjrqEqualTo(String value) {
            addCriterion("DJRQ =", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqNotEqualTo(String value) {
            addCriterion("DJRQ <>", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqGreaterThan(String value) {
            addCriterion("DJRQ >", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqGreaterThanOrEqualTo(String value) {
            addCriterion("DJRQ >=", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqLessThan(String value) {
            addCriterion("DJRQ <", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqLessThanOrEqualTo(String value) {
            addCriterion("DJRQ <=", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqLike(String value) {
            addCriterion("DJRQ like", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqNotLike(String value) {
            addCriterion("DJRQ not like", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqIn(List<String> values) {
            addCriterion("DJRQ in", values, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqNotIn(List<String> values) {
            addCriterion("DJRQ not in", values, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqBetween(String value1, String value2) {
            addCriterion("DJRQ between", value1, value2, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqNotBetween(String value1, String value2) {
            addCriterion("DJRQ not between", value1, value2, "djrq");
            return (Criteria) this;
        }

        public Criteria andSfjyIsNull() {
            addCriterion("SFJY is null");
            return (Criteria) this;
        }

        public Criteria andSfjyIsNotNull() {
            addCriterion("SFJY is not null");
            return (Criteria) this;
        }

        public Criteria andSfjyEqualTo(String value) {
            addCriterion("SFJY =", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotEqualTo(String value) {
            addCriterion("SFJY <>", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyGreaterThan(String value) {
            addCriterion("SFJY >", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyGreaterThanOrEqualTo(String value) {
            addCriterion("SFJY >=", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyLessThan(String value) {
            addCriterion("SFJY <", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyLessThanOrEqualTo(String value) {
            addCriterion("SFJY <=", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyLike(String value) {
            addCriterion("SFJY like", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotLike(String value) {
            addCriterion("SFJY not like", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyIn(List<String> values) {
            addCriterion("SFJY in", values, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotIn(List<String> values) {
            addCriterion("SFJY not in", values, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyBetween(String value1, String value2) {
            addCriterion("SFJY between", value1, value2, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotBetween(String value1, String value2) {
            addCriterion("SFJY not between", value1, value2, "sfjy");
            return (Criteria) this;
        }

        public Criteria andPxhIsNull() {
            addCriterion("PXH is null");
            return (Criteria) this;
        }

        public Criteria andPxhIsNotNull() {
            addCriterion("PXH is not null");
            return (Criteria) this;
        }

        public Criteria andPxhEqualTo(Integer value) {
            addCriterion("PXH =", value, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhNotEqualTo(Integer value) {
            addCriterion("PXH <>", value, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhGreaterThan(Integer value) {
            addCriterion("PXH >", value, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhGreaterThanOrEqualTo(Integer value) {
            addCriterion("PXH >=", value, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhLessThan(Integer value) {
            addCriterion("PXH <", value, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhLessThanOrEqualTo(Integer value) {
            addCriterion("PXH <=", value, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhIn(List<Integer> values) {
            addCriterion("PXH in", values, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhNotIn(List<Integer> values) {
            addCriterion("PXH not in", values, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhBetween(Integer value1, Integer value2) {
            addCriterion("PXH between", value1, value2, "pxh");
            return (Criteria) this;
        }

        public Criteria andPxhNotBetween(Integer value1, Integer value2) {
            addCriterion("PXH not between", value1, value2, "pxh");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
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