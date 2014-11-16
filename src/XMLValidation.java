import java.io.File;
import java.io.IOException;
 
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
 
import org.xml.sax.SAXException;
 
public class XMLValidation {
	 
    public static void main(String[] args) {
         
      System.out.println("Fiche_Client.xml validates against File.xsd? "+validateXMLSchema("File.xsd", "Fiche_Client.xml"));
      System.out.println("Fiche_Objet.xml validates against File.xsd? "+validateXMLSchema("File.xsd", "Fiche_Objet.xml"));
      System.out.println("File.xml validates against File.xsd? "+validateXMLSchema("File.xsd", "File.xml"));
      }
     
    public static boolean validateXMLSchema(String xsdPath, String xmlPath){
         
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("../XML_engine/src/File.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("../XML_engine/src/File.xml")));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}