package pl.controller.administrator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.library.dao.BookDao;
import pl.library.dao.BorrowingDao;
import pl.library.dto.BookView;
import pl.library.dto.BorrowingView;
import pl.view.ViewModel;

import java.util.List;

public class BorrowingController {

    private final BorrowingDao borrowingDao = new BorrowingDao();
    private final BookDao bookDao = new BookDao();
    private final ViewModel viewModel = new ViewModel();
    private ObservableList<BorrowingView> borrowingList;
    private ObservableList<BookView> availableBookList;

    @FXML
    private TableView<BorrowingView> tableViewId;

    @FXML
    private TableColumn<BorrowingView, Integer> borrowingIdColumn;

    @FXML
    private TableColumn<BorrowingView, String> titleColumn;

    @FXML
    private TableColumn<BorrowingView, String> isbnColumn;

    @FXML
    private TableColumn<BorrowingView, String> borrowDateColumn;

    @FXML
    private TableColumn<BorrowingView, String> readerNameColumn;

    @FXML
    private TableColumn<BorrowingView, String> readerEmailColumn;

    @FXML
    private TableView<BookView> availableBooksTableView;

    @FXML
    private TableColumn<BookView, Integer> availBookIdColumn;

    @FXML
    private TableColumn<BookView, String> availTitleColumn;

    @FXML
    private TableColumn<BookView, String> availIsbnColumn;

    @FXML
    private TableColumn<BookView, String> availPublisherColumn;

    @FXML
    private TableColumn<BookView, String> availGenreColumn;

    @FXML
    private TableColumn<BookView, String> availLocationColumn;

    @FXML
    private Button returnBtn;

    @FXML
    private CheckBox checkId;

    @FXML
    public void initialize() {

        borrowingList = FXCollections.observableArrayList();
        availableBookList = FXCollections.observableArrayList();

        // konfiguracja tabeli wypożyczeń
        borrowingIdColumn.setCellValueFactory(new PropertyValueFactory<>("borrowingId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        readerNameColumn.setCellValueFactory(new PropertyValueFactory<>("readerName"));
        readerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("readerEmail"));

        // konfiguracja tabeli dostępnych książek
        availBookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        availTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        availIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        availPublisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        availGenreColumn.setCellValueFactory(new PropertyValueFactory<>("genreName"));
        availLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        tableViewId.setItems(borrowingList);
        availableBooksTableView.setItems(availableBookList);

        checkId.selectedProperty().bindBidirectional(viewModel.confirmPropertyProperty());
        returnBtn.disableProperty().bind(viewModel.confirmPropertyProperty().not());

        loadsData();

        returnBtn.setOnAction(e -> returnBook());
    }

    private void loadsData() {
        List<BorrowingView> temp = borrowingDao.getActiveBorrowings();
        tableViewId.getItems().setAll(temp);

        List<BookView> available = bookDao.getAvailableBooks();
        availableBooksTableView.getItems().setAll(available);
    }

    private void returnBook() {
        BorrowingView selected = tableViewId.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        borrowingDao.returnBook(selected.getBorrowingId(), selected.getBookId());
        borrowingList.remove(selected);
        viewModel.setConfirmProperty(false);

        // odśwież tabelę dostępnych książek
        loadsData();
    }
}