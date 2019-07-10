package org.quickMap.fileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quickMap.fileService.model.FileInfoData;
import org.quickMap.fileService.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileController2Test {
  @Autowired
  protected IFileService fileService;

  @Test
  public void testuploadLocal() throws FileNotFoundException {
    File file = new File("C:\\Users\\zhupengfei\\Downloads\\20190531114159_Camera 01_抓拍原图.jpg");
    FileInputStream input = new FileInputStream(file);
    assertThat(file.getName(), equalToIgnoringCase("20190531114159_Camera 01_抓拍原图.jpg"));
    System.out.println(file.getTotalSpace());
  }

  @Test
  public void testuploadlocal() throws Exception {
    File file = new File("C:\\Users\\zhupengfei\\Downloads\\20190531114159_Camera 01_抓拍原图.jpg");
    FileInputStream input;
    try {
      input = new FileInputStream(file);
      FileInfoData data = fileService.uploadFile(input, file.getName(), file.length(), true);
      assertThat(data.getFilename(), equalToIgnoringCase("20190531114159_Camera 01_抓拍原图.jpg"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Test
  public void testSearch() throws Exception {
    List<FileInfoData> data = fileService.search("20190531114159_Camera 01_抓拍原图.jpg", null, null, null, null);
      assertThat(data.get(0).getFilename(),equalToIgnoringCase("20190531114159_Camera 01_抓拍原图.jpg"));
    
  }
  @Test
  public void testpageSearch() throws Exception {
    List<FileInfoData> data = fileService.pagesearch("20190531114159_Camera 01_抓拍原图.jpg", null, null, null, null,0,1);
      assertThat(data.get(0).getFilename(),equalToIgnoringCase("20190531114159_Camera 01_抓拍原图.jpg"));
    
  }
}