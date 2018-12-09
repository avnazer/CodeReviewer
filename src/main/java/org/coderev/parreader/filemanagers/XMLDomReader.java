package org.coderev.parreader.filemanagers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDomReader {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(XMLDomReader.class);
	
    private XMLDomReader() {
        super();
    }

    public static Document createDOMXML(File file)  {

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = null;
        try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}

        Document doc = null;
		try {
			doc = docBuilder.parse(file);
		} catch (SAXException | IOException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}
        doc.getDocumentElement().normalize();

        return doc;
    }
}
