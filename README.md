# CUIE Courseware

Das Courseware-Projekt dient dazu die Beispiele und Musterlösungen aus dem Unterricht zu verwalten und die Übungsbeispiele zu implementieren.

In einem ersten Schritt soll das initial enthaltene "Hello World"-Beispiel in der Entwicklungsumgebung gestartet werden können.

### Schritt 1 - Clonen

Im "Terminal" in die gewünschte Directory wechseln.

Das Repository von GitHub auf ihren Laptop "kopieren":

`git clone <url dieses Repositories>`

### Schritt 2 - IDE aufsetzen

Am besten den IntelliJ-Setup neu generieren zu lassen. Dazu verwendet man den File "build.gradle", der eine 
komplette und IDE-unabhängige Projektbeschreibung enthält.

Die einzelnen Schritte:

- Schliessen Sie alle geöffneten Projekte (File -> Close Project)

- Wählen Sie “OPEN” 

- Es erscheint ein Finder-Fenster mit dem Sie zu ihrem Projekt navigieren.

- Dort wählen Sie den File “build.gradle” aus.

- Beim nächsten Dialog “Open as Project” wählen.

- Beim nächsten Dialog kontrollieren ob Java 10 ausgewählt ist.

- Warten, warten, warten.

Wenn alles gut gegangen ist sollte im Project-View der Java-Ordner unter src/main blau sein und der Java-Ordner unter src/test grün.

Analog können Sie auch build.gradle in Eclipse oder NetBeans für den Projekt-Setup verwenden.


### Bitte beachten Sie:

Die benoteten Hausarbeiten und die Projektarbeit werden **nicht** in diesem Courseware-Projekt bearbeitet. Dafür wird es separate Repositories/Projekte geben.

