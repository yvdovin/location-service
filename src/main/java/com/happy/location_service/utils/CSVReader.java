package com.happy.location_service.utils;

import com.happy.location_service.model.City;
import com.happy.location_service.model.Country;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReader {

    private final static String CANADA_FILE_NAME = "canadacities.xlsx";
    private final static String USA_FILE_NAME = "uscities.xlsx";

    public List<City> readAllCities() throws Exception {
        List<City> cities = readAllCanadaCities();
        cities.addAll(readAllUSACities());
        return cities;
    }

    private List<City> readAllCanadaCities() throws Exception {
        final URL resource = this.getClass().getClassLoader().getResource(CANADA_FILE_NAME);
        final File file = new File(resource.toURI());
        final FileInputStream input = new FileInputStream(file);
        final Workbook workbook = new XSSFWorkbook(input);
        final Sheet sheet = workbook.getSheetAt(0);
        final List<City> cities = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            City city = new City()
                    .setName(row.getCell(1).getStringCellValue())
                    .setCountry(Country.CA)
                    .setProvince(row.getCell(2).getStringCellValue())
                    .setLatitude(row.getCell(4).getNumericCellValue())
                    .setLongitude(row.getCell(5).getNumericCellValue());
            cities.add(city);
        }
        return cities;
    }

    private List<City> readAllUSACities() throws Exception {
        final URL resource = this.getClass().getClassLoader().getResource(USA_FILE_NAME);
        final File file = new File(resource.toURI());
        final FileInputStream input = new FileInputStream(file);
        final Workbook workbook = new XSSFWorkbook(input);
        final Sheet sheet = workbook.getSheetAt(0);
        final List<City> cities = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            City city = new City()
                    .setName(row.getCell(1).getStringCellValue())
                    .setCountry(Country.USA)
                    .setProvince(row.getCell(2).getStringCellValue())
                    .setLatitude(row.getCell(6).getNumericCellValue())
                    .setLongitude(row.getCell(7).getNumericCellValue());
            cities.add(city);
        }
        return cities;
    }
}
