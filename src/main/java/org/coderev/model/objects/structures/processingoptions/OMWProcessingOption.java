package org.coderev.model.objects.structures.processingoptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.coderev.model.objects.OMWBaseObject;

public class OMWProcessingOption extends OMWBaseObject {
	
	private List<OMWPOTab>tabs = new ArrayList<OMWPOTab>();
	
	public ArrayList<OMWPOTab>getTabs(){
		return new ArrayList<OMWPOTab>(tabs);
	}
	
	public void addTab(OMWPOTab tab) {
		if(!this.tabs.contains(tab)) 
			this.tabs.add(tab);
		Collections.sort(this.tabs, Comparator.comparingInt(OMWPOTab::getSequence));
	}
	
	public void addElementToTab(OMWPOElement element, int tab) {
		this.tabs.get(tab).addElement(element);
	}
}
