package com.db.dbtemplate.dao.impl;

import com.db.dbtemplate.TestDataUtil;
import com.db.dbtemplate.domain.Author;
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
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest; //testing the AuthorDaoImpl class

    @Test
    public void testCreateAuthorGeneratesCorrectSQL(){
        // test that create author generates the correct sql
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?,?,?)"), //eq for equals
                eq(1L), eq("A R"), eq(80));
        // dont use string concatenation, this way above protects from sql injection attacks
    }

    @Test
    public void testFindOneAuthorGeneratesCorrectSQL(){
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testFindManyGeneratesCorrectSQL(){
        underTest.find();

        verify(jdbcTemplate).query(
            eq("SELECT id, name, age FROM authors"),
            ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testUpdateGeneratesCorrectSQL(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(3L, author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                1L, "A R", 80, 3L
        );
    }

    @Test
    public void testDeleteGeneratesCorrectSQL(){
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?",
                1L
        );
    }

}
