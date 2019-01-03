package org.coderev.mapper;

public interface Mapper <E> {
	public E map();
	public void addRule(String tag, String value);
	
}
