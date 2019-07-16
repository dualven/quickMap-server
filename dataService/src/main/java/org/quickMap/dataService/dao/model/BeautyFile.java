package org.quickMap.dataService.dao.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author author
*/
public class BeautyFile implements Serializable {



    private static final long serialVersionUID = 2985827166125035746L;

    /**
     * 主键
     * 
     * isNullAble:0,defaultVal:
     */
    private String id;

    /**
    * 
    * isNullAble:1
    */
    private String filename;

    /**
    * 
    * isNullAble:1
    */
    private Long size;

    /**
    * 
    * isNullAble:1
    */
    private String image_src;
    private String group;
    private String tag;

    /**
    * 
    * isNullAble:1
    */
    private Long timestamp;

    /**
    * 
    * isNullAble:1
    */
    private Integer author;

    /**
    * 
    * isNullAble:0,defaultVal:1
    */
    private Integer isdel;

    /**
    * 
    * isNullAble:1
    */
    private String thumbImagePath;
    public int start;

    public int end;

    /**
    * 
    * isNullAble:1
    */
    private String suffix;


    public void setId(String id){this.id = id;}

    public String getId(){return this.id;}

    public void setFilename(String filename){this.filename = filename;}

    public String getFilename(){return this.filename;}

    public void setSize(Long size){this.size = size;}

    public Long getSize(){return this.size;}

    public void setImage_src(String image_src){this.image_src = image_src;}

    public String getGroup(){return this.group;}
    public void setGroup(String group){this.group = group;}

    public String getTag(){return this.tag;}
    public void setTag(String tag){this.tag = tag;}

    public String getImage_src(){return this.image_src;}

    public void setTimestamp(Long timestamp){this.timestamp = timestamp;}

    public Long getTimestamp(){return this.timestamp;}

    public void setAuthor(Integer author){this.author = author;}

    public Integer getAuthor(){return this.author;}

    public void setIsdel(Integer isdel){this.isdel = isdel;}

    public Integer getIsdel(){return this.isdel;}

    public void setThumbImagePath(String thumbImagePath){this.thumbImagePath = thumbImagePath;}

    public String getThumbImagePath(){return this.thumbImagePath;}

    public void setSuffix(String suffix){this.suffix = suffix;}

    public String getSuffix(){return this.suffix;}
    @Override
    public String toString() {
        return "BeautyFile{" +
                "id='" + id + '\'' +
                "filename='" + filename + '\'' +
                "size='" + size + '\'' +
                "image_src='" + image_src + '\'' +
                "group='" + group + '\'' +
                "tag='" + tag + '\'' +
                "timestamp='" + timestamp + '\'' +
                "author='" + author + '\'' +
                "isdel='" + isdel + '\'' +
                "start='" + start + '\'' +
                "end='" + end + '\'' +
                "thumbImagePath='" + thumbImagePath + '\'' +
                "suffix='" + suffix + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}


    public static class Builder {

        private BeautyFile obj;

        public Builder(){
            this.obj = new BeautyFile();
        }

        public Builder id(String id){
            this.obj.setId(id);
            return this;
        }
        public Builder filename(String filename){
            this.obj.setFilename(filename);
            return this;
        }
        public Builder size(Long size){
            this.obj.setSize(size);
            return this;
        }
        public Builder image_src(String image_src){
            this.obj.setImage_src(image_src);
            return this;
        }
        public Builder group(String group){
            this.obj.setGroup(group);
            return this;
        }
        public Builder tag(String tag){
            this.obj.setTag(tag);
            return this;
        }
        public Builder timestamp(Long timestamp){
            this.obj.setTimestamp(timestamp);
            return this;
        }
        public Builder author(Integer author){
            this.obj.setAuthor(author);
            return this;
        }
        public Builder isdel(Integer isdel){
            this.obj.setIsdel(isdel);
            return this;
        }
        public Builder thumbImagePath(String thumbImagePath){
            this.obj.setThumbImagePath(thumbImagePath);
            return this;
        }
        public Builder suffix(String suffix){
            this.obj.setSuffix(suffix);
            return this;
        }
        public BeautyFile build(){return this.obj;}
    }

}
