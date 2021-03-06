package org.coderev.mapper.tables;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.tables.OMWTable;

public class OMWTableMapper implements Mapper<OMWTable> {
	private Map <String, String> rules = new HashMap<String, String>();	

	
	@Override
	public OMWTable map() {
		OMWTable table = new OMWTable();
		table.setId(rules.get("SIOBNM"));
		table.setDescription(rules.get("SIMD"));
		table.setPrefix(rules.get("SIPFX"));
		return table;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());		
	}

}
