package org.coderev.parreader.objects;

import java.io.File;

import org.coderev.mapper.views.OMWViewMapper;
import org.coderev.model.objects.views.OMWView;
import org.coderev.parreader.filemanagers.FileManager;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OMWViewParReader {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWViewParReader.class);
	private String parPath;
	private OMWView view;
	private OMWViewMapper viewMapper = new OMWViewMapper();
	
	public OMWViewParReader(String parPath) {
		super();
		FileManager.unzip(parPath);
		this.parPath = parPath.substring(0, parPath.indexOf(".par"));
	}
	
	public OMWView load() {
		this.loadView();
		return view;	
	}
	
	private void loadView() {
		String fileName = this.parPath + "/F9860.xml";
		File file = new File(fileName);
		Document doc = XMLDomReader.createDOMXML(file);
		
		if(doc != null) {
			NodeList nodes = doc.getElementsByTagName("row");
			Node node = nodes.item(0);
			node = node.getFirstChild();
			while(node != null) {
				
				Element elm = (Element)node;
				
				String tag = elm.getAttribute("name");
				String value = elm.getTextContent();
				
				viewMapper.addRule(tag, value);
				node = node.getNextSibling();
			}
			view = viewMapper.map();
		}
	}
	
}
