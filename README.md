# Biblioteka Projekt

Aplikacja desktopowa zbudowana w **JavaFX + MySQL**.

## Technologie

- Java 21
- JavaFX 21
- MySQL (connector 8.3.0)
- jBCrypt 0.4 (hashowanie haseł)
- Maven


## Struktura projektu

```
src/
└── main/
    ├── java/
    │   ├── module-info.java
    │   ├── .gitignore                        # do przeniesienia do głównego katalogu
    │   └── pl/
    │       ├── ConnectionDB.java             # połączenie z bazą MySQL
    │       ├── controller/
    │       │   ├── LoginController.java      # obsługa logowania
    │       │   ├── MainController.java       # główny kontroler aplikacji
    │       │   ├── administrator/            # kontrolery panelu administratora
    │       │   └── pane/                     # kontrolery paneli UI (top, bottom)
    │       ├── library/                      # logika biblioteki + modele danych
    │       ├── session/                      # zarządzanie sesją zalogowanego użytkownika
    │       └── view/                         # helpery widoków
    └── resources/                            # pliki FXML, CSS, obrazki itp.
```
