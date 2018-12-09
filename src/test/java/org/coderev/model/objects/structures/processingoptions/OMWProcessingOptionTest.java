package org.coderev.model.objects.structures.processingoptions;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.coderev.parreader.objects.OMWProcessingOptionReader;
import org.junit.Before;
import org.junit.Test;

public class OMWProcessingOptionTest {
	private OMWProcessingOptionReader reader;
	
    @Before
    public void setUp() throws UnsupportedEncodingException {
        URL url = OMWProcessingOptionTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/DSTR_T57OK087_60_99.par");
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        reader = new OMWProcessingOptionReader(path);
    }
    
	@Test
	public void testLoadProcessingOption() {
		OMWProcessingOption op = reader.load();
		assertThat(op.getId(), equalTo("T57OK087"));
		assertThat(op.getDescription(), equalTo("OK 3PL Inventory Snapshot"));
	}
	
	@Test
	public void testNumberOfTabs() {
		OMWProcessingOption op = reader.load();
		assertThat(op.getTabs().size(), equalTo(2));
		
	}	
	
	@Test
	public void testTabInformation() {
		OMWProcessingOption op = reader.load();
		assertThat(op.getTabs().get(0).getSequence(), equalTo(0));
		assertThat(op.getTabs().get(0).getTabName(), equalTo("General"));
		assertThat(op.getTabs().get(1).getSequence(), equalTo(1));
		assertThat(op.getTabs().get(1).getTabName(), equalTo("XML Files"));
	}
	
	@Test
	public void testFirstFieldFirstTab() {
		OMWProcessingOption op = reader.load();
		
		assert(op.getTabs().get(0).getElements().get(0) instanceof OMWPOField);
		
		OMWPOField field = (OMWPOField)op.getTabs().get(0).getElements().get(0);
		
		assertThat(field.getAlias(), equalTo("EV01"));
		assertThat(field.getFieldName(), equalTo("cProcessMode_EV01"));
		assertThat(field.getSequence(), equalTo(0));
		assertThat(field.getFieldText(), equalTo("1. Process Mode\n" + 
				"    Default: Proof\n" + 
				"    1: Final"));
	}
	
	@Test
	public void testTwelveCommentFirstTab() {
		OMWProcessingOption op = reader.load();
		
		assert(op.getTabs().get(0).getElements().get(0) instanceof OMWPOElement);
		
		OMWPOElement comment = op.getTabs().get(0).getElements().get(12);
		
		assertThat(comment.getSequence(), equalTo(12));
		assertThat(comment.getFieldText(), equalTo("12. For Modules at Customer:"));
	}

}
