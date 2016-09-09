Ce programme Java permet de classifier les trois services de l'EPFL (CFF, UBS, Librairie) sur la base des notes donn�es par les �tudiants sond�s.

Cet exemple comprend un document "poll.xml" qui doit toujours �tre dans le m�me r�pertoire que le programme Java. Ce document contient les r�sultats du sondage r�alis� aupr�s de certains �tudiants.

Installation et fonctionnement :

Copier les fichiers du .Zip dans un r�pertoire.
Ouvrir une console DOS
Ajouter le path vers ce r�pertoire et vers le fichier xerces.jar dans la variable CLASSPATH comme suit : 
	set CLASSPATH=%CLASSPATH%;c:\....\repertoire-exemple;c:\....\xerces.jar

Ex�cuter l'exemple avec la commande : java Sondage > resultat.html

Cette commande permet d'ex�cuter le programme java et de red�riger sa sortie vers le fichier resultat.html

Aller dans le r�pertoire de l'exemple, et ouvrir le fichier resultat.html avec un browser pour voir les r�sultats de la classification.

Remarque : vous pouvez ajouter d'autres �l�ment Answer et re-ex�cuter le programme. Si vous avez respect� la structure du document et que ce dernier reste valide, alors le programme recalculera les nouvelles moyennes.

