package org.example.springboot4.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Document(indexName = "employee")
@Getter
@Setter
public class Employee {
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = Text)
    private String firstName;

    @Field(type = Text)
    private String lastName;

    @Field(type = Date, format = date)
    private LocalDate dob;

    @Field(type = Keyword)
    private String email;

    @Field(type = Keyword)
    private String phoneNumber;

    @Field(type = Keyword)
    private String occupation;

    @Field(type = FieldType.Double)
    private Double salary;

    @Field(type = Keyword)
    private String country;

    @Field(type = Text)
    private String bio;
}
