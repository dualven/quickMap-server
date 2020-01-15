package org.quickMap.fileService.service.impl;

import java.util.List;

import org.quickMap.dataService.dao.NovelMapper;
import org.quickMap.dataService.dao.model.Chapter;
import org.quickMap.dataService.dao.model.Novel;
import org.quickMap.fileService.service.INovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NovelService implements INovelService {

    @Autowired NovelMapper novelmapper;
    @Override
    public List<Novel> getNovels() throws Exception {
        return novelmapper.getNovels();
    }

    @Override
    public List<Chapter> getChaptersByNovelID(int id) throws Exception {
        Novel n = new Novel();
        n.setNovel_ID(id);
        return novelmapper.getChaptersByNovel(n);
    }

}