import org.apache.xerces.parsers.*;
import org.w3c.dom.*;
import java.io.*;
public class TestParsing {
	private static final String DEFAULT_PARSER_NAME = "dom.wrappers.DOMParser";
    private static Document poll;
    private static float CFFTotal = 0;
    private static float CFFNbr = 0;
    private static float CFFAvg = 0;
    private static float LibTotal = 0;
    private static float LibNbr = 0;
    private static float LibAvg = 0;
    private static float UBSTotal = 0;
    private static float UBSNbr = 0;
    private static float UBSAvg = 0;
    private static NodeList FormList;
    private static NodeList AnswerList;

    //**********************************************************************
    //Cette methode permet de parser un document dont le nom est donnee
    //en entree, elle retourne un noeud de type Document

    public static Document parseDocument(String uri) throws Exception {
	DOMParser parser = new DOMParser();
	parser.parse(uri);
	Document document;
	document = parser.getDocument();
	return document;
    }

    //**********************************************************************
    //Cette methode permet de traiter chaque réponse et d'activer le traitement
    //approprié selon que la question s'intéresse au CFF, UBS ou librairie

    public static void TraiterReponse(Node Answer) {

	Node Ref = ((Element)Answer).getElementsByTagName("QuestionRef").item(0);
	
	String Refcontent = Ref.getFirstChild().getNodeValue();
	
	if (Refcontent.equals("10")) {
		TraiterCFF(Answer);
	}
	if (Refcontent.equals("11")) {
		TraiterLib(Answer);
	}
	if (Refcontent.equals("12")) {
		TraiterUBS(Answer);
	}

    }

    //**********************************************************************
    //Traitement de la réponse associée au CFF


    public static void TraiterCFF(Node Answer) {
	
	CFFNbr ++;
	Node Reponse = ((Element)Answer).getElementsByTagName("ValueOnScale").item(0);
	String Reponsecontent = Reponse.getFirstChild().getNodeValue();
	Float valeur = new Float(0);
	valeur = valeur.valueOf(Reponsecontent);
	CFFTotal = CFFTotal + valeur.floatValue();
    }

    //**********************************************************************
    //Traitement de la réponse associée à la librairie polytechnique

    public static void TraiterLib(Node Answer) {
	LibNbr ++;
	Node Reponse = ((Element)Answer).getElementsByTagName("ValueOnScale").item(0);
	String Reponsecontent = Reponse.getFirstChild().getNodeValue();
	Float valeur = new Float(0);
	valeur = valeur.valueOf(Reponsecontent);
	LibTotal = LibTotal + valeur.floatValue();
    }

    //**********************************************************************
    //Traitement de la réponse associée à l'UBS

    public static void TraiterUBS(Node Answer) {
	UBSNbr ++;
	Node Reponse = ((Element)Answer).getElementsByTagName("ValueOnScale").item(0);
	String Reponsecontent = Reponse.getFirstChild().getNodeValue();
	Float valeur = new Float(0);
	valeur = valeur.valueOf(Reponsecontent);
	UBSTotal = UBSTotal + valeur.floatValue();	
    }


    //**********************************************************************
    //Publication des réponse avec trie décroissant des trois services

