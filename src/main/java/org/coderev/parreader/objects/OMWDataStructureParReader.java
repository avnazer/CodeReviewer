package org.coderev.parreader.objects;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.coderev.mapper.structures.datastructures.OMWDSParameterMapper;
import org.coderev.mapper.structures.datastructures.OMWDataStructureMapper;
import org.coderev.model.objects.structures.datastructures.OMWDSParameter;
import org.coderev.model.objects.structures.datastructures.OMWDataStructure;
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

public class OMWDataStructureParReader implements Reader {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWDataStructureParReader.class);
	private String parPath;
	private OMWDataStructure ds;
	private OMWDSParameterMapper dsParmMapper = new OMWDSParameterMapper();
	private OMWDataStructureMapper dsMapper = new OMWDataStructureMapper();
	
	public OMWDataStructureParReader(String parPath) {
        FileManager.unzip(parPath);
        this.parPath = parPath.substring(0, parPath.indexOf(".par"));
        
	}

	@Override
	public OMWDataStructure load() {
		this.loadDS();
		
		if(this.ds != null)
			this.loadParameters();
		
		return this.ds;
	}
	
	private void loadDS (){
		String fileName =  this.parPath + "/F9860.xml";
		File file = new File(fileName);
		Document doc = XMLDomReader.createDOMXML(file);
		
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
		String directory = this.parPath+"/specs/DSTMPL/";
		String[] files = FileManager.getFilesFromDirectory(directory, ".xml");
		for(String fileName :files) {
			File file = new File(directory + fileName);
			Document doc = XMLDomReader.createDOMXML(file);
			
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
