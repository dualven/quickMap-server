package org.quickMap.fileService.model;

public class BeautyInfoData extends FileInfoData {

    public String image_src;
    public String group;
    public String tag;

    public String getimage_src() {
        return image_src;
    }

    public void setimage_src(String image_src) {
        this.image_src = image_src;
    }

	public void setgroup(String group) {
        this.group = group;
	}

	public void settag(String tag) {
        this.tag = tag;
	}


    
}
