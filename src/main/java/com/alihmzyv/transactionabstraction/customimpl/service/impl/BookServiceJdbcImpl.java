package com.alihmzyv.transactionabstraction.customimpl.service.impl;

import com.alihmzyv.transactionabstraction.customimpl.annotation.CustomTransactional;
import com.alihmzyv.transactionabstraction.customimpl.entity.Book;
import com.alihmzyv.transactionabstraction.customimpl.repository.BookRepository;
import com.alihmzyv.transactionabstraction.customimpl.repository.impl.BookJpaRepository;
import com.alihmzyv.transactionabstraction.customimpl.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookServiceJdbcImpl implements BookService {

    private final BookRepository bookJdbRepository;
    private final BookJpaRepository bookJpaRepository;

    @Override
    @CustomTransactional
    public void saveBookAndThrowUncheckedException(String title) {
        Book book = new Book();
        book.setTitle(title);
        bookJdbRepository.save(book);
        throw new RuntimeException();
    }

    @Override
    @CustomTransactional
    public void saveBookAndThrowCheckedException(String title) throws Exception {
        Book book = new Book();
        book.setTitle(title);
        bookJdbRepository.save(book);
        throw new Exception();
    }

    @Override
    @Transactional
    public void saveBookModeAspectJ(String title) {
        Book book = new Book();
        book.setTitle(title);
        bookJpaRepository.save(book);
        throw new RuntimeException("Something went wrong");
    }

    @Override
    public void callSaveBookModeAspectJ(String title) {
        saveBookModeAspectJ(title);
    }
}
