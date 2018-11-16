package model;

import model.objects.OMWObject;

import java.util.List;

public class OMWProject {
    private String id;
    private String description;
    private List<OMWObject> objects;

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

    public List<OMWObject> getObjects() {
        return objects;
    }

    public void setObjects(List<OMWObject> objects) {
        this.objects = objects;
    }

    public List<OMWObject> getObjects(String objectType) {
        return objects;
    }
}
