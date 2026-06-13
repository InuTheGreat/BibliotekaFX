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
biblioteka_projekt/
├── pom.xml                                   # konfiguracja Maven
└── src/
└── main/
    ├── java/
    │   ├── module-info.java
    │   └── pl/
    │       ├── ConnectionDB.java             # połączenie z bazą MySQL
    │       ├── controller/
    │       │   ├── LoginController.java      # obsługa logowania
    │       │   ├── MainController.java       # główny kontroler aplikacji
    │       │   ├── UserDashboardController.java  # kontroler panelu użytkownika
    │       │   ├── administrator/
    │       │   │   ├── AdminMainPaneController.java
    │       │   │   ├── BorrowingController.java
    │       │   │   ├── author/
    │       │   │   │   ├── AuthorAddController.java
    │       │   │   │   └── AuthorController.java
    │       │   │   ├── book/
    │       │   │   │   ├── BookController.java
    │       │   │   │   └── BookAddController.java
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
    │       │   │   ├── BookDao.java
    │       │   │   ├── BorrowingDao.java
    │       │   │   ├── GenreDao.java
    │       │   │   ├── LocationDao.java
    │       │   │   ├── PublisherDao.java
    │       │   │   └── UserDao.java
    │       │   ├── mapper/
    │       │   │   └── BookMapper.java               # mapowanie Book -> BookView
    │       │   ├── model/
    │       │   │   ├── Author.java
    │       │   │   ├── Book.java
    │       │   │   ├── Borrowing.java
    │       │   │   ├── Genre.java
    │       │   │   ├── Location.java
    │       │   │   ├── Publisher.java
    │       │   │   └── User.java
    │       │   ├── dto/
    │       │   │   ├── BookView.java                 # widok książki z powiązanymi danymi
    │       │   │   └── BorrowingView.java            # widok wypożyczenia z danymi czytelnika
    │       │   └── service/
    │       │       ├── password/
    │       │       │   └── UserPassword.java         # logika zmiany hasła
    │       │       └── UserService.java              # logika biznesowa użytkownika
    │       ├── session/
    │       │   └── UserSession.java              # zarządzanie sesją zalogowanego użytkownika
    │       └── view/
    │           └── ViewModel.java                # klasa pomocnicza do wiązania właściwości w widokach
    └── resources/
        └── fxml/
            ├── administrator/
            │   ├── author/
            │   │   ├── addAuthor.fxml            # formularz dodawania autora
            │   │   └── authors.fxml              # widok listy autorów
            │   ├── book/
            │   │   ├── addBooks.fxml             # formularz dodawania książki
            │   │   └── books.fxml                # widok listy książek
            │   ├── genre/
            │   │   └── genres.fxml               # widok listy gatunków
            │   ├── publisher/
            │   │   └── publishers.fxml           # widok listy wydawnictw
            │   ├── borrowings.fxml               # widok statusu książek i wypożyczeń
            │   └── adminDashboard.fxml           # dashboard administratora
            ├── pane/
            │   ├── setup/
            │   │   └── changePassword.fxml       # formularz zmiany hasła
            │   ├── footer.fxml                   # widok stopki
            │   └── top.fxml                      # widok górnego paska
            ├── loginPane.fxml                    # ekran logowania
            ├── main.fxml                         # główny widok aplikacji
            └── userDashboard.fxml                # dashboard użytkownika
```