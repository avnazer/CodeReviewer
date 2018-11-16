package model;

import java.util.List;

import model.objects.OMWObject;
import model.objects.tables.OMWTable;

public class OMWProject {
    private String id;
    private String description;
    private List<OMWObject>objects;
    
    public OMWProject() {
        super();
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setObjects(List<OMWObject> objects) {
        this.objects = objects;
    }

    public List<OMWObject> getObjects() {
        return objects;
    }

    public List<OMWObject> getObjects(String objectType){
        return objects;
    }
}
