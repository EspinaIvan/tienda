package com.tienda.servicios;

import com.tienda.dao.productos.Producto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.tienda.dao.productos.ProductoInterfaceDAO;

@Service
public class Importacion {

	@Autowired
	private ProductoInterfaceDAO productoDAO;

	public  void importarDatosDesdeExcel() throws IOException {

		try (FileInputStream fileInputStream = new FileInputStream("productos/producto_import.xlsx")) {
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Producto producto = new Producto();
				producto.setNombre(row.getCell(1).getStringCellValue());
				producto.setPrecio(row.getCell(2).getNumericCellValue());
				producto.setStock((int) row.getCell(3).getNumericCellValue());

				productoDAO.actualizarProducto(producto);
			}

			workbook.close();
		}
	}
}
