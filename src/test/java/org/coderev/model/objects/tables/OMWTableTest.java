package org.coderev.model.objects.tables;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.coderev.parreader.OMWTableParReader;
import org.junit.Before;
import org.junit.Test;

public class OMWTableTest {

	private OMWTableParReader reader;

	@Before
	public void setUp() {
		String path = "";
		try {
			path = URLDecoder.decode(OMWTableTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/TBLE_F57OK087_60_99").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader = new OMWTableParReader(path);
	}
	
	@Test
	public void testTableLoad() {
		
		OMWTable table = reader.load();
		assertTrue(table!= null 
				&& !table.getDescription().isEmpty() 
				&& table.getId().startsWith("F")
				&& !table.getPrefix().isEmpty());
	}
	
	@Test
	public void testNumberOfColumns() {
		OMWTable table = reader.load();
		assertThat(table.getColumns().size(), equalTo(36));
	}
	
	@Test
	public void testNumberOfIndexes() {
		//OMWTable table = reader.map();
		//assertTrue(table.getIndexes().size() == 4);
	}
	

}
