package model.objects.tables;

import java.util.Comparator;

public class OMWTableColumn  {
    private String alias;
    private int sequence;
    
    public OMWTableColumn() {
        super();
    }

    public void setAlias(String columnAlias) {
        this.alias = columnAlias;
    }

    public String getAlias() {
        return alias;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }
    @Override
    public String toString(){
        return this.sequence + " - "  + this.alias;
    }
}
