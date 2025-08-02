package com.db.dbtemplate;

import com.db.dbtemplate.domain.Author;
import com.db.dbtemplate.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L) // L for long
                .name("A R")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L) // L for long
                .name("T C")
                .age(44)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L) // L for long
                .name("J A C")
                .age(24)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("123-456-789")
                .title("shadow")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("987-654-321")
                .title("beyond")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("879-456-123")
                .title("ember")
                .authorId(3L)
                .build();
    }
}
