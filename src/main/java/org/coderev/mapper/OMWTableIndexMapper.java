package org.coderev.mapper;

import java.util.HashMap;
import java.util.Map;

import org.coderev.model.objects.tables.OMWTableIndex;

public class OMWTableIndexMapper implements Mapper<OMWTableIndex> {
	private Map<String,String> rules = new HashMap<String, String>();
	
	@Override
	public OMWTableIndex map() {
		OMWTableIndex index = new OMWTableIndex();
		index.setDescription(rules.get("TPDESC").trim());
		index.setSequence(Integer.parseInt(rules.get("TPINID").trim()));
		index.isPrimary(rules.get("TPPRMF").equals("1"));
		index.isUnique(rules.get("TPUNIQ").equals("1"));
		return index;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
		
	}

}
