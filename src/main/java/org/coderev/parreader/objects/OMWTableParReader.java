package org.coderev.parreader.objects;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.coderev.mapper.tables.OMWColumnMapper;
import org.coderev.mapper.tables.OMWTableIndexItemMapper;
import org.coderev.mapper.tables.OMWTableIndexMapper;
import org.coderev.mapper.tables.OMWTableMapper;
import org.coderev.model.objects.tables.OMWTable;
import org.coderev.model.objects.tables.OMWTableColumn;
import org.coderev.model.objects.tables.OMWTableIndex;
import org.coderev.model.objects.tables.OMWTableIndexItem;
import org.coderev.parreader.Reader;
import org.coderev.parreader.filemanagers.FileManager;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OMWTableParReader implements Reader{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWTableParReader.class);
    private String parPath;
    private OMWTable table;
	private OMWColumnMapper columnMapper = new OMWColumnMapper();
	private OMWTableMapper tableMapper = new OMWTableMapper();
	private OMWTableIndexMapper indexMapper = new OMWTableIndexMapper();
	private OMWTableIndexItemMapper indexItemMapper = new OMWTableIndexItemMapper();
	
    public OMWTableParReader(String parPath) {
        super();
        FileManager.unzip(parPath);
        this.parPath = parPath.substring(0, parPath.indexOf(".par"));
    }

    public OMWTable load(){
        this.loadTable();
        
        if(table!= null) {
		    this.loadColumns();
		    this.loadindexes();
        }
        return this.table;
    }
    
    private void loadTable()  {
    	String fileName = parPath + "/F9860.xml";
    	File file = new File(fileName);
    	Document doc = XMLDomReader.createDOMXML(file);
		
		if(doc != null) {
			NodeList nodes = doc.getElementsByTagName("row");	
			Node node = nodes.item(0);
			node = node.getFirstChild();
			while(node != null)
			{
				Element elm = (Element)node;
				
				String tag = elm.getAttribute("name");
				String value = elm.getTextContent();
			
				tableMapper.addRule(tag, value);
				node = node.getNextSibling();
			}
			
			table = tableMapper.map();	
		}    	
    }

    private void loadindexes() {
    	String indexDirectory = parPath + "/specs/DDKEYH/";
    	String[] indexFiles = FileManager.getFilesFromDirectory(indexDirectory, ".xml");
       
        for (String fileDir : indexFiles) {
            File file = new File(indexDirectory + fileDir);
            Document doc = XMLDomReader.createDOMXML(file);
            
            if(doc != null) {
	            NodeList nodes = doc.getElementsByTagName("row");
	            Node node = nodes.item(0);
	
	            loadIndex(node.getFirstChild());
	            OMWTableIndex index = indexMapper.map(); 
	            
	            loadIndexItems(fileDir.substring(0,fileDir.indexOf(".xml")), index);
	            table.addIndex(index);
            }
        }
    }

    private void loadIndex(Node node) {
        if (node != null) {
            Element elm = (Element) node;
            String tag = elm.getAttribute("name");
            String value = elm.getTextContent();
	        indexMapper.addRule(tag, value);
            loadIndex(node.getNextSibling());
        }
    }   
    
    private void loadIndexItems(String IndexFileName, OMWTableIndex index)  {
    	String idexItemsDirectory = parPath + "/specs/DDKEYD/";
    	String[] indexFiles = FileManager.getFilesFromDirectory(idexItemsDirectory, IndexFileName);
       
        for (String fileDir : indexFiles) {
            File file = new File(idexItemsDirectory + fileDir);
            Document doc = XMLDomReader.createDOMXML(file);
            
            if(doc != null) {
                NodeList nodes = doc.getElementsByTagName("row");
                Node node = nodes.item(0);

                loadIndexItem(node.getFirstChild());
                
                OMWTableIndexItem indexItem = indexItemMapper.map();                
                index.addIndexItem(indexItem);
            }
        }
    }
    
    private void loadIndexItem(Node node) {
        if (node != null) {
            Element elm = (Element) node;
            String tag = elm.getAttribute("name");
            String value = elm.getTextContent();
	        indexItemMapper.addRule(tag, value);
	        loadIndexItem(node.getNextSibling());
        }
    }
    
    private void loadColumns() {
    	String columnsDirectory = parPath + "/specs/DDCLMN/";
        String[] columnFiles = FileManager.getFilesFromDirectory(columnsDirectory, ".xml");
        

        for (String fileDir : columnFiles) {
            File file = new File(columnsDirectory  + fileDir);
            Document doc = XMLDomReader.createDOMXML(file);
            	
            if(doc != null) {
                NodeList nodes = doc.getElementsByTagName("row");
                Node node = nodes.item(0);

                loadColumn(node.getFirstChild());
                OMWTableColumn column = columnMapper.map();
                
                table.addColumn(column);
            }
        }
    }

    private void loadColumn(Node node) {
        if (node != null) {
            Element elm = (Element) node;
            String tag = elm.getAttribute("name");
            String value = elm.getTextContent();
	        columnMapper.addRule(tag, value);
            loadColumn(node.getNextSibling());
        }
    }
}


