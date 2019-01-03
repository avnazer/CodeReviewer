package org.coderev.model.objects.views;

import java.util.ArrayList;
import java.util.List;

public class OMWTableJoin {
	
	private OMWViewJoinTypes joinType;
	private String leftTable;
	private String rightTable;
	private List<OMWFieldJoin> fields = new ArrayList<OMWFieldJoin>();
	
	public OMWViewJoinTypes getJoinType() {
		return joinType;
	}
	
	public void setJoinType(OMWViewJoinTypes joinType) {
		this.joinType = joinType;
	}
	
	public String getLeftTable() {
		return leftTable;
	}
	
	public void setLeftTable(String leftTable) {
		this.leftTable = leftTable;
	}
	
	public String getRightTable() {
		return rightTable;
	}
	
	public void setRightTable(String rightTable) {
		this.rightTable = rightTable;
	}
	
	public void addField(OMWFieldJoin fieldJoin) {
		if(!this.fields.contains(fieldJoin)) {
			this.fields.add(fieldJoin);
		}
	}
	
	public List<OMWFieldJoin>getFields(){
		return new ArrayList<OMWFieldJoin>(fields);
	}
	
	@Override
	public boolean equals(Object tableJoin){
		return tableJoin instanceof OMWTableJoin ?
				((OMWTableJoin)tableJoin).getLeftTable().equals(this.getLeftTable()) &((OMWTableJoin)tableJoin).getRightTable().equals(this.getRightTable()):
					false;
	}
}
	
