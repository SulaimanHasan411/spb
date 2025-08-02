package com.db.dbtemplate.dao.impl;

import com.db.dbtemplate.TestDataUtil;
import com.db.dbtemplate.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testCreateBookGeneratesCorrectSQL(){
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("123-456-789"), eq("shadow"), eq(1L)
        );
    }

    @Test
    public void testFindOneBookGeneratesCorrectSQL(){
        underTest.findOne("123-456-789");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("123-456-789")
        );
    }

    @Test
    public void testFindGeneratesCorrectSQL(){
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testUpdateGeneratesCorrectSQL(){
        Book book = TestDataUtil.createTestBookA();
        underTest.update("123-456-789", book);

        verify(jdbcTemplate).update(
          "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "123-456-789", "shadow", 1L, "123-456-789"
        );
    }

    @Test
    public void testDeleteGeneratesCorrectSQL(){
        underTest.delete("123-456-789");
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "123-456-789"
        );
    }

}
