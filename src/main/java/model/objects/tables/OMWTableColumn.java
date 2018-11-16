package model.objects.tables;

public class OMWTableColumn {
    private String alias;
    private int sequence;

    public OMWTableColumn() {
        super();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String columnAlias) {
        this.alias = columnAlias;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return this.sequence + " - " + this.alias;
    }
}
