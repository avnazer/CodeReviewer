package org.coderev.model.objects.views;

public class OMWViewColumn {
	private String table;
	private int sequence;
	private String alias;
	private boolean isRequired;
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void isRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	
	@Override
	public boolean equals(Object column){
		return column instanceof OMWViewColumn ? ((OMWViewColumn)column).getSequence() == this.getSequence():false ;
	}
	
	
}
