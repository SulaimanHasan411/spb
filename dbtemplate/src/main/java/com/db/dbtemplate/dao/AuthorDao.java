package com.db.dbtemplate.dao;

import com.db.dbtemplate.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long authorID);

    List<Author> find();

    void update(long id, Author author);

    void delete(long id);
}
