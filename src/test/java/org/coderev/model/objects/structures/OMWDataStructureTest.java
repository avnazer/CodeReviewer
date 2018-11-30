package org.coderev.model.objects.structures;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.coderev.parreader.objects.OMWDataStructureReader;
import org.junit.Before;
import org.junit.Test;

public class OMWDataStructureTest {
	OMWDataStructureReader reader;
	
    @Before
    public void setUp() throws UnsupportedEncodingException {
        URL url = OMWDataStructureTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/DSTR_D57OK088A_60_99");
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
}
