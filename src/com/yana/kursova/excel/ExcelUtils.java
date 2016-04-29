package com.yana.kursova.excel;

import com.yana.kursova.domain.Good;
import com.yana.kursova.domain.Shop;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {

    private static final ExcelUtils INSTANCE = new ExcelUtils();

    public static ExcelUtils getInstance() {
        return INSTANCE;
    }

    private ExcelUtils() {
    }

    public void exportShops( File file, List<Shop> shops ) {
        Workbook workbook = new XSSFWorkbook();
        Sheet shopsSheet = workbook.createSheet( "Магазини" );

        int rowIndex = 0;
        for ( Shop shop : shops ) {
            Row row = shopsSheet.createRow( rowIndex++ );
            int cellIndex = 0;

            row.createCell( cellIndex++ ).setCellValue( shop.getId() );
            row.createCell( cellIndex++ ).setCellValue( shop.getName() );
            row.createCell( cellIndex++ ).setCellValue( shop.getAddress() );
            row.createCell( cellIndex++ ).setCellValue( shop.getPhone() );
            row.createCell( cellIndex++ ).setCellValue( shop.getChief() );
            row.createCell( cellIndex++ ).setCellValue( shop.getSite() );
            row.createCell( cellIndex ).setCellValue( shop.getSchedule() );

        }

        write( file, workbook );

    }

    public void exportGoods( File file, List<Good> goods ) {
        Workbook workbook = new XSSFWorkbook();
        Sheet shopsSheet = workbook.createSheet( "Магазини" );

        int rowIndex = 0;
        for ( Good good : goods ) {
            Row row = shopsSheet.createRow( rowIndex++ );
            int cellIndex = 0;

            row.createCell( cellIndex++ ).setCellValue( good.getId() );
            row.createCell( cellIndex++ ).setCellValue( good.getName() );
            row.createCell( cellIndex++ ).setCellValue( good.getType() );
            row.createCell( cellIndex++ ).setCellValue( good.getManufacturer() );
            row.createCell( cellIndex++ ).setCellValue( good.getArticle() );
            row.createCell( cellIndex++ ).setCellValue( good.getPrice() );
            row.createCell( cellIndex++ ).setCellValue( good.getScale() );
            row.createCell( cellIndex++ ).setCellValue( good.getAmount() );
            row.createCell( cellIndex++ ).setCellValue( good.getColor() );
            row.createCell( cellIndex ).setCellValue( good.getSpecifications() );

        }

        write( file, workbook );
    }

    private void write( File file, Workbook workbook ) {
        try ( FileOutputStream fileOutputStream = new FileOutputStream( file ) ) {
            workbook.write( fileOutputStream );
            showOkMessage();
        } catch ( IOException e ) {
            showFailureMessage( e.getMessage() );
            e.printStackTrace();
        }
    }

    private void showFailureMessage( String message ) {
        JOptionPane.showMessageDialog( null, "Сталася помилка: " + message );
    }

    private void showOkMessage() {
        JOptionPane.showMessageDialog( null, "Готово! Дані експортовано!" );
    }

}
