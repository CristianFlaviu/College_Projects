package ro.utcn.sd.export;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import ro.utcn.sd.entity.User;


import java.io.FileNotFoundException;
import java.util.List;

public class PdfGenerator implements GenerateFile {




    @Override
    public void generateFile(List<User> users) throws FileNotFoundException {

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("Users.pdf"));
        Document doc = new Document(pdfDoc);
        Table table = new Table(UnitValue.createPercentArray(new float[]{25, 35, 35, 35,35}));
        table.setWidth(UnitValue.createPercentValue(100));
        table.setTextAlignment(TextAlignment.LEFT);

        table.addHeaderCell("No");
        table.addHeaderCell("Email");
        table.addHeaderCell("FirstName");
        table.addHeaderCell("LastName");
        table.addHeaderCell("Room");

        int index=0;
        for(User user:users)
        {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(index++))).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(user.getFirstName())).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(user.getLastName())).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(user.getEmail())).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(user.getRoom().getRoomNumber()))).setBorder(Border.NO_BORDER));
        }
        doc.add(table);
        doc.close();

    }
}