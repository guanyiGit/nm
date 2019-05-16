package com.soholy.cb.entity.otherDb.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WifiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WifiExample() {
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

        public Criteria andPriIdIsNull() {
            addCriterion("pri_id is null");
            return (Criteria) this;
        }

        public Criteria andPriIdIsNotNull() {
            addCriterion("pri_id is not null");
            return (Criteria) this;
        }

        public Criteria andPriIdEqualTo(String value) {
            addCriterion("pri_id =", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdNotEqualTo(String value) {
            addCriterion("pri_id <>", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdGreaterThan(String value) {
            addCriterion("pri_id >", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdGreaterThanOrEqualTo(String value) {
            addCriterion("pri_id >=", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdLessThan(String value) {
            addCriterion("pri_id <", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdLessThanOrEqualTo(String value) {
            addCriterion("pri_id <=", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdLike(String value) {
            addCriterion("pri_id like", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdNotLike(String value) {
            addCriterion("pri_id not like", value, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdIn(List<String> values) {
            addCriterion("pri_id in", values, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdNotIn(List<String> values) {
            addCriterion("pri_id not in", values, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdBetween(String value1, String value2) {
            addCriterion("pri_id between", value1, value2, "priId");
            return (Criteria) this;
        }

        public Criteria andPriIdNotBetween(String value1, String value2) {
            addCriterion("pri_id not between", value1, value2, "priId");
            return (Criteria) this;
        }

        public Criteria andBssid1IsNull() {
            addCriterion("bssid1 is null");
            return (Criteria) this;
        }

        public Criteria andBssid1IsNotNull() {
            addCriterion("bssid1 is not null");
            return (Criteria) this;
        }

        public Criteria andBssid1EqualTo(String value) {
            addCriterion("bssid1 =", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1NotEqualTo(String value) {
            addCriterion("bssid1 <>", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1GreaterThan(String value) {
            addCriterion("bssid1 >", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1GreaterThanOrEqualTo(String value) {
            addCriterion("bssid1 >=", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1LessThan(String value) {
            addCriterion("bssid1 <", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1LessThanOrEqualTo(String value) {
            addCriterion("bssid1 <=", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1Like(String value) {
            addCriterion("bssid1 like", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1NotLike(String value) {
            addCriterion("bssid1 not like", value, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1In(List<String> values) {
            addCriterion("bssid1 in", values, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1NotIn(List<String> values) {
            addCriterion("bssid1 not in", values, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1Between(String value1, String value2) {
            addCriterion("bssid1 between", value1, value2, "bssid1");
            return (Criteria) this;
        }

        public Criteria andBssid1NotBetween(String value1, String value2) {
            addCriterion("bssid1 not between", value1, value2, "bssid1");
            return (Criteria) this;
        }

        public Criteria andRssi1IsNull() {
            addCriterion("rssi1 is null");
            return (Criteria) this;
        }

        public Criteria andRssi1IsNotNull() {
            addCriterion("rssi1 is not null");
            return (Criteria) this;
        }

        public Criteria andRssi1EqualTo(Float value) {
            addCriterion("rssi1 =", value, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1NotEqualTo(Float value) {
            addCriterion("rssi1 <>", value, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1GreaterThan(Float value) {
            addCriterion("rssi1 >", value, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1GreaterThanOrEqualTo(Float value) {
            addCriterion("rssi1 >=", value, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1LessThan(Float value) {
            addCriterion("rssi1 <", value, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1LessThanOrEqualTo(Float value) {
            addCriterion("rssi1 <=", value, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1In(List<Float> values) {
            addCriterion("rssi1 in", values, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1NotIn(List<Float> values) {
            addCriterion("rssi1 not in", values, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1Between(Float value1, Float value2) {
            addCriterion("rssi1 between", value1, value2, "rssi1");
            return (Criteria) this;
        }

        public Criteria andRssi1NotBetween(Float value1, Float value2) {
            addCriterion("rssi1 not between", value1, value2, "rssi1");
            return (Criteria) this;
        }

        public Criteria andBssid2IsNull() {
            addCriterion("bssid2 is null");
            return (Criteria) this;
        }

        public Criteria andBssid2IsNotNull() {
            addCriterion("bssid2 is not null");
            return (Criteria) this;
        }

        public Criteria andBssid2EqualTo(String value) {
            addCriterion("bssid2 =", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2NotEqualTo(String value) {
            addCriterion("bssid2 <>", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2GreaterThan(String value) {
            addCriterion("bssid2 >", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2GreaterThanOrEqualTo(String value) {
            addCriterion("bssid2 >=", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2LessThan(String value) {
            addCriterion("bssid2 <", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2LessThanOrEqualTo(String value) {
            addCriterion("bssid2 <=", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2Like(String value) {
            addCriterion("bssid2 like", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2NotLike(String value) {
            addCriterion("bssid2 not like", value, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2In(List<String> values) {
            addCriterion("bssid2 in", values, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2NotIn(List<String> values) {
            addCriterion("bssid2 not in", values, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2Between(String value1, String value2) {
            addCriterion("bssid2 between", value1, value2, "bssid2");
            return (Criteria) this;
        }

        public Criteria andBssid2NotBetween(String value1, String value2) {
            addCriterion("bssid2 not between", value1, value2, "bssid2");
            return (Criteria) this;
        }

        public Criteria andRssi2IsNull() {
            addCriterion("rssi2 is null");
            return (Criteria) this;
        }

        public Criteria andRssi2IsNotNull() {
            addCriterion("rssi2 is not null");
            return (Criteria) this;
        }

        public Criteria andRssi2EqualTo(Float value) {
            addCriterion("rssi2 =", value, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2NotEqualTo(Float value) {
            addCriterion("rssi2 <>", value, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2GreaterThan(Float value) {
            addCriterion("rssi2 >", value, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2GreaterThanOrEqualTo(Float value) {
            addCriterion("rssi2 >=", value, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2LessThan(Float value) {
            addCriterion("rssi2 <", value, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2LessThanOrEqualTo(Float value) {
            addCriterion("rssi2 <=", value, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2In(List<Float> values) {
            addCriterion("rssi2 in", values, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2NotIn(List<Float> values) {
            addCriterion("rssi2 not in", values, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2Between(Float value1, Float value2) {
            addCriterion("rssi2 between", value1, value2, "rssi2");
            return (Criteria) this;
        }

        public Criteria andRssi2NotBetween(Float value1, Float value2) {
            addCriterion("rssi2 not between", value1, value2, "rssi2");
            return (Criteria) this;
        }

        public Criteria andBssid3IsNull() {
            addCriterion("bssid3 is null");
            return (Criteria) this;
        }

        public Criteria andBssid3IsNotNull() {
            addCriterion("bssid3 is not null");
            return (Criteria) this;
        }

        public Criteria andBssid3EqualTo(String value) {
            addCriterion("bssid3 =", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3NotEqualTo(String value) {
            addCriterion("bssid3 <>", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3GreaterThan(String value) {
            addCriterion("bssid3 >", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3GreaterThanOrEqualTo(String value) {
            addCriterion("bssid3 >=", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3LessThan(String value) {
            addCriterion("bssid3 <", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3LessThanOrEqualTo(String value) {
            addCriterion("bssid3 <=", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3Like(String value) {
            addCriterion("bssid3 like", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3NotLike(String value) {
            addCriterion("bssid3 not like", value, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3In(List<String> values) {
            addCriterion("bssid3 in", values, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3NotIn(List<String> values) {
            addCriterion("bssid3 not in", values, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3Between(String value1, String value2) {
            addCriterion("bssid3 between", value1, value2, "bssid3");
            return (Criteria) this;
        }

        public Criteria andBssid3NotBetween(String value1, String value2) {
            addCriterion("bssid3 not between", value1, value2, "bssid3");
            return (Criteria) this;
        }

        public Criteria andRssi3IsNull() {
            addCriterion("rssi3 is null");
            return (Criteria) this;
        }

        public Criteria andRssi3IsNotNull() {
            addCriterion("rssi3 is not null");
            return (Criteria) this;
        }

        public Criteria andRssi3EqualTo(Float value) {
            addCriterion("rssi3 =", value, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3NotEqualTo(Float value) {
            addCriterion("rssi3 <>", value, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3GreaterThan(Float value) {
            addCriterion("rssi3 >", value, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3GreaterThanOrEqualTo(Float value) {
            addCriterion("rssi3 >=", value, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3LessThan(Float value) {
            addCriterion("rssi3 <", value, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3LessThanOrEqualTo(Float value) {
            addCriterion("rssi3 <=", value, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3In(List<Float> values) {
            addCriterion("rssi3 in", values, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3NotIn(List<Float> values) {
            addCriterion("rssi3 not in", values, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3Between(Float value1, Float value2) {
            addCriterion("rssi3 between", value1, value2, "rssi3");
            return (Criteria) this;
        }

        public Criteria andRssi3NotBetween(Float value1, Float value2) {
            addCriterion("rssi3 not between", value1, value2, "rssi3");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andRadiusIsNull() {
            addCriterion("radius is null");
            return (Criteria) this;
        }

        public Criteria andRadiusIsNotNull() {
            addCriterion("radius is not null");
            return (Criteria) this;
        }

        public Criteria andRadiusEqualTo(Float value) {
            addCriterion("radius =", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotEqualTo(Float value) {
            addCriterion("radius <>", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusGreaterThan(Float value) {
            addCriterion("radius >", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusGreaterThanOrEqualTo(Float value) {
            addCriterion("radius >=", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusLessThan(Float value) {
            addCriterion("radius <", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusLessThanOrEqualTo(Float value) {
            addCriterion("radius <=", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusIn(List<Float> values) {
            addCriterion("radius in", values, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotIn(List<Float> values) {
            addCriterion("radius not in", values, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusBetween(Float value1, Float value2) {
            addCriterion("radius between", value1, value2, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotBetween(Float value1, Float value2) {
            addCriterion("radius not between", value1, value2, "radius");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(Integer value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(Integer value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(Integer value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(Integer value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(Integer value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<Integer> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<Integer> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(Integer value1, Integer value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(Integer value1, Integer value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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