package model.objects.tables;

public class OMWTableIndexItem {
    private String alias;
    private String sortingType;
    private int sequence;

    public OMWTableIndexItem() {
        super();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String name) {
        this.alias = name;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return this.sequence + " - " + this.alias + " - " + this.sortingType;
    }
}
