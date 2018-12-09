package org.coderev.model.objects.structures.processingoptions;

public class OMWPOElement {
	private int sequence;
	private String fieldText;
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getFieldText() {
		return fieldText;
	}
	public void setFieldText(String fieldText) {
		this.fieldText = fieldText;
	}	
	
	@Override
	public boolean equals(Object object) {
		return object instanceof OMWPOElement ? ((OMWPOElement)object).sequence == this.sequence : false;
	}
}
