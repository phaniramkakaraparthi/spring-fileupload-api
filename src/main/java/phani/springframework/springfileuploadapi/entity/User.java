package phani.springframework.springfileuploadapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String email;
    @CsvBindByName
    @Column(name = "country")
    private String countryCode;
    @CsvBindByName
    private int age;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
}