    public static void PublierResultats() {
	System.out.println("<html><head>");
	System.out.println("<title>sondage</title>");
	System.out.println("</head><body><h1>Document Multimedia 2002</h1><br>");
	System.out.println("<b>Projet Sondage : Etape 3 - Génération de résultats de sondage avec DOM</b>");
	System.out.println("<br><br>Classification des prestations de certains services &agrave; l'EPFL ");
	System.out.println("selon les r&eacute;ponses des &eacute;tudiants :<br>");
	System.out.println("<br><table cellpadding=\"2\" cellspacing=\"2\" border=\"1\" width=\"400\">");
	System.out.println("<tbody><tr><td valign=\"Top\" width=\"200\">Service<br>");
	System.out.println("</td><td valign=\"Top\">Moyenne sur 5<br></td></tr>");

	if ((CFFAvg >= UBSAvg) && (CFFAvg >= LibAvg)) {
		System.out.println("<tr><td valign=\"Top\">CFF<br></td><td valign=\"Top\">");
		System.out.println(CFFAvg);
		System.out.println("<br></td></tr>");
		if (UBSAvg >= LibAvg) {
		System.out.println("<tr><td valign=\"Top\">UBS<br></td><td valign=\"Top\">");
		System.out.println(UBSAvg);
		System.out.println("<br></td></tr>");
		System.out.println("<tr><td valign=\"Top\">Librairie polytechnique<br></td><td valign=\"Top\">");
		System.out.println(LibAvg);
		System.out.println("<br></td></tr>");
		}
		else {
		System.out.println("<tr><td valign=\"Top\">Librairie polytechnique<br></td><td valign=\"Top\">");
		System.out.println(LibAvg);
		System.out.println("<br></td></tr>");
		System.out.println("<tr><td valign=\"Top\">UBS<br></td><td valign=\"Top\">");
		System.out.println(UBSAvg);
		System.out.println("<br></td></tr>");
		}	
	}

	if ((UBSAvg > CFFAvg) && (UBSAvg >= LibAvg)) {
		System.out.println("<tr><td valign=\"Top\">UBS<br></td><td valign=\"Top\">");
		System.out.println(UBSAvg);
		System.out.println("<br></td></tr>");
		if (CFFAvg >= LibAvg) {
		System.out.println("<tr><td valign=\"Top\">CFF<br></td><td valign=\"Top\">");
		System.out.println(CFFAvg);
		System.out.println("<br></td></tr>");
		System.out.println("<tr><td valign=\"Top\">Librairie polytechnique<br></td><td valign=\"Top\">");
		System.out.println(LibAvg);
		System.out.println("<br></td></tr>");
		}
		else {
		System.out.println("<tr><td valign=\"Top\">Librairie polytechnique<br></td><td valign=\"Top\">");
		System.out.println(LibAvg);
		System.out.println("<br></td></tr>");
		System.out.println("<tr><td valign=\"Top\">CFF<br></td><td valign=\"Top\">");
		System.out.println(CFFAvg);
		System.out.println("<br></td></tr>");
		}	
	}

	if ((LibAvg > UBSAvg) && (LibAvg > CFFAvg)) {
		System.out.println("<tr><td valign=\"Top\">Librairie polytechnique<br></td><td valign=\"Top\">");
		System.out.println(LibAvg);
		System.out.println("<br></td></tr>");
		if (UBSAvg >= CFFAvg) {
		System.out.println("<tr><td valign=\"Top\">UBS<br></td><td valign=\"Top\">");
		System.out.println(UBSAvg);
		System.out.println("<br></td></tr>");
		System.out.println("<tr><td valign=\"Top\">CFF<br></td><td valign=\"Top\">");
		System.out.println(CFFAvg);
		System.out.println("<br></td></tr>");
		}
		else {
		System.out.println("<tr><td valign=\"Top\">CFF<br></td><td valign=\"Top\">");
		System.out.println(CFFAvg);
		System.out.println("<br></td></tr>");
		System.out.println("<tr><td valign=\"Top\">UBS<br></td><td valign=\"Top\">");
		System.out.println(UBSAvg);
		System.out.println("<br></td></tr>");
		}	
	}


	System.out.println("</tbody></table><br></body></html>");
    }

    //**********************************************************************
    // Programme principal : sans argument

    public static void main(String argv[]) throws Exception {

	String parserName = DEFAULT_PARSER_NAME;
	poll = parseDocument("poll.xml");

	FormList = poll.getElementsByTagName("FilledForm");
	for ( int i = 0; i < FormList.getLength() ; i++ ) {
	
	   AnswerList = ((Element)(FormList.item(i))).getElementsByTagName("Answer");
	   for ( int j = 0; j < AnswerList.getLength() ; j++ ) {
		TraiterReponse(AnswerList.item(j));
	   }
	}
	CFFAvg = CFFTotal / CFFNbr ;
	LibAvg = LibTotal / LibNbr ;
	UBSAvg = UBSTotal / UBSNbr ;

	PublierResultats();
    }
}
