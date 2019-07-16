package org.quickMap.dataService.dao;

import org.quickMap.dataService.dao.base.BeautyFileBaseMapper;

import java.util.List;

/**
*  @author author
*/
public interface BeautyFileMapper extends BeautyFileBaseMapper{

    List<String> queryFileNameSet();


}