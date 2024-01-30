package ru.hogwarts.school.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Faculty {

    @Id
    @GeneratedValue
    private Long facultyId;
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;
}
