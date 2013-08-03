package ch.genidea.geniweb.base.utility;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUploader {

    private List<MultipartFile> fileList;

    public List<MultipartFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<MultipartFile> fileList) {
        this.fileList = fileList;
    }
}
