package com.alihmzyv.transactionabstraction.customimpl.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class BookServiceJdbcImplTest {
    @Autowired
    private BookServiceJdbcImpl bookService;

    @Autowired
    private DataSource dataSource;

    @Test
    void whenUncheckedExceptionIsThrownThenBookShouldNotBeSaved() throws SQLException {
        String bookTitle = "Book 1";

        assertThrowsExactly(RuntimeException.class, () -> bookService.saveBookAndThrowUncheckedException(bookTitle));
        assertFalse(bookExistsByTitle(bookTitle));
    }

    @Test
    void whenCheckedExceptionIsThrownThenBookShouldBeSaved() throws SQLException {
        String bookTitle = "Book 1";

        assertThrowsExactly(Exception.class, () -> bookService.saveBookAndThrowCheckedException(bookTitle));
        assertTrue(bookExistsByTitle(bookTitle));
    }

    @Test
    void givenModeIsAspectJThenLocalCallsShouldBeTransactional() throws SQLException {
        String bookTitle = "Book 1";

        assertThrowsExactly(RuntimeException.class, () -> bookService.callSaveBookModeAspectJ(bookTitle));
        assertFalse(bookExistsByTitle(bookTitle));
    }


    private boolean bookExistsByTitle(String title) throws SQLException {
        try (PreparedStatement pstmt = dataSource.getConnection().prepareStatement("select count(*) from book where title = ?")) {
            pstmt.setString(1, title);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        }
    }
}