package model.objects.tables;

import model.objects.OMWBaseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parreader.omwtables.OMWTableParReader;

import java.util.Comparator;
import java.util.List;

public class OMWTable extends OMWBaseObject implements Comparator<OMWTableColumn> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OMWTable.class);
    private String prefix;
    private List<OMWTableIndex> indexes;
    private List<OMWTableColumn> columns;

    public OMWTable() {
        super();
    }

    public OMWTable(String mainPath, String parName) {
        String parFile = mainPath + "\\" + parName.substring(0, parName.indexOf(".par"));
        OMWTableParReader reader = new OMWTableParReader(parFile);
        reader.load(this);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<OMWTableIndex> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<OMWTableIndex> indexes) {
        this.indexes = indexes;
    }

    public List<OMWTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<OMWTableColumn> columns) {
        this.columns = columns;
    }

    @Override
    public int compare(OMWTableColumn col1, OMWTableColumn col2) {
        return Integer.compare(col1.getSequence(), col2.getSequence());
    }

    public int compare(OMWTableIndex ind1, OMWTableIndex ind2) {
        return Integer.compare(ind1.getSequence(), ind2.getSequence());
    }

    @Override
    public String toString() {
        return this.id + " - " + this.description + " - " + this.prefix;
    }
}
