package com.gec.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.annotation.JSONField;

public class Document {
    private String id;

    private String documentname;

    private String uploader;

    private String uploaderName;  //上传者名称
    
    private String filename;

    private String filesize;

    private Integer downtimes;

    @JSONField(format="yyyy-MM-dd")
    private Date createdate;

    private String note;

    //{ps}这一个数据项与数据库无关(用作文件上传)
  	//    写入服务器磁盘。
  	private MultipartFile file;    //文件数据
  	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDocumentname() {
        return documentname;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname == null ? null : documentname.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }
    
    public String getUploaderName() {
		return uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize == null ? null : filesize.trim();
    }

    public Integer getDowntimes() {
        return downtimes;
    }

    public void setDowntimes(Integer downtimes) {
        this.downtimes = downtimes;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
    
    public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Document [documentname=" + documentname + ", uploader=" + uploader + ", uploaderName=" + uploaderName
				+ ", filename=" + filename + ", filesize=" + filesize + ", downtimes=" + downtimes + ", createdate="
				+ createdate + ", note=" + note + "]";
	}
	
	
}