package com.alihmzyv.transactionabstraction.customimpl.repository.impl;


import com.alihmzyv.transactionabstraction.customimpl.entity.Book;
import com.alihmzyv.transactionabstraction.customimpl.repository.BookRepository;
import com.alihmzyv.transactionabstraction.customimpl.util.CustomThreadLocalConnectionProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@RequiredArgsConstructor
@Repository
public class BookRepositoryJdbcImpl implements BookRepository {

    private final CustomThreadLocalConnectionProvider customThreadLocalConnectionProvider;

    @SneakyThrows
    @Override
    public void save(Book book) {
        try (PreparedStatement pstmt = customThreadLocalConnectionProvider.getConnection().prepareStatement("INSERT INTO book (title) VALUES (?)")) {
            pstmt.setString(1, book.getTitle());
            pstmt.executeUpdate();
        }
    }
}
