package model;

import model.objects.OMWBaseObject;

import java.util.List;

public class OMWProject {
    private String id;
    private String description;
    private List<OMWBaseObject> objects;

    public OMWProject() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OMWBaseObject> getObjects() {
        return objects;
    }

    public void setObjects(List<OMWBaseObject> objects) {
        this.objects = objects;
    }

    public List<OMWBaseObject> getObjects(String objectType) {
        return objects;
    }
}
