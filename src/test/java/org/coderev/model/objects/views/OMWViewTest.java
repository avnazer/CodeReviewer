package org.coderev.model.objects.views;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.coderev.parreader.objects.OMWViewParReader;
import org.junit.Before;
import org.junit.Test;

public class OMWViewTest {
	private OMWViewParReader reader;
	private OMWViewParReader v57OK088Reader;
	private OMWViewParReader v00051LJReader;
	
	@Before
	public void setUp() throws UnsupportedEncodingException {

		URL url = OMWViewTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/BSVW_V57OK087_60_99.par");
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        reader = new OMWViewParReader(path);
        
		url = OMWViewTest.class.getResource("/BSVW_V57OK088_60_99.par");
        path = URLDecoder.decode(url.getPath(), "UTF-8");
        v57OK088Reader = new OMWViewParReader(path);
        
		url = OMWViewTest.class.getResource("/BSVW_V00051LJ_60_99.par");
        path = URLDecoder.decode(url.getPath(), "UTF-8");
        v00051LJReader = new OMWViewParReader(path);
	}
	
	@Test
	public void testViewLoad() {
		OMWView view = reader.load();
		assertThat(view.getId(), equalTo("V57OK087"));
		assertThat(view.getDescription(), equalTo("Full View Over F57OK087"));
	}
	
	@Test
	public void testNumberOfColumns() {
		OMWView view = reader.load();
		assertThat(view.getColumns().size(), equalTo(36));
	}
	
	@Test
	public void testFirstColumn() {
		OMWView view = reader.load();
		OMWViewColumn column = view.getColumns().get(0);
		assertThat(column.getAlias(), equalTo("EDBT"));
		assertThat(column.getSequence(), equalTo(0));
		assertThat(column.getTable(), equalTo("F57OK087"));
		assert(column.isRequired());
	}

	@Test
	public void testSecondColumn() {
		OMWView view = reader.load();
		OMWViewColumn column = view.getColumns().get(1);
		assertThat(column.getAlias(), equalTo("EDLN"));
		assertThat(column.getSequence(), equalTo(1));
		assertThat(column.getTable(), equalTo("F57OK087"));
		assert(column.isRequired());
	}
		
	@Test
	public void testMiddleColumn() {
		OMWView view = reader.load();
		OMWViewColumn column = view.getColumns().get(17);
		assertThat(column.getAlias(), equalTo("UOM"));
		assertThat(column.getSequence(), equalTo(17));
		assertThat(column.getTable(), equalTo("F57OK087"));
		assert(!column.isRequired());
	}
	
	@Test
	public void testLastColumn() {
		OMWView view = reader.load();
		OMWViewColumn column = view.getColumns().get(35);
		assertThat(column.getAlias(), equalTo("MMEJ"));
		assertThat(column.getSequence(), equalTo(35));
		assertThat(column.getTable(), equalTo("F57OK087"));
		assert(!column.isRequired());
	}	
	
	@Test
	public void testV57OK088() {
		
        OMWView view = v57OK088Reader.load();
        assertThat(view.getJoins().size(), equalTo(2));
        
        OMWTableJoin join;
        
        join = view.getJoins().get(0);
        assertThat(join.getLeftTable(), equalTo("F4102"));
        assertThat(join.getRightTable(), equalTo("F41021"));
        assertThat(join.getJoinType(), equalTo(OMWViewJoinTypes.SIMPLE));
        
        OMWFieldJoin field;
        field = join.getFields().get(0);
        assertThat(field.getLeftField(), equalTo("ITM"));
        assertThat(field.getRightField(), equalTo("ITM"));
        assertThat(field.getOperator(), equalTo(OMWFieldOperators.EQUAL));
        
        field = join.getFields().get(1);
        assertThat(field.getLeftField(), equalTo("MCU"));
        assertThat(field.getRightField(), equalTo("MCU"));
        assertThat(field.getOperator(), equalTo(OMWFieldOperators.EQUAL));
        
        
        join = view.getJoins().get(1);
        assertThat(join.getLeftTable(), equalTo("F4102"));
        assertThat(join.getRightTable(), equalTo("F4101"));
        assertThat(join.getJoinType(), equalTo(OMWViewJoinTypes.SIMPLE));
        
	}
	
	@Test
	public void testV00051LJ() {
		OMWView view = v00051LJReader.load();
		assertThat(view.getJoins().get(0).getJoinType(), equalTo(OMWViewJoinTypes.LEFT_OUTER));
	}
}
