package ro.sd.a2.util;/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34480476
 */


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.Transform;
import com.itextpdf.layout.property.UnitValue;
import ro.sd.a2.entity.Factura;
import ro.sd.a2.entity.SavingAccount;
import ro.sd.a2.entity.Tranzaction;
import ro.sd.a2.service.TranzactionService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratePDF implements GenerateFile {


    public void generateFile(List<Tranzaction> tranzactionList) throws FileNotFoundException {


        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("Transcations.pdf"));
        Document doc = new Document(pdfDoc);


        Table table = new Table(UnitValue.createPercentArray(new float[]{75, 75, 75, 75}));
        table.setWidth(UnitValue.createPercentValue(120));
        table.setTextAlignment(TextAlignment.LEFT);

        table.addHeaderCell("ID");
        table.addHeaderCell("Date");
        table.addHeaderCell("ID Factura");
        table.addHeaderCell("ID Account");

        for (Tranzaction tranzaction : tranzactionList) {

            table.addCell(new Cell().add(new Paragraph(tranzaction.getId())).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph(tranzaction.getDate().toString())).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph(tranzaction.getFactura().getId())).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(tranzaction.getGeneralAccount().getId()))).setBorder(Border.NO_BORDER));

        }
        doc.add(table);

        doc.close();
    }


}