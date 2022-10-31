# Programmeringsuppgift Omegapoint

Detta program validerar personnummer, samordningsnummer, och organisationsnummer.
Den läser indatan från standard in, ett nummer per rad, och skriver ut alla ogiltiga tal på standard ut.
Om indatan består av endast giltiga tal skrivs inget ut.

## Kompilering

Detta projekt använder sig av Maven.
För att bygga en körbar jar-fil, kör kommandot `mvn package`.
Du behöver minst Java 8 för att bygga projektet och köra programmet.

## Körning

För att köra programmet, kör kommandot `java -jar target/omegapoint-proguppgift-1.0-SNAPSHOT.jar`.
Då kommer du kunna skriva in nummer i terminalen, i ett "REPL-läge".
För att köra programmet i batch-läge kan du förslagsvis köra `java -jar target/omegapoint-proguppgift-1.0-SNAPSHOT.jar < input.txt > output.txt`, om du har indatan i `input.txt` och vill få utdatan skriven till `output.txt`.
