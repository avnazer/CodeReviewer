package org.coderev.mapper;

import java.util.HashMap;
import java.util.Map;

import org.coderev.model.objects.tables.OMWTableIndexItem;

public class OMWTableIndexItemMapper implements Mapper<OMWTableIndexItem> {
	private Map<String, String>rules = new HashMap<String, String>();
	
	@Override
	public OMWTableIndexItem map() {
		OMWTableIndexItem item = new OMWTableIndexItem();
		item.setAlias(rules.get("TLOBND"));
		item.setSequence(Integer.parseInt(rules.get("TLCMPI")));
		item.setSortingType(rules.get("TLSRTO"));
		return item;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	}

}
