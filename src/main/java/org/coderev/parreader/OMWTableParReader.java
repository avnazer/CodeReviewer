package org.coderev.parreader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.coderev.mapper.OMWColumnMapper;
import org.coderev.mapper.OMWTableMapper;
import org.coderev.model.objects.tables.OMWTable;
import org.coderev.model.objects.tables.OMWTableColumn;
import org.coderev.model.objects.tables.OMWTableIndex;
import org.coderev.model.objects.tables.OMWTableIndexItem;
import org.coderev.parreader.filemanagers.FileManager;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OMWTableParReader{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWTableParReader.class);
    private String parFileDirectory;
    private OMWTable table;
	private OMWColumnMapper columnMapper = new OMWColumnMapper();
	private OMWTableMapper tableMapper = new OMWTableMapper();
	
    public OMWTableParReader(String parFileDirectory) {
        super();
        this.parFileDirectory = parFileDirectory;

    }

    public OMWTable load() {
        this.loadTable(parFileDirectory + "/F9860.xml");
        
        if(table!= null) {
		    this.loadColumns(parFileDirectory + "/DDCLMN/");
		    this.loadKeys(parFileDirectory + "/DDKEYH/");
        }
        return this.table;
    }
    
    private void loadTable(String tableDefinition) {
    	File file = new File(tableDefinition);
    	Document doc = null;
    	
		try {
			doc = XMLDomReader.createDOMXML(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOGGER.error(e.getMessage());
		}
		
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

    private void loadKeys(String parFileDirectory) {
        String[] columnFiles = FileManager.getFilesFromDirectory(parFileDirectory, ".xml");
        List<OMWTableIndex> indexes = new ArrayList<OMWTableIndex>();
        for (String fileDir : columnFiles) {
            File file = new File(parFileDirectory + fileDir);
            OMWTableIndex index = new OMWTableIndex();

            try {
                Document doc = XMLDomReader.createDOMXML(file);

                NodeList nodes = doc.getElementsByTagName("row");
                Node node = nodes.item(0);

                loadIndex(node.getFirstChild(), index);
                loadIndexDetails(this.parFileDirectory + "/DDKEYD/");
                indexes.add(index);


            } catch (ParserConfigurationException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException | SAXException e) {
                LOGGER.error(e.getMessage());
            }
        }

        Collections.sort(indexes, Comparator.comparingInt(OMWTableIndex::getSequence));
        table.setIndexes(indexes);
    }

    private void loadColumns(String parFileDirectory) {
        String[] columnFiles = FileManager.getFilesFromDirectory(parFileDirectory, ".xml");
        List<OMWTableColumn> columns = new ArrayList<OMWTableColumn>();

        for (String fileDir : columnFiles) {
            File file = new File(parFileDirectory  + fileDir);
            
            try {

                Document doc = XMLDomReader.createDOMXML(file);

                NodeList nodes = doc.getElementsByTagName("row");
                Node node = nodes.item(0);

                loadColumn(node.getFirstChild());
                OMWTableColumn column = columnMapper.map();
                
                table.addColumn(column);
                

            } catch (ParserConfigurationException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException | SAXException e) {
                LOGGER.error(e.getMessage());
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

    private void loadIndex(Node node, OMWTableIndex index) {
        if (node != null) {
            Element elm = (Element) node;

            String attr = elm.getAttribute("name");
            String val = elm.getTextContent();

            if (attr.equalsIgnoreCase("TPDESC") || attr.equalsIgnoreCase("TPPRMF") ||
                    attr.equalsIgnoreCase("TPUNIQ") || attr.equalsIgnoreCase("TPINID")) {
                switch (attr) {
                    case "TPINID":
                        int sequence = Integer.parseInt(val);
                        index.setSequence(sequence);
                        break;

                    case "TPDESC":
                        index.setDescription(val);
                        break;

                    case "TPPRMF":
                        boolean isPrimary = val.equalsIgnoreCase("1");
                        index.setIsPrimary(isPrimary);
                        break;

                    case "TPUNIQ":
                        boolean isUnique = val.equalsIgnoreCase("1");
                        index.setIsUnique(isUnique);
                        break;
                }
            }

            loadIndex(node.getNextSibling(), index);
        }
    }

    private void loadIndexDetails(String indexPrefix) {
        String[] indexDetailFiles = FileManager.getFilesFromDirectory(this.parFileDirectory, indexPrefix);
        

    }

    private void loadIndexDetail(Node node, OMWTableIndexItem indexDetail) {
        if (node != null) {
            Element elm = (Element) node;

            String attr = elm.getAttribute("row");
            String val = elm.getTextContent();

            loadIndexDetail(node.getNextSibling(), indexDetail);
        }
    }
    

}


