package model.objects.tables;

import java.util.List;

public class OMWTableIndex {
    private int sequence;
    private boolean isPrimary;
    private boolean isUnique;
    private String description;
    
    private List<OMWTableIndexItem>indexItems;
    
    public OMWTableIndex() {
        super();
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public boolean isIsPrimary() {
        return isPrimary;
    }

    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public boolean isIsUnique() {
        return isUnique;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIndexItems(List<OMWTableIndexItem> indexItems) {
        this.indexItems = indexItems;
    }

    public List<OMWTableIndexItem> getIndexItems() {
        return indexItems;
    }
    
    @Override
    public String toString(){
        return this.sequence + " - "  + this.description;
    }
}
