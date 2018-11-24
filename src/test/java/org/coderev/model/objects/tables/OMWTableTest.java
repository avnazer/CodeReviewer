package org.coderev.model.objects.tables;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

import org.coderev.parreader.omwtables.OMWTableParReader;
import org.junit.Before;
import org.junit.Test;

public class OMWTableTest {

	private OMWTableParReader reader;

	@Before
	public void setUp() {
		reader = new OMWTableParReader(OMWTableTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/TBLE_F57OK087_60_99").getPath());
	}
	
	@Test
	public void testNumberOfColumns() {
		OMWTable table = new OMWTable();
		reader.load(table);
		assertThat(table.getColumns().size(), equalTo(36));
	}
	
	

}
