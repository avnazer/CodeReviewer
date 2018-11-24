package org.coderev.parreader.omwproject;

import org.coderev.model.OMWProject;
import org.coderev.model.objects.OMWBaseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class OMWProjectParReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(OMWProjectParReader.class);

    private String parFileDirectory;
    private File mainManifest;


    private OMWProject omwProject;

    public OMWProjectParReader(String parFileDirectory) {
        super();
        //String mainFolder = FileManager.unZip(parFileDirectory, "PRJ_");
        this.parFileDirectory = parFileDirectory;
    }

    public OMWProject loadProject(String mainManifest) {
        this.mainManifest = new File(mainManifest);

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(this.mainManifest);

            doc.getDocumentElement().normalize();
            this.readProjectNode(doc);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return this.omwProject;
    }

    private void readProjectNode(Document doc) {
        NodeList nodes = doc.getElementsByTagName("omw");
        Node node = nodes.item(0);
        node = node.getFirstChild();
        Element elm = (Element) node;

        if (omwProject == null) {
            omwProject = new OMWProject();
        }

        this.omwProject.setId(elm.getAttribute("id"));
        this.omwProject.setDescription(elm.getAttribute("description"));

        this.omwProject.setObjects(this.readObjectsNodes(node.getFirstChild()));
    }

    private List<OMWBaseObject> readObjectsNodes(Node firstOMWObject) {
        Node node = firstOMWObject;
        List<OMWBaseObject> objects = new ArrayList<OMWBaseObject>();

        do {
            Element elm = (Element) node;
            OMWBaseObject omwObject = OMWObjectFactory.createInstance(elm, this.parFileDirectory);
            objects.add(omwObject);
            node = node.getNextSibling();
        } while (node != null);

        return objects;
    }

}



