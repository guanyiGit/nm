package com.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TDeviceCommandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TDeviceCommandExample() {
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

        public Criteria andDeviceCommandIdIsNull() {
            addCriterion("device_command_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdIsNotNull() {
            addCriterion("device_command_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdEqualTo(Long value) {
            addCriterion("device_command_id =", value, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdNotEqualTo(Long value) {
            addCriterion("device_command_id <>", value, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdGreaterThan(Long value) {
            addCriterion("device_command_id >", value, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdGreaterThanOrEqualTo(Long value) {
            addCriterion("device_command_id >=", value, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdLessThan(Long value) {
            addCriterion("device_command_id <", value, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdLessThanOrEqualTo(Long value) {
            addCriterion("device_command_id <=", value, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdIn(List<Long> values) {
            addCriterion("device_command_id in", values, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdNotIn(List<Long> values) {
            addCriterion("device_command_id not in", values, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdBetween(Long value1, Long value2) {
            addCriterion("device_command_id between", value1, value2, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceCommandIdNotBetween(Long value1, Long value2) {
            addCriterion("device_command_id not between", value1, value2, "deviceCommandId");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoEqualTo(Long value) {
            addCriterion("device_id =", value, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotEqualTo(Long value) {
            addCriterion("device_id <>", value, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThan(Long value) {
            addCriterion("device_id >", value, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThanOrEqualTo(Long value) {
            addCriterion("device_id >=", value, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThan(Long value) {
            addCriterion("device_id <", value, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThanOrEqualTo(Long value) {
            addCriterion("device_id <=", value, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIn(List<Long> values) {
            addCriterion("device_id in", values, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotIn(List<Long> values) {
            addCriterion("device_id not in", values, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoBetween(Long value1, Long value2) {
            addCriterion("device_id between", value1, value2, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotBetween(Long value1, Long value2) {
            addCriterion("device_id not between", value1, value2, "DeviceNo");
            return (Criteria) this;
        }

        public Criteria andCmdTypeIsNull() {
            addCriterion("cmd_type is null");
            return (Criteria) this;
        }

        public Criteria andCmdTypeIsNotNull() {
            addCriterion("cmd_type is not null");
            return (Criteria) this;
        }

        public Criteria andCmdTypeEqualTo(Integer value) {
            addCriterion("cmd_type =", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeNotEqualTo(Integer value) {
            addCriterion("cmd_type <>", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeGreaterThan(Integer value) {
            addCriterion("cmd_type >", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmd_type >=", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeLessThan(Integer value) {
            addCriterion("cmd_type <", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cmd_type <=", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeIn(List<Integer> values) {
            addCriterion("cmd_type in", values, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeNotIn(List<Integer> values) {
            addCriterion("cmd_type not in", values, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeBetween(Integer value1, Integer value2) {
            addCriterion("cmd_type between", value1, value2, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cmd_type not between", value1, value2, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdValueIsNull() {
            addCriterion("cmd_value is null");
            return (Criteria) this;
        }

        public Criteria andCmdValueIsNotNull() {
            addCriterion("cmd_value is not null");
            return (Criteria) this;
        }

        public Criteria andCmdValueEqualTo(Integer value) {
            addCriterion("cmd_value =", value, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueNotEqualTo(Integer value) {
            addCriterion("cmd_value <>", value, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueGreaterThan(Integer value) {
            addCriterion("cmd_value >", value, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmd_value >=", value, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueLessThan(Integer value) {
            addCriterion("cmd_value <", value, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueLessThanOrEqualTo(Integer value) {
            addCriterion("cmd_value <=", value, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueIn(List<Integer> values) {
            addCriterion("cmd_value in", values, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueNotIn(List<Integer> values) {
            addCriterion("cmd_value not in", values, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueBetween(Integer value1, Integer value2) {
            addCriterion("cmd_value between", value1, value2, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andCmdValueNotBetween(Integer value1, Integer value2) {
            addCriterion("cmd_value not between", value1, value2, "cmdValue");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Long value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Long value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Long value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Long value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Long value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Long> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Long> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Long value1, Long value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Long value1, Long value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeIsNull() {
            addCriterion("cmd_issued_time is null");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeIsNotNull() {
            addCriterion("cmd_issued_time is not null");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeEqualTo(Date value) {
            addCriterion("cmd_issued_time =", value, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeNotEqualTo(Date value) {
            addCriterion("cmd_issued_time <>", value, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeGreaterThan(Date value) {
            addCriterion("cmd_issued_time >", value, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cmd_issued_time >=", value, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeLessThan(Date value) {
            addCriterion("cmd_issued_time <", value, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeLessThanOrEqualTo(Date value) {
            addCriterion("cmd_issued_time <=", value, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeIn(List<Date> values) {
            addCriterion("cmd_issued_time in", values, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeNotIn(List<Date> values) {
            addCriterion("cmd_issued_time not in", values, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeBetween(Date value1, Date value2) {
            addCriterion("cmd_issued_time between", value1, value2, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdIssuedTimeNotBetween(Date value1, Date value2) {
            addCriterion("cmd_issued_time not between", value1, value2, "cmdIssuedTime");
            return (Criteria) this;
        }

        public Criteria andCmdMidIsNull() {
            addCriterion("cmd_mid is null");
            return (Criteria) this;
        }

        public Criteria andCmdMidIsNotNull() {
            addCriterion("cmd_mid is not null");
            return (Criteria) this;
        }

        public Criteria andCmdMidEqualTo(Integer value) {
            addCriterion("cmd_mid =", value, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidNotEqualTo(Integer value) {
            addCriterion("cmd_mid <>", value, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidGreaterThan(Integer value) {
            addCriterion("cmd_mid >", value, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmd_mid >=", value, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidLessThan(Integer value) {
            addCriterion("cmd_mid <", value, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidLessThanOrEqualTo(Integer value) {
            addCriterion("cmd_mid <=", value, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidIn(List<Integer> values) {
            addCriterion("cmd_mid in", values, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidNotIn(List<Integer> values) {
            addCriterion("cmd_mid not in", values, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidBetween(Integer value1, Integer value2) {
            addCriterion("cmd_mid between", value1, value2, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdMidNotBetween(Integer value1, Integer value2) {
            addCriterion("cmd_mid not between", value1, value2, "cmdMid");
            return (Criteria) this;
        }

        public Criteria andCmdStatusIsNull() {
            addCriterion("cmd_status is null");
            return (Criteria) this;
        }

        public Criteria andCmdStatusIsNotNull() {
            addCriterion("cmd_status is not null");
            return (Criteria) this;
        }

        public Criteria andCmdStatusEqualTo(Integer value) {
            addCriterion("cmd_status =", value, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusNotEqualTo(Integer value) {
            addCriterion("cmd_status <>", value, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusGreaterThan(Integer value) {
            addCriterion("cmd_status >", value, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmd_status >=", value, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusLessThan(Integer value) {
            addCriterion("cmd_status <", value, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusLessThanOrEqualTo(Integer value) {
            addCriterion("cmd_status <=", value, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusIn(List<Integer> values) {
            addCriterion("cmd_status in", values, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusNotIn(List<Integer> values) {
            addCriterion("cmd_status not in", values, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusBetween(Integer value1, Integer value2) {
            addCriterion("cmd_status between", value1, value2, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("cmd_status not between", value1, value2, "cmdStatus");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeIsNull() {
            addCriterion("cmd_rsp_time is null");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeIsNotNull() {
            addCriterion("cmd_rsp_time is not null");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeEqualTo(Date value) {
            addCriterion("cmd_rsp_time =", value, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeNotEqualTo(Date value) {
            addCriterion("cmd_rsp_time <>", value, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeGreaterThan(Date value) {
            addCriterion("cmd_rsp_time >", value, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cmd_rsp_time >=", value, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeLessThan(Date value) {
            addCriterion("cmd_rsp_time <", value, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeLessThanOrEqualTo(Date value) {
            addCriterion("cmd_rsp_time <=", value, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeIn(List<Date> values) {
            addCriterion("cmd_rsp_time in", values, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeNotIn(List<Date> values) {
            addCriterion("cmd_rsp_time not in", values, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeBetween(Date value1, Date value2) {
            addCriterion("cmd_rsp_time between", value1, value2, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCmdRspTimeNotBetween(Date value1, Date value2) {
            addCriterion("cmd_rsp_time not between", value1, value2, "cmdRspTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIsNull() {
            addCriterion("creation_time is null");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIsNotNull() {
            addCriterion("creation_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreationTimeEqualTo(Date value) {
            addCriterion("creation_time =", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotEqualTo(Date value) {
            addCriterion("creation_time <>", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeGreaterThan(Date value) {
            addCriterion("creation_time >", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creation_time >=", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeLessThan(Date value) {
            addCriterion("creation_time <", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeLessThanOrEqualTo(Date value) {
            addCriterion("creation_time <=", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIn(List<Date> values) {
            addCriterion("creation_time in", values, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotIn(List<Date> values) {
            addCriterion("creation_time not in", values, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeBetween(Date value1, Date value2) {
            addCriterion("creation_time between", value1, value2, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotBetween(Date value1, Date value2) {
            addCriterion("creation_time not between", value1, value2, "creationTime");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdIsNull() {
            addCriterion("iot_command_id is null");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdIsNotNull() {
            addCriterion("iot_command_id is not null");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdEqualTo(String value) {
            addCriterion("iot_command_id =", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdNotEqualTo(String value) {
            addCriterion("iot_command_id <>", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdGreaterThan(String value) {
            addCriterion("iot_command_id >", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdGreaterThanOrEqualTo(String value) {
            addCriterion("iot_command_id >=", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdLessThan(String value) {
            addCriterion("iot_command_id <", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdLessThanOrEqualTo(String value) {
            addCriterion("iot_command_id <=", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdLike(String value) {
            addCriterion("iot_command_id like", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdNotLike(String value) {
            addCriterion("iot_command_id not like", value, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdIn(List<String> values) {
            addCriterion("iot_command_id in", values, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdNotIn(List<String> values) {
            addCriterion("iot_command_id not in", values, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdBetween(String value1, String value2) {
            addCriterion("iot_command_id between", value1, value2, "iotCommandId");
            return (Criteria) this;
        }

        public Criteria andIotCommandIdNotBetween(String value1, String value2) {
            addCriterion("iot_command_id not between", value1, value2, "iotCommandId");
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