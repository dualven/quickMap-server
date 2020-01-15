package org.quickMap.fileService;

import org.quickMap.base.BaseController;
import org.quickMap.fileService.service.INovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/novel")
public class NovelController extends BaseController {



    @Autowired
    protected INovelService novelService;

    /**
     * 搜索文件
     * @param fileName 文件全名
     * @param before 时间戳 在..之前
     * @param after  时间戳 在..之后
     * @param suffix 后缀名
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @RequestMapping("/getNovels")
    public String getNovels()throws Exception{
        return jsonRender(novelService.getNovels());
    }
    @CrossOrigin
    @RequestMapping("/getChaptersByID")
    public String pageSearch(@RequestParam(value = "id",required = false) int id)throws Exception{
        return jsonRender(novelService.getChaptersByNovelID(id));
    }
}
