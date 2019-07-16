package org.quickMap.dataService.dao.base;

import java.util.List;

import org.quickMap.dataService.dao.model.BeautyFile;
import org.quickMap.dataService.dao.model.FileInfo;
/**
*  @author author
*/
public interface BeautyFileBaseMapper {

    int insertBeautyFile(BeautyFile object);
    int updateFileInfo(BeautyFile object);
    List<BeautyFile> queryBeautyFile(BeautyFile object);
    FileInfo queryFileInfoLimit1(BeautyFile object);

}