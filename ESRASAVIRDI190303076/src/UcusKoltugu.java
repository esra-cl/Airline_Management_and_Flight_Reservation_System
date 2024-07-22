enum KoltukSinifi {
    Economy ,
    Business,
}
public class UcusKoltugu extends Koltuk{
    private float ucret;
    private String rezervasyon;
    private KoltukSinifi Sinif;


    public float getirUcret(){

        return 0;
    }

    @Override
    public String getKoltukNo() {
        return super.getKoltukNo();
    }

    public float getUcret() {
        return ucret;
    }

    public String getRezervasyon() {
        return rezervasyon;
    }

    @Override
    public void setKoltukNo(String koltukNo) {
        super.setKoltukNo(koltukNo);
    }

    @Override
    public void setSinif(KoltukSinifi sinif) {
        super.setSinif(sinif);
    }

    public KoltukSinifi getSinif() {
        return Sinif;
    }

    public void setRezervasyon(String rezervasyon) {
        this.rezervasyon = rezervasyon;
    }

    public void setUcret(float ucret) {
        this.ucret = ucret;
    }
}
