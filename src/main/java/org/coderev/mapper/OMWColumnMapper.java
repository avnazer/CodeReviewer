package org.coderev.mapper;

import java.util.HashMap;
import java.util.Map;

import org.coderev.model.objects.tables.OMWTableColumn;

public class OMWColumnMapper implements Mapper<OMWTableColumn> {
	private Map <String, String> rules = new HashMap<String, String>();	
	
	@Override
	public OMWTableColumn map() {
		OMWTableColumn column = new OMWTableColumn();
		column.setAlias(rules.get("TDOBND").trim());
		column.setSequence(Integer.parseInt(rules.get("TDPSEQ")));
		return column;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	
	}

}
