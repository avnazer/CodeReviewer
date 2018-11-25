package org.coderev.model.objects.tables;

import java.util.Comparator;

public class OMWTableIndexItem implements Comparator<OMWTableIndexItem>{
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

	@Override
	public int compare(OMWTableIndexItem o1, OMWTableIndexItem o2) {
		return Integer.compare(o1.getSequence(), o2.getSequence());
	}
}
