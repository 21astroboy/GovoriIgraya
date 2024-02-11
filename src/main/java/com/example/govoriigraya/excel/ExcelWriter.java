package com.example.govoriigraya.excel;


import java.util.List;

public interface ExcelWriter {
    <T> byte[] write(List<T> list, String name);
}
