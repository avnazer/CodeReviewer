package org.coderev.mapper.structures.processingoptions;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.structures.processingoptions.OMWPOElement;

public class OMWPOElementMapper implements Mapper<OMWPOElement> {
	protected Map<String, String>rules = new HashMap<String, String>();
	
	@Override
	public OMWPOElement map() {
		OMWPOElement element = new OMWPOElement();
		element.setSequence(Integer.parseInt(rules.get("DisplaySequence")));
		element.setFieldText(rules.get("PTPOTX"));
		return element;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	}

}
