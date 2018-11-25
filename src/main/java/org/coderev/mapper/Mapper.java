package org.coderev.mapper;

import org.coderev.model.objects.OMWObject;
import org.coderev.model.objects.tables.OMWTable;

public interface Mapper <E> {
	public E map();
	public void addRule(String tag, String value);
}
