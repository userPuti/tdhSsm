package com.tdh.domain;

import java.util.ArrayList;
import java.util.List;

public class DepartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepartExample() {
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

        public Criteria andBmdmIsNull() {
            addCriterion("BMDM is null");
            return (Criteria) this;
        }

        public Criteria andBmdmIsNotNull() {
            addCriterion("BMDM is not null");
            return (Criteria) this;
        }

        public Criteria andBmdmEqualTo(String value) {
            addCriterion("BMDM =", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmNotEqualTo(String value) {
            addCriterion("BMDM <>", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmGreaterThan(String value) {
            addCriterion("BMDM >", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmGreaterThanOrEqualTo(String value) {
            addCriterion("BMDM >=", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmLessThan(String value) {
            addCriterion("BMDM <", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmLessThanOrEqualTo(String value) {
            addCriterion("BMDM <=", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmLike(String value) {
            addCriterion("BMDM like", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmNotLike(String value) {
            addCriterion("BMDM not like", value, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmIn(List<String> values) {
            addCriterion("BMDM in", values, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmNotIn(List<String> values) {
            addCriterion("BMDM not in", values, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmBetween(String value1, String value2) {
            addCriterion("BMDM between", value1, value2, "bmdm");
            return (Criteria) this;
        }

        public Criteria andBmdmNotBetween(String value1, String value2) {
            addCriterion("BMDM not between", value1, value2, "bmdm");
            return (Criteria) this;
        }

        public Criteria andDwdmIsNull() {
            addCriterion("DWDM is null");
            return (Criteria) this;
        }

        public Criteria andDwdmIsNotNull() {
            addCriterion("DWDM is not null");
            return (Criteria) this;
        }

        public Criteria andDwdmEqualTo(String value) {
            addCriterion("DWDM =", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotEqualTo(String value) {
            addCriterion("DWDM <>", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmGreaterThan(String value) {
            addCriterion("DWDM >", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmGreaterThanOrEqualTo(String value) {
            addCriterion("DWDM >=", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmLessThan(String value) {
            addCriterion("DWDM <", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmLessThanOrEqualTo(String value) {
            addCriterion("DWDM <=", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmLike(String value) {
            addCriterion("DWDM like", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotLike(String value) {
            addCriterion("DWDM not like", value, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmIn(List<String> values) {
            addCriterion("DWDM in", values, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotIn(List<String> values) {
            addCriterion("DWDM not in", values, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmBetween(String value1, String value2) {
            addCriterion("DWDM between", value1, value2, "dwdm");
            return (Criteria) this;
        }

        public Criteria andDwdmNotBetween(String value1, String value2) {
            addCriterion("DWDM not between", value1, value2, "dwdm");
            return (Criteria) this;
        }

        public Criteria andBmidIsNull() {
            addCriterion("BMID is null");
            return (Criteria) this;
        }

        public Criteria andBmidIsNotNull() {
            addCriterion("BMID is not null");
            return (Criteria) this;
        }

        public Criteria andBmidEqualTo(String value) {
            addCriterion("BMID =", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidNotEqualTo(String value) {
            addCriterion("BMID <>", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidGreaterThan(String value) {
            addCriterion("BMID >", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidGreaterThanOrEqualTo(String value) {
            addCriterion("BMID >=", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidLessThan(String value) {
            addCriterion("BMID <", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidLessThanOrEqualTo(String value) {
            addCriterion("BMID <=", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidLike(String value) {
            addCriterion("BMID like", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidNotLike(String value) {
            addCriterion("BMID not like", value, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidIn(List<String> values) {
            addCriterion("BMID in", values, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidNotIn(List<String> values) {
            addCriterion("BMID not in", values, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidBetween(String value1, String value2) {
            addCriterion("BMID between", value1, value2, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmidNotBetween(String value1, String value2) {
            addCriterion("BMID not between", value1, value2, "bmid");
            return (Criteria) this;
        }

        public Criteria andBmmcIsNull() {
            addCriterion("BMMC is null");
            return (Criteria) this;
        }

        public Criteria andBmmcIsNotNull() {
            addCriterion("BMMC is not null");
            return (Criteria) this;
        }

        public Criteria andBmmcEqualTo(String value) {
            addCriterion("BMMC =", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotEqualTo(String value) {
            addCriterion("BMMC <>", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcGreaterThan(String value) {
            addCriterion("BMMC >", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcGreaterThanOrEqualTo(String value) {
            addCriterion("BMMC >=", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcLessThan(String value) {
            addCriterion("BMMC <", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcLessThanOrEqualTo(String value) {
            addCriterion("BMMC <=", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcLike(String value) {
            addCriterion("BMMC like", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotLike(String value) {
            addCriterion("BMMC not like", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcIn(List<String> values) {
            addCriterion("BMMC in", values, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotIn(List<String> values) {
            addCriterion("BMMC not in", values, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcBetween(String value1, String value2) {
            addCriterion("BMMC between", value1, value2, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotBetween(String value1, String value2) {
            addCriterion("BMMC not between", value1, value2, "bmmc");
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