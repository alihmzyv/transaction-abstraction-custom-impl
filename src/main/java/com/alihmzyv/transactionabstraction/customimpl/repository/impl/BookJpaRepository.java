package com.alihmzyv.transactionabstraction.customimpl.repository.impl;

import com.alihmzyv.transactionabstraction.customimpl.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
