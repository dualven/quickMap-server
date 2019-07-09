package org.quickMap.fileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;  
import org.junit.Test;
public class FileController2Test {
    @Test
    public void testuploadLocal() throws FileNotFoundException {
         File file   = new File("C:\\Users\\zhupengfei\\Downloads\\20190531114159_Camera 01_抓拍原图.jpg");
         FileInputStream input = new FileInputStream(file);
         assertThat(file.getName(),equalToIgnoringCase("20190531114159_Camera 01_抓拍原图.jpg"));
         System.out.println(file.getTotalSpace());
       }
    }