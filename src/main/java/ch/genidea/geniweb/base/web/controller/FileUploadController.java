package ch.genidea.geniweb.base.web.controller;

import ch.genidea.geniweb.base.component.UserSessionComponent;
import ch.genidea.geniweb.base.domain.DocumentInfo;
import ch.genidea.geniweb.base.repository.DocumentInfoRepository;
import ch.genidea.geniweb.base.utility.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    DocumentInfoRepository documentInfoRepository;
    @Autowired
    UserSessionComponent userSessionComponent;

    @RequestMapping(value = "/base/uploadForm")
    public String uploadForm(){
        return "/base/file/upload";
    }

    @Value("#{appProperties['files.property']}") private String fileProperty;
    @Value("#{appProperties['files.destination']}") private String fileDestination;


    @RequestMapping(value = "/base/fileUpload", method = RequestMethod.POST)
    public String importParse(@RequestParam("myFile") MultipartFile myFile) {
        DocumentInfo documentInfo = new DocumentInfo();
        documentInfo.setFileName(myFile.getOriginalFilename());

        documentInfo.setOwner(userSessionComponent.getCurrentUser());
        documentInfoRepository.persist(documentInfo);

        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        String filePath = System.getProperty(fileProperty) + "/" + fileDestination + "/" +
                timestamp.getTime() + "_" + documentInfo.getId();
        try {

            myFile.transferTo(new File(filePath));
        } catch (Exception e) {
            System.out.println("Error while saving file");
            return "FileUploadForm";
        }

        documentInfo.setFileName(filePath);

        return "redirect:/base/project/list";
    }

    @RequestMapping(value = "/base/show", method = RequestMethod.GET)
    public String displayForm() {
        return "base/file_upload_form";
    }

    @RequestMapping(value = "/base/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("uploadForm") FileUploader uploadForm,
            Model map) {

        List<MultipartFile> files = uploadForm.getFileList();

        List<String> fileNames = new ArrayList<String>();

        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {

                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                //Handle file content - multipartFile.getInputStream()

            }
        }

        map.addAttribute("files", fileNames);
        return "/base/file_upload_success";
    }
}
