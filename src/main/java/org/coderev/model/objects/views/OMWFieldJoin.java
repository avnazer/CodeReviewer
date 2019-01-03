package org.coderev.model.objects.views;

public class OMWFieldJoin {
	
	private OMWFieldOperators operator;
	private String leftField;
	private String rightField;
	
	public String getLeftField() {
		return leftField;
	}
	
	public void setLeftField(String leftField) {
		this.leftField = leftField;
	}
	
	public String getRightField() {
		return rightField;
	}
	
	public void setRightField(String rightField) {
		this.rightField = rightField;
	}
	
	public void setOperator(OMWFieldOperators operator) {
		this.operator = operator;
	}
	
	public OMWFieldOperators getOperator() {
		return this.operator;
	}
}
