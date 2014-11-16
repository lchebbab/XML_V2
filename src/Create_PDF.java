
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;




import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class Create_PDF {


    /** The resulting PDF file. */
    public static final String RESULT = "../XML_engine/src/PDF/Contrat.pdf";

    /**
     * Creates a PDF with information about the movies
     * @param    filename the name of the PDF file that will be created.
     * @throws    DocumentException 
     * @throws    IOException 
     * @throws    SQLException
     */
    
    
    public static Paragraph addTitle(){
        Font fontbold = FontFactory.getFont("Times-Roman", 40, Font.BOLD);
        Paragraph p = new Paragraph("CONTRAT D'ECHANGE ", fontbold);
        p.setSpacingAfter(20);
        p.setAlignment(1); // Center
        return p;
   }
    public void create1Pdf(String filename,String per1,String numero1,String per2,String numero2,
    		String obj1,String obj2,String clause,String mode,
    		String discription,String montant,String personne,String taxe,
    		String date)
        throws IOException, DocumentException, SQLException {
    	// step 1
        Document document = new Document();

        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
     
        // step 3
        document.open();
        document.add(addTitle());
        document.add(Chunk.NEWLINE);
        // step 4
        // creates the database connection and statement




        // creates line separators
        Chunk CONNECT = new Chunk(
            new LineSeparator(0.5f, 95, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER, 3.5f));
        LineSeparator UNDERLINE =
            new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);
        // creates tabs
        Chunk tab1 = new Chunk(new VerticalPositionMark(), 200, true);
        Chunk tab2 = new Chunk(new VerticalPositionMark(), 350, true);
        Chunk tab3 = new Chunk(new DottedLineSeparator(), 450, true);
        // loops over the directors
    
            // creates a paragraph with the director name
         
            Paragraph p = new Paragraph("Personnes concernees :"),redFont;
            // adds a separator
            p.add(CONNECT);
            // adds more info about the director
           
            // adds a separator
            p.add(UNDERLINE);
            // adds the paragraph to the document
            document.add(p);
          
   
                p = new Paragraph(per1);
                p.add(new Chunk(tab1));
                p.add(new Chunk(tab3));
                p.add(new Chunk(numero1));       
                document.add(p);
                
                p = new Paragraph(per2);
                p.add(new Chunk(tab1));
                p.add(new Chunk(tab3));
                p.add(new Chunk(numero2));     
  
                document.add(p);
               
           
                
                
                Paragraph p1 = new Paragraph("objet concernees :");
                // adds a separator
                p1.add(CONNECT);
                // adds more info about the director
               
                // adds a separator
                p1.add(UNDERLINE);
                // adds the paragraph to the document
                document.add(p1);
       
                p1 = new Paragraph(obj1);
                p1.add(new Chunk(tab1));
 
                    document.add(p1);
                    
                    p1= new Paragraph(obj2);
                    p1.add(new Chunk(tab1));
   
                    document.add(p1);
                    
                 
               
                  
            document.add(Chunk.NEWLINE);
            

            Paragraph p3 = new Paragraph("Clause de rupture :");
            // adds a separator
            p3.add(CONNECT);
            // adds more info about the director
           
            // adds a separator
            p3.add(UNDERLINE);
            // adds the paragraph to the document
            document.add(p3);
   
            p3 = new Paragraph(clause);
            p3.add(new Chunk(tab1));
    
                document.add(p3);
                
      
                
        document.add(Chunk.NEWLINE);
        
        Paragraph p4 = new Paragraph("Mode de r�solution :");
        // adds a separator
        p4.add(CONNECT);
        // adds more info about the director
       
        // adds a separator
        p4.add(UNDERLINE);
        // adds the paragraph to the document
        document.add(p4);

        p4 = new Paragraph(mode);
        p4.add(new Chunk(tab1));
    
            document.add(p4);
            
   
            
    document.add(Chunk.NEWLINE);
    
    Paragraph p5 = new Paragraph("Description de la Clause Taxation :");
    // adds a separator
    p5.add(CONNECT);
    // adds more info about the director
   
    // adds a separator
    p5.add(UNDERLINE);
    // adds the paragraph to the document
    document.add(p5);

    p5 = new Paragraph(discription);
    p5.add(new Chunk(tab1));
   
        document.add(p5);

        
document.add(Chunk.NEWLINE);

Paragraph p6 = new Paragraph("Montant de la taxe :");
// adds a separator
p6.add(CONNECT);
// adds more info about the director

// adds a separator
p6.add(UNDERLINE);
// adds the paragraph to the document
document.add(p6);

p6 = new Paragraph(montant);
p6.add(new Chunk(tab1));
    
    document.add(p6);
    

    
document.add(Chunk.NEWLINE);


Paragraph p7 = new Paragraph("Taxe due a  :");
// adds a separator
p7.add(CONNECT);
// adds more info about the director

// adds a separator
p7.add(UNDERLINE);
// adds the paragraph to the document
document.add(p7);

p7 = new Paragraph(taxe);
p7.add(new Chunk(tab1));
     
    document.add(p7);

    
document.add(Chunk.NEWLINE);

Paragraph p8 = new Paragraph("Date limite de r�ception :");
//adds a separator
p8.add(CONNECT);
//adds more info about the director

//adds a separator
p8.add(UNDERLINE);
//adds the paragraph to the document
document.add(p8);

p8= new Paragraph(date);
p8.add(new Chunk(tab1));
 
 document.add(p8);

 
document.add(Chunk.NEWLINE);

document.add(new Paragraph("Accus� de r�ception des informations\n Signature des consommateurs: ."));



        
        // step 5
        document.close();

    }
    
    /**
     * Main method.
     *
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException 
     * @throws SQLException
     */
    public static void main(String[] args)
        throws IOException, DocumentException, SQLException {
     
    }


}
