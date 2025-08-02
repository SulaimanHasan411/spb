package com.db.dbtemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    private Long id; //capital L so it is the object, defaults to null instead of 0 for lowercase l
    private String name;
    private Integer age;
}
