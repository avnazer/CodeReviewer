package org.coderev.parreader.filemanagers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileManager {
    private static final int BUFFER = 10000;
    private static Logger LOGGER = LoggerFactory.getLogger(FileManager.class);

    public static String[] getFilesFromDirectory(String directory, String fileNameFixedPart) {
        File f = new File(directory);
        FileFilter filter = new FileFilter(fileNameFixedPart);
        return f.list(filter);
    }

    public static void unzip(String zipFile)  {
    	ZipFile zip = null;
    	try {
         zip = new ZipFile(new File(zipFile));
    	}catch(IOException e) { 
    		LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}
        Enumeration<? extends ZipEntry> entries = zip.entries();
        String rootPath = getRootPath(zip);

        LOGGER.info("Unzipping {}", zipFile);

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String currentEntry = getCurrentEntryName(entry);
            File destFile = new File(rootPath, currentEntry);
            File destinationParent = destFile.getParentFile();

            // create the parent directory structure if needed
            destinationParent.mkdirs();
            if (!entry.isDirectory()) {
                BufferedInputStream is;
				try {
					is = new BufferedInputStream(zip.getInputStream(entry));
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
					throw new RuntimeException();
				}
                extractEntry(destFile, is);
            }
            if (currentEntry.endsWith(".zip")) {
                unzip(destFile.getAbsolutePath());
            }
        }
    }

    private static void makeRootDir(String rootPath) {
        new File(rootPath).mkdir();
    }

    private static String getRootPath(ZipFile zip) {
        String zipFile = zip.getName();
        zipFile = zipFile.substring(0, zipFile.length() - 4);
        makeRootDir(zipFile);
        return zipFile;
    }

    private static String getCurrentEntryName(ZipEntry entry) {
        String currentEntry = entry.getName();
        return currentEntry.replace("\\", "/");
    }

    private static void extractEntry(File destFile, BufferedInputStream is) {
        try (
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER)) {
            byte data[] = new byte[BUFFER];
            int currentByte;

            // read and write until last byte is encountered
            while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, currentByte);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
