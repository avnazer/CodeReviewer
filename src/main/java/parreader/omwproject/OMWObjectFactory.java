package parreader.omwproject;

import model.objects.OMWObject;
import model.objects.OMWObjectType;
import model.objects.tables.OMWTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import static model.objects.OMWObjectType.*;

public final class OMWObjectFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(OMWObjectFactory.class);
    private OMWObjectFactory() {
        super();
    }

    /* TODO: FIX! This should not receive a par path! decouple the implementation! */
    public static OMWObject createInstance(Element elm, String parMainPath) {
        OMWObject omwObject;
        OMWObjectType type = valueOf(elm.getAttribute("type"));

        switch (type) {
            case TBLE:
                omwObject = new OMWTable(parMainPath, elm.getAttribute("file"));
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
                omwObject = new OMWObject();
                break;
        }

        omwObject.setDescription(elm.getAttribute("description"));
        omwObject.setId(elm.getAttribute("id"));

        return omwObject;
    }
}
