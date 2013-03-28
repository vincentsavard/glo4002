GLO 4002 : Qualité du logiciel
==============================

Commandes maven utiles
* `mvn install` afin d'installer les projets;
* `mvn test` afin de lancer les tests unitaires;
* `mvn integration-test -pl acceptanceTests` afin de lancer les tests d'acceptation;
* `mvn exec:java -pl centralServer` afin de lancer le serveur central;
* `mvn exec:java -pl emergencyServer` afin de lancer le serveur d'urgence.
