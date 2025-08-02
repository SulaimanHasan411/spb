package com.db.dbtemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String isbn;
    private String title;
    private Long authorId;
    // not using author object since we want to make this like out data table
    // the table has a numeric value for author, not another object, so should be same here
    // to make that work
}
