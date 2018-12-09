package org.coderev.model.objects.structures.processingoptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OMWPOTab {
	private String tabName;
	private int sequence;
	private List<OMWPOElement> elements = new ArrayList<OMWPOElement>();	
	
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	public String getTabName() {
		return this.tabName;
	}
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public int getSequence() {
		return this.sequence;
	}
	
	public ArrayList<OMWPOElement> getElements(){
		return new ArrayList<OMWPOElement>(this.elements);
	}
	
	public void addElement(OMWPOElement element) {
		if(!this.elements.contains(element))
			this.elements.add(element);
		Collections.sort(this.elements, Comparator.comparingInt(OMWPOElement::getSequence));
	}
	
}
