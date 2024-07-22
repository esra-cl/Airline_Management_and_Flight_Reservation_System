import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

enum UcusDurum {
    Planlandi,
    ERtelendi,
    Ayrildi,
    Tamamlandi,
    Iptal
}

public class Ucus {

    private int UcusNo;
    private Havalimani kalkis;
    private Havalimani varis;
    private HavaYoluSirketi sirket;
    private Ucak ucak;
    //private LocalDateTime kalkisSaat;
    private String kalkisSaat;

    //private LocalDateTime  varisSaat;
    private String  varisSaat;

    private UcusDurum durum;
    private float sure;


    public float getSure() {
        return sure;
    }

    public Havalimani getKalkis() {
        return kalkis;
    }

    public Havalimani getVaris() {
        return varis;
    }

    public int getUcusNo() {
        return UcusNo;
    }

    public Ucak getUcak() {
        return ucak;
    }

    public UcusDurum getDurum() {
        return durum;
    }

    public String getKalkisSaat() {
        return kalkisSaat;
    }

    public String getVarisSaat() {
        return varisSaat;
    }

    public void setDurum(UcusDurum durum) {
        this.durum = durum;
    }

    public void setKalkis(Havalimani kalkis) {
        this.kalkis = kalkis;
    }

    public void setKalkisSaat(String kalkisSaat) {
        this.kalkisSaat = kalkisSaat;
    }

    public void setSure(float sure) {
        this.sure = sure;
    }

    public void setUcak(Ucak ucak) {
        this.ucak = ucak;
    }

    public void setUcusNo(int ucusNo) {
        UcusNo = ucusNo;
    }

    public void setVaris(Havalimani varis) {
        this.varis = varis;
    }

    public void setVarisSaat(String varisSaat) {
        this.varisSaat = varisSaat;
    }

    public HavaYoluSirketi getSirket() {
        return sirket;
    }

    public void setSirket(HavaYoluSirketi sirket) {
        this.sirket = sirket;
    }
}
