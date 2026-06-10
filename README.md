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
    │   └── pl/
    │       ├── ConnectionDB.java             # połączenie z bazą MySQL
    │       ├── controller/
    │       │   ├── LoginController.java      # obsługa logowania
    │       │   ├── MainController.java       # główny kontroler aplikacji
    │       │   ├── administrator/
    │       │   │   ├── AdminMainPaneController.java
    │       │   │   ├── author/
    │       │   │   │   ├── AuthorAddController.java
    │       │   │   │   └── AuthorController.java
    │       │   │   ├── genre/
    │       │   │   │   └── GenreController.java
    │       │   │   └── publisher/
    │       │   │       └── PublisherController.java
    │       │   └── pane/
    │       │       ├── bottom/
    │       │       │   └── PaneBottomPaneController.java
    │       │       └── top/
    │       │           ├── setup/
    │       │           │   └── SetupController.java
    │       │           └── PaneTopController.java
    │       ├── library/
    │       │   ├── Main.java                     # punkt startowy aplikacji
    │       │   ├── dao/
    │       │   │   ├── AuthorDao.java
    │       │   │   ├── GenreDao.java
    │       │   │   ├── PublisherDao.java
    │       │   │   └── UserDao.java
    │       │   ├── model/                        # modele danych
    │       │   └── service/                      # logika biznesowa
    │       ├── session/                      # zarządzanie sesją zalogowanego użytkownika
    │       └── view/                         # helpery widoków
    └── resources/                            # pliki FXML, CSS, obrazki itp.
```