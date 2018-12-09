package org.coderev.mapper.structures.processingoptions;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.structures.processingoptions.OMWPOTab;

public class OMWPOTabMapper implements Mapper<OMWPOTab> {
	private Map<String, String>rules = new HashMap<String, String>();
	@Override
	public OMWPOTab map() {
		OMWPOTab tab = new OMWPOTab();
		tab.setSequence(Integer.parseInt(rules.get("PTITNUM")));
		tab.setTabName(rules.get("PTPOTX"));
		return tab;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	}

}
