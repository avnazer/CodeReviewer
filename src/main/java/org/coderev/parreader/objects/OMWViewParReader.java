package org.coderev.parreader.objects;

import java.io.File;

import org.coderev.mapper.views.OMWFieldJoinMapper;
import org.coderev.mapper.views.OMWTableJoinMapper;
import org.coderev.mapper.views.OMWViewColumnMapper;
import org.coderev.mapper.views.OMWViewMapper;
import org.coderev.model.objects.views.OMWFieldJoin;
import org.coderev.model.objects.views.OMWTableJoin;
import org.coderev.model.objects.views.OMWView;
import org.coderev.model.objects.views.OMWViewColumn;
import org.coderev.parreader.filemanagers.FileManager;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OMWViewParReader {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWViewParReader.class);
	private String parPath;
	private Document doc;
	private OMWView view;
	private OMWViewMapper viewMapper = new OMWViewMapper();
	private OMWViewColumnMapper colMapper;
	private OMWTableJoinMapper joinMapper;
	private OMWFieldJoinMapper fieldJoinMapper;
	
	public OMWViewParReader(String parPath) {
		super();
		FileManager.unzip(parPath);
		this.parPath = parPath.substring(0, parPath.indexOf(".par"));
	}
	
	public OMWView load() {
		this.loadView();
		if(view != null) {
			this.loadColumns();
			this.loadJoins();
		}
		return view;	
	}
	
	private void loadView() {
		String fileName = parPath + "/F9860.xml";
    	File file = new File(fileName);
    	Document f9860Doc = XMLDomReader.createDOMXML(file);
    	String viewName = "";
    	
    	if(f9860Doc != null) {
			NodeList nodes = f9860Doc.getElementsByTagName("row");	
			Node node = nodes.item(0);
			node = node.getFirstChild();
			while(node != null)
			{
				Element elm = (Element)node;
				
				String tag = elm.getAttribute("name");
				String value = elm.getTextContent();
				
				viewMapper.addRule(tag, value);
				
				if(tag.equals("SIOBNM"))
					viewName = value.trim();
				
				node = node.getNextSibling();
			}
    	}
    	
		fileName = this.parPath + "/specs/BUSVIEW/" +  viewName + ".xml";
		file = new File(fileName);
		doc = XMLDomReader.createDOMXML(file);
		
		if(doc != null) {
			NamedNodeMap attrList = doc.getFirstChild().getAttributes();
			for(int i = 0; i<attrList.getLength(); i++) {
				Node node = attrList.item(i);
				String tag = node.getNodeName();
				String value = node.getNodeValue();
				viewMapper.addRule(tag, value);
			}
			view = viewMapper.map();
		}
	}
	
	private void loadColumns() {		
		NodeList nodes = doc.getElementsByTagName("Bob_ColumnCollection");
		Node node = nodes.item(0);
		node = node.getFirstChild();
		while (node != null) {
			colMapper = new OMWViewColumnMapper();
			this.loadColumn(node);
			OMWViewColumn column = colMapper.map();
			view.addColumn(column);
			node = node.getNextSibling();
		}
	}
	
	private void loadColumn(Node node) {
		NamedNodeMap attrList = node.getAttributes();
		for(int i = 0; i<attrList.getLength(); i++) {
			Node attr = attrList.item(i);
			String tag = attr.getNodeName();
			String value = attr.getNodeValue();
			colMapper.addRule(tag, value);
		}
	}
	
	private void loadJoins() {
		NodeList nodes = doc.getElementsByTagName("Bob_JoinCollection");
		Node node = nodes.item(0);
		if(node != null) {
			node = node.getFirstChild();
			while (node != null) {
				joinMapper = new OMWTableJoinMapper();
				fieldJoinMapper = new OMWFieldJoinMapper();
				this.loadJoin(node);

				OMWTableJoin tableJoin = joinMapper.map();
				OMWFieldJoin fieldJoin = fieldJoinMapper.map();
				
				if(!view.tablesJoinExist(tableJoin))
					view.addJoin(tableJoin);

				view.addFieldJoin(fieldJoin,tableJoin);
				
				node = node.getNextSibling();
			}
		}
	}
	
	private void loadJoin(Node node) {
		NamedNodeMap attrList = node.getAttributes();
		for(int i = 0; i<attrList.getLength(); i++) {
			Node attr = attrList.item(i);
			String tag = attr.getNodeName();
			String value = attr.getNodeValue();
			joinMapper.addRule(tag, value);
			fieldJoinMapper.addRule(tag, value);
		}
	}
	
}
