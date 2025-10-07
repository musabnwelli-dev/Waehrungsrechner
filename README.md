## You can find the English version of this README below.

# Währungsrechner (Java Swing)

Ein einfacher und eleganter **Währungsrechner in Java Swing**.  
Er ermöglicht die Umrechnung zwischen verschiedenen Währungen mit unterschiedlichen Umrechnungsarten wie **Echtzeit**, **Fix** und **Historisch**.

---

## Screenshots

![Main Window](https://github.com/musabnwelli-dev/Waehrungsrechner/blob/e45b9fbb75599bc3cfb9cda5543df0a131f428ee/docs/Bildschirmfoto%202025-10-07%20um%2022.32.06.png)


## Wechselkurse


![Wechselkurse](https://github.com/musabnwelli-dev/Waehrungsrechner/blob/d8812d5917a664da77f2eee0b1cd4657a58640aa/docs/Bildschirmfoto%202025-10-07%20um%2022.32.29.png)



---

## Funktionen

- Umrechnung zwischen mehreren Währungen (USD, EUR, GBP usw.)  
- Auswahl zwischen **Echtzeit-, Fix-** und **historischer** Umrechnung  
- Auswahl eines **beliebigen Datums** für historische Umrechnungen  
- Eingabeprüfung mit Fehlermeldungen bei ungültigen Werten  
- Übersichtliche und benutzerfreundliche **Swing-Benutzeroberfläche**

---

## Ausführen

 Kompilieren und Starten (manuell)

Alle Java-Dateien kompilieren
javac -d out $(find src -name "*.java")

## Anwendung starten
java -cp out de.musab.currency.WindowCurrencyConverter

## In IntelliJ IDEA
Öffne das Projekt in IntelliJ.
Rechtsklick auf WindowCurrencyConverter.java.
Wähle Run 'WindowCurrencyConverter.main()' aus.

## Voraussetzungen
Java 17 oder höher

Optional: IntelliJ IDEA oder eine andere Java-IDE

## Technischer Überblick
Dieses Projekt verwendet das Strategy Pattern (Strategie-Muster), um verschiedene Umrechnungsarten flexibel zu handhaben:

FixedCurrencyConverter → feste (statische) Wechselkurse

LatestCurrencyConverter → für Echtzeitdaten (API-bereit)

HistoricalCurrencyConverter → erlaubt Umrechnungen basierend auf einem gewählten Datum

Die Klasse CurrencyConversionHandler dient als Vermittler zwischen der grafischen Oberfläche (UI) und der Umrechnungslogik.

## Autor
Musab Nwelli

Studiengang: B.Sc. Software System Development – Universität Hamburg 🇩🇪

Leidenschaftlich interessiert an Softwareentwicklung, sauberem Code und moderner Java-Technologie.

-----------------------------------------------------------------------------------------------------------

## English

# Currency Converter (Java Swing)

A simple and elegant **currency converter built with Java Swing**.  
It allows users to convert between multiple currencies using different conversion modes such as **Real-time**, **Fixed**, and **Historical**.

---


## Features

- Convert between multiple currencies (USD, EUR, GBP, etc.)
- Choose between **Real-time**, **Fixed**, and **Historical** conversion modes
- Select a **custom date** for historical conversions
- Input validation with friendly warning dialogs
- Clean and responsive **Swing UI**

---
## How to Run

###  Compile and Run (manually)

 Compile all .java files
javac -d out $(find src -name "*.java")

Run the application
java -cp out de.musab.currency.WindowCurrencyConverter

## With IntelliJ IDEA
Open the project in IntelliJ.
Right-click on WindowCurrencyConverter.java.
Select Run 'WindowCurrencyConverter.main()'.

## Requirements
Java 17 or newer

Optional: IntelliJ IDEA or another IDE for development

## Implementation Overview
This project follows a Strategy Pattern to manage different conversion types:

FixedCurrencyConverter → uses static exchange rates

LatestCurrencyConverter → designed for real-time data (API-ready)

HistoricalCurrencyConverter → allows conversion using a selected date

The CurrencyConversionHandler acts as a bridge between the UI and the conversion logic.

## Author
Musab Nwelli

B.Sc. Software System Development — University of Hamburg 🇩🇪

Passionate about software engineering, clean code, and modern Java development.




