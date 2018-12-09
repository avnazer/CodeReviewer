package org.coderev.mapper.structures.processingoptions;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.structures.processingoptions.OMWProcessingOption;

public class OMWProcessingOptionMapper implements Mapper<OMWProcessingOption> {

	private Map<String, String> rules = new HashMap<String, String>();
	@Override
	public OMWProcessingOption map() {
		OMWProcessingOption op = new OMWProcessingOption();
		op.setId(rules.get("SIOBNM"));
		op.setDescription(rules.get("SIMD"));
		return op;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	}
}
