import java.io.File;
import java.io.IOException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
class SaxBrowser {
   public static void main(String[] args) {
      try {
         File x = new File("poll.xml");
         SAXParserFactory f = SAXParserFactory.newInstance();
         SAXParser p = f.newSAXParser();
         DefaultHandler h = new MyContentHandler();
         p.parse(x,h);
      } 
      catch (ParserConfigurationException e) {
         System.out.println(e.toString()); 
      } 
      catch (SAXException e) {
         System.out.println(e.toString()); 
      } 
      catch (IOException e) {
         System.out.println(e.toString()); 
      }
   }
   private static class MyContentHandler extends DefaultHandler {
      static String p = "_";
      public void startDocument() throws SAXException {
         System.out.println("Starting document...");
      }
      public void endDocument() throws SAXException {
         System.out.println("Ending document...");
      }
      public void startElement(String ns, String sName, String qName,
         Attributes attrs) throws SAXException {
         String eName = sName;
         if (sName.equals("")) eName = qName;
         System.out.println("e"+p+eName);
         if (attrs!=null) {
            for (int i=0; i<attrs.getLength(); i++) {
               String aName = attrs.getLocalName(i);
               if (aName.equals("")) aName = attrs.getQName(i);
               System.out.println("a"+p+" "+aName+"="
                  +attrs.getValue(i));
            }
         }
         p = p + "_";
      }
      public void endElement(String ns, String sName, String qName)
         throws SAXException {
         p = p.replaceFirst("__", "_");
      }
      public void characters(char buf[], int offset, int len)
         throws SAXException {
         String s = new String(buf, offset, len);
         System.out.println("c"+p+s);
      }
      public void ignorableWhitespace(char buf[], int offset, int len)
         throws SAXException {
         String s = new String(buf, offset, len);
         System.out.println("i"+p+s);
      }
   }
}
