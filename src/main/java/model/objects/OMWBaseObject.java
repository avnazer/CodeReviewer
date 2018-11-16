package model.objects;

public class OMWBaseObject implements OMWObject {
    protected String id;
    protected String description;

    public OMWBaseObject() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
