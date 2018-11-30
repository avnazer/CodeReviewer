package org.coderev.parreader.objects;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.coderev.mapper.OMWDataStructureMapper;
import org.coderev.model.objects.structures.OMWDataStructure;
import org.coderev.parreader.Reader;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OMWDataStructureReader implements Reader {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWDataStructureReader.class);
	private String parFile;
	private OMWDataStructure ds;
	
	private OMWDataStructureMapper dsMapper = new OMWDataStructureMapper();
	
	public OMWDataStructureReader(String path) {
		this.parFile = path;
	}

	@Override
	public OMWDataStructure load() {
		this.loadStructure();	
		return this.ds;
	}
	
	private void loadStructure (){
		String fileName =  this.parFile + "/F9860.xml";
		File file = new File(fileName);
		Document doc = null;
		
		try {
			doc = XMLDomReader.createDOMXML(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}
		
		if(doc != null){
			NodeList nodes = doc.getElementsByTagName("row");
			Node node = nodes.item(0);

			node = node.getFirstChild();
			while (node != null) {
				Element elm = (Element)node;
				String tag = elm.getAttribute("name");
				String value = elm.getTextContent();
				dsMapper.addRule(tag, value);
			}
			this.ds = dsMapper.map();
		}	
	}
}
