Ce programme Java permet de classifier les trois services de l'EPFL (CFF, UBS, Librairie) sur la base des notes données par les étudiants sondés.

Cet exemple comprend un document "poll.xml" qui doit toujours être dans le même répertoire que le programme Java. Ce document contient les résultats du sondage réalisé auprès de certains étudiants.

Installation et fonctionnement :

Copier les fichiers du .Zip dans un répertoire.
Ouvrir une console DOS
Ajouter le path vers ce répertoire et vers le fichier xerces.jar dans la variable CLASSPATH comme suit : 
	set CLASSPATH=%CLASSPATH%;c:\....\repertoire-exemple;c:\....\xerces.jar

Exécuter l'exemple avec la commande : java Sondage > resultat.html

Cette commande permet d'exécuter le programme java et de redériger sa sortie vers le fichier resultat.html

Aller dans le répertoire de l'exemple, et ouvrir le fichier resultat.html avec un browser pour voir les résultats de la classification.

Remarque : vous pouvez ajouter d'autres élément Answer et re-exécuter le programme. Si vous avez respecté la structure du document et que ce dernier reste valide, alors le programme recalculera les nouvelles moyennes.

