import java.util.List;

public class Ucak {
    private String tip;
    private int kapasite ;
    private int uretinYil;

    public String getTip() {
        return tip;
    }

    public int getKapasite() {
        return kapasite;
    }

    public int getUretinYil() {
        return uretinYil;
    }

    public void setUretinYil(int uretinYil) {
        this.uretinYil = uretinYil;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    public List<Ucus>  getirUcuslar(){
        return null;
    }
}
