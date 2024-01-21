package com.axelapp.jiraexcelimporter.utils;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtils {

    /*
     * Returns string value of the cell. If failed, return null
     */
    public static String convertCellValueToString(Cell cell) {
        
        try {
            return cell.getRichStringCellValue().getString();
        } catch (Exception e) {
            return null;
        }   
    }

    public static Long convertCellValueToLong(Cell cell) {
        try {

            if (cell.getCellType() == CellType.STRING) {
                String value = cell.getRichStringCellValue().getString();
                return Long.valueOf(value);
            }

            return Double.valueOf(cell.getNumericCellValue()).longValue();

        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean convertCellValueToBoolean(Cell cell) {
        try {
            return cell.getBooleanCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public static Date convertCellValueToDate(Cell cell) {
        try {
            return cell.getDateCellValue();
        } catch (Exception e) {
            return null;
        }
    }

}
