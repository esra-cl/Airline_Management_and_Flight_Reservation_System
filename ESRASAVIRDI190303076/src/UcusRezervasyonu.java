import java.io.*;
import java.util.*;
import java.io.BufferedReader;

enum RezervasiyonDurumu{
    Gerceklestirildi,
    Iptal,
}
public class UcusRezervasyonu {
    private String rezevasyonNo ;
    private Ucus ucusNo;
    private UcusKoltugu koltukNo;
    private Yolucu yolucu;
    private RezervasiyonDurumu durum;
    //done
    public List<Yolucu> getirYolcu(String tc) throws IOException {
        List<Yolucu> yolcular = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("Kisiler.txt"));
        String satir;
        LinkedList<String> yolcuList= new LinkedList<String>();
        int i,yolcuBulCounter=-1;
        while ((satir = br.readLine()) != null) {
            if(satir.contains("Yolcu") && satir.contains(tc)){
                //yolcu buldum counter 1 artır (-1 den başlayan ilk eleman 0. index e eklemeye )
                yolcuBulCounter++;
                Yolucu yolcu= new Yolucu();
                String temp = " ";
                char a[] = satir.toCharArray();
                for (i = 1; i <= a.length; i++) {
                    boolean flage = true;
                    while (flage == true && i<a.length) {
                        temp = temp + a[i-1];
                        if (a[i] == ';') {
                            yolcuList.add(temp);
                            temp = " ";
                            flage = false;
                        }
                        i++;
                    }
                }
                //18876545643;Ayşe Temel;2000\10\16;Yolcu;
                yolcu.setKisiTC(yolcuList.get(0));
                yolcu.setAdSoyad(yolcuList.get(1));
                yolcu.setDogumTarihi(yolcuList.get(2));
                yolcu.setKategori(yolcuList.get(3));
                // oluşturup doldurdun nesneyi yocular listeye sonra yolcuList boşalt
                yolcular.add(yolcu);
                yolcuList.clear();
            }

        }
        return yolcular;
    }
    //done

    public List<UcusRezervasyonu> getirRezervasyonlar(String tc) throws IOException {

        List<UcusRezervasyonu> rezervasyonlar = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("Rezervasyonlar.txt"));
        String satir;
        LinkedList<String> rezervasyonList= new LinkedList<String>();

        int i;
        while ((satir = br.readLine()) != null) {

            if(satir.contains(";"+tc+";")==true ) {
                UcusRezervasyonu rezervasyon = new UcusRezervasyonu();
                String temp = " ";
                char a[] = satir.toCharArray();
                for (i = 1; i <= a.length; i++) {
                    boolean flage = true;
                    while (flage == true && i < a.length) {
                        temp = temp + a[i - 1];
                        if (a[i] == ';') {
                            rezervasyonList.add(temp);
                            temp = "";
                            flage = false;
                        }
                        i++;
                    }
                }
                //yolcu bilgileri
                rezervasyon.setYolucu(new Yolucu());
                rezervasyon.getYolucu().setYolcuNo(Integer.parseInt(rezervasyonList.get(0).replaceAll("\\D", "")));
                rezervasyon.getYolucu().setKisiTC(rezervasyonList.get(1));
                rezervasyon.getYolucu().setAdSoyad(rezervasyonList.get(2));
                rezervasyon.setRezevasyonNo(rezervasyonList.get(3));
                //kalkış
                rezervasyon.setUcusNo(new Ucus());
                rezervasyon.getUcusNo().setKalkisSaat(rezervasyonList.get(4));
                rezervasyon.getUcusNo().setKalkis(new Havalimani());
                rezervasyon.getUcusNo().getKalkis().setHavalimaniAd(rezervasyonList.get(5));
                //varış
                rezervasyon.getUcusNo().setVaris(new Havalimani());
                rezervasyon.getUcusNo().getVaris().setHavalimaniAd(rezervasyonList.get(6));
                //rezervasyon->ucus->setUcusNo
                rezervasyon.getUcusNo().setUcusNo(Integer.parseInt(rezervasyonList.get(7).replaceAll("\\D", "")));
                rezervasyon.setKoltukNo(new UcusKoltugu());
                rezervasyon.getKoltukNo().setKoltukNo(rezervasyonList.get(8));
                rezervasyon.getKoltukNo().setSinif(getKoltukSinifi(Integer.parseInt(rezervasyonList.get(9).replaceAll("\\D", ""))));
                rezervasyon.getKoltukNo().setUcret(Integer.parseInt(rezervasyonList.get(10).replaceAll("\\D", "")));
                rezervasyon.setDurum(getrezDurumu(Integer.parseInt(rezervasyonList.get(11).replaceAll("\\D", ""))));

                rezervasyonlar.add(rezervasyon);
                rezervasyonList.clear();
            }
        }
        br.close();
        return rezervasyonlar;
    }
    /*
        Economy,
        Business,
    */
    public KoltukSinifi getKoltukSinifi(int getInt){
        // ucusun durum nesneyi setedebilmek için ucusDurumu donduran ucusDurum
        if(getInt==1){
            return KoltukSinifi.Economy;
        }
        else if(getInt==2){

            return KoltukSinifi.Business;
        }
        return null;
    }
    /*
    * enum RezervasiyonDurumu{
    Gerceklestirildi,
    Iptal
}
    * */
    public  RezervasiyonDurumu getrezDurumu(int getInt){
        // ucusun durumu setedebilmek için ucusDurumu donduran ucusDurum metodu
        if(getInt==1){
            return RezervasiyonDurumu.Gerceklestirildi;
        }
        else if(getInt==2){

            return RezervasiyonDurumu.Iptal;
        }
        return null;
    }
    public boolean ekleRezevasyon(String tc,String DT,String tamAd,String st,String Khavalimani,String Vhavalimani,int Kno,int sinif,int durum) throws IOException {
        UcusRezervasyonu r= new UcusRezervasyonu();
        r.setYolucu(new Yolucu());
        r.getYolucu().setYolcuNo(getNo());
        r.getYolucu().setKisiTC(tc);
        r.getYolucu().setAdSoyad(tamAd);
        r.setRezevasyonNo(String.valueOf(getNo()));
        //kalkış
        r.setUcusNo(new Ucus());
        r.getUcusNo().setKalkisSaat( st);
        r.getUcusNo().setKalkis(new Havalimani());
        r.getUcusNo().getKalkis().setHavalimaniAd(Khavalimani);
        //varış
        r.getUcusNo().setVaris(new Havalimani());
        r.getUcusNo().getVaris().setHavalimaniAd(Vhavalimani);

        if(UcusAra(st,Khavalimani,Vhavalimani)>0){
            //rezervasyon->ucus->setUcusNo()
            r.getUcusNo().setUcusNo(UcusAra(st,Khavalimani,Vhavalimani));
        }
        r.setKoltukNo(new UcusKoltugu());
        r.getKoltukNo().setKoltukNo(String.valueOf(Kno));

        r.getKoltukNo().setSinif(getKoltukSinifi(1));

        if(ucretbelirtme(UcusAra(st,Khavalimani,Vhavalimani))>0){
            r.getKoltukNo().setUcret(ucretbelirtme(UcusAra(st,Khavalimani,Vhavalimani)));
        }
        r.setDurum(getrezDurumu(durum));
        String satir,satir1;
        satir=r.getYolucu().getYolcuNo()+";"+r.getYolucu().getKisiTC()+";"+r.getYolucu().getAdSoyad()+";"+r.getRezevasyonNo()+ ";"+r.getUcusNo().getKalkisSaat()+";"+r.getUcusNo().getKalkis().getHavalimaniAd()+";"+r.getUcusNo().getVaris().getHavalimaniAd()+";"+r.getUcusNo().getUcusNo()+";"+r.getKoltukNo().getKoltukNo()+";"+sinif+";"+(int)r.getKoltukNo().getUcret()+";"+durum+";";

        BufferedReader br = new BufferedReader(new FileReader("Rezervasyonlar.txt"));
        while ((satir1 = br.readLine()) != null) {
            if(satir1.equals(satir)==true ){
                System.out.println("\nUYARI:Rezervasyonuz bulunmaktadır !\n");
                break;
            }
        }
        br.close();
        if((UcusAra(st,Khavalimani,Vhavalimani)>0) && (ucretbelirtme(UcusAra(st,Khavalimani,Vhavalimani))>0) ){
            //eğer ilk sefer rezarvasyon yapıyorsa bilgilerini kişiler dosyasına eklenecek
            if(yolcuEkle(tamAd,tc,DT)==true){
                Kisi yolcu= new Kisi();
                yolcu.setAdSoyad(tamAd);
                yolcu.setKisiTC(tc);
                yolcu.setKategori("Yolcu");
                yolcu.setDogumTarihi(DT);
                String ekleYolcu=yolcu.getKisiTC()+";"+yolcu.getAdSoyad()+";"+yolcu.getDogumTarihi()+";"+yolcu.getKategori()+";";
                BufferedWriter writer= new BufferedWriter(new FileWriter("Kisiler.txt",true));
                writer.append("\n");
                writer.append(ekleYolcu);
                writer.close();
            }
            try{
                Writer gets;
                gets = new BufferedWriter(new FileWriter("Rezervasyonlar.txt",true));
                gets.append("\n");
                gets.append(satir);
                gets.close();
                return true;
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
        return false ;
    }
    public boolean guncelleRezervasyonDurum(String tc,int Yno) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Rezervasyonlar.txt"));
        String satir="";
        LinkedList<String> rezervasyonList= new LinkedList<String>();
        int i ;
        while ((satir = br.readLine()) != null) {
            //satir.contains(tc)==true && satir.contains(String.valueOf(Yno))==true

            if(satir.contains(tc)==true && satir.contains(String.valueOf(Yno))==true ) {
                String temp = "";
                char a[] = satir.toCharArray();
                for (i = 1; i <= a.length; i++) {
                    boolean flage = true;
                    while (flage == true && i < a.length) {
                        temp = temp + a[i - 1];
                        if (a[i] == ';') {
                            rezervasyonList.add(temp);
                            temp = "";
                            flage = false;
                        }
                        i++;
                    }
                }
                deleteRezervasyon(satir);
                System.out.println("sistemde bulundu kayıtlarınız");
                System.out.println(rezervasyonList);
            }
        }
        br.close();
        rezervasyonList.set(11,String.valueOf(2));
        String temp="";
        for(int k=0;k<rezervasyonList.size();k++) {
            temp +=rezervasyonList.get(k)+";";
        }
        System.out.println(temp);
        Writer ekle = new BufferedWriter(new FileWriter("Rezervasyonlar.txt",true));
        if(temp!= null){
            ekle.append(temp);
        }
        ekle.close();

        return false ;
    }
    public boolean deleteRezervasyon (String silenecek){
        try{
            BufferedReader br= new BufferedReader(new FileReader("Rezervasyonlar.txt"));
            StringBuffer sildiktenSonra = new StringBuffer();
            String satir;
            while((satir= br.readLine())!=null){
                if(!satir.trim().equals(silenecek)){
                    sildiktenSonra.append(satir);
                    sildiktenSonra.append("\n");
                }
            }
            br.close();
            String get = String.valueOf(sildiktenSonra);
            FileWriter yazma = new FileWriter("Rezervasyonlar.txt");
            yazma.write(get);
            yazma.close();
            return true;

        }
        catch(IOException e){
            e.printStackTrace();
        }

        return false;
    }

    public String getRezevasyonNo() {
        return rezevasyonNo;
    }

    public Ucus getUcusNo() {
        return ucusNo;
    }

    public Yolucu getYolucu() {
        return yolucu;
    }

    public UcusKoltugu getKoltukNo() {
        return koltukNo;
    }

    public RezervasiyonDurumu getDurum() {
        return durum;
    }

    public void setUcusNo(Ucus ucusNo) {
        this.ucusNo = ucusNo;
    }

    public void setKoltukNo(UcusKoltugu koltukNo) {
        this.koltukNo = koltukNo;
    }

    public void setDurum(RezervasiyonDurumu durum) {
        this.durum = durum;
    }

    public void setRezevasyonNo(String rezevasyonNo) {
        this.rezevasyonNo = rezevasyonNo;
    }

    public void setYolucu(Yolucu yolucu) {
        this.yolucu = yolucu;
    }
    // eğer uygun ucus bulunmazsa rezervasyon yapılmamaktadır
    public int UcusAra(String t,String KhavaLimani, String VhavaLimani) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Ucuslar.txt"));
        String satir;int i=0;
        while ((satir = br.readLine()) != null) {
            String temp = " ";
            if (satir.contains(t) == true && satir.contains(KhavaLimani) == true && satir.contains(VhavaLimani) == true ) {
                char a[] = satir.toCharArray();
                while(a[i]!=';'){
                    temp+=a[i];
                    i++;
                }
                return Integer.parseInt(temp.replaceAll("\\D" ,""));
            }
        }
        br.close();
        return 0;
    }
    //eğer ucus bulunursa ucusno girerek bu ucusun ucreti dondurur
    public int ucretbelirtme(int getUcusno) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Rezervasyonlar.txt"));
        String satir;
        LinkedList<String> RList= new LinkedList<String>();
        int i;
        while ((satir = br.readLine()) != null) {
            if(satir.contains(String.valueOf(getUcusno))){
                String temp = " ";
                char a[] = satir.toCharArray();
                for (i = 1; i <= a.length; i++) {
                    boolean flage = true;
                    while (flage == true && i<a.length) {
                        temp = temp + a[i-1];
                        if (a[i] == ';') {
                            RList.add(temp);
                            temp = " ";
                            flage = false;
                        }
                        i++;
                    }
                }
                return Integer.parseInt(RList.get(10).replaceAll("\\D" ,""));
            }
            br.close();
        }
        return 0;
    }
    //rezervasyon ve Yolcu numaraları rastgele uretilmek için
    public int getNo(){
        Random rand= new Random();
        int sinir=100;
        int rastgeleSayi = rand.nextInt(sinir);
        return  rastgeleSayi;
    }

    //yolcuekle fonskiyonu rezervasyon yaptığı kişinin girdiği bilgileri kisiler dosyasında kayıdı var mı kontrol eder
    public boolean yolcuEkle(String tamAd, String tc,String DT){
        try{
            BufferedReader read= new BufferedReader(new FileReader("Kisiler.txt"));
            String oku;

            while((oku= read.readLine())!=null){
                if(oku.contains(tc)==true){
                    return false;
                }
            }
            read.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }
}