package com.alihmzyv.transactionabstraction.customimpl.util;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@RequiredArgsConstructor
@Component
public class CustomSimpleTransactionManager {
    private final CustomThreadLocalConnectionProvider customThreadLocalConnectionProvider;

    @SneakyThrows
    public void beginTransaction() {
        Connection conn = customThreadLocalConnectionProvider.getConnection();
        conn.setAutoCommit(false);
    }

    @SneakyThrows
    public void commit() {
        customThreadLocalConnectionProvider.getConnection().commit();
    }

    @SneakyThrows
    public void rollback() {
        customThreadLocalConnectionProvider.getConnection().rollback();
    }
}
