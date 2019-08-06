package com.jie.util;

import java.io.Closeable;
import java.io.IOException;

public class TcpUtil {
    public static void close(Closeable... closeables) throws IOException {
        for (Closeable closeable:closeables) {
            closeable.close();
        }
    }
}
