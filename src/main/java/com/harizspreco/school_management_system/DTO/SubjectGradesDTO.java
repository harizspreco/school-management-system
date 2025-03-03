package com.harizspreco.school_management_system.DTO;


import lombok.Data;

import java.util.List;

@Data
public class SubjectGradesDTO {
    private String name;
    private List<Integer> grades;

    public SubjectGradesDTO(String name, List<Integer> grades) {
        this.name = name;
        this.grades = grades;
    }
}