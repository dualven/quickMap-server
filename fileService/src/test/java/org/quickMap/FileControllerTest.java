package org.quickMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {
  @Autowired
  private MockMvc mockMvc;

  // @Test
  // public void testuploadLocal() throws Exception {
  //   JSONObject jsonObject = new JSONObject();
  //       jsonObject.put("fileName", "20190531114159_Camera 01_抓拍原图.jpg");
  //       jsonObject.put("page", 0);
  //       jsonObject.put("per_page", 5);
  //       jsonObject.put("group", "1");
  //       jsonObject.put("tag", "girl");
  //       System.out.println("kanaka ");
  //       this.mockMvc.perform(MockMvcRequestBuilders.post("/search/beautySearchEx").
  //       content(jsonObject.toString()).
  //       contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
  //       .andExpect(jsonPath("$.result").value("success"));
  // }
  /**
   * http://10.60.2.193:28091/search/beautySearchEx?page=0&per_page=5&group=1&tag=girl&fileName=20190531114159_Camera%2001_%E6%8A%93%E6%8B%8D%E5%8E%9F%E5%9B%BE.jpg
   * [{"author":0,"delParam":"3C8C8CC47C103AB7722CAA16DD22759775E787A61627A91CFBE565CAD8EF68E3C8582CBA62AEBB9A","downloadUrl":"http://x.dualven.cn:9080/null?attname=20190531114159_Camera 01_抓拍原图.jpg","filename":"20190531114159_Camera 01_抓拍原图.jpg","size":199757,"thumbImagePath":"http://x.dualven.cn:9080/group1/M00/00/05/CjwCr10tL3SAKfiyAAMMTUiLU_8133_80x60.jpg","timestamp":1563242356311}]
   * @throws Exception
   */
  // @Test
  // public void testUploadBeauty() throws Exception {
  //   JSONObject jsonObject = new JSONObject();
  //       jsonObject.put("fileName", "20190531114159_Camera 01_抓拍原图.jpg");
  //       jsonObject.put("page", 0);
  //       jsonObject.put("per_page", 5);
  //       jsonObject.put("group", "1");
  //       jsonObject.put("tag", "girl");
  //       System.out.println("kanaka ");
  //       this.mockMvc.perform(get("/file/uploadBeauty?fileName="+ jsonObject.get("fileName")
  //       + "&page=0&per_page=5&group='1'&tag='girl'"
  //       )
  //       )
  //      .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
  //       // .andExpect(jsonPath("$")[0].fileName.value("20190531114159_Camera 01_抓拍原图.jpg"));
  // }
  @Test
  public void testsearchController() throws Exception {
    JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileName", "20190531114159_Camera 01_抓拍原图.jpg");
        jsonObject.put("page", 0);
        jsonObject.put("per_page", 5);
        jsonObject.put("group", "1");
        jsonObject.put("tag", "girl");
        System.out.println("kanaka ");
        this.mockMvc.perform(get("/search/beautySearchEx?fileName="+ jsonObject.get("fileName")
        + "&page=0&per_page=5&group='1'&tag='girl'"
        )
        )
       .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        // .andExpect(jsonPath("$")[0].fileName.value("20190531114159_Camera 01_抓拍原图.jpg"));
  }
}