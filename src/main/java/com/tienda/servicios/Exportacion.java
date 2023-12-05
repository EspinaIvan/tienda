package com.tienda.servicios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tienda.dao.productos.Producto;

public class Exportacion {

	public static void exportProductData(List<Producto> listaProducto) throws IOException {

		String filePath = "productos/producto_data.xlsx";

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Product Data");

		Row headerRow = sheet.createRow(0);
		String[] headers = { "ID", "Nombre", "Precio", "Stock" };
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}

		int rowNum = 1;
		for (Producto producto : listaProducto) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(producto.getId());
			row.createCell(1).setCellValue(producto.getNombre());
			row.createCell(2).setCellValue(producto.getPrecio());
			row.createCell(3).setCellValue(producto.getStock());
		}

		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}

	}
}
