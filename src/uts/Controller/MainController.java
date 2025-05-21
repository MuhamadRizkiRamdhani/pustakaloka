/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package uts.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import uts.Model.Buku;
import uts.DAO.BukuDAO;

/**
 *
 * @author Lenovo
 */
public class MainController implements Initializable {
    @FXML private TextField LKodeBuku;
    @FXML private TextField LJudul;
    @FXML private TextField LPengarang;
    @FXML private TextField LPenerbit;
    @FXML private TextField LTahun;
    @FXML private TextField LEdisi;

    @FXML private Button BtnAdd;
    @FXML private Button BtnUpdate;
    @FXML private Button BtnDelete;

    @FXML private TableView<Buku> tabel;
    @FXML private TableColumn<Buku, String> CKodeBuku;
    @FXML private TableColumn<Buku, String> CJudul;
    @FXML private TableColumn<Buku, String> CPengarang;
    @FXML private TableColumn<Buku, String> CPenerbit;
    @FXML private TableColumn<Buku, String> CTahun;
    @FXML private TableColumn<Buku, String> CEdisi;

    private Buku selectedBuku;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        loadDataBuku();

        tabel.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> selectBuku(newSelection)
        );
        
        tabel.setOnMouseClicked(e -> {
    Buku buku = (Buku) tabel.getSelectionModel().getSelectedItem();
    if (buku != null) {
        selectedBuku = buku;
        LKodeBuku.setText(buku.getKode_buku());
        LJudul.setText(buku.getJudul());
        LPengarang.setText(buku.getPengarang());
        LPenerbit.setText(buku.getPenerbit());
        LTahun.setText(buku.getTahun());
        LEdisi.setText(buku.getEdisi());
    }
});
    }

    private void setupTable() {
        CKodeBuku.setCellValueFactory(new PropertyValueFactory<>("kode_buku"));
        CJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        CPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        CPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        CTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        CEdisi.setCellValueFactory(new PropertyValueFactory<>("edisi"));
    }

    private void loadDataBuku() {
        tabel.setItems(FXCollections.observableArrayList(BukuDAO.getBuku()));
    }

    
    //alert
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showInformationAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }
    
    private void showWarningAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }
    
    private void showConfirmationAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }
    //alert selesai
    
    private boolean showDeleteConfirmation(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);

    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void clearFields() {
        LKodeBuku.clear();
        LJudul.clear();
        LPengarang.clear();
        LPenerbit.clear();
        LTahun.clear();
        LEdisi.clear();
        selectedBuku = null;
    }

    private void selectBuku(Buku buku) {
        if (buku != null) {
            selectedBuku = buku;
            LKodeBuku.setText(buku.getKode_buku());
            LJudul.setText(buku.getJudul());
            LPengarang.setText(buku.getPengarang());
            LPenerbit.setText(buku.getPenerbit());
            LTahun.setText(buku.getTahun());
            LEdisi.setText(buku.getEdisi());
        }
    }

    @FXML
    private void addBuku(ActionEvent event) {
    String kode = LKodeBuku.getText();
    String judul = LJudul.getText();
    String pengarang = LPengarang.getText();
    String penerbit = LPenerbit.getText();
    String tahun = LTahun.getText();
    String edisi = LEdisi.getText();

    if (judul.isEmpty() || kode.isEmpty()) {
        showErrorAlert("Input Error", "Kode dan Judul tidak boleh kosong!");
        return;
    }

    Buku newBuku = new Buku(kode, judul, penerbit, pengarang, tahun, edisi);
    showInformationAlert("Berhasil", "Data berhasil ditambahkan.");
    BukuDAO.addBuku(newBuku);
    loadDataBuku();
    clearFields();
    
    }

    @FXML
    private void updateBuku(ActionEvent event) {
    if (selectedBuku == null) {
        showErrorAlert("Update Gagal", "Silakan pilih buku yang ingin diperbarui terlebih dahulu.");
        return;
    }

    selectedBuku.setJudul(LJudul.getText());
    selectedBuku.setPengarang(LPengarang.getText());
    selectedBuku.setPenerbit(LPenerbit.getText());
    selectedBuku.setTahun(LTahun.getText());
    selectedBuku.setEdisi(LEdisi.getText());

    BukuDAO.updateBuku(selectedBuku);
    showInformationAlert("Berhasil", "Data berhasil diperbarui.");
    loadDataBuku();
    clearFields();
    }

    @FXML
    private void deleteBuku(ActionEvent event) {
    if (selectedBuku == null) {
        showErrorAlert("Hapus Gagal", "Silakan pilih buku yang ingin dihapus terlebih dahulu.");
        return;
    }
        showDeleteConfirmation("Konfirmasi Hapus", "Apakah Anda yakin ingin menghapus buku ini?");
        BukuDAO.deleteBuku(selectedBuku.getJudul());
        showInformationAlert("Berhasil", "Data berhasil dihapus.");
        loadDataBuku();
        clearFields();
    }

    private boolean isFieldEmpty() {
        return LKodeBuku.getText().isEmpty() ||
               LJudul.getText().isEmpty() ||
               LPengarang.getText().isEmpty() ||
               LPenerbit.getText().isEmpty() ||
               LTahun.getText().isEmpty() ||
               LEdisi.getText().isEmpty();
    }
}
