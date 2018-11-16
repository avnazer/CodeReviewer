package guiapp;

import model.OMWProject;

import parreader.omwproject.OMWProjectParReader;

public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) {
        OMWProjectParReader prjReader = new OMWProjectParReader("C:\\Users\\Verónica\\Desktop\\Java\\certificacion\\PRJ_VNAZER - BSFN_60_99");
        OMWProject project = prjReader.loadProject("C:\\Users\\Verónica\\Desktop\\Java\\certificacion\\PRJ_VNAZER - BSFN_60_99\\manifest.xml");
     //   Test.test(project.getObjects("TBLE"));
    }
}
