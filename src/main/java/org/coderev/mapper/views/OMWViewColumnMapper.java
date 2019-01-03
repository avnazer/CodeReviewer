package org.coderev.mapper.views;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.views.OMWViewColumn;

public class OMWViewColumnMapper implements Mapper<OMWViewColumn> {
	private Map<String, String>rules = new HashMap<String, String>();
	
	@Override
	public OMWViewColumn map() {
		OMWViewColumn column = new OMWViewColumn();
		column.setAlias(rules.get("szDict"));
		column.setSequence(Integer.parseInt(rules.get("nSeq")));
		column.isRequired(rules.get("bRequired").equals("1"));
		column.setTable(rules.get("szTable"));
		return column;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	}

}
