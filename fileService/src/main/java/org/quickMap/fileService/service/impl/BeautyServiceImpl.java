package org.quickMap.fileService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.quickMap.Utils.EncryptDesUtil;
import org.quickMap.Utils.FileOperatorUtil;
import org.quickMap.constant.FileServiceConstant;
import org.quickMap.constant.FileServiceConstant.Meta;
import org.quickMap.fileService.cfg.FdfsConstant;
import org.quickMap.fileService.model.BeautyInfoData;
import org.quickMap.fileService.service.IBeautyFile;
import org.quickMap.fileService.service.IFilePrefixSuggestionService;
import org.quickMap.dataService.dao.BeautyFileMapper;
import org.quickMap.dataService.dao.model.BeautyFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BeautyServiceImpl implements IBeautyFile {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected FastFileStorageClient client;

    @Autowired
    protected IFilePrefixSuggestionService sug;

    @Autowired
    protected BeautyFileMapper fileInfoMapper;

    @Autowired
    protected FdfsConstant fdfsConstant;

    @Autowired
    protected ThumbImageConfig thumbImageCfg;

    @Value("${des.seed}")
    private String desSeed;

    private EncryptDesUtil encrypter;

    @Override
    public BeautyInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, boolean genThumbImage) throws Exception {
        return uploadFile(file, uploadFileName, fileLength, 0, genThumbImage,null ,null);
    }

    @Override
    public BeautyInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, Integer author, boolean genThumbImage,String group,String tag) throws Exception {
        Assert.hasText(uploadFileName, "文件名不能为空");
        Assert.notNull(file, "文件不能为空");

        BeautyFile fileInfo = new BeautyFile();
        Set<MetaData> metaData = genMeta(fileInfo);

        StorePath storePath = genThumbImage && isSupportThumbImageType(uploadFileName) ?
                client.uploadImageAndCrtThumbImage(file, fileLength, FileOperatorUtil.getSuffix(uploadFileName), metaData) :
                client.uploadFile(file, fileLength, genStoreFileName(uploadFileName), metaData);
        fileInfo.setId(UUID.randomUUID().toString());
        fileInfo.setSize(fileLength);//文件大小
        fileInfo.setImage_src(storePath.getFullPath());//完整路径
        fileInfo.setTimestamp(System.currentTimeMillis());//时间戳
        fileInfo.setAuthor(author);//创建者
        fileInfo.setFilename(uploadFileName);//文件名
        fileInfo.setSuffix(FileOperatorUtil.getSuffix(uploadFileName));//后缀
        fileInfo.setThumbImagePath(genThumbImage ? thumbImageCfg.getThumbImagePath(storePath.getFullPath()) : null);//缩略图地址
        fileInfo.setGroup(group);
        fileInfo.setTag(tag);
            sug.addSugKey(metaData, uploadFileName);

        fileInfoMapper.insertBeautyFile(fileInfo);
        return solveFileInfoData(fileInfo);
    }

   

    @Override
    public byte[] downloadFile(String fullPath) {
        Assert.hasText(fullPath, "完整路径不能为空");
        return downloadFile(getStorePath(fullPath));
    }

    @Override
    public byte[] downloadFile(StorePath storePath) {
        Assert.notNull(storePath, "存储路径不能为空");
        return client.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
    }

   

   
    /**
     * [{"id":"fengjing05","title":"\u98ce\u666f","name":"fengjing05","img_num":10,"img_src":"https:\/\/img-cdn-qiniu.dcloud.net.cn\/tuku\/img\/fengjing05.jpg"},
     */
    @Override
    public List<BeautyInfoData> pagesearch(String name,Long before,Long after,String suffix,Integer author,Integer ss,Integer ee,String group,String tag) {
        // if((!StringUtils.hasText(name)) && (!StringUtils.hasText(suffix)) && before == null && after == null && author == null){
        //     return Collections.emptyList();
        // }
        suffix = suffix != null ? suffix.replace(".","") : null;
        List<BeautyFile> f = fileInfoMapper.queryBeautyFile(BeautyFile.Build()
        .suffix(suffix).filename(name).author(author).isdel(FileServiceConstant.DelStatus.common).group(group).tag(tag).build());
        if (f.size() == 0 && StringUtils.hasText(name)) {
            sug.deleteSugKey(name);
        }
        return solveFileInfoData(f);
    }
    

    /**
     * 是否支持生成缩略图
     * @param uploadFileName 文件名称
     * @return
     */
    protected boolean isSupportThumbImageType(String uploadFileName) {
        final List<String> list = Arrays.asList("jpeg", "jpg", "bmp", "png");
        return list.indexOf(FileOperatorUtil.getSuffix(uploadFileName)) > -1;
    }

    /**
     * 获取加密工具
     *
     * @return
     */
    protected EncryptDesUtil getEncrypter() {
        if (encrypter == null) {
            encrypter = new EncryptDesUtil(desSeed);
        }
        return encrypter;
    }

    /**
     * 获取下载地址
     *
     * @param uploadFileName 上传文件名
     * @param fullPath 下载地址
     * @return
     */
    protected String getDownloadUrl(String uploadFileName, String fullPath) {
        return fdfsConstant.getDownloadServer() + fullPath + "?attname=" + uploadFileName;
    }

    /**
     * 获取路径对象
     * @param fullPath 完整下载路径
     * @return
     */
    protected StorePath getStorePath(String fullPath) {
        if (!StringUtils.hasText(fullPath)) {
            return null;
        }
        int i;
        String group = fullPath.substring(0, (i = fullPath.indexOf("/")));
        String path = fullPath.substring(i + 1);
        StorePath storePath = new StorePath();
        storePath.setPath(path);
        storePath.setGroup(group);
        return storePath;
    }

    /**
     * 获取删除地址
     * @param param id
     * @return
     */
    protected String getDelParam(Object param) {
        Assert.notNull(param, "参数不能为空");
        if (param instanceof String) {
            Assert.hasText((String) param, "参数不能为空");
        }
        return getEncrypter().encrypt(String.valueOf(param));
    }


    /**
     * 生成元数据
     *
     * @return
     */
    protected Set<MetaData> genMeta(BeautyFile fileInfo) {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData(Meta.FILENAME, fileInfo.getFilename()));
        return metaDataSet;
    }

    /**
     * 重新编码文件存储名称,解决上传失败,错误码22问题
     *
     * @param original 原文件名
     * @return
     */
    protected String genStoreFileName(String original) {
        if (StringUtils.hasText(original)) {
            String separator = ".";
            int i;
            String s = original.substring(0, (i = original.indexOf(separator)) == -1 ? original.length() : i);
            String suffix = i != -1 ? original.substring(i) : "";
            return DigestUtils.sha1Hex(s) + suffix;
        } else return "";
    }


    protected List<BeautyInfoData> solveFileInfoData(List<BeautyFile> fileInfos) {
        Assert.notNull(fileInfos, "");
        return fileInfos.stream().map(f -> solveFileInfoData(f)).collect(Collectors.toList());
    }

    protected BeautyInfoData solveFileInfoData(BeautyFile info) {
        BeautyInfoData data = new BeautyInfoData();
        data.setAuthor(info.getAuthor());
        data.setSize(info.getSize());
        data.setTimestamp(info.getTimestamp());
        data.setDelParam(getDelParam(info.getId()));
        data.setFilename(info.getFilename());
        data.setimage_src(info.getImage_src());
        data.setDownloadUrl(getDownloadUrl(info.getFilename(), info.getImage_src()));
        data.setThumbImagePath(info.getThumbImagePath() != null ? fdfsConstant.getDownloadServer() + info.getThumbImagePath() : null);
        return data;
    }



}
