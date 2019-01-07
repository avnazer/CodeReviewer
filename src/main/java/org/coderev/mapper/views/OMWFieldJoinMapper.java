package org.coderev.mapper.views;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.views.OMWFieldJoin;
import org.coderev.model.objects.views.OMWFieldOperators;

public class OMWFieldJoinMapper implements Mapper<OMWFieldJoin>{

	private Map<String, String>rules = new HashMap<String, String>();
	@Override
	public OMWFieldJoin map() {
		OMWFieldJoin fieldJoin = new OMWFieldJoin();
		fieldJoin.setLeftField(rules.get("szFDict"));
		fieldJoin.setRightField(rules.get("szPDict"));
		
		int operator = 0;
		if(rules.containsKey("chOperator"))
			operator = Integer.parseInt(rules.get("chOperator"));

		OMWFieldOperators fieldOperator;
		
		switch(operator) {
			case 1:
				fieldOperator = OMWFieldOperators.LESS_THAN;
				break;
			case 2:
				fieldOperator = OMWFieldOperators.GREATER_THAN;
				break;
			case 3:
				fieldOperator = OMWFieldOperators.LESS_OR_EQUAL;
				break;
			case 4:
				fieldOperator = OMWFieldOperators.GREATER_OR_EQUAL;
				break;
			default:
				fieldOperator = OMWFieldOperators.EQUAL;
				break;
		}
		
		fieldJoin.setOperator(fieldOperator);
		return fieldJoin;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());
	}

}
