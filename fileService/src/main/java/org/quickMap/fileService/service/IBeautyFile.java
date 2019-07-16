package org.quickMap.fileService.service;

import com.github.tobato.fastdfs.domain.StorePath;

import org.quickMap.fileService.model.BeautyInfoData;
import org.quickMap.fileService.model.FileInfoData;

import java.io.InputStream;
import java.util.List;

/**
 * 文件服务
 */
public interface IBeautyFile {

    /**
     * 上传文件
     *
     * @param file           文件输入流
     * @param uploadFileName 文件名称
     * @param fileLength     文件长度
     * @param genThumbImage 生成图片缩略图
     * @return
     * @throws Exception
     */
    BeautyInfoData uploadFile(InputStream file, String uploadFileName, long fileLength,boolean genThumbImage) throws Exception;

    /**
     * 上传文件
     *
     * @param file           文件输入流
     * @param uploadFileName 文件名称
     * @param fileLength     文件长度
     * @param author         用户id
     * @param genThumbImage 生成图片缩略图
     * @return
     * @throws Exception
     */
    FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, Integer author,boolean genThumbImage) throws Exception;

   
    /**
     * 下载文件
     *
     * @param storePath
     * @return
     * @throws Exception
     */
    byte[] downloadFile(StorePath storePath) throws Exception;

    /**
     * 下载文件
     *
     * @param fullPath
     * @return
     * @throws Exception
     */
    byte[] downloadFile(String fullPath) throws Exception;


   
    /**
     * 分页查询
     * @param name
     * @param before
     * @param after
     * @param suffix
     * @param author
     * @return
     * @throws Exception
     */
    List<BeautyInfoData> pagesearch(String name,Long before,Long after,String suffix,Integer author,Integer ss,Integer ee)throws Exception;


}
