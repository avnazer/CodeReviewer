package org.coderev.model.objects.structures;

public class OMWDSParameter {
	private String alias;
	private int sequence;
	private String ioType;
	private String fieldName;
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getIoType() {
		return ioType;
	}
	public void setIoType(String ioType) {
		this.ioType = ioType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public boolean equals(Object parameter) {
		return parameter instanceof OMWDSParameter ? ((OMWDSParameter)parameter).getSequence() == this.sequence : false;
	}
	
}
