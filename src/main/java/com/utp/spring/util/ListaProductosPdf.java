package com.utp.spring.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.utp.spring.models.entity.Producto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("table_producto.pdf")
public class ListaProductosPdf  extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Producto> listaP = (List<Producto>) model.get("listaProductos");


        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20, -20, 40, 20);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;

        Font fuenteTitulo = FontFactory.getFont("Helvetica", 16, Color.BLUE);

        celda = new PdfPCell(new Phrase("LISTADO GENERAL DE PRODUCTOS", fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(40, 190, 138));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(30);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        PdfPTable tablaCategories = new PdfPTable(5);
        listaP.forEach(produc ->{
            tablaCategories.addCell(produc.getIdproducto().toString());
            tablaCategories.addCell(produc.getCategoria().toString());
            tablaCategories.addCell(produc.getNombre());
            tablaCategories.addCell(produc.getDescripcion());
            tablaCategories.addCell(produc.getPrecio().toString());
        });

        document.add(tablaTitulo);
        document.add(tablaCategories);

    }


}
