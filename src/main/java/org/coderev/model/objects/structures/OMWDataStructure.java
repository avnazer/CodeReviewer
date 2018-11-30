package org.coderev.model.objects.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.coderev.model.objects.OMWBaseObject;
import org.coderev.model.objects.tables.OMWTableColumn;

public class OMWDataStructure extends OMWBaseObject {
	private List<OMWDSParameter> parameters = new ArrayList<OMWDSParameter>();
	
	public List<OMWDSParameter>getParameters(){
		return new ArrayList<OMWDSParameter>(parameters);
	}
	
	public void addParameter(OMWDSParameter parameter) {
		if(!parameters.contains(parameter))
			parameters.add(parameter);
		Collections.sort(this.parameters, Comparator.comparingInt(OMWDSParameter::getSequence));
	}
}
