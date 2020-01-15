package org.quickMap.fileService.service;


import org.quickMap.dataService.dao.model.Chapter;
import org.quickMap.dataService.dao.model.Novel;
import java.util.List;

/**
 * 文件服务
 */
public interface INovelService {

 
    List<Novel> getNovels() throws Exception;


    List<Chapter> getChaptersByNovelID(int id ) throws Exception;



}
