import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Yonetici extends Kisi{
    private String departman;
    //done
    Yonetici(){
        this.departman="yonetici";
    }
    public List<Ucak> getirUcak() throws IOException {
        //oluşturulacak Ucak nesneleri için ucaklar listesi oluştur
        List<Ucak> ucaklar = new ArrayList<>();
        //ucaklar dosyası okuma modunda aç
        BufferedReader br = new BufferedReader(new FileReader("Ucaklar.txt"));
        String satir;
        //okunacak satirin kelimeleri alabilmek için String tipinden bir ucakList oluştur
        LinkedList<String> ucakList= new LinkedList<String>();
        int i;
        while ((satir = br.readLine()) != null) {
            //ucaklar dosyadaki olan satirlar her satir için bir nesen oluşturulacak
            Ucak ucak= new Ucak();
            //temp kullanrak her kelimenin herfleri toplayıp geçici bir şekilde kelimeyi tutacak
            String temp = " ";
            //aldığımız satır üzerinde harf harf gezinebilmek için kakter arrayin için tut
            //harf harf geçmekten amacım noktalı virgülleri bulmak için
            char a[] = satir.toCharArray();
            for (i = 1; i <= a.length; i++) {
                boolean flage = true; // flag= true virgül görmedi görünce false a dönüşür
                while (flage == true && i<a.length) { //boundsooutIndex hatası için destekleyen i<a.length kotrolu
                    temp = temp + a[i-1]; // virgül görene kadar harfler temp içine topla
                    if (a[i] == ';') {
                        //virgül görünce elde ettiğimiz kelimeyi ucakListye ekle
                        ucakList.add(temp);
                        temp = " ";
                        //sonra tempi boşalt sonraki kelimeyi tutbilmeye
                        flage = false; //flage == false döngüden çık
                    }
                    i++;//virgülü atlat

                }
            }
            ucak.setTip(ucakList.get(0)); //ucakList esindeki ilk tutulan kelimeyi ucağın tipi placak
            ucak.setKapasite(Integer.parseInt(ucakList.get(1).replaceAll("\\D" ,"")));// bulduğumuz kelimeyi temizleyip sonra ıntegere dönüştür "r,122"  ise "122" sonra integere dönüştür ParseInt()ile
            ucak.setUretinYil(Integer.parseInt(ucakList.get(2).replaceAll("\\D" ,"")));
            //birinci ucak nesnenin özelliklerini set ettikten sonra
            //ucaklar listesine ekle dolu haliyle
            ucaklar.add(ucak);
            ucakList.clear(); //ucaklisteye eklemiş olduğumuz 1. satırın bilgileri kullandıktan sonra artık listeyi boşalt
            //sonraki satır bilgilerini 0 indexten başlayarak tutmaya için
        }
        //while den çıktık demekki dosyadaki satırlarına kadar nesne oluşturulumuş
        return ucaklar; //dolu listeye dondur içinde {ucak1,ucak2,ucak2....}
    }
    //done
    public boolean ekleUcak(String tip, int kapasite ,int uretimYili){
        Ucak ucak= new Ucak();
        //her ekleme iiçin bir ucak nesnesi oluşturulacak
        ucak.setTip(tip);//Yöneticinden alnmış olan verileri kullanarak ucağın özellikleri set edilir
        ucak.setKapasite(kapasite);
        ucak.setUretinYil(uretimYili);
        try{
            Writer gets;
            //oluşturuduğumuz ucağın bilgilere Ucaklar.txt dosayasına ekle
            String satir= ucak.getTip()+";"+ucak.getKapasite()+";"+ucak.getUretinYil()+";";
            gets = new BufferedWriter(new FileWriter("Ucaklar.txt",true));
            gets.append("\n");//işareci son satırın sonunda duruyor yeni satır eklemeye işaretci kaysın aşağa
            gets.append(satir); //dosaya ekle
            gets.close();// dosyayı kapat
            return true;// try ettisye ve herhangi sorun yaşanmadıysa true dondursun
        }
        catch(IOException e){
            //yazma yaparken hata yaşınırsa hata exceptioni olsun
            e.printStackTrace();
            System.out.println("error");

        }
        return false;
    }

    public List<Ucus> getirUcus() throws IOException {
        List<Ucus> ucuslar = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("Ucuslar.txt"));
        String satir;
        LinkedList<String> ucusList= new LinkedList<String>();
        int i;

        while ((satir = br.readLine()) != null) {
            Ucus ucus = new Ucus();
            String temp = " ";
            char a[] = satir.toCharArray();
            for (i = 1; i <= a.length; i++) {
                boolean flage = true;
                while (flage == true && i<a.length) {
                    temp = temp + a[i-1];
                    if (a[i] == ';') {
                        ucusList.add(temp);
                        temp = " ";
                        flage = false;
                    }
                    i++;
                }
            }
            ucus.setUcusNo(Integer.parseInt(ucusList.get(0).replaceAll("\\D" ,"")));
            ucus.setKalkisSaat(ucusList.get(1));
            ucus.setSure(Integer.parseInt(ucusList.get(2).replaceAll("\\D" ,"")));
            if(true){
                //dosyadan okunanan integer değerini getDurum function la karşıdurumunu bulup onu dondurur
                ucus.setDurum(getDurum(Integer.parseInt(ucusList.get(11).replaceAll("\\D" ,""))));
            }

            ucus.setKalkis(new Havalimani());
            ucus.getKalkis().setUlke(ucusList.get(3));
            ucus.getKalkis().setSehir(ucusList.get(4));
            ucus.getKalkis().setHavalimaniAd(ucusList.get(5));
            //varışı set
            ucus.setVaris(new Havalimani());
            ucus.getVaris().setUlke(ucusList.get(6));
            ucus.getVaris().setSehir(ucusList.get(7));
            ucus.getVaris().setHavalimaniAd(ucusList.get(8));
            ucus.setUcak(new Ucak());
            ucus.getUcak().setTip(ucusList.get(9));
            ucuslar.add(ucus);
            ucusList.clear();
            satir="";

        }
        return ucuslar;
    }
    /*
    Planlandi,
    ERtelendi,
    Ayrildi,
    Tamamlandi,
    Iptal
*/
    public UcusDurum getDurum(int getInt){
        // ucusun durum nesneyi setedebilmek için ucusDurumu donduran ucusDurum
        if(getInt==1){
            return UcusDurum.Planlandi;
        }

        else if(getInt==2){
            return UcusDurum.ERtelendi;
        }
        else if(getInt==3){
            return UcusDurum.Ayrildi;
        }
        else if(getInt==4){
            return UcusDurum.Tamamlandi;
        }
        else if(getInt==5){
            return UcusDurum.Iptal;
        }
        return null;
    }
    // not yet !
    //                               Yıl;Ay;Gün;Saat(13:15)
    public boolean ekleUcus(int UcusNo,int yil,int Ay, int gun,int saat,int dakika , float sure, String kalkisUlke, String kalkisSehir, String kalkisHavLimAd, String varisUlke, String varisSehir, String varisHavLimAd , String Tip, String SirketAd,int durum) throws IOException {
        String date=yil + "/"+Ay+"/"+gun+ "/"+saat+":"+dakika;
        Ucus ucus= new Ucus (); //ucus nesneyi oluştur
        ucus.setUcusNo(UcusNo); //user den gelen bilgileriyle uçuşun bilgileri set edilsin
        ucus.setKalkisSaat(date);
        ucus.setVarisSaat(getvarisdate(saat,dakika,sure));
        ucus.setSure(sure);
        //kalkış bilgileri

        ucus.setKalkis(new Havalimani());
        ucus.getKalkis().setUlke(kalkisUlke);
        ucus.getKalkis().setSehir(kalkisSehir);
        ucus.getKalkis().setHavalimaniAd(kalkisHavLimAd);
        //varış bilgileri
        ucus.setVaris(new Havalimani());
        ucus.getVaris().setUlke(varisUlke);
        ucus.getVaris().setSehir(varisSehir);
        ucus.getVaris().setHavalimaniAd(varisHavLimAd);
        ucus.setSirket(new HavaYoluSirketi());
        ucus.getSirket().setHavaYoluSirkeiAdi(SirketAd);
        ucus.setUcak(new Ucak());
        ucus.getUcak().setTip(Tip);
        ucus.setDurum(getDurum(durum)); // get durum fonksiyonuyla user (1 ile 5) arasında ayarlayabilir ucusun durumu 6 girdiyse null dondurur fakat 1 girdiyse karşı durumu belirtilir
        String satir=ucus.getUcusNo()+";" +ucus.getKalkisSaat()+";"+(int)ucus.getSure()+";"+ucus.getKalkis().getUlke()+";"+ucus.getKalkis().getSehir()+";"+ucus.getKalkis().getHavalimaniAd()+";"+ucus.getVaris().getUlke()+";"+ucus.getVaris().getSehir()+";"+ucus.getVaris().getHavalimaniAd()+";"+ucus.getUcak().getTip()+";"+ ucus.getSirket().getHavaYoluSirkeiAdi()+";"+durum+";";
        String satir1;
        BufferedReader br = new BufferedReader(new FileReader("Ucuslar.txt"));
        while ((satir1 = br.readLine()) != null) {
            if(satir1.equals(satir)==true){
                System.out.println("\nUYARI: Eklemek istediğiniz uçuşu bulunmaktadır!\n");
                break;
            }
        }
        br.close();
        try{
            Writer gets;
            gets = new BufferedWriter(new FileWriter("Ucuslar.txt",true));
            gets.append("\n");
            gets.append(satir);
            gets.close();
            return true;
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return false ;
    }
    //varış saati hesaplama
    public String getvarisdate(int st,int dk,float sure){
        String varisdate;
        dk= (int) (dk+ sure);
        while(dk>60){
            dk=dk-60;
            st++;
        }
        if(st>24){
            st=0;
        }
        varisdate=st+":"+dk;
        return varisdate;
    }

    //done
    public List<Havalimani> getirHavalimani( ) throws IOException {
        List<Havalimani> havalimanilar = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("Havalimanilar.txt"));
        String satir;
        LinkedList<String> havalimaiList= new LinkedList<String>();
        int i;

        while ((satir = br.readLine()) != null) {
            Havalimani havalimani = new Havalimani();
            String temp = " ";
            char a[] = satir.toCharArray();
            for (i = 1; i <= a.length; i++) {
                boolean flage = true;
                while (flage == true && i<a.length) {
                    temp = temp + a[i-1];
                    if (a[i] == ';') {
                        havalimaiList.add(temp);
                        temp = " ";
                        flage = false;
                    }
                    i++;
                }
            }
            //System.out.println(havalimaiList);

            havalimani.setHavalimaniAd(havalimaiList.get(0));
            havalimani.setUlke(havalimaiList.get(1));
            havalimani.setSehir(havalimaiList.get(2));
            havalimanilar.add(havalimani);
            havalimaiList.clear();
        }
        return havalimanilar;
    }
    //done
    public boolean ekleHavalimani(String ad,String ulke ,String sehir){
        Havalimani havali=new  Havalimani();
        havali.setHavalimaniAd(ad);
        havali.setUlke(ulke);
        havali.setSehir(sehir);
        try{
            Writer gets;
            String satir ;
            satir = havali.getHavalimaniAd() +";"+ havali.getUlke()+";" + havali.getSehir()+";";
            gets = new BufferedWriter(new FileWriter("Havalimanilar.txt",true));
            gets.append("\n");
            gets.append(satir);
            gets.close();
            return true ;

        }
        catch(IOException ei){
            ei.printStackTrace();

        }
        return false ;
    }

    //done
    public List<HavaYoluSirketi> getirHavalimaniSirketler() throws IOException {
        List<HavaYoluSirketi> havaYoluSirketisler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("HavayoluSirketleri.txt"));
        String satir;
        LinkedList<String> havaYoluSirketList= new LinkedList<String>();
        int i;

        while ((satir = br.readLine()) != null) {
            HavaYoluSirketi havaYoluSirketi = new HavaYoluSirketi();
            String temp = "";
            char a[] = satir.toCharArray();
            for (i = 1; i <= a.length; i++) {
                boolean flage = true;
                while (flage == true && i<a.length) {
                    temp = temp + a[i-1];
                    if (a[i] == ';') {
                        havaYoluSirketList.add(temp);
                        temp = "";
                        flage = false;
                    }
                    i++;
                }
            }
            //System.out.println(havalimaiList);
            havaYoluSirketi.setKod((Integer.parseInt(havaYoluSirketList.get(0).replaceAll("\\D", ""))));
            havaYoluSirketi.setHavaYoluSirkeiAdi(havaYoluSirketList.get(1));
            havaYoluSirketisler.add(havaYoluSirketi);
            havaYoluSirketList.clear();
        }
        return havaYoluSirketisler;
    }
    //done
    public void ekleHavalimaniSirketler(int kod, String sirketAd){
        HavaYoluSirketi sirket=new HavaYoluSirketi();
        sirket.setKod(kod);
        sirket.setHavaYoluSirkeiAdi(sirketAd);

        try{
            Writer gets;
            String satir ;
            satir = sirket.getKod()+";"+sirket.getHavaYoluSirkeiAdi()+";";
            gets = new BufferedWriter(new FileWriter("HavayoluSirketleri.txt",true));
            gets.append("\n");
            gets.append(satir);
            gets.close();
        }
        catch(IOException ei){
            ei.printStackTrace();
        }
    }
}
