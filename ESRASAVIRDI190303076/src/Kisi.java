import java.time.LocalDateTime;
import java.util.Scanner;

public class Kisi {
    private String KisiTC;
    private String adSoyad ;
    private String adres;
    private String dogumTarihi;
    private String kategori;


    public String getAdres() {
        return adres;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getKisiTC() {
        return KisiTC;
    }

    public String getKategori() {
        return kategori;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setKisiTC(String kisiTC) {
        KisiTC = kisiTC;
    }

}
