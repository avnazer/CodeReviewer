package org.coderev.model.objects.tables;

import static org.junit.Assert.*;

import org.coderev.parreader.omwtables.OMWTableParReader;
import org.junit.Before;
import org.junit.Test;

public class OMWTableTest {

	private OMWTableParReader reader;

	@Before
	public void setUp() {
		reader = new OMWTableParReader("C:\\Users\\Verónica\\Desktop\\WorkSpace\\CodeReviewer\\tests\\PRJ_VNAZER - BSFN_60_99\\TBLE_F57OK087_60_99");
	}
	
	@Test
	public void testNumberOfColumns() {
		OMWTable table = new OMWTable();
		reader.load(table);
		assertTrue(table.getColumns().size() == 19);
	}
	
	

}
