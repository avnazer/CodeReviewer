package org.coderev.parreader.objects;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.coderev.mapper.OMWDSParameterMapper;
import org.coderev.mapper.OMWDataStructureMapper;
import org.coderev.model.objects.structures.OMWDSParameter;
import org.coderev.model.objects.structures.OMWDataStructure;
import org.coderev.parreader.Reader;
import org.coderev.parreader.filemanagers.FileManager;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OMWDataStructureReader implements Reader {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWDataStructureReader.class);
	private String parFile;
	private OMWDataStructure ds;
	private OMWDSParameterMapper dsParmMapper = new OMWDSParameterMapper();
	
	private OMWDataStructureMapper dsMapper = new OMWDataStructureMapper();
	
	public OMWDataStructureReader(String path) {
		this.parFile = path;
	}

	@Override
	public OMWDataStructure load() {
		this.loadDS();
		
		if(this.ds != null)
			this.loadParameters();
		
		return this.ds;
	}
	
	private void loadDS (){
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
				
				node = node.getNextSibling();
			}
			this.ds = dsMapper.map();
		}	
	}
	
	private void loadParameters(){
		String directory = this.parFile+"/DSTMPL/";
		String[] files = FileManager.getFilesFromDirectory(directory, ".xml");
		for(String fileName :files) {
			File file = new File(directory + fileName);
			Document doc;
			
			try {
				doc = XMLDomReader.createDOMXML(file);
			} catch (ParserConfigurationException | SAXException | IOException e) {
				LOGGER.error(e.getMessage());
				throw new RuntimeException();
			}
			
			if(doc != null) {
			
				NodeList nodes = doc.getElementsByTagName("DSTemplateDetails");
				Node node = nodes.item(0);
				node = node.getFirstChild();
				while (node != null) {
					this.loadParameter(node);
					OMWDSParameter dsParm = dsParmMapper.map();
					ds.addParameter(dsParm);
					node = node.getNextSibling();
				}
			}
		}
	}
	
	private void loadParameter(Node node) {
		if(node != null) {
			NamedNodeMap attrList = node.getAttributes();
			for(int i = 0; i<attrList.getLength(); i++) {
				Node attr = attrList.item(i);
				String tag = attr.getNodeName();
				String value = attr.getNodeValue();
				dsParmMapper.addRule(tag, value);
			}
		}
	}
}
