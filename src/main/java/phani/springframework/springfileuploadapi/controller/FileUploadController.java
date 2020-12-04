package phani.springframework.springfileuploadapi.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import phani.springframework.springfileuploadapi.repository.UserRepository;
import phani.springframework.springfileuploadapi.entity.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@Slf4j
public class FileUploadController {

    private UserRepository userRepository;

    public FileUploadController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/upload-csv-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(User.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<User> users = csvToBean.parse();

                userRepository.saveAll(users);

            } catch (Exception ex) {
                return new ResponseEntity<>("An Error occured while processing csv file" , HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("File uploaded Successfully", HttpStatus.OK);
    }
}
