package com.utp.spring.util;

import com.utp.spring.models.entity.Producto;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("table_producto")
public class ListaProductosExcel extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"listado-productos.xls\"");
        Sheet hoja = workbook.createSheet("Productos");


        Row filaTitulo = hoja.createRow(0);
        Cell celda = filaTitulo.createCell(0);
        celda.setCellValue("LISTADO GENERAL DE PRODUCTOS");

        Row filaData = hoja.createRow(2);
        String[] columnas = {"ID", "CATEGORIA", "NOMBRE","DESCRIPCION", "PRECIO" };

        for (int i = 0; i< columnas.length; i++) {
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
        }
        @SuppressWarnings("unchecked")
        List<Producto> listaC = (List<Producto>) model.get("listaProductos");

        int numFila = 3;
        for (Producto producto: listaC) {
            filaData = hoja.createRow(numFila);

            filaData.createCell(0).setCellValue(producto.getId());
            filaData.createCell(1).setCellValue(producto.getCategoria().toString());
            filaData.createCell(2).setCellValue(producto.getNombre());
            filaData.createCell(3).setCellValue(producto.getDescripcion());
            filaData.createCell(4).setCellValue(producto.getPrecio());


            numFila ++;
        }
    }
}
