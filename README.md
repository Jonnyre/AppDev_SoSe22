# App Konzept - Bierolympiade

## Team Members
Felix Lütte, Jonathan Rißler

## Platform
Android

## Summary, main features, user flow
Unsere App ist zum “managen” einer Bierolympiade, welche sich aus verschiedenen Spielen zusammensetzt. Ein Spiel besteht hierbei aus genau einer Runde. Die Bierolympiade hat somit mehrere Spiele und Teilnehmer. Die Spiele können aus vorgefertigten Spielen ausgesucht und leicht konfiguriert werden (Teamgrößen, Punktzahl bei Sieg etc.). Bei Start des Events, werde für die einzelnen Spiele Spielpläne erstellt (wer spielt gegen wen?). Das Interface der App erlaubt außerdem das Eintragen von Spielergebnissen. Aus diesen wird dann eine aktuelle Tabelle errechnet.

* Anlegen von Teilnehmern -> allgemein angelegt
    * (Detailansicht (Foto))
    * (Einlaufmusik)
    * (Vergangene Siege)
* Event
    * mehrere Spieler werden zugewiesen aus generellen Pool
        * Status (Zuschauer, Teilnehmer)
        * Attribute (hat Teilnehmerbeitrag bezahlt)
    * mehrere Spiele
        * Vorgefertigte Spiele anlegen (+ Regeln)
            * Spiel 1 gibt 3 Punkt wird in Teams von 2 gespielt
            * Verteilt Teilnehmer auf die Teams
            * Ergebnisse eintragen
            * Spielansicht (wer gegen wen)
    * Tabelle

## Weitere Features je nach Zeit:
* Anbinden als Backend Service für zb Anzeige Tabelle auf Website
* Unterscheiden Spieler/Teams ( feste Teams , antretende Spieler werden ausgelost)
