package org.coderev.mapper.views;

import java.util.HashMap;
import java.util.Map;

import org.coderev.mapper.Mapper;
import org.coderev.model.objects.views.OMWFieldJoin;
import org.coderev.model.objects.views.OMWTableJoin;
import org.coderev.model.objects.views.OMWViewJoinTypes;

public class OMWTableJoinMapper implements Mapper<OMWTableJoin>{

	private Map<String, String> rules = new HashMap<String, String>();
	
	@Override
	public OMWTableJoin map() {
		OMWTableJoin join = new OMWTableJoin();
		join.setLeftTable(rules.get("szFTable"));
		join.setRightTable(rules.get("szPTable"));
		
		if(rules.containsKey("chType")) {
			int joinType = Integer.parseInt(rules.get("chType"));	
			if(joinType == 1) 
				join.setJoinType(OMWViewJoinTypes.LEFT_OUTER);
			else if(joinType == 2)
				join.setJoinType(OMWViewJoinTypes.RIGHT_OUTER);
		}else
			join.setJoinType(OMWViewJoinTypes.SIMPLE);
		
		return join;
	}

	@Override
	public void addRule(String tag, String value) {
		rules.put(tag, value.trim());		
	}
}
