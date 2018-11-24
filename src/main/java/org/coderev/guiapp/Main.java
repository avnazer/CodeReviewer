package org.coderev.guiapp;

import org.coderev.model.OMWProject;
import org.coderev.parreader.omwproject.OMWProjectParReader;

public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) {
        OMWProjectParReader prjReader = new OMWProjectParReader("C:\\Users\\Ver�nica\\Desktop\\Java\\certificacion\\PRJ_VNAZER - BSFN_60_99");
        OMWProject project = prjReader.loadProject("C:\\Users\\Ver�nica\\Desktop\\Java\\certificacion\\PRJ_VNAZER - BSFN_60_99\\manifest.xml");
        //   Test.test(project.getObjects("TBLE"));
    }
}
