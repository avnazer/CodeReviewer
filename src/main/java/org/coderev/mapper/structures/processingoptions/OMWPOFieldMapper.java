package org.coderev.mapper.structures.processingoptions;

import org.coderev.model.objects.structures.processingoptions.OMWPOField;

public class OMWPOFieldMapper extends OMWPOElementMapper {
	
	@Override
	public OMWPOField map() {
		OMWPOField field = new OMWPOField();
		field.setAlias(rules.get("DDAlias"));
		field.setFieldName(rules.get("FieldName"));
		field.setSequence(Integer.parseInt(rules.get("DisplaySequence")));
		field.setFieldText(rules.get("PTPOTX"));
		return field;
	}

}
