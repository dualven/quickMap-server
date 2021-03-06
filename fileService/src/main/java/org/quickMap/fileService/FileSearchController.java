package org.quickMap.fileService;

import org.quickMap.base.BaseController;
import org.quickMap.fileService.service.IBeautyFile;
import org.quickMap.fileService.service.IFilePrefixSuggestionService;
import org.quickMap.fileService.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/search")
public class FileSearchController extends BaseController {

    @Autowired
    protected IFilePrefixSuggestionService suggestionService;


    @Autowired
    protected IFileService fileService;
    @Autowired
    protected IBeautyFile beautyService;

    /**
     * 搜索文件
     * @param fileName 文件全名
     * @param before 时间戳 在..之前
     * @param after  时间戳 在..之后
     * @param suffix 后缀名
     * @return
     * @throws Exception
     */
    @RequestMapping("/exec")
    public String search(@RequestParam(value = "fileName",required = false) String fileName,@RequestParam(value = "before",required = false)Long before,@RequestParam(value = "after",required = false)Long after,@RequestParam(value = "suffix",required = false)String suffix)throws Exception{
        return jsonRender(fileService.search(fileName,before,after,suffix,null));
    }
    @RequestMapping("/pageSearch")
    public String pageSearch(@RequestParam(value = "fileName",required = false) String fileName,@RequestParam(value = "start",required = false)Integer start,@RequestParam(value = "end",required = false)Integer end)throws Exception{
        return jsonRender(fileService.pagesearch(fileName,null,null,null,null,start,end));
    }
    @CrossOrigin
    @RequestMapping("/beautySearch")
    public String beautySearch(@RequestParam(value = "fileName",required = false) String fileName,@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "per_page",required = false)Integer per_page)throws Exception{
        int start = page * per_page;
        int end = per_page;
        return jsonRender(fileService.pagesearch(fileName,null,null,null,null,start,end));
    }
    @CrossOrigin
    @RequestMapping("/beautySearchEx")
    public String beautySearchEx(@RequestParam(value = "fileName",required = false) String fileName,
    @RequestParam(value = "author",required = false,defaultValue="0")Integer author,@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "per_page",required = false)Integer per_page,
    @RequestParam(value = "group",required = false)String group,@RequestParam(value = "tag",required = false)String tag)throws Exception{
        int start = page * per_page;
        int end = per_page;
        return jsonRender(beautyService.pagesearch(fileName,null,null,null,author,start,end,group,tag));
    }
    /**
     * 根据前缀获取完成建议
     *
     * @param prefix
     * @return
     */
    @RequestMapping(value = "/prefix/{prefix}")
    public String prefix(@PathVariable("prefix") String prefix) {
        return jsonRender(suggestionService.getSuggestions(prefix));
    }
}
