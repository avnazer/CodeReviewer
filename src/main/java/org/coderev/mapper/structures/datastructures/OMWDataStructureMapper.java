package org.coderev.mapper.structures.datastructures;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.structures.datastructures.OMWDataStructure;

public class OMWDataStructureMapper implements Mapper<OMWDataStructure> {
	private Map<String, String> rules = new HashMap<String, String>();
	
	@Override
	public OMWDataStructure map() {
		OMWDataStructure ds = new OMWDataStructure();
		ds.setId(rules.get("SIOBNM"));
		ds.setDescription(rules.get("SIMD"));
		return ds;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());		
	}

}
