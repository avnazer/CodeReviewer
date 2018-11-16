package guiapp;

import model.objects.tables.OMWTable;
import model.objects.tables.OMWTableColumn;
import model.objects.tables.OMWTableIndex;
import model.objects.tables.OMWTableIndexItem;

import java.util.List;

public class Test {
    public Test() {
        super();
    }

    public static void test(List<OMWTable> tables) {
        for (OMWTable table : tables) {
            System.out.println(table.toString());
            System.out.println(" *** COLUMNS *** ");

            for (OMWTableColumn column : table.getColumns()) {
                System.out.println(column.toString());
            }

            System.out.println(" *** INDEX *** ");
            for (OMWTableIndex index : table.getIndexes()) {
                System.out.println(index.toString());
                System.out.println("   -- Index Detail:");
                for (OMWTableIndexItem indexItem : index.getIndexItems()) {
                    System.out.println(indexItem.toString());
                }
            }
        }
    }
}
