package org.coderev.mapper.views;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.views.OMWView;
import org.coderev.model.objects.views.OMWViewTypes;

public class OMWViewMapper implements Mapper<OMWView> {
	private Map<String, String>rules = new HashMap<String, String>();
	
	@Override
	public OMWView map() {
		OMWView view = new OMWView();
		view.setId(rules.get("SIOBNM"));
		view.setDescription(rules.get("SIMD"));
		
		if(rules.containsKey("nType") && rules.get("nType").equals("1"))
			view.setViewType(OMWViewTypes.JOIN);
		else
			view.setViewType(OMWViewTypes.UNION);
		return view;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	}

}
