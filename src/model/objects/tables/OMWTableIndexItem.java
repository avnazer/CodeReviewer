package model.objects.tables;

public class OMWTableIndexItem {
    private String alias;
    private String sortingType;
    private int sequence;
    
    public OMWTableIndexItem() {
        super();
    }

    public void setAlias(String name) {
        this.alias = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }
    
    @Override
    public String toString(){
        return this.sequence + " - "  + this.alias + " - "  + this.sortingType;
    }
}
