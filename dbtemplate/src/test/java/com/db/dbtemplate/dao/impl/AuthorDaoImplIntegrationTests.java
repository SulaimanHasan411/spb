package com.db.dbtemplate.dao.impl;

import com.db.dbtemplate.TestDataUtil;
import com.db.dbtemplate.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isPresent(); // the optional is present
        assertThat(result.get()).isEqualTo(author); // should be same objects
    }

    @Test
    public void testMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.create(authorA);
        underTest.create(authorB);
        underTest.create(authorC);
        List<Author> result = underTest.find();

        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);

    }

    @Test
    public void testAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);

        authorA.setName("UPDATED");
        underTest.update(authorA.getId(), authorA);

        Optional<Author> result = underTest.findOne(authorA.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
        assertThat(result.get().getName()).isEqualTo("UPDATED");
    }

    @Test
    public void testAuthorCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);

        underTest.delete(authorA.getId());
        Optional<Author> result = underTest.findOne(authorA.getId());

        assertThat(result).isEmpty();
    }

}
