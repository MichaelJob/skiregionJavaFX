# Custom Control für SkiAreasFX

## Bearbeitet von
 - Widmer, Ina
 - Job, Michael


## Bei Problemen mit dem IntelliJ-Setup
Es kommt immer wieder mal vor, dass der Setup des IntelliJ-Projekts nicht auf Anhieb funktioniert oder "plötzlich"
nicht mehr funktioniert.

Sie brauchen in so einem Fall NICHT nochmal den Invitation-Link annehmen oder das Projekt via “Check out from Version Control” nochmal anlegen.

Statt dessen ist es am besten den IntelliJ-Setup neu generieren zu lassen. Dazu verwendet man den File "build.gradle", der eine 
komplette und IDE-unabhängige Projektbeschreibung enthält.

Die einzelnen Schritte:

- Schliessen Sie alle geöffneten Projekte (File -> Close Project)

- Wählen Sie “OPEN” 

- Es erscheint ein Finder-Fenster mit dem Sie zu ihrem Projekt navigieren.

- Dort wählen Sie den File “build.gradle” aus.

- Beim nächsten Dialog “Open as Project” wählen.

- Beim nächsten Dialog kontrollieren ob Java 9 ausgewählt ist.

- Dann “File already exists” mit YES bestätigen.

- ACHTUNG: Jetzt “Delete existing Project and Import” anklicken.

- Warten, warten, warten.

Wenn alles gut gegangen ist sollte im Project-View der Java-Ordner unter src/main blau sein und der Java-Ordner unter src/test grün.

