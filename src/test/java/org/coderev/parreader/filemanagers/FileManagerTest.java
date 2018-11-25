package org.coderev.parreader.filemanagers;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {

    private static Logger LOGGER = LoggerFactory.getLogger(FileManagerTest.class);

    @Test
    public void test_unzip_file() throws IOException {
        //TODO: This is a super fragile test!! Needs improvement
        FileManager manager = new FileManager();
        String path = getSanitizedPath("/testZippedFIle.par");

        manager.unzip(path);

        String unzippedPath = getSanitizedPath("/testZippedFIle");
        File unzipped = new File(unzippedPath);

        assertTrue(unzipped.isDirectory());
        File[] files = unzipped.listFiles();

        assertThat(new File(getSanitizedPath("/testZippedFIle/include")), isIn(files));
    }

    private String getSanitizedPath(String fileName) {
        URL url = FileManagerTest.class.getResource(fileName);
        String decodedPath = "";
        try {
            decodedPath = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Encoding error ", e);
        }
        return decodedPath;
    }

}