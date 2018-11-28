package org.coderev.model.objects.tables;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.coderev.parreader.objects.OMWTableParReader;
import org.junit.Before;
import org.junit.Test;

public class OMWTableTest {

    private OMWTableParReader reader;

    @Before
    public void setUp() throws UnsupportedEncodingException {
        URL url = OMWTableTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/TBLE_F57OK087_60_99");
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        reader = new OMWTableParReader(path);
    }
    
    @Test
    public void testTableLoad() {
    	OMWTable table = reader.load();
		assertNotNull(table);
        assertThat(table.getDescription(), equalTo("OK 3PL Inventory Snapshot"));
        assertThat(table.getId(), equalTo("F57OK087"));
        assertThat(table.getPrefix(), equalTo("PL"));
    }

    @Test
    public void testNumberOfColumns() {
    	OMWTable table = reader.load();
		assertThat(table.getColumns().size(), equalTo(36));			
    }
    
    @Test
    public void testFirstColumn() {
    	OMWTable table = reader.load();
		assertThat(table.getColumns().get(0).getAlias(), equalTo("EDBT"));
		assertThat(table.getColumns().get(0).getSequence(), equalTo(1));		
    }
    
    @Test
    public void testMiddleColumn() {
    	OMWTable table = reader.load();
		assertThat(table.getColumns().get(17).getAlias(), equalTo("UORG"));
		assertThat(table.getColumns().get(17).getSequence(), equalTo(18));		
    }
    
    @Test
    public void testLastColumn() {
    	OMWTable table = reader.load();
		assertThat(table.getColumns().get(35).getAlias(), equalTo("DTAI"));
		assertThat(table.getColumns().get(35).getSequence(), equalTo(36));		
    }
    
    @Test
    public void testNumberOfIndexes() {
    	OMWTable table = reader.load();
		assertThat(table.getIndexes().size(), equalTo(4));
    }
    
    @Test
    public void testIndexNames() {
    	OMWTable table = reader.load();
		assertThat(table.getIndexes().get(0).getDescription(), equalTo("EDBT, EDLN"));
		assertThat(table.getIndexes().get(1).getDescription(), equalTo("CRTJ DESC"));
		assertThat(table.getIndexes().get(2).getDescription(), equalTo("EDBT,ORTP,57LOTN,LOCN,LITM ..+"));
		assertThat(table.getIndexes().get(3).getDescription(), equalTo("EDBT,ITM,LOTN"));		
    }
    
    @Test
    public void testUniqueness() {
    	OMWTable table = reader.load();
    	assertTrue(table.getIndexes().get(0).isPrimary());
    	assertFalse(table.getIndexes().get(1).isPrimary());
    	assertFalse(table.getIndexes().get(2).isPrimary());
    	assertFalse(table.getIndexes().get(3).isPrimary());
    }
    
    @Test
    public void testPrimary() {
        OMWTable table = reader.load();
    	assertTrue(table.getIndexes().get(0).isPrimary());
    	assertFalse(table.getIndexes().get(1).isPrimary());
    	assertFalse(table.getIndexes().get(2).isPrimary());
    	assertFalse(table.getIndexes().get(3).isPrimary());
    }
    
    @Test
    public void testFirstIndexDetail() {
    	OMWTable table = reader.load();
		assertThat(table.getIndexes().get(0).getIndexItems().size(), equalTo(2));
    	assertThat(table.getIndexes().get(0).getIndexItems().get(0).toString(), equalTo("1 - EDBT - A"));
    	assertThat(table.getIndexes().get(0).getIndexItems().get(1).toString(), equalTo("2 - EDLN - A"));
    }

    @Test
    public void testSecondIndexDetail() {
    	OMWTable table = reader.load();
    	assertThat(table.getIndexes().get(1).getIndexItems().size(), equalTo(1));
    	assertThat(table.getIndexes().get(1).getIndexItems().get(0).toString(), equalTo("1 - CRTJ - D"));
    }
    
    @Test
    public void testThirdIndexDetail() {
    	OMWTable table = reader.load();
    	assertThat(table.getIndexes().get(2).getIndexItems().size(), equalTo(6));
    	assertThat(table.getIndexes().get(2).getIndexItems().get(0).toString(), equalTo("1 - EDBT - A"));
    	assertThat(table.getIndexes().get(2).getIndexItems().get(1).toString(), equalTo("2 - ORTP - A"));
    	assertThat(table.getIndexes().get(2).getIndexItems().get(2).toString(), equalTo("3 - 57LOTN - A"));
    	assertThat(table.getIndexes().get(2).getIndexItems().get(3).toString(), equalTo("4 - LOCN - A"));
    	assertThat(table.getIndexes().get(2).getIndexItems().get(4).toString(), equalTo("5 - LITM - A"));
    	assertThat(table.getIndexes().get(2).getIndexItems().get(5).toString(), equalTo("6 - LOTN - A"));
    }

    @Test
    public void testForthIndexDetail() {
    	OMWTable table = reader.load();
    	assertThat(table.getIndexes().get(3).getIndexItems().size(), equalTo(3));
    	assertThat(table.getIndexes().get(3).getIndexItems().get(0).toString(), equalTo("1 - EDBT - A"));
    	assertThat(table.getIndexes().get(3).getIndexItems().get(1).toString(), equalTo("2 - ITM - A"));
    	assertThat(table.getIndexes().get(3).getIndexItems().get(2).toString(), equalTo("3 - LOTN - A"));
    }
}
