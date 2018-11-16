package parreader.filemanagers;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

public class XMLDomReader {
    
    private XMLDomReader() {
        super();
    }
    
    public static Document createDOMXML(File file) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder;
        docBuilder = docBuilderFactory.newDocumentBuilder();

        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();
        
        return doc;
    }
}
