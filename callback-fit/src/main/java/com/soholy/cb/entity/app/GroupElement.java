package com.soholy.cb.entity.app;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

public class GroupElement {
  private Operator operator;
  
  private List<JSONObject> elements;
  
  public Operator getOperator() { return this.operator; }
  
  public void setOperator(Operator operator) { this.operator = operator; }
  
  public List<JSONObject> getElements() { return this.elements; }
  
  public void setElements(List<JSONObject> elements) { this.elements = elements; }
}
