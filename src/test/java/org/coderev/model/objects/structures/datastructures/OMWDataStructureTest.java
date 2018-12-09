package org.coderev.model.objects.structures.datastructures;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.coderev.parreader.objects.OMWDataStructureReader;
import org.junit.Before;
import org.junit.Test;

public class OMWDataStructureTest {
	private OMWDataStructureReader reader;
	
    @Before
    public void setUp() throws UnsupportedEncodingException {
        URL url = OMWDataStructureTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/DSTR_D57OK088A_60_99.par");
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        reader = new OMWDataStructureReader(path);
    }
    
	@Test
	public void testDSLoad() {
		OMWDataStructure ds = reader.load();
		assertNotNull(ds);
		assertThat(ds.getId(), equalTo("D57OK088A"));
		assertThat(ds.getDescription(), equalTo("Retrieve comma separated string of items GTIN values DS"));
	}
	
	@Test
	public void testNumberOfParameters() {
		OMWDataStructure ds = reader.load();
		assertThat(ds.getParameters().size(), equalTo(5));
	}
	
	@Test
	public void testDSFirstParam() {
		OMWDataStructure ds = reader.load();
		assertThat(ds.getParameters().get(0).getAlias(), equalTo("GS5A"));
		assertThat(ds.getParameters().get(0).getSequence(), equalTo(1));
		assertThat(ds.getParameters().get(0).getFieldName(), equalTo("szGTINCrossReferenceTypes_GS5A"));
		assertThat(ds.getParameters().get(0).getIoType(), equalTo("IN"));	
	}
	@Test
	public void testDSSecondParam() {
		OMWDataStructure ds = reader.load();
		assertThat(ds.getParameters().get(1).getAlias(), equalTo("LITM"));
		assertThat(ds.getParameters().get(1).getSequence(), equalTo(2));
		assertThat(ds.getParameters().get(1).getFieldName(), equalTo("szIdentifier2ndItem_LITM"));
		assertThat(ds.getParameters().get(1).getIoType(), equalTo("IN"));	
	}
	@Test
	public void testDSThirdParam() {
		OMWDataStructure ds = reader.load();
		assertThat(ds.getParameters().get(2).getAlias(), equalTo("D200"));
		assertThat(ds.getParameters().get(2).getSequence(), equalTo(3));
		assertThat(ds.getParameters().get(2).getFieldName(), equalTo("szGTINString_D200"));
		assertThat(ds.getParameters().get(2).getIoType(), equalTo("OUT"));	
	}
	@Test
	public void testDSForthParam() {
		OMWDataStructure ds = reader.load();
		assertThat(ds.getParameters().get(3).getAlias(), equalTo("SUPPS"));
		assertThat(ds.getParameters().get(3).getSequence(), equalTo(4));
		assertThat(ds.getParameters().get(3).getFieldName(), equalTo("cSuppressErrorMessage_SUPPS"));
		assertThat(ds.getParameters().get(3).getIoType(), equalTo("IN"));	
	}
	@Test
	public void testDSFifthParam() {
		OMWDataStructure ds = reader.load();
		assertThat(ds.getParameters().get(4).getAlias(), equalTo("DTAI"));
		assertThat(ds.getParameters().get(4).getSequence(), equalTo(5));
		assertThat(ds.getParameters().get(4).getFieldName(), equalTo("szErrorID_DTAI"));
		assertThat(ds.getParameters().get(4).getIoType(), equalTo("OUT"));	
	}
}