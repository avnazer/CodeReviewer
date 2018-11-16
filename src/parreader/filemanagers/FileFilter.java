package parreader.filemanagers;

import java.io.File;
import java.io.FilenameFilter;

import java.util.List;

public class FileFilter implements FilenameFilter {
    private String fileFixedPart;
    
    public FileFilter(String filesExtention) {
        super();
        this.fileFixedPart = filesExtention;
    }

    @Override
    public boolean accept(File dir, String name) {
        // TODO Implement this method
        return name.toLowerCase().contains(this.fileFixedPart.toLowerCase());
    }

}
