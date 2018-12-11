package org.coderev.model.objects.views;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.coderev.model.objects.tables.OMWTableTest;
import org.coderev.parreader.objects.OMWTableParReader;
import org.coderev.parreader.objects.OMWViewParReader;
import org.junit.Before;
import org.junit.Test;

public class OMWViewTest {
	private OMWViewParReader reader;
	
	@Before
	public void setUp() throws UnsupportedEncodingException {
		URL url = OMWViewTest.class.getResource("/PRJ_VNAZER-BSFN_60_99/BSVW_V57OK087_60_99.par");
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        reader = new OMWViewParReader(path);
	
	}
	
	@Test
	public void test() {
		OMWView view = reader.load();
		assertThat(view.getId(), equalTo("V57OK087"));
		assertThat(view.getDescription(), equalTo("Full View Over F57OK087"));
	}
	
}
