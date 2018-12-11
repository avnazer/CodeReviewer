package org.coderev.parreader.objects;

import java.io.File;

import org.coderev.mapper.structures.processingoptions.OMWPOElementMapper;
import org.coderev.mapper.structures.processingoptions.OMWPOFieldMapper;
import org.coderev.mapper.structures.processingoptions.OMWPOTabMapper;
import org.coderev.mapper.structures.processingoptions.OMWProcessingOptionMapper;
import org.coderev.model.objects.structures.processingoptions.OMWProcessingOption;
import org.coderev.parreader.Reader;
import org.coderev.parreader.filemanagers.FileManager;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OMWProcessingOptionParReader implements Reader {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWProcessingOptionParReader.class);
	private String parPath;
	private OMWProcessingOption po;
	private OMWProcessingOptionMapper poMapper = new OMWProcessingOptionMapper();
	private Document mainPODoc;
	
	public OMWProcessingOptionParReader(String parPath) {
		FileManager.unzip(parPath);
		this.parPath = parPath.substring(0, parPath.indexOf(".par"));
	}

	@Override
	public OMWProcessingOption load() {
		this.loadPO();
		if(this.po != null) {
			this.loadPOTabs();
			this.loadPOFields();
			this.loadPOComments();
		}
		return po;
	}
	
	
	private void loadPO() {
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
				poMapper.addRule(tag, value);
				node = node.getNextSibling();
			}
			po = poMapper.map();
		}
		
		fileName = this.parPath+"/specs/DSTMPL/"+this.po.getId()+".xml";
		file = new File(fileName);
		mainPODoc = XMLDomReader.createDOMXML(file);
	}
	
	private void loadPOTabs() {
		
		if(this.mainPODoc != null) {
			NodeList nodes = this.mainPODoc.getElementsByTagName("DSPageTitles");
			Node node = nodes.item(0);
			node = node.getFirstChild();
			while(node != null) {
				NamedNodeMap attrList = node.getAttributes();
				this.loadPOTabText(attrList.getNamedItem("PageNumber"));
				
				node = node.getNextSibling();
			}
		}
	}
	
	private void loadPOTabText(Node node) {
		StringBuilder tabTextFile = new StringBuilder();
		tabTextFile.append(this.parPath + "/specs/POTEXT/");
		tabTextFile.append(po.getId() + ".3.");
		tabTextFile.append(node.getNodeValue());
		tabTextFile.append(".0..xml");
		File file = new File(tabTextFile.toString()); 

		Document doc = XMLDomReader.createDOMXML(file);
		
		if(doc != null) {
			OMWPOTabMapper poTabMapper = new OMWPOTabMapper();
			NodeList nodes = doc.getElementsByTagName("row");
			Node tabNode = nodes.item(0);
			tabNode = tabNode.getFirstChild();
			while (tabNode != null) {
				Element elm = (Element)tabNode;
				String tag = elm.getAttribute("name");
				String value = elm.getTextContent();
				poTabMapper.addRule(tag, value);
				tabNode = tabNode.getNextSibling();
			}
			
			this.po.addTab(poTabMapper.map());
		}		
	}
	
	private void loadPOFields() {		
		if(this.mainPODoc!= null) {
			OMWPOElementMapper poFieldMapper = new OMWPOFieldMapper();
			
			NodeList nodes = this.mainPODoc.getElementsByTagName("DSTemplateDetails");
			Node node = nodes.item(0);
			node = node.getFirstChild();
			
			while(node != null) {
				NamedNodeMap attrList = node.getAttributes();
			
				for(int i = 0; i<attrList.getLength(); i++) {
					Node attr = attrList.item(i);
					String tag = attr.getNodeName();
					String value = attr.getNodeValue();
					poFieldMapper.addRule(tag, value);
				}
				
				String page = attrList.getNamedItem("PageNumber").getNodeValue();
				String sequence = attrList.getNamedItem("DisplaySequence").getNodeValue();
				this.loadPOElementText(page, sequence,'1',poFieldMapper);
				
				this.po.addElementToTab(poFieldMapper.map(), Integer.parseInt(page));
				node = node.getNextSibling();
			}
		}
	}
	
	private void loadPOComments() {
		if(this.mainPODoc!= null) {
			OMWPOElementMapper poCommentMapper = new OMWPOElementMapper();
			
			NodeList nodes = this.mainPODoc.getElementsByTagName("DSComments");
			Node node = nodes.item(0);
			node = node.getFirstChild();
			
			while(node != null) {
				NamedNodeMap attrList = node.getAttributes();
				
				for(int i = 0; i<attrList.getLength(); i++) {
					Node attr = attrList.item(i);
					String tag = attr.getNodeName();
					String value = attr.getNodeValue();
					poCommentMapper.addRule(tag, value);
				}
				
				String page = attrList.getNamedItem("PageNumber").getNodeValue();
				String sequence = attrList.getNamedItem("DisplaySequence").getNodeValue();
				this.loadPOElementText(page, sequence,'2', poCommentMapper);
				
				this.po.addElementToTab(poCommentMapper.map(), Integer.parseInt(page));
				node = node.getNextSibling();
			}		
		}
	}
	
	private void loadPOElementText(String page, String sequence,char fileType, OMWPOElementMapper poElementMapper) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(this.parPath);
		fileName.append("/specs/POTEXT/");
		fileName.append(this.po.getId() +"."+ fileType + ".");
		fileName.append(page + ".");
		fileName.append(sequence + "..xml");
		
		File file = new File(fileName.toString());
		Document doc = XMLDomReader.createDOMXML(file);
		
		NodeList nodes = doc.getElementsByTagName("row");
		Node node = nodes.item(0);
		node = node.getFirstChild();
		
		while(node != null) {
			Element elm = (Element)node;
			String tag = elm.getAttribute("name");
			String value = elm.getTextContent();
			poElementMapper.addRule(tag, value);
			
			node = node.getNextSibling();
		}
	}
}
