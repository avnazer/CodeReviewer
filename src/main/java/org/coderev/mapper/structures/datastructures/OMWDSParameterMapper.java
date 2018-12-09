package org.coderev.mapper.structures.datastructures;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.structures.datastructures.OMWDSParameter;

public class OMWDSParameterMapper implements Mapper<OMWDSParameter>{
	private Map<String, String> rules = new HashMap<String, String>();

	@Override
	public OMWDSParameter map() {
		OMWDSParameter parameter = new OMWDSParameter();
		parameter.setAlias(rules.get("DDAlias"));
		parameter.setFieldName(rules.get("FieldName"));
		parameter.setIoType(rules.get("IOType"));
		parameter.setSequence(Integer.parseInt(rules.get("ItemID")));
		return parameter;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
		
	}
	
	

}
