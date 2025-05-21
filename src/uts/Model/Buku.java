/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.Model;

/**
 *
 * @author Lenovo
 */
public class Buku {
    private String kode_buku;
    private String judul;
    private String penerbit;
    private String pengarang;
    private String tahun;
    private String edisi;
    
    public Buku(String kode_buku, String judul, String penerbit, String pengarang, String tahun, String edisi){
        this.kode_buku = kode_buku;
        this.judul = judul;
        this.penerbit = penerbit;
        this.pengarang = pengarang;
        this.tahun = tahun;
        this.edisi = edisi;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getEdisi() {
        return edisi;
    }

    public void setEdisi(String edisi) {
        this.edisi = edisi;
    }
    
    
}
