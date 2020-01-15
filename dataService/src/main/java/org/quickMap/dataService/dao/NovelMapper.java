package org.quickMap.dataService.dao;

import java.util.List;

import org.quickMap.dataService.dao.model.Chapter;
import org.quickMap.dataService.dao.model.Novel;
/**
 * @author duanxiongwen
 */
public interface NovelMapper {
    
    List<Novel> getNovels();
    List<Chapter> getChaptersByNovel(Novel novel);
}