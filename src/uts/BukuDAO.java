/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public class BukuDAO {
    public static ObservableList<Buku> getBuku() {
        // ObservableList untuk menyimpan data user
        ObservableList bukuList = FXCollections.observableArrayList();
        String query = "SELECT * FROM buku";

        try {
            // Membuat koneksi ke database
            Connection koneksi = DBConnection.getConnection();
            // Membuat statement
            Statement stmt = koneksi.createStatement();
            // Query untuk mengambil data user
            ResultSet rs = stmt.executeQuery(query);
            
             // Menambahkan data ke ObservableList
              while (rs.next()) {
            String kode_buku = rs.getString("kode_buku");  
            String judul = rs.getString("judul");
            String penerbit = rs.getString("penerbit");
            String pengarang = rs.getString("pengarang");
            String tahun = rs.getString("tahun");
            String edisi = rs.getString("edisi");

            bukuList.add(new Buku(kode_buku, judul, penerbit, pengarang, tahun, edisi));
        }

            // Menutup koneksi
            rs.close();
            stmt.close();
            koneksi.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
     
        return bukuList;
    }
    
    public static void addBuku(Buku buku) {
      String query = "INSERT INTO buku (kode_buku, judul, penerbit, pengarang, tahun, edisi) VALUES (?, ?, ?, ?, ?, ?)";

    try {
        Connection koneksi = DBConnection.getConnection();
        PreparedStatement smt = koneksi.prepareStatement(query);

        smt.setString(1, buku.getKode_buku());
        smt.setString(2, buku.getJudul());
        smt.setString(3, buku.getPenerbit());
        smt.setString(4, buku.getPengarang());
        smt.setString(5, buku.getTahun());
        smt.setString(6, buku.getEdisi());

        smt.executeUpdate();

        smt.close();
        koneksi.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public static void updateBuku(Buku buku) {
        String query = "UPDATE buku SET judul = ?, penerbit = ?, pengarang = ?, tahun = ?, edisi = ? WHERE kode_buku = ?";

        try (Connection koneksi = DBConnection.getConnection()) {
            PreparedStatement mst = koneksi.prepareStatement(query);
            mst.setString(1, buku.getJudul());
            mst.setString(2, buku.getPenerbit());
            mst.setString(3, buku.getPengarang());
            mst.setString(4, buku.getTahun());
            mst.setString(5, buku.getEdisi());
            mst.setString(6, buku.getKode_buku());

            mst.executeUpdate();
            mst.close();
            koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteBuku(String judul) {
        String query = "DELETE FROM buku WHERE judul = ?";

        try (Connection koneksi = DBConnection.getConnection()) {
           PreparedStatement mst = koneksi.prepareStatement(query);
           mst.setString(1, judul);

           mst.executeUpdate();
           mst.close();
           koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
}
