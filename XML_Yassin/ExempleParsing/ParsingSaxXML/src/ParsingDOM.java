import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
class ParsingDOM {
   public static void main(String[] args) {
      try {
         File x = new File("poll.xml");
         DocumentBuilderFactory f
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder b = f.newDocumentBuilder();
         Document d = b.parse(x);
         printNode(d, "");
      } catch (ParserConfigurationException e) {
         System.out.println(e.toString());      
      } catch (SAXException e) {
         System.out.println(e.toString());      
      } catch (IOException e) {
         System.out.println(e.toString());      
      }
   }
   static void printNode(Node n, String p) {
      NodeList l = n.getChildNodes();
      NamedNodeMap m = n.getAttributes();
      int ml = -1;
      if (m!=null) ml = m.getLength(); 
      System.out.println(p+n.getNodeName()+": "+n.getNodeType()+", "
         +l.getLength()+", "+ml+", "+n.getNodeValue());
      for (int i=0; i<ml; i++) {
         Node c = m.item(i);
         printNode(c,p+" |-");
      }
      for (int i=0; i<l.getLength(); i++) {
         Node c = l.item(i);
         printNode(c,p+" ");
      }
   }
}
