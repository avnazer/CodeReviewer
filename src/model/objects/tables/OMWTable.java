package model.objects.tables;

import java.util.Comparator;
import java.util.List;
import model.objects.OMWObject;

import parreader.omwtables.OMWTableParReader;

public class OMWTable extends OMWObject implements Comparator<OMWTableColumn> {
    private String alias;
    private List<OMWTableIndex>indexes;
    private List<OMWTableColumn>columns;
    
    public OMWTable() {
        super();
    }
    
    public OMWTable(String mainPath, String parName){
        String parFile = mainPath + "\\" + parName.substring(0, parName.indexOf(".par"));
        OMWTableParReader reader = new OMWTableParReader(parFile);
        reader.load(this);   
    }


    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setIndexes(List<OMWTableIndex> indexes) {
        this.indexes = indexes;
    }

    public List<OMWTableIndex> getIndexes() {
        return indexes;
    }

    public void setColumns(List<OMWTableColumn> columns) {
        this.columns = columns;
    }

    public List<OMWTableColumn> getColumns() {
        return columns;
    }

    @Override
    public int compare(OMWTableColumn col1, OMWTableColumn col2) {
        return col1.getSequence()>col2.getSequence() ?  1
             : col1.getSequence()<col2.getSequence() ?  -1
             : 0;
    }
    
    public int compare(OMWTableIndex ind1, OMWTableIndex ind2){
        return ind1.getSequence() > ind2.getSequence() ? 1
             : ind1.getSequence() < ind2.getSequence() ? -1
             : 0;
    }
    
    @Override
    public String toString(){
        return this.id + " - "  + this.description + " - "  + this.alias ;
    }
}
