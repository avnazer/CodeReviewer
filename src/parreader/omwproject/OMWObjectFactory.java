package parreader.omwproject;

import model.objects.*;
import model.objects.tables.OMWTable;
import org.w3c.dom.Element;

public final class OMWObjectFactory {
    private OMWObjectFactory() {
        super();
    }
    
    public static OMWObject createInstance(Element elm, String parMainPath){
        OMWObject omwObject;
        switch(elm.getAttribute("type")){
            case "TBLE":                
                omwObject = new OMWTable(parMainPath, elm.getAttribute("file"));
                break;
            case "DSTR":
                omwObject = new OMWTable();
                break;
            case "UBE":
                omwObject = new OMWTable();
                break;
            case "BSFN":
                omwObject = new OMWTable();
                break;
            case "BSVW":
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
