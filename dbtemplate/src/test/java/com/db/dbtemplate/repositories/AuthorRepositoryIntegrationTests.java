package com.db.dbtemplate.repositories;

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
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();

        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent(); // the optional is present
        assertThat(result.get()).isEqualTo(author); // should be same objects
    }

    @Test
    public void testMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);
        Iterable<Author> result = underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);

    }

    @Test
    public void testAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        authorA.setName("UPDATED");
        underTest.save(authorA);

        Optional<Author> result = underTest.findById(authorA.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
        assertThat(result.get().getName()).isEqualTo("UPDATED");
    }

    @Test
    public void testAuthorCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        underTest.deleteById(authorA.getId());
        Optional<Author> result = underTest.findById(authorA.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testGetAuthorsWithAgeLessThanReturnsCorrectAuthors(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<Author> result = underTest.ageLessThan(50);

        assertThat(result)
                .containsExactly(authorB, authorC);

    }

    @Test
    public void testGetAuthorsWithAgeGreaterThanReturnsCorrectAuthors(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        // on purpose, we wont call this ageGreaterThan
        // so that we can demonstrate using HQL instead of this being
        // done by spring data jpa automatically with no help
        Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(50);

        assertThat(result)
                .containsExactly(authorA);
    }

}