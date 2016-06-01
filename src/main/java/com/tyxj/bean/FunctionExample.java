package com.tyxj.bean;

import com.tyxj.common.Limit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FunctionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = 0;

    protected int limitEnd = 0;

    protected Limit limit;

    public FunctionExample() {
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

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    public void setLimit(Limit limit) {
        this.limit=limit;
    }

    public Limit getLimit() {
        return limit;
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

        public Criteria andFuncIdIsNull() {
            addCriterion("func_id is null");
            return (Criteria) this;
        }

        public Criteria andFuncIdIsNotNull() {
            addCriterion("func_id is not null");
            return (Criteria) this;
        }

        public Criteria andFuncIdEqualTo(Integer value) {
            addCriterion("func_id =", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotEqualTo(Integer value) {
            addCriterion("func_id <>", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdGreaterThan(Integer value) {
            addCriterion("func_id >", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("func_id >=", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdLessThan(Integer value) {
            addCriterion("func_id <", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdLessThanOrEqualTo(Integer value) {
            addCriterion("func_id <=", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdIn(List<Integer> values) {
            addCriterion("func_id in", values, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotIn(List<Integer> values) {
            addCriterion("func_id not in", values, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdBetween(Integer value1, Integer value2) {
            addCriterion("func_id between", value1, value2, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotBetween(Integer value1, Integer value2) {
            addCriterion("func_id not between", value1, value2, "funcId");
            return (Criteria) this;
        }

        public Criteria andFunNameIsNull() {
            addCriterion("fun_name is null");
            return (Criteria) this;
        }

        public Criteria andFunNameIsNotNull() {
            addCriterion("fun_name is not null");
            return (Criteria) this;
        }

        public Criteria andFunNameEqualTo(String value) {
            addCriterion("fun_name =", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameNotEqualTo(String value) {
            addCriterion("fun_name <>", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameGreaterThan(String value) {
            addCriterion("fun_name >", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameGreaterThanOrEqualTo(String value) {
            addCriterion("fun_name >=", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameLessThan(String value) {
            addCriterion("fun_name <", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameLessThanOrEqualTo(String value) {
            addCriterion("fun_name <=", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameLike(String value) {
            addCriterion("fun_name like", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameNotLike(String value) {
            addCriterion("fun_name not like", value, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameIn(List<String> values) {
            addCriterion("fun_name in", values, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameNotIn(List<String> values) {
            addCriterion("fun_name not in", values, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameBetween(String value1, String value2) {
            addCriterion("fun_name between", value1, value2, "funName");
            return (Criteria) this;
        }

        public Criteria andFunNameNotBetween(String value1, String value2) {
            addCriterion("fun_name not between", value1, value2, "funName");
            return (Criteria) this;
        }

        public Criteria andFunPidIsNull() {
            addCriterion("fun_pid is null");
            return (Criteria) this;
        }

        public Criteria andFunPidIsNotNull() {
            addCriterion("fun_pid is not null");
            return (Criteria) this;
        }

        public Criteria andFunPidEqualTo(Integer value) {
            addCriterion("fun_pid =", value, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidNotEqualTo(Integer value) {
            addCriterion("fun_pid <>", value, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidGreaterThan(Integer value) {
            addCriterion("fun_pid >", value, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fun_pid >=", value, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidLessThan(Integer value) {
            addCriterion("fun_pid <", value, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidLessThanOrEqualTo(Integer value) {
            addCriterion("fun_pid <=", value, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidIn(List<Integer> values) {
            addCriterion("fun_pid in", values, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidNotIn(List<Integer> values) {
            addCriterion("fun_pid not in", values, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidBetween(Integer value1, Integer value2) {
            addCriterion("fun_pid between", value1, value2, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunPidNotBetween(Integer value1, Integer value2) {
            addCriterion("fun_pid not between", value1, value2, "funPid");
            return (Criteria) this;
        }

        public Criteria andFunUrlIsNull() {
            addCriterion("fun_url is null");
            return (Criteria) this;
        }

        public Criteria andFunUrlIsNotNull() {
            addCriterion("fun_url is not null");
            return (Criteria) this;
        }

        public Criteria andFunUrlEqualTo(String value) {
            addCriterion("fun_url =", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlNotEqualTo(String value) {
            addCriterion("fun_url <>", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlGreaterThan(String value) {
            addCriterion("fun_url >", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlGreaterThanOrEqualTo(String value) {
            addCriterion("fun_url >=", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlLessThan(String value) {
            addCriterion("fun_url <", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlLessThanOrEqualTo(String value) {
            addCriterion("fun_url <=", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlLike(String value) {
            addCriterion("fun_url like", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlNotLike(String value) {
            addCriterion("fun_url not like", value, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlIn(List<String> values) {
            addCriterion("fun_url in", values, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlNotIn(List<String> values) {
            addCriterion("fun_url not in", values, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlBetween(String value1, String value2) {
            addCriterion("fun_url between", value1, value2, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunUrlNotBetween(String value1, String value2) {
            addCriterion("fun_url not between", value1, value2, "funUrl");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoIsNull() {
            addCriterion("fun_seq_no is null");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoIsNotNull() {
            addCriterion("fun_seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoEqualTo(Byte value) {
            addCriterion("fun_seq_no =", value, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoNotEqualTo(Byte value) {
            addCriterion("fun_seq_no <>", value, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoGreaterThan(Byte value) {
            addCriterion("fun_seq_no >", value, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoGreaterThanOrEqualTo(Byte value) {
            addCriterion("fun_seq_no >=", value, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoLessThan(Byte value) {
            addCriterion("fun_seq_no <", value, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoLessThanOrEqualTo(Byte value) {
            addCriterion("fun_seq_no <=", value, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoIn(List<Byte> values) {
            addCriterion("fun_seq_no in", values, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoNotIn(List<Byte> values) {
            addCriterion("fun_seq_no not in", values, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoBetween(Byte value1, Byte value2) {
            addCriterion("fun_seq_no between", value1, value2, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunSeqNoNotBetween(Byte value1, Byte value2) {
            addCriterion("fun_seq_no not between", value1, value2, "funSeqNo");
            return (Criteria) this;
        }

        public Criteria andFunLevelIsNull() {
            addCriterion("fun_level is null");
            return (Criteria) this;
        }

        public Criteria andFunLevelIsNotNull() {
            addCriterion("fun_level is not null");
            return (Criteria) this;
        }

        public Criteria andFunLevelEqualTo(Byte value) {
            addCriterion("fun_level =", value, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelNotEqualTo(Byte value) {
            addCriterion("fun_level <>", value, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelGreaterThan(Byte value) {
            addCriterion("fun_level >", value, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("fun_level >=", value, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelLessThan(Byte value) {
            addCriterion("fun_level <", value, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelLessThanOrEqualTo(Byte value) {
            addCriterion("fun_level <=", value, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelIn(List<Byte> values) {
            addCriterion("fun_level in", values, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelNotIn(List<Byte> values) {
            addCriterion("fun_level not in", values, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelBetween(Byte value1, Byte value2) {
            addCriterion("fun_level between", value1, value2, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("fun_level not between", value1, value2, "funLevel");
            return (Criteria) this;
        }

        public Criteria andFunIconClassIsNull() {
            addCriterion("fun_icon_class is null");
            return (Criteria) this;
        }

        public Criteria andFunIconClassIsNotNull() {
            addCriterion("fun_icon_class is not null");
            return (Criteria) this;
        }

        public Criteria andFunIconClassEqualTo(String value) {
            addCriterion("fun_icon_class =", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassNotEqualTo(String value) {
            addCriterion("fun_icon_class <>", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassGreaterThan(String value) {
            addCriterion("fun_icon_class >", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassGreaterThanOrEqualTo(String value) {
            addCriterion("fun_icon_class >=", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassLessThan(String value) {
            addCriterion("fun_icon_class <", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassLessThanOrEqualTo(String value) {
            addCriterion("fun_icon_class <=", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassLike(String value) {
            addCriterion("fun_icon_class like", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassNotLike(String value) {
            addCriterion("fun_icon_class not like", value, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassIn(List<String> values) {
            addCriterion("fun_icon_class in", values, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassNotIn(List<String> values) {
            addCriterion("fun_icon_class not in", values, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassBetween(String value1, String value2) {
            addCriterion("fun_icon_class between", value1, value2, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunIconClassNotBetween(String value1, String value2) {
            addCriterion("fun_icon_class not between", value1, value2, "funIconClass");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtIsNull() {
            addCriterion("fun_create_at is null");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtIsNotNull() {
            addCriterion("fun_create_at is not null");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtEqualTo(Date value) {
            addCriterion("fun_create_at =", value, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtNotEqualTo(Date value) {
            addCriterion("fun_create_at <>", value, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtGreaterThan(Date value) {
            addCriterion("fun_create_at >", value, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("fun_create_at >=", value, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtLessThan(Date value) {
            addCriterion("fun_create_at <", value, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtLessThanOrEqualTo(Date value) {
            addCriterion("fun_create_at <=", value, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtIn(List<Date> values) {
            addCriterion("fun_create_at in", values, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtNotIn(List<Date> values) {
            addCriterion("fun_create_at not in", values, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtBetween(Date value1, Date value2) {
            addCriterion("fun_create_at between", value1, value2, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCreateAtNotBetween(Date value1, Date value2) {
            addCriterion("fun_create_at not between", value1, value2, "funCreateAt");
            return (Criteria) this;
        }

        public Criteria andFunCodeIsNull() {
            addCriterion("fun_code is null");
            return (Criteria) this;
        }

        public Criteria andFunCodeIsNotNull() {
            addCriterion("fun_code is not null");
            return (Criteria) this;
        }

        public Criteria andFunCodeEqualTo(String value) {
            addCriterion("fun_code =", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeNotEqualTo(String value) {
            addCriterion("fun_code <>", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeGreaterThan(String value) {
            addCriterion("fun_code >", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeGreaterThanOrEqualTo(String value) {
            addCriterion("fun_code >=", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeLessThan(String value) {
            addCriterion("fun_code <", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeLessThanOrEqualTo(String value) {
            addCriterion("fun_code <=", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeLike(String value) {
            addCriterion("fun_code like", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeNotLike(String value) {
            addCriterion("fun_code not like", value, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeIn(List<String> values) {
            addCriterion("fun_code in", values, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeNotIn(List<String> values) {
            addCriterion("fun_code not in", values, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeBetween(String value1, String value2) {
            addCriterion("fun_code between", value1, value2, "funCode");
            return (Criteria) this;
        }

        public Criteria andFunCodeNotBetween(String value1, String value2) {
            addCriterion("fun_code not between", value1, value2, "funCode");
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