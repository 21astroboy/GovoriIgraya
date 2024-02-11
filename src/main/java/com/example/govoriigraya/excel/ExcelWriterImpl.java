package com.example.govoriigraya.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

@Service
public class ExcelWriterImpl implements ExcelWriter {
    @Override
    public <T> byte[] write(List<T> list, String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);

        int rowNum = 0;
        for (Object obj : list) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;

            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(ExcelCol.class)) {
                    boolean hasProtection = Modifier.isPrivate(field.getModifiers()) || Modifier.isProtected(field.getModifiers());
                    if (hasProtection) field.setAccessible(true);
                    Object value = null;
                    try {
                        value = field.get(obj);
                    } catch (IllegalAccessException ignored) {
                    }
                    if (hasProtection) field.setAccessible(false);

                    row.createCell(cellNum++).setCellValue(value != null ? value.toString() : "");
                }
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException ignored) {
        }

        return outputStream.toByteArray();
    }
}
