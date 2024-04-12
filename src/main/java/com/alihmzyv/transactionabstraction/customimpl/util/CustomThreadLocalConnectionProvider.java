package com.alihmzyv.transactionabstraction.customimpl.util;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@RequiredArgsConstructor
@Component
public class CustomThreadLocalConnectionProvider {
    private static final ThreadLocal<Connection> connections = new ThreadLocal<>();

    private final DataSource dataSource;

    @SneakyThrows
    public Connection getConnection() {
        if (connections.get() == null) {
            connections.set(dataSource.getConnection());
        } else {
            if (connections.get().isClosed()) {
                connections.set(dataSource.getConnection());
            }
        }
        return connections.get();
    }
}
