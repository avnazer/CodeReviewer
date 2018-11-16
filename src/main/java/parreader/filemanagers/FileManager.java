package parreader.filemanagers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileManager {
    private static final int BUFFER_SIZE = 10000;
    private static Logger log = Logger.getLogger(FileManager.class.getCanonicalName());

    private FileManager() {
        super();
    }

    public static String unZip(String parFilePath, String fileType) {
        StringBuilder parPath = new StringBuilder(parFilePath);
        String mainFolder = parPath.substring(0, parPath.indexOf(".par"));


        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(parFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();

            while (entry != null) {
                if (!entry.isDirectory()) {
                    new File(parPath.toString()).getParentFile().mkdirs();
                    extractFile(zipIn, parPath.toString());
                } else {
                    // if the entry is a directory, make the directory
                    File dir = new File(parPath.toString());
                    dir.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        } catch (IOException e) {
            log.severe(e.getMessage());

        }
        return mainFolder;

    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public static String[] getFilesFromDirectory(String directory, String fileNameFixedPart) {
        String[] files;
        File f = new File(directory);
        FileFilter filter = new FileFilter(fileNameFixedPart);
        files = f.list(filter);
        return files;
    }
}
