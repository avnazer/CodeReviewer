package org.coderev.model.objects.tables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.coderev.model.objects.OMWBaseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OMWTable extends OMWBaseObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(OMWTable.class);

    private String prefix;
    private List<OMWTableIndex> indexes = new ArrayList<OMWTableIndex>();
    private List<OMWTableColumn> columns = new ArrayList<OMWTableColumn>();

    public OMWTable() {
        super();
    }
    
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<OMWTableIndex> getIndexes() {
    	return new ArrayList<>(indexes);
    }


    public List<OMWTableColumn> getColumns() {
        return new ArrayList<>(columns);
    }

    @Override
    public String toString() {
        return this.id + " - " + this.description + " - " + this.prefix;
    }

	public void addColumn(OMWTableColumn column) {
		if(!this.columns.contains(column))
		{
			this.columns.add(column);
		}
		Collections.sort(this.columns, Comparator.comparingInt(OMWTableColumn::getSequence));
		
	}
	
	public void addIndex(OMWTableIndex index) {
		if(!this.indexes.contains(index)) {
			this.indexes.add(index);
		}
		Collections.sort(this.indexes, Comparator.comparingInt(OMWTableIndex::getSequence));
	}
	
	@Override
	public boolean equals(Object table) {
		return table instanceof OMWTable ? ((OMWTable)table).getId().equalsIgnoreCase(this.id) : false;
	}
}
