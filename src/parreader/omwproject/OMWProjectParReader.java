package parreader.omwproject;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

import javax.xml.parsers.*;

import model.OMWProject;

import model.objects.OMWObject;

import org.w3c.dom.*;


public class OMWProjectParReader {
    private static  Logger log = Logger.getLogger(OMWProjectParReader.class.getCanonicalName());
    
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
        
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(this.mainManifest);
            
            doc.getDocumentElement().normalize();
            this.readProjectNode(doc);
            
        } catch (Exception e) {
            
            log.severe(e.getMessage());
        }
        finally{
            
        }
        return this.omwProject;
    } 
    
    private List<OMWObject> readObjectsNodes(Node firstOMWObject){        
        Node node = firstOMWObject;
        List<OMWObject>objects = new ArrayList<OMWObject>();
        
        do{
            Element elm = (Element)node;
            OMWObject omwObject = OMWObjectFactory.createInstance(elm, this.parFileDirectory);
            objects.add(omwObject);
            node = node.getNextSibling();
        }while(node != null);
    
        return objects;
    }
    
    private void readProjectNode(Document doc){
        NodeList nodes = doc.getElementsByTagName("omw");
        Node node = nodes.item(0);
        node = node.getFirstChild();
        Element elm = (Element)node;
        
        if(omwProject == null){
            omwProject = new OMWProject();
        }        
        
        this.omwProject.setId(elm.getAttribute("id"));
        this.omwProject.setDescription(elm.getAttribute("description"));
        
        this.omwProject.setObjects(this.readObjectsNodes(node.getFirstChild()));
    }
    
}



