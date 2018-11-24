package org.coderev.parreader.omwtables;

import org.coderev.model.objects.tables.OMWTable;
import org.coderev.model.objects.tables.OMWTableColumn;
import org.coderev.model.objects.tables.OMWTableIndex;
import org.coderev.model.objects.tables.OMWTableIndexItem;
import org.coderev.parreader.filemanagers.FileManager;
import org.coderev.parreader.filemanagers.XMLDomReader;
import org.coderev.parreader.omwproject.OMWObjectFactory;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class OMWTableParReader {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OMWTableParReader.class);
    private String parFileDirectory;
    private OMWTable omwTable;

    public OMWTableParReader(String parFileDirectory) {
        super();
        //FileManager.unZip(parFileDirectory, "TBLE_");
        this.parFileDirectory = parFileDirectory;

    }

    public void load(OMWTable omwTable) {
        this.omwTable = omwTable;
        this.loadColumns(parFileDirectory + "/DDCLMN/");
        this.loadKeys(parFileDirectory + "/DDKEYH/");
    }


    private void loadKeys(String parFileDirectory) {
        String[] columnFiles = FileManager.getFilesFromDirectory(parFileDirectory, ".xml");
        List<OMWTableIndex> indexes = new ArrayList<OMWTableIndex>();
        for (String fileDir : columnFiles) {
            File file = new File(parFileDirectory + "\\" + fileDir);
            OMWTableIndex index = new OMWTableIndex();

            try {
                Document doc = XMLDomReader.createDOMXML(file);

                NodeList nodes = doc.getElementsByTagName("row");
                Node node = nodes.item(0);

                loadIndex(node.getFirstChild(), index);
                loadIndexDetails(this.parFileDirectory + "\\DDKEYD\\ + ");
                indexes.add(index);


            } catch (ParserConfigurationException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException | SAXException e) {
                LOGGER.error(e.getMessage());
            }
        }

        Collections.sort(indexes, Comparator.comparingInt(OMWTableIndex::getSequence));
        omwTable.setIndexes(indexes);
    }

    private void loadColumns(String parFileDirectory) {
        String[] columnFiles = FileManager.getFilesFromDirectory(parFileDirectory, ".xml");
        List<OMWTableColumn> columns = new ArrayList<OMWTableColumn>();

        for (String fileDir : columnFiles) {
            File file = new File(parFileDirectory  + fileDir);
            OMWTableColumn column = new OMWTableColumn();
            try {

                Document doc = XMLDomReader.createDOMXML(file);

                NodeList nodes = doc.getElementsByTagName("row");
                Node node = nodes.item(0);

                loadColumn(node.getFirstChild(), column);
                columns.add(column);

            } catch (ParserConfigurationException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException | SAXException e) {
                LOGGER.error(e.getMessage());
            }
        }

       /* Collections.sort(columns, new Comparator<OMWTableColumn>(){
            public int compare(OMWTableColumn o1, OMWTableColumn o2){
                System.out.println(o1.getSequence() +  " - " + o2.getSequence());
                return o1.getSequence()>o2.getSequence()? 1 
                     : o1.getSequence()< o2.getSequence()? -1
                     : 0;
                }
        });*/


        Collections.sort(columns, Comparator.comparingInt(OMWTableColumn::getSequence));

        omwTable.setColumns(columns);
    }

    private void loadColumn(Node node, OMWTableColumn column) {
        if (node != null) {
            Element elm = (Element) node;

            String attr = elm.getAttribute("name");
            String val = elm.getTextContent();

            if (attr.equalsIgnoreCase("TDOBND") || attr.equalsIgnoreCase("TDPSEQ")) {
                switch (attr) {
                    case "TDOBND":
                        column.setAlias(val);
                        break;

                    case "TDPSEQ":
                        int sequence = Integer.parseInt(val);
                        column.setSequence(sequence);
                        break;
                }
            }

            loadColumn(node.getNextSibling(), column);
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


