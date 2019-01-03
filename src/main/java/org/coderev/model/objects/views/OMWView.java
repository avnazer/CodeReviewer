package org.coderev.model.objects.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.coderev.model.objects.OMWBaseObject;

public class OMWView extends OMWBaseObject {

	private OMWViewTypes viewType;
	private List<OMWViewColumn>columns = new ArrayList<OMWViewColumn>();
	private List<OMWTableJoin> joins = new ArrayList<OMWTableJoin>();
	
	
	public List<OMWViewColumn> getColumns() {
		return new ArrayList<OMWViewColumn>(columns);
	}
	
	public void addColumn(OMWViewColumn column) {
		if(!this.columns.contains(column))
			this.columns.add(column);
		Collections.sort(this.columns, Comparator.comparingInt((OMWViewColumn::getSequence)));
	}
	
	public List<OMWTableJoin> getJoins() {
		return new ArrayList<OMWTableJoin>(joins);
	}

	public void addJoin(OMWTableJoin join) {
		if(!this.joins.contains(join))
			this.joins.add(join);
	}
	
	public void setViewType(OMWViewTypes viewType) {
		this.viewType = viewType;
	}
	
	public OMWViewTypes getViewType() {
		return this.viewType;
	}
	
	public boolean tablesJoinExist(OMWTableJoin tableJoin) {
		return this.joins.contains(tableJoin);
	}

	public void addFieldJoin(OMWFieldJoin fieldJoin, OMWTableJoin tableJoin) {
		int i = 0;
		
		while(i <this.joins.size()) {
			if(joins.get(i).equals(tableJoin)) {
				joins.get(i).addField(fieldJoin);
				break;
			}
			i++;
		}
	}
}
