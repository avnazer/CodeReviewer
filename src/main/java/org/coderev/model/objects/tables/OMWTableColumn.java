package org.coderev.model.objects.tables;

import java.util.Comparator;

public class OMWTableColumn implements Comparator<OMWTableColumn>{
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

	@Override
    public int compare(OMWTableColumn col1, OMWTableColumn col2) {
        return Integer.compare(col1.getSequence(), col2.getSequence());
    }
	
	@Override
	public boolean equals(Object column) {
		return column instanceof OMWTableColumn ? 
				((OMWTableColumn)column).getAlias().equalsIgnoreCase(this.alias) : false;
	}
}
