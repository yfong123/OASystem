package com.gec.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProcessConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDeplomentidIsNull() {
            addCriterion("deplomentId is null");
            return (Criteria) this;
        }

        public Criteria andDeplomentidIsNotNull() {
            addCriterion("deplomentId is not null");
            return (Criteria) this;
        }

        public Criteria andDeplomentidEqualTo(String value) {
            addCriterion("deplomentId =", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidNotEqualTo(String value) {
            addCriterion("deplomentId <>", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidGreaterThan(String value) {
            addCriterion("deplomentId >", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidGreaterThanOrEqualTo(String value) {
            addCriterion("deplomentId >=", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidLessThan(String value) {
            addCriterion("deplomentId <", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidLessThanOrEqualTo(String value) {
            addCriterion("deplomentId <=", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidLike(String value) {
            addCriterion("deplomentId like", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidNotLike(String value) {
            addCriterion("deplomentId not like", value, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidIn(List<String> values) {
            addCriterion("deplomentId in", values, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidNotIn(List<String> values) {
            addCriterion("deplomentId not in", values, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidBetween(String value1, String value2) {
            addCriterion("deplomentId between", value1, value2, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andDeplomentidNotBetween(String value1, String value2) {
            addCriterion("deplomentId not between", value1, value2, "deplomentid");
            return (Criteria) this;
        }

        public Criteria andProcdefidIsNull() {
            addCriterion("procDefId is null");
            return (Criteria) this;
        }

        public Criteria andProcdefidIsNotNull() {
            addCriterion("procDefId is not null");
            return (Criteria) this;
        }

        public Criteria andProcdefidEqualTo(String value) {
            addCriterion("procDefId =", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidNotEqualTo(String value) {
            addCriterion("procDefId <>", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidGreaterThan(String value) {
            addCriterion("procDefId >", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidGreaterThanOrEqualTo(String value) {
            addCriterion("procDefId >=", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidLessThan(String value) {
            addCriterion("procDefId <", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidLessThanOrEqualTo(String value) {
            addCriterion("procDefId <=", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidLike(String value) {
            addCriterion("procDefId like", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidNotLike(String value) {
            addCriterion("procDefId not like", value, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidIn(List<String> values) {
            addCriterion("procDefId in", values, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidNotIn(List<String> values) {
            addCriterion("procDefId not in", values, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidBetween(String value1, String value2) {
            addCriterion("procDefId between", value1, value2, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProcdefidNotBetween(String value1, String value2) {
            addCriterion("procDefId not between", value1, value2, "procdefid");
            return (Criteria) this;
        }

        public Criteria andProccategoryIsNull() {
            addCriterion("procCategory is null");
            return (Criteria) this;
        }

        public Criteria andProccategoryIsNotNull() {
            addCriterion("procCategory is not null");
            return (Criteria) this;
        }

        public Criteria andProccategoryEqualTo(String value) {
            addCriterion("procCategory =", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryNotEqualTo(String value) {
            addCriterion("procCategory <>", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryGreaterThan(String value) {
            addCriterion("procCategory >", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryGreaterThanOrEqualTo(String value) {
            addCriterion("procCategory >=", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryLessThan(String value) {
            addCriterion("procCategory <", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryLessThanOrEqualTo(String value) {
            addCriterion("procCategory <=", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryLike(String value) {
            addCriterion("procCategory like", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryNotLike(String value) {
            addCriterion("procCategory not like", value, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryIn(List<String> values) {
            addCriterion("procCategory in", values, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryNotIn(List<String> values) {
            addCriterion("procCategory not in", values, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryBetween(String value1, String value2) {
            addCriterion("procCategory between", value1, value2, "proccategory");
            return (Criteria) this;
        }

        public Criteria andProccategoryNotBetween(String value1, String value2) {
            addCriterion("procCategory not between", value1, value2, "proccategory");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andProckeyIsNull() {
            addCriterion("procKey is null");
            return (Criteria) this;
        }

        public Criteria andProckeyIsNotNull() {
            addCriterion("procKey is not null");
            return (Criteria) this;
        }

        public Criteria andProckeyEqualTo(String value) {
            addCriterion("procKey =", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyNotEqualTo(String value) {
            addCriterion("procKey <>", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyGreaterThan(String value) {
            addCriterion("procKey >", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyGreaterThanOrEqualTo(String value) {
            addCriterion("procKey >=", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyLessThan(String value) {
            addCriterion("procKey <", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyLessThanOrEqualTo(String value) {
            addCriterion("procKey <=", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyLike(String value) {
            addCriterion("procKey like", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyNotLike(String value) {
            addCriterion("procKey not like", value, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyIn(List<String> values) {
            addCriterion("procKey in", values, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyNotIn(List<String> values) {
            addCriterion("procKey not in", values, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyBetween(String value1, String value2) {
            addCriterion("procKey between", value1, value2, "prockey");
            return (Criteria) this;
        }

        public Criteria andProckeyNotBetween(String value1, String value2) {
            addCriterion("procKey not between", value1, value2, "prockey");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createDate not between", value1, value2, "createdate");
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