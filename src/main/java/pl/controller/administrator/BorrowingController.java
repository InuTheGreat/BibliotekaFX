package pl.controller.administrator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.library.dao.BorrowingDao;
import pl.library.dto.BorrowingView;
import pl.view.ViewModel;

import java.time.LocalDate;
import java.util.List;

public class BorrowingController {

    private final BorrowingDao borrowingDao = new BorrowingDao();
    private final ViewModel viewModel = new ViewModel();
    private ObservableList<BorrowingView> borrowingList;

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
    private Button returnBtn;

    @FXML
    private CheckBox checkId;

    @FXML
    public void initialize() {

        borrowingList = FXCollections.observableArrayList();

        borrowingIdColumn.setCellValueFactory(new PropertyValueFactory<>("borrowingId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        readerNameColumn.setCellValueFactory(new PropertyValueFactory<>("readerName"));
        readerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("readerEmail"));

        tableViewId.setItems(borrowingList);

        checkId.selectedProperty().bindBidirectional(viewModel.confirmPropertyProperty());
        returnBtn.disableProperty().bind(viewModel.confirmPropertyProperty().not());

        loadsData();

        returnBtn.setOnAction(e -> returnBook());
    }

    private void loadsData() {
        List<BorrowingView> temp = borrowingDao.getActiveBorrowings();
        tableViewId.getItems().setAll(temp);
    }

    private void returnBook() {
        BorrowingView selected = tableViewId.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        borrowingDao.returnBook(selected.getBorrowingId(), selected.getBookId());
        borrowingList.remove(selected);
        viewModel.setConfirmProperty(false);
    }
}