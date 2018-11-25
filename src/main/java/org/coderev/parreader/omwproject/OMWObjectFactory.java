package org.coderev.parreader.omwproject;

import static org.coderev.model.objects.OMWObjectType.*;

import org.coderev.model.objects.OMWBaseObject;
import org.coderev.model.objects.OMWObjectType;
import org.coderev.model.objects.tables.OMWTable;
import org.coderev.parreader.OMWTableParReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public final class OMWObjectFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(OMWObjectFactory.class);
    private OMWObjectFactory() {
        super();
    }

    /* TODO: FIX! This should not receive a par path! decouple the implementation! */
    public static OMWBaseObject createInstance(Element elm, String parMainPath) {
        OMWBaseObject omwObject;
        OMWObjectType type = valueOf(elm.getAttribute("type"));

        switch (type) {
            case TBLE:
            	
            	String parFile = parMainPath + elm.getAttribute("file").substring(0, elm.getAttribute("file").indexOf(".par"));
            	
            	OMWTableParReader reader = new OMWTableParReader(parFile);
            	omwObject = reader.load();
                
                break;
            case DSTR:
                omwObject = new OMWTable();
                break;
            case UBE:
                omwObject = new OMWTable();
                break;
            case BSFN:
                omwObject = new OMWTable();
                break;
            case BSVW:
                omwObject = new OMWTable();
                break;
            default:
                omwObject = new OMWBaseObject();
                break;
        }

        omwObject.setDescription(elm.getAttribute("description"));
        omwObject.setId(elm.getAttribute("id"));

        return omwObject;
    }
}
