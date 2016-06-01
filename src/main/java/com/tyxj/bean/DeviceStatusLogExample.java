package com.tyxj.bean;

import com.tyxj.common.Limit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceStatusLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = 0;

    protected int limitEnd = 0;

    protected Limit limit;

    public DeviceStatusLogExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialIsNull() {
            addCriterion("device_serial is null");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialIsNotNull() {
            addCriterion("device_serial is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialEqualTo(String value) {
            addCriterion("device_serial =", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialNotEqualTo(String value) {
            addCriterion("device_serial <>", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialGreaterThan(String value) {
            addCriterion("device_serial >", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialGreaterThanOrEqualTo(String value) {
            addCriterion("device_serial >=", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialLessThan(String value) {
            addCriterion("device_serial <", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialLessThanOrEqualTo(String value) {
            addCriterion("device_serial <=", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialLike(String value) {
            addCriterion("device_serial like", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialNotLike(String value) {
            addCriterion("device_serial not like", value, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialIn(List<String> values) {
            addCriterion("device_serial in", values, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialNotIn(List<String> values) {
            addCriterion("device_serial not in", values, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialBetween(String value1, String value2) {
            addCriterion("device_serial between", value1, value2, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andDeviceSerialNotBetween(String value1, String value2) {
            addCriterion("device_serial not between", value1, value2, "deviceSerial");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1IsNull() {
            addCriterion("water_filter_serial1 is null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1IsNotNull() {
            addCriterion("water_filter_serial1 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1EqualTo(String value) {
            addCriterion("water_filter_serial1 =", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1NotEqualTo(String value) {
            addCriterion("water_filter_serial1 <>", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1GreaterThan(String value) {
            addCriterion("water_filter_serial1 >", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1GreaterThanOrEqualTo(String value) {
            addCriterion("water_filter_serial1 >=", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1LessThan(String value) {
            addCriterion("water_filter_serial1 <", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1LessThanOrEqualTo(String value) {
            addCriterion("water_filter_serial1 <=", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1Like(String value) {
            addCriterion("water_filter_serial1 like", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1NotLike(String value) {
            addCriterion("water_filter_serial1 not like", value, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1In(List<String> values) {
            addCriterion("water_filter_serial1 in", values, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1NotIn(List<String> values) {
            addCriterion("water_filter_serial1 not in", values, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1Between(String value1, String value2) {
            addCriterion("water_filter_serial1 between", value1, value2, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial1NotBetween(String value1, String value2) {
            addCriterion("water_filter_serial1 not between", value1, value2, "waterFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2IsNull() {
            addCriterion("water_filter_serial2 is null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2IsNotNull() {
            addCriterion("water_filter_serial2 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2EqualTo(String value) {
            addCriterion("water_filter_serial2 =", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2NotEqualTo(String value) {
            addCriterion("water_filter_serial2 <>", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2GreaterThan(String value) {
            addCriterion("water_filter_serial2 >", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2GreaterThanOrEqualTo(String value) {
            addCriterion("water_filter_serial2 >=", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2LessThan(String value) {
            addCriterion("water_filter_serial2 <", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2LessThanOrEqualTo(String value) {
            addCriterion("water_filter_serial2 <=", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2Like(String value) {
            addCriterion("water_filter_serial2 like", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2NotLike(String value) {
            addCriterion("water_filter_serial2 not like", value, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2In(List<String> values) {
            addCriterion("water_filter_serial2 in", values, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2NotIn(List<String> values) {
            addCriterion("water_filter_serial2 not in", values, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2Between(String value1, String value2) {
            addCriterion("water_filter_serial2 between", value1, value2, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial2NotBetween(String value1, String value2) {
            addCriterion("water_filter_serial2 not between", value1, value2, "waterFilterSerial2");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3IsNull() {
            addCriterion("water_filter_serial3 is null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3IsNotNull() {
            addCriterion("water_filter_serial3 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3EqualTo(String value) {
            addCriterion("water_filter_serial3 =", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3NotEqualTo(String value) {
            addCriterion("water_filter_serial3 <>", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3GreaterThan(String value) {
            addCriterion("water_filter_serial3 >", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3GreaterThanOrEqualTo(String value) {
            addCriterion("water_filter_serial3 >=", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3LessThan(String value) {
            addCriterion("water_filter_serial3 <", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3LessThanOrEqualTo(String value) {
            addCriterion("water_filter_serial3 <=", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3Like(String value) {
            addCriterion("water_filter_serial3 like", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3NotLike(String value) {
            addCriterion("water_filter_serial3 not like", value, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3In(List<String> values) {
            addCriterion("water_filter_serial3 in", values, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3NotIn(List<String> values) {
            addCriterion("water_filter_serial3 not in", values, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3Between(String value1, String value2) {
            addCriterion("water_filter_serial3 between", value1, value2, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial3NotBetween(String value1, String value2) {
            addCriterion("water_filter_serial3 not between", value1, value2, "waterFilterSerial3");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4IsNull() {
            addCriterion("water_filter_serial4 is null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4IsNotNull() {
            addCriterion("water_filter_serial4 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4EqualTo(String value) {
            addCriterion("water_filter_serial4 =", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4NotEqualTo(String value) {
            addCriterion("water_filter_serial4 <>", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4GreaterThan(String value) {
            addCriterion("water_filter_serial4 >", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4GreaterThanOrEqualTo(String value) {
            addCriterion("water_filter_serial4 >=", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4LessThan(String value) {
            addCriterion("water_filter_serial4 <", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4LessThanOrEqualTo(String value) {
            addCriterion("water_filter_serial4 <=", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4Like(String value) {
            addCriterion("water_filter_serial4 like", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4NotLike(String value) {
            addCriterion("water_filter_serial4 not like", value, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4In(List<String> values) {
            addCriterion("water_filter_serial4 in", values, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4NotIn(List<String> values) {
            addCriterion("water_filter_serial4 not in", values, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4Between(String value1, String value2) {
            addCriterion("water_filter_serial4 between", value1, value2, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andWaterFilterSerial4NotBetween(String value1, String value2) {
            addCriterion("water_filter_serial4 not between", value1, value2, "waterFilterSerial4");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1IsNull() {
            addCriterion("air_filter_serial1 is null");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1IsNotNull() {
            addCriterion("air_filter_serial1 is not null");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1EqualTo(String value) {
            addCriterion("air_filter_serial1 =", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1NotEqualTo(String value) {
            addCriterion("air_filter_serial1 <>", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1GreaterThan(String value) {
            addCriterion("air_filter_serial1 >", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1GreaterThanOrEqualTo(String value) {
            addCriterion("air_filter_serial1 >=", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1LessThan(String value) {
            addCriterion("air_filter_serial1 <", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1LessThanOrEqualTo(String value) {
            addCriterion("air_filter_serial1 <=", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1Like(String value) {
            addCriterion("air_filter_serial1 like", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1NotLike(String value) {
            addCriterion("air_filter_serial1 not like", value, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1In(List<String> values) {
            addCriterion("air_filter_serial1 in", values, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1NotIn(List<String> values) {
            addCriterion("air_filter_serial1 not in", values, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1Between(String value1, String value2) {
            addCriterion("air_filter_serial1 between", value1, value2, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andAirFilterSerial1NotBetween(String value1, String value2) {
            addCriterion("air_filter_serial1 not between", value1, value2, "airFilterSerial1");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeIsNull() {
            addCriterion("water_filter_remain_time is null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeIsNotNull() {
            addCriterion("water_filter_remain_time is not null");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeEqualTo(String value) {
            addCriterion("water_filter_remain_time =", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeNotEqualTo(String value) {
            addCriterion("water_filter_remain_time <>", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeGreaterThan(String value) {
            addCriterion("water_filter_remain_time >", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeGreaterThanOrEqualTo(String value) {
            addCriterion("water_filter_remain_time >=", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeLessThan(String value) {
            addCriterion("water_filter_remain_time <", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeLessThanOrEqualTo(String value) {
            addCriterion("water_filter_remain_time <=", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeLike(String value) {
            addCriterion("water_filter_remain_time like", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeNotLike(String value) {
            addCriterion("water_filter_remain_time not like", value, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeIn(List<String> values) {
            addCriterion("water_filter_remain_time in", values, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeNotIn(List<String> values) {
            addCriterion("water_filter_remain_time not in", values, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeBetween(String value1, String value2) {
            addCriterion("water_filter_remain_time between", value1, value2, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andWaterFilterRemainTimeNotBetween(String value1, String value2) {
            addCriterion("water_filter_remain_time not between", value1, value2, "waterFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeIsNull() {
            addCriterion("air_filter_remain_time is null");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeIsNotNull() {
            addCriterion("air_filter_remain_time is not null");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeEqualTo(String value) {
            addCriterion("air_filter_remain_time =", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeNotEqualTo(String value) {
            addCriterion("air_filter_remain_time <>", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeGreaterThan(String value) {
            addCriterion("air_filter_remain_time >", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeGreaterThanOrEqualTo(String value) {
            addCriterion("air_filter_remain_time >=", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeLessThan(String value) {
            addCriterion("air_filter_remain_time <", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeLessThanOrEqualTo(String value) {
            addCriterion("air_filter_remain_time <=", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeLike(String value) {
            addCriterion("air_filter_remain_time like", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeNotLike(String value) {
            addCriterion("air_filter_remain_time not like", value, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeIn(List<String> values) {
            addCriterion("air_filter_remain_time in", values, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeNotIn(List<String> values) {
            addCriterion("air_filter_remain_time not in", values, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeBetween(String value1, String value2) {
            addCriterion("air_filter_remain_time between", value1, value2, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andAirFilterRemainTimeNotBetween(String value1, String value2) {
            addCriterion("air_filter_remain_time not between", value1, value2, "airFilterRemainTime");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumIsNull() {
            addCriterion("drink_type_num is null");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumIsNotNull() {
            addCriterion("drink_type_num is not null");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumEqualTo(String value) {
            addCriterion("drink_type_num =", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumNotEqualTo(String value) {
            addCriterion("drink_type_num <>", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumGreaterThan(String value) {
            addCriterion("drink_type_num >", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumGreaterThanOrEqualTo(String value) {
            addCriterion("drink_type_num >=", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumLessThan(String value) {
            addCriterion("drink_type_num <", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumLessThanOrEqualTo(String value) {
            addCriterion("drink_type_num <=", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumLike(String value) {
            addCriterion("drink_type_num like", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumNotLike(String value) {
            addCriterion("drink_type_num not like", value, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumIn(List<String> values) {
            addCriterion("drink_type_num in", values, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumNotIn(List<String> values) {
            addCriterion("drink_type_num not in", values, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumBetween(String value1, String value2) {
            addCriterion("drink_type_num between", value1, value2, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andDrinkTypeNumNotBetween(String value1, String value2) {
            addCriterion("drink_type_num not between", value1, value2, "drinkTypeNum");
            return (Criteria) this;
        }

        public Criteria andCupsIsNull() {
            addCriterion("cups is null");
            return (Criteria) this;
        }

        public Criteria andCupsIsNotNull() {
            addCriterion("cups is not null");
            return (Criteria) this;
        }

        public Criteria andCupsEqualTo(Integer value) {
            addCriterion("cups =", value, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsNotEqualTo(Integer value) {
            addCriterion("cups <>", value, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsGreaterThan(Integer value) {
            addCriterion("cups >", value, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsGreaterThanOrEqualTo(Integer value) {
            addCriterion("cups >=", value, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsLessThan(Integer value) {
            addCriterion("cups <", value, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsLessThanOrEqualTo(Integer value) {
            addCriterion("cups <=", value, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsIn(List<Integer> values) {
            addCriterion("cups in", values, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsNotIn(List<Integer> values) {
            addCriterion("cups not in", values, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsBetween(Integer value1, Integer value2) {
            addCriterion("cups between", value1, value2, "cups");
            return (Criteria) this;
        }

        public Criteria andCupsNotBetween(Integer value1, Integer value2) {
            addCriterion("cups not between", value1, value2, "cups");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusIsNull() {
            addCriterion("water_device_status is null");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusIsNotNull() {
            addCriterion("water_device_status is not null");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusEqualTo(Integer value) {
            addCriterion("water_device_status =", value, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusNotEqualTo(Integer value) {
            addCriterion("water_device_status <>", value, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusGreaterThan(Integer value) {
            addCriterion("water_device_status >", value, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("water_device_status >=", value, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusLessThan(Integer value) {
            addCriterion("water_device_status <", value, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("water_device_status <=", value, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusIn(List<Integer> values) {
            addCriterion("water_device_status in", values, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusNotIn(List<Integer> values) {
            addCriterion("water_device_status not in", values, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusBetween(Integer value1, Integer value2) {
            addCriterion("water_device_status between", value1, value2, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andWaterDeviceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("water_device_status not between", value1, value2, "waterDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusIsNull() {
            addCriterion("coffee_device_status is null");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusIsNotNull() {
            addCriterion("coffee_device_status is not null");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusEqualTo(Integer value) {
            addCriterion("coffee_device_status =", value, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusNotEqualTo(Integer value) {
            addCriterion("coffee_device_status <>", value, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusGreaterThan(Integer value) {
            addCriterion("coffee_device_status >", value, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("coffee_device_status >=", value, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusLessThan(Integer value) {
            addCriterion("coffee_device_status <", value, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("coffee_device_status <=", value, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusIn(List<Integer> values) {
            addCriterion("coffee_device_status in", values, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusNotIn(List<Integer> values) {
            addCriterion("coffee_device_status not in", values, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusBetween(Integer value1, Integer value2) {
            addCriterion("coffee_device_status between", value1, value2, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("coffee_device_status not between", value1, value2, "coffeeDeviceStatus");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeIsNull() {
            addCriterion("coffee_device_error_code is null");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeIsNotNull() {
            addCriterion("coffee_device_error_code is not null");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeEqualTo(Integer value) {
            addCriterion("coffee_device_error_code =", value, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeNotEqualTo(Integer value) {
            addCriterion("coffee_device_error_code <>", value, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeGreaterThan(Integer value) {
            addCriterion("coffee_device_error_code >", value, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("coffee_device_error_code >=", value, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeLessThan(Integer value) {
            addCriterion("coffee_device_error_code <", value, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeLessThanOrEqualTo(Integer value) {
            addCriterion("coffee_device_error_code <=", value, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeIn(List<Integer> values) {
            addCriterion("coffee_device_error_code in", values, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeNotIn(List<Integer> values) {
            addCriterion("coffee_device_error_code not in", values, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeBetween(Integer value1, Integer value2) {
            addCriterion("coffee_device_error_code between", value1, value2, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andCoffeeDeviceErrorCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("coffee_device_error_code not between", value1, value2, "coffeeDeviceErrorCode");
            return (Criteria) this;
        }

        public Criteria andPmIsNull() {
            addCriterion("pm is null");
            return (Criteria) this;
        }

        public Criteria andPmIsNotNull() {
            addCriterion("pm is not null");
            return (Criteria) this;
        }

        public Criteria andPmEqualTo(Integer value) {
            addCriterion("pm =", value, "pm");
            return (Criteria) this;
        }

        public Criteria andPmNotEqualTo(Integer value) {
            addCriterion("pm <>", value, "pm");
            return (Criteria) this;
        }

        public Criteria andPmGreaterThan(Integer value) {
            addCriterion("pm >", value, "pm");
            return (Criteria) this;
        }

        public Criteria andPmGreaterThanOrEqualTo(Integer value) {
            addCriterion("pm >=", value, "pm");
            return (Criteria) this;
        }

        public Criteria andPmLessThan(Integer value) {
            addCriterion("pm <", value, "pm");
            return (Criteria) this;
        }

        public Criteria andPmLessThanOrEqualTo(Integer value) {
            addCriterion("pm <=", value, "pm");
            return (Criteria) this;
        }

        public Criteria andPmIn(List<Integer> values) {
            addCriterion("pm in", values, "pm");
            return (Criteria) this;
        }

        public Criteria andPmNotIn(List<Integer> values) {
            addCriterion("pm not in", values, "pm");
            return (Criteria) this;
        }

        public Criteria andPmBetween(Integer value1, Integer value2) {
            addCriterion("pm between", value1, value2, "pm");
            return (Criteria) this;
        }

        public Criteria andPmNotBetween(Integer value1, Integer value2) {
            addCriterion("pm not between", value1, value2, "pm");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsIsNull() {
            addCriterion("raw_water_tds is null");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsIsNotNull() {
            addCriterion("raw_water_tds is not null");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsEqualTo(Integer value) {
            addCriterion("raw_water_tds =", value, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsNotEqualTo(Integer value) {
            addCriterion("raw_water_tds <>", value, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsGreaterThan(Integer value) {
            addCriterion("raw_water_tds >", value, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsGreaterThanOrEqualTo(Integer value) {
            addCriterion("raw_water_tds >=", value, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsLessThan(Integer value) {
            addCriterion("raw_water_tds <", value, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsLessThanOrEqualTo(Integer value) {
            addCriterion("raw_water_tds <=", value, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsIn(List<Integer> values) {
            addCriterion("raw_water_tds in", values, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsNotIn(List<Integer> values) {
            addCriterion("raw_water_tds not in", values, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsBetween(Integer value1, Integer value2) {
            addCriterion("raw_water_tds between", value1, value2, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andRawWaterTdsNotBetween(Integer value1, Integer value2) {
            addCriterion("raw_water_tds not between", value1, value2, "rawWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsIsNull() {
            addCriterion("treated_water_tds is null");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsIsNotNull() {
            addCriterion("treated_water_tds is not null");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsEqualTo(Integer value) {
            addCriterion("treated_water_tds =", value, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsNotEqualTo(Integer value) {
            addCriterion("treated_water_tds <>", value, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsGreaterThan(Integer value) {
            addCriterion("treated_water_tds >", value, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsGreaterThanOrEqualTo(Integer value) {
            addCriterion("treated_water_tds >=", value, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsLessThan(Integer value) {
            addCriterion("treated_water_tds <", value, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsLessThanOrEqualTo(Integer value) {
            addCriterion("treated_water_tds <=", value, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsIn(List<Integer> values) {
            addCriterion("treated_water_tds in", values, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsNotIn(List<Integer> values) {
            addCriterion("treated_water_tds not in", values, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsBetween(Integer value1, Integer value2) {
            addCriterion("treated_water_tds between", value1, value2, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andTreatedWaterTdsNotBetween(Integer value1, Integer value2) {
            addCriterion("treated_water_tds not between", value1, value2, "treatedWaterTds");
            return (Criteria) this;
        }

        public Criteria andEnvirTempIsNull() {
            addCriterion("envir_temp is null");
            return (Criteria) this;
        }

        public Criteria andEnvirTempIsNotNull() {
            addCriterion("envir_temp is not null");
            return (Criteria) this;
        }

        public Criteria andEnvirTempEqualTo(Integer value) {
            addCriterion("envir_temp =", value, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempNotEqualTo(Integer value) {
            addCriterion("envir_temp <>", value, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempGreaterThan(Integer value) {
            addCriterion("envir_temp >", value, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempGreaterThanOrEqualTo(Integer value) {
            addCriterion("envir_temp >=", value, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempLessThan(Integer value) {
            addCriterion("envir_temp <", value, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempLessThanOrEqualTo(Integer value) {
            addCriterion("envir_temp <=", value, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempIn(List<Integer> values) {
            addCriterion("envir_temp in", values, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempNotIn(List<Integer> values) {
            addCriterion("envir_temp not in", values, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempBetween(Integer value1, Integer value2) {
            addCriterion("envir_temp between", value1, value2, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirTempNotBetween(Integer value1, Integer value2) {
            addCriterion("envir_temp not between", value1, value2, "envirTemp");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityIsNull() {
            addCriterion("envir_humidity is null");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityIsNotNull() {
            addCriterion("envir_humidity is not null");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityEqualTo(Integer value) {
            addCriterion("envir_humidity =", value, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityNotEqualTo(Integer value) {
            addCriterion("envir_humidity <>", value, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityGreaterThan(Integer value) {
            addCriterion("envir_humidity >", value, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("envir_humidity >=", value, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityLessThan(Integer value) {
            addCriterion("envir_humidity <", value, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityLessThanOrEqualTo(Integer value) {
            addCriterion("envir_humidity <=", value, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityIn(List<Integer> values) {
            addCriterion("envir_humidity in", values, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityNotIn(List<Integer> values) {
            addCriterion("envir_humidity not in", values, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityBetween(Integer value1, Integer value2) {
            addCriterion("envir_humidity between", value1, value2, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andEnvirHumidityNotBetween(Integer value1, Integer value2) {
            addCriterion("envir_humidity not between", value1, value2, "envirHumidity");
            return (Criteria) this;
        }

        public Criteria andVocIsNull() {
            addCriterion("voc is null");
            return (Criteria) this;
        }

        public Criteria andVocIsNotNull() {
            addCriterion("voc is not null");
            return (Criteria) this;
        }

        public Criteria andVocEqualTo(Integer value) {
            addCriterion("voc =", value, "voc");
            return (Criteria) this;
        }

        public Criteria andVocNotEqualTo(Integer value) {
            addCriterion("voc <>", value, "voc");
            return (Criteria) this;
        }

        public Criteria andVocGreaterThan(Integer value) {
            addCriterion("voc >", value, "voc");
            return (Criteria) this;
        }

        public Criteria andVocGreaterThanOrEqualTo(Integer value) {
            addCriterion("voc >=", value, "voc");
            return (Criteria) this;
        }

        public Criteria andVocLessThan(Integer value) {
            addCriterion("voc <", value, "voc");
            return (Criteria) this;
        }

        public Criteria andVocLessThanOrEqualTo(Integer value) {
            addCriterion("voc <=", value, "voc");
            return (Criteria) this;
        }

        public Criteria andVocIn(List<Integer> values) {
            addCriterion("voc in", values, "voc");
            return (Criteria) this;
        }

        public Criteria andVocNotIn(List<Integer> values) {
            addCriterion("voc not in", values, "voc");
            return (Criteria) this;
        }

        public Criteria andVocBetween(Integer value1, Integer value2) {
            addCriterion("voc between", value1, value2, "voc");
            return (Criteria) this;
        }

        public Criteria andVocNotBetween(Integer value1, Integer value2) {
            addCriterion("voc not between", value1, value2, "voc");
            return (Criteria) this;
        }

        public Criteria andTempT2IsNull() {
            addCriterion("temp_t2 is null");
            return (Criteria) this;
        }

        public Criteria andTempT2IsNotNull() {
            addCriterion("temp_t2 is not null");
            return (Criteria) this;
        }

        public Criteria andTempT2EqualTo(Integer value) {
            addCriterion("temp_t2 =", value, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2NotEqualTo(Integer value) {
            addCriterion("temp_t2 <>", value, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2GreaterThan(Integer value) {
            addCriterion("temp_t2 >", value, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2GreaterThanOrEqualTo(Integer value) {
            addCriterion("temp_t2 >=", value, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2LessThan(Integer value) {
            addCriterion("temp_t2 <", value, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2LessThanOrEqualTo(Integer value) {
            addCriterion("temp_t2 <=", value, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2In(List<Integer> values) {
            addCriterion("temp_t2 in", values, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2NotIn(List<Integer> values) {
            addCriterion("temp_t2 not in", values, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2Between(Integer value1, Integer value2) {
            addCriterion("temp_t2 between", value1, value2, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2NotBetween(Integer value1, Integer value2) {
            addCriterion("temp_t2 not between", value1, value2, "tempT2");
            return (Criteria) this;
        }

        public Criteria andTempT2bIsNull() {
            addCriterion("temp_t2b is null");
            return (Criteria) this;
        }

        public Criteria andTempT2bIsNotNull() {
            addCriterion("temp_t2b is not null");
            return (Criteria) this;
        }

        public Criteria andTempT2bEqualTo(Integer value) {
            addCriterion("temp_t2b =", value, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bNotEqualTo(Integer value) {
            addCriterion("temp_t2b <>", value, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bGreaterThan(Integer value) {
            addCriterion("temp_t2b >", value, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bGreaterThanOrEqualTo(Integer value) {
            addCriterion("temp_t2b >=", value, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bLessThan(Integer value) {
            addCriterion("temp_t2b <", value, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bLessThanOrEqualTo(Integer value) {
            addCriterion("temp_t2b <=", value, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bIn(List<Integer> values) {
            addCriterion("temp_t2b in", values, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bNotIn(List<Integer> values) {
            addCriterion("temp_t2b not in", values, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bBetween(Integer value1, Integer value2) {
            addCriterion("temp_t2b between", value1, value2, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT2bNotBetween(Integer value1, Integer value2) {
            addCriterion("temp_t2b not between", value1, value2, "tempT2b");
            return (Criteria) this;
        }

        public Criteria andTempT3IsNull() {
            addCriterion("temp_t3 is null");
            return (Criteria) this;
        }

        public Criteria andTempT3IsNotNull() {
            addCriterion("temp_t3 is not null");
            return (Criteria) this;
        }

        public Criteria andTempT3EqualTo(Integer value) {
            addCriterion("temp_t3 =", value, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3NotEqualTo(Integer value) {
            addCriterion("temp_t3 <>", value, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3GreaterThan(Integer value) {
            addCriterion("temp_t3 >", value, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3GreaterThanOrEqualTo(Integer value) {
            addCriterion("temp_t3 >=", value, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3LessThan(Integer value) {
            addCriterion("temp_t3 <", value, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3LessThanOrEqualTo(Integer value) {
            addCriterion("temp_t3 <=", value, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3In(List<Integer> values) {
            addCriterion("temp_t3 in", values, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3NotIn(List<Integer> values) {
            addCriterion("temp_t3 not in", values, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3Between(Integer value1, Integer value2) {
            addCriterion("temp_t3 between", value1, value2, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempT3NotBetween(Integer value1, Integer value2) {
            addCriterion("temp_t3 not between", value1, value2, "tempT3");
            return (Criteria) this;
        }

        public Criteria andTempPqIsNull() {
            addCriterion("temp_pq is null");
            return (Criteria) this;
        }

        public Criteria andTempPqIsNotNull() {
            addCriterion("temp_pq is not null");
            return (Criteria) this;
        }

        public Criteria andTempPqEqualTo(Integer value) {
            addCriterion("temp_pq =", value, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqNotEqualTo(Integer value) {
            addCriterion("temp_pq <>", value, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqGreaterThan(Integer value) {
            addCriterion("temp_pq >", value, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqGreaterThanOrEqualTo(Integer value) {
            addCriterion("temp_pq >=", value, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqLessThan(Integer value) {
            addCriterion("temp_pq <", value, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqLessThanOrEqualTo(Integer value) {
            addCriterion("temp_pq <=", value, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqIn(List<Integer> values) {
            addCriterion("temp_pq in", values, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqNotIn(List<Integer> values) {
            addCriterion("temp_pq not in", values, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqBetween(Integer value1, Integer value2) {
            addCriterion("temp_pq between", value1, value2, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempPqNotBetween(Integer value1, Integer value2) {
            addCriterion("temp_pq not between", value1, value2, "tempPq");
            return (Criteria) this;
        }

        public Criteria andTempHotIsNull() {
            addCriterion("temp_hot is null");
            return (Criteria) this;
        }

        public Criteria andTempHotIsNotNull() {
            addCriterion("temp_hot is not null");
            return (Criteria) this;
        }

        public Criteria andTempHotEqualTo(Integer value) {
            addCriterion("temp_hot =", value, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotNotEqualTo(Integer value) {
            addCriterion("temp_hot <>", value, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotGreaterThan(Integer value) {
            addCriterion("temp_hot >", value, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotGreaterThanOrEqualTo(Integer value) {
            addCriterion("temp_hot >=", value, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotLessThan(Integer value) {
            addCriterion("temp_hot <", value, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotLessThanOrEqualTo(Integer value) {
            addCriterion("temp_hot <=", value, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotIn(List<Integer> values) {
            addCriterion("temp_hot in", values, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotNotIn(List<Integer> values) {
            addCriterion("temp_hot not in", values, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotBetween(Integer value1, Integer value2) {
            addCriterion("temp_hot between", value1, value2, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempHotNotBetween(Integer value1, Integer value2) {
            addCriterion("temp_hot not between", value1, value2, "tempHot");
            return (Criteria) this;
        }

        public Criteria andTempIceIsNull() {
            addCriterion("temp_ice is null");
            return (Criteria) this;
        }

        public Criteria andTempIceIsNotNull() {
            addCriterion("temp_ice is not null");
            return (Criteria) this;
        }

        public Criteria andTempIceEqualTo(Integer value) {
            addCriterion("temp_ice =", value, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceNotEqualTo(Integer value) {
            addCriterion("temp_ice <>", value, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceGreaterThan(Integer value) {
            addCriterion("temp_ice >", value, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceGreaterThanOrEqualTo(Integer value) {
            addCriterion("temp_ice >=", value, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceLessThan(Integer value) {
            addCriterion("temp_ice <", value, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceLessThanOrEqualTo(Integer value) {
            addCriterion("temp_ice <=", value, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceIn(List<Integer> values) {
            addCriterion("temp_ice in", values, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceNotIn(List<Integer> values) {
            addCriterion("temp_ice not in", values, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceBetween(Integer value1, Integer value2) {
            addCriterion("temp_ice between", value1, value2, "tempIce");
            return (Criteria) this;
        }

        public Criteria andTempIceNotBetween(Integer value1, Integer value2) {
            addCriterion("temp_ice not between", value1, value2, "tempIce");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1IsNull() {
            addCriterion("water_level1 is null");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1IsNotNull() {
            addCriterion("water_level1 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1EqualTo(Integer value) {
            addCriterion("water_level1 =", value, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1NotEqualTo(Integer value) {
            addCriterion("water_level1 <>", value, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1GreaterThan(Integer value) {
            addCriterion("water_level1 >", value, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1GreaterThanOrEqualTo(Integer value) {
            addCriterion("water_level1 >=", value, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1LessThan(Integer value) {
            addCriterion("water_level1 <", value, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1LessThanOrEqualTo(Integer value) {
            addCriterion("water_level1 <=", value, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1In(List<Integer> values) {
            addCriterion("water_level1 in", values, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1NotIn(List<Integer> values) {
            addCriterion("water_level1 not in", values, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1Between(Integer value1, Integer value2) {
            addCriterion("water_level1 between", value1, value2, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel1NotBetween(Integer value1, Integer value2) {
            addCriterion("water_level1 not between", value1, value2, "waterLevel1");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2IsNull() {
            addCriterion("water_level2 is null");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2IsNotNull() {
            addCriterion("water_level2 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2EqualTo(Integer value) {
            addCriterion("water_level2 =", value, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2NotEqualTo(Integer value) {
            addCriterion("water_level2 <>", value, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2GreaterThan(Integer value) {
            addCriterion("water_level2 >", value, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2GreaterThanOrEqualTo(Integer value) {
            addCriterion("water_level2 >=", value, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2LessThan(Integer value) {
            addCriterion("water_level2 <", value, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2LessThanOrEqualTo(Integer value) {
            addCriterion("water_level2 <=", value, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2In(List<Integer> values) {
            addCriterion("water_level2 in", values, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2NotIn(List<Integer> values) {
            addCriterion("water_level2 not in", values, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2Between(Integer value1, Integer value2) {
            addCriterion("water_level2 between", value1, value2, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel2NotBetween(Integer value1, Integer value2) {
            addCriterion("water_level2 not between", value1, value2, "waterLevel2");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3IsNull() {
            addCriterion("water_level3 is null");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3IsNotNull() {
            addCriterion("water_level3 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3EqualTo(Integer value) {
            addCriterion("water_level3 =", value, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3NotEqualTo(Integer value) {
            addCriterion("water_level3 <>", value, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3GreaterThan(Integer value) {
            addCriterion("water_level3 >", value, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3GreaterThanOrEqualTo(Integer value) {
            addCriterion("water_level3 >=", value, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3LessThan(Integer value) {
            addCriterion("water_level3 <", value, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3LessThanOrEqualTo(Integer value) {
            addCriterion("water_level3 <=", value, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3In(List<Integer> values) {
            addCriterion("water_level3 in", values, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3NotIn(List<Integer> values) {
            addCriterion("water_level3 not in", values, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3Between(Integer value1, Integer value2) {
            addCriterion("water_level3 between", value1, value2, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andWaterLevel3NotBetween(Integer value1, Integer value2) {
            addCriterion("water_level3 not between", value1, value2, "waterLevel3");
            return (Criteria) this;
        }

        public Criteria andFlow1IsNull() {
            addCriterion("flow1 is null");
            return (Criteria) this;
        }

        public Criteria andFlow1IsNotNull() {
            addCriterion("flow1 is not null");
            return (Criteria) this;
        }

        public Criteria andFlow1EqualTo(Integer value) {
            addCriterion("flow1 =", value, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1NotEqualTo(Integer value) {
            addCriterion("flow1 <>", value, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1GreaterThan(Integer value) {
            addCriterion("flow1 >", value, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1GreaterThanOrEqualTo(Integer value) {
            addCriterion("flow1 >=", value, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1LessThan(Integer value) {
            addCriterion("flow1 <", value, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1LessThanOrEqualTo(Integer value) {
            addCriterion("flow1 <=", value, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1In(List<Integer> values) {
            addCriterion("flow1 in", values, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1NotIn(List<Integer> values) {
            addCriterion("flow1 not in", values, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1Between(Integer value1, Integer value2) {
            addCriterion("flow1 between", value1, value2, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow1NotBetween(Integer value1, Integer value2) {
            addCriterion("flow1 not between", value1, value2, "flow1");
            return (Criteria) this;
        }

        public Criteria andFlow2IsNull() {
            addCriterion("flow2 is null");
            return (Criteria) this;
        }

        public Criteria andFlow2IsNotNull() {
            addCriterion("flow2 is not null");
            return (Criteria) this;
        }

        public Criteria andFlow2EqualTo(Integer value) {
            addCriterion("flow2 =", value, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2NotEqualTo(Integer value) {
            addCriterion("flow2 <>", value, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2GreaterThan(Integer value) {
            addCriterion("flow2 >", value, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2GreaterThanOrEqualTo(Integer value) {
            addCriterion("flow2 >=", value, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2LessThan(Integer value) {
            addCriterion("flow2 <", value, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2LessThanOrEqualTo(Integer value) {
            addCriterion("flow2 <=", value, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2In(List<Integer> values) {
            addCriterion("flow2 in", values, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2NotIn(List<Integer> values) {
            addCriterion("flow2 not in", values, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2Between(Integer value1, Integer value2) {
            addCriterion("flow2 between", value1, value2, "flow2");
            return (Criteria) this;
        }

        public Criteria andFlow2NotBetween(Integer value1, Integer value2) {
            addCriterion("flow2 not between", value1, value2, "flow2");
            return (Criteria) this;
        }

        public Criteria andWindSizeIsNull() {
            addCriterion("wind_size is null");
            return (Criteria) this;
        }

        public Criteria andWindSizeIsNotNull() {
            addCriterion("wind_size is not null");
            return (Criteria) this;
        }

        public Criteria andWindSizeEqualTo(Integer value) {
            addCriterion("wind_size =", value, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeNotEqualTo(Integer value) {
            addCriterion("wind_size <>", value, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeGreaterThan(Integer value) {
            addCriterion("wind_size >", value, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("wind_size >=", value, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeLessThan(Integer value) {
            addCriterion("wind_size <", value, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeLessThanOrEqualTo(Integer value) {
            addCriterion("wind_size <=", value, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeIn(List<Integer> values) {
            addCriterion("wind_size in", values, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeNotIn(List<Integer> values) {
            addCriterion("wind_size not in", values, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeBetween(Integer value1, Integer value2) {
            addCriterion("wind_size between", value1, value2, "windSize");
            return (Criteria) this;
        }

        public Criteria andWindSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("wind_size not between", value1, value2, "windSize");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeIsNull() {
            addCriterion("device_run_time is null");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeIsNotNull() {
            addCriterion("device_run_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeEqualTo(Integer value) {
            addCriterion("device_run_time =", value, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeNotEqualTo(Integer value) {
            addCriterion("device_run_time <>", value, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeGreaterThan(Integer value) {
            addCriterion("device_run_time >", value, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_run_time >=", value, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeLessThan(Integer value) {
            addCriterion("device_run_time <", value, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeLessThanOrEqualTo(Integer value) {
            addCriterion("device_run_time <=", value, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeIn(List<Integer> values) {
            addCriterion("device_run_time in", values, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeNotIn(List<Integer> values) {
            addCriterion("device_run_time not in", values, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeBetween(Integer value1, Integer value2) {
            addCriterion("device_run_time between", value1, value2, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andDeviceRunTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("device_run_time not between", value1, value2, "deviceRunTime");
            return (Criteria) this;
        }

        public Criteria andNfcIsNull() {
            addCriterion("nfc is null");
            return (Criteria) this;
        }

        public Criteria andNfcIsNotNull() {
            addCriterion("nfc is not null");
            return (Criteria) this;
        }

        public Criteria andNfcEqualTo(String value) {
            addCriterion("nfc =", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcNotEqualTo(String value) {
            addCriterion("nfc <>", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcGreaterThan(String value) {
            addCriterion("nfc >", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcGreaterThanOrEqualTo(String value) {
            addCriterion("nfc >=", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcLessThan(String value) {
            addCriterion("nfc <", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcLessThanOrEqualTo(String value) {
            addCriterion("nfc <=", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcLike(String value) {
            addCriterion("nfc like", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcNotLike(String value) {
            addCriterion("nfc not like", value, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcIn(List<String> values) {
            addCriterion("nfc in", values, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcNotIn(List<String> values) {
            addCriterion("nfc not in", values, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcBetween(String value1, String value2) {
            addCriterion("nfc between", value1, value2, "nfc");
            return (Criteria) this;
        }

        public Criteria andNfcNotBetween(String value1, String value2) {
            addCriterion("nfc not between", value1, value2, "nfc");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("receive_time is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("receive_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("receive_time =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("receive_time <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("receive_time >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_time >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("receive_time <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("receive_time <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("receive_time in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("receive_time not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("receive_time between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("receive_time not between", value1, value2, "receiveTime");
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