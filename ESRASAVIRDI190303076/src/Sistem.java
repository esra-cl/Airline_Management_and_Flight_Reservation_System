import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Sistem {
    public void Login() throws InterruptedException, IOException {
        System.out.println("Hoş geldiniz ...");
        // giriş metotu seçmek  yolcu olarak yoks yonetici...
        System.out.println("lütfen seç :\n1-> Yolcu\n2->Yonetici\n3->Kabin Üyesi\nseçeneğiniz:");
        int kulSec;
        Scanner scn =new Scanner (System.in);// for integers to ignore pase othertype error
        Scanner scn1 =new Scanner (System.in);// for Strings to ignore pase othertype error
        kulSec=scn.nextInt();
        //karara göre yönlendirme yapılıyor
        switch (kulSec){
            case 1:
                //yolcu ise Rezervazyon işlemleri yapabiyor
                System.out.println("Rezervazyona sizi yönlendiriyoruz....");
                UcusRezervasyonu rezervasyon = new UcusRezervasyonu();
                int islemKodu;
                YolucuislemListesi();
                islemKodu=scn.nextInt();

                while(islemKodu>=1 && islemKodu<=4){
                    switch (islemKodu){
                        case 1:

                            String tc, DT,tamAd,st,Khavalimani,Vhavalimani;
                            int Kno,sinif;
                            System.out.println("Tc no girin :");
                            tc=scn1.nextLine();
                            System.out.println("Doğum tarihi giriniz (yyyy/xx/zz):");
                            DT= scn1.nextLine();
                            System.out.println("Adınız Soyadınız girin:");
                            tamAd=scn1.nextLine();
                            System.out.println("tarihi ve saati giriniz (yyyy/xx/zz-ss:dd) şekilinde girin:");
                            st=scn1.nextLine();
                            System.out.println("kalkış havalimani adı:");
                            Khavalimani=scn1.nextLine();
                            System.out.println("varış havalimai adı:");
                            Vhavalimani=scn1.nextLine();
                            System.out.println("koltuk no seçiniz:");
                            Kno=scn.nextInt();
                            System.out.println("Koltuğun sınıfı giriniz (1:Economy 2:Business):");
                            sinif=scn.nextInt();
                            if(rezervasyon.ekleRezevasyon(tc,DT,tamAd,st,Khavalimani,Vhavalimani,Kno,sinif,1)==true){
                                System.out.println("Rezervasyonunu yapıldı");
                            }
                            else {
                                System.out.println("Uygun ucus bulunmadı için rezervasyonuzu yapılmadı!");
                            }
                            break;
                        case 2:
                            String TC;
                            System.out.println("Tc No:");
                            TC= scn1.nextLine();
                            if(rezervasyon.getirRezervasyonlar(TC).isEmpty()==true){
                                System.out.println("herhangi kayıt bulunmadı!");
                            }
                            for(int i=0; i<rezervasyon.getirRezervasyonlar(TC).size();i++){
                                System.out.println(rezervasyon.getirRezervasyonlar(TC).get(i).getUcusNo().getUcusNo()+" "+rezervasyon.getirRezervasyonlar(TC).get(i).getYolucu().getKisiTC()+" "+rezervasyon.getirRezervasyonlar(TC).get(i).getYolucu().getAdSoyad()+" "+rezervasyon.getirRezervasyonlar(TC).get(i).getUcusNo().getKalkisSaat()+" "+rezervasyon.getirRezervasyonlar(TC).get(i).getUcusNo().getKalkis().getHavalimaniAd()+" "+rezervasyon.getirRezervasyonlar(TC).get(i).getUcusNo().getVaris().getHavalimaniAd() );
                            }
                            break;
                        case 3:
                            String TcNo;int Yno;
                            System.out.println("Tc No:");
                            TcNo= scn1.nextLine();
                            System.out.println("yolcu no:");
                            Yno= scn.nextInt();
                            rezervasyon.guncelleRezervasyonDurum(TcNo,Yno);
                            System.out.println("Rezervasyonunuz iptal edildi ");

                            break;
                        case 4:
                            String tcNO;
                            System.out.println("Tc numaranız girin:");
                            tcNO=scn1.nextLine();
                            for(int k=0;k<rezervasyon.getirYolcu(tcNO+";").size();k++){
                                System.out.println(rezervasyon.getirYolcu(tcNO+";").get(k).getKisiTC()+" "+rezervasyon.getirYolcu(tcNO+";").get(k).getAdSoyad()+" "+rezervasyon.getirYolcu(tcNO+";").get(k).getKategori());

                            }
                            break;
                    }
                    YolucuislemListesi();
                    islemKodu=scn.nextInt();
                }
                break;
            case 2:

                // yonetici ise sifre ve kullanici adı istenilmekte
                //NOTE admin (kullanıcı ad, şifre)  admin1(11,124) , admin2 (56,123) , admin3(109,109) kullanarak girilir
                int kullaniciAd,Sifre,counter=2;
                System.out.println("Lütfen Giriş bilgilerinizi girin...");
                System.out.println("kullancı Ad :");
                kullaniciAd = scn.nextInt();
                System.out.println("Sifreniz :");
                Sifre= scn.nextInt();
                //girilen sifre ve kullanıcı ad SifreKontrolu metotla kontrol ediliyor
                //3 deneme giriş hakkı oluyor
                // denem hakkı bitiyse kullancıdan 10 saniye bekleme istenilecektir sonra tekrar denemeyi açılacak
                while(SifreKontrolu(kullaniciAd,Sifre)==false && counter >0){
                    if(counter!=3){
                        System.out.println("Kullanıcı Adı veya Şifreyi yanlış girdiniz\n"+counter+" deneme hakkınız kaldı\nLütfen tekrar girin\n");
                    }
                    System.out.println("kullancı Ad :");
                    kullaniciAd = scn.nextInt();
                    System.out.println("Sifreniz :");
                    Sifre= scn.nextInt();
                    counter--;
                    if(counter==0 && SifreKontrolu(kullaniciAd,Sifre)==false ){
                        System.out.println("deneme hakkınınız bitti!\n10 saniye sonra tekrar deneyebilirsiniz");
                        sleep(10000); //millli saniye cisinsinden
                        counter=3;
                    }

                }
                if(SifreKontrolu(kullaniciAd,Sifre)==true) {
                    System.out.println("Sisteme Hoş Geldiniz ...\n");
                    yonetici();
                    Yonetici admin = new Yonetici();
                    int IslemKodu;
                    IslemKodu= scn.nextInt();

                    while (IslemKodu >=1 && IslemKodu <=9) {
                        switch (IslemKodu) {
                            case 1:
                                for(int k=0;k <admin.getirUcak().size();k++){
                                    System.out.println(admin.getirUcak().get(k).getTip() + " "+ admin.getirUcak().get(k).getKapasite()+ " "+admin.getirUcak().get(k).getUretinYil() );
                                }
                                break;
                            case 2:
                                String tip;
                                int kapasite, uretimYili;
                                System.out.println("ucağın tipi :");
                                tip = scn1.nextLine();
                                System.out.println("ucağın kapasitesi :");
                                kapasite = scn1.nextInt();
                                System.out.println("uçağın Üretim yılı :");
                                uretimYili = scn1.nextInt();
                                //admin.ekleUcak(tip, kapasite, uretimYili); iki sefer basıyor biri burdan deiğeri kontroldan
                                if (admin.ekleUcak(tip, kapasite, uretimYili) == true) {
                                    System.out.println("İşleminiz başarlı bir şekilde tamamlandı!");
                                }
                                break;
                            case 3:
                                for(int i=0;i<admin.getirUcus().size();i++){
                                    System.out.println(admin.getirUcus().get(i).getUcusNo() +" "+admin.getirUcus().get(i).getKalkisSaat()+" "+admin.getirUcus().get(i).getSure()+" " + admin.getirUcus().get(i).getKalkis().getUlke()+" "+admin.getirUcus().get(i).getKalkis().getSehir()+ " "+admin.getirUcus().get(i).getKalkis().getHavalimaniAd()+ " "+ admin.getirUcus().get(i).getVaris().getUlke() +" "+ admin.getirUcus().get(i).getVaris().getSehir()+" "+admin.getirUcus().get(i).getVaris().getHavalimaniAd()+ " "+ admin.getirUcus().get(i).getUcak().getTip()+ " " +admin.getirUcus().get(i).getDurum());
                                }
                                break;
                            case 4:
                                int UcusNo, sure, durum,yil,Ay,gun,saat,dakika;
                                String date, kalkisUlke, kalkisSehir, kalkisHavLimAd, varisUlke, varisSehir, varisHavLimAd, Tip, SirketAd;
                                System.out.println("ucus no girin:");
                                UcusNo = scn.nextInt();
                                //date al
                                System.out.println("kalkış Tarih ve saat girin(yyyy/xx/zz/ss:dd)");
                                System.out.println("yil:");
                                yil=scn.nextInt();
                                System.out.println("Ay:");
                                Ay=scn.nextInt();
                                System.out.println("gün:");
                                gun=scn.nextInt();
                                System.out.println("saat:");
                                saat=scn.nextInt();
                                System.out.println("dakika");
                                dakika=scn.nextInt();


                                System.out.println("sure girin:");
                                sure = scn.nextInt();
                                System.out.println("kalkış ulke :");
                                kalkisUlke = scn1.nextLine();
                                System.out.println("kalkış şehir:");
                                kalkisSehir = scn1.nextLine();
                                System.out.println("kalkış Havalimanın adı:");
                                kalkisHavLimAd = scn1.nextLine();
                                System.out.println("varış ulke:");
                                varisUlke = scn1.nextLine();
                                System.out.println("varış şehir:");
                                varisSehir = scn1.nextLine();
                                System.out.println("varış Havalimaının adı:");
                                varisHavLimAd = scn1.nextLine();
                                System.out.println("ucağın tipi girin:");
                                Tip = scn1.nextLine();
                                System.out.println("Hava yolun şirketin adı girin :");
                                SirketAd = scn1.nextLine();
                                System.out.println("durumu girin(1:Planlandı,2:Ertelendi,3:Ayrıldı;4:Tamamlandi,5:iptal):");
                                durum = scn.nextInt();
                                //admin.ekleUcus(UcusNo, date, sure, kalkisUlke, kalkisSehir, kalkisHavLimAd, varisUlke, varisSehir, varisHavLimAd, Tip, SirketAd, durum);
                                if (admin.ekleUcus(UcusNo, yil,Ay,gun,saat,dakika, sure, kalkisUlke, kalkisSehir, kalkisHavLimAd, varisUlke, varisSehir, varisHavLimAd, Tip, SirketAd, durum) == true) {
                                    System.out.println("İşleminiz başarlı bir şekilde tamamlandı!");
                                }
                                break;
                            case 5:
                                for(int k=0;k<admin.getirHavalimani().size();k++){
                                    System.out.println(admin.getirHavalimani().get(k).getHavalimaniAd() + " "+ admin.getirHavalimani().get(k).getUlke()+ " "+admin.getirHavalimani().get(k).getSehir() );
                                }
                                break;
                            case 6:
                                String ad, Ulke, Sehir;
                                System.out.println("havaLimanin ad girin:");
                                ad = scn1.nextLine();
                                System.out.println("ülke girin:");
                                Ulke = scn1.nextLine();
                                System.out.println("şehiri girin:");
                                Sehir = scn1.nextLine();
                                //admin.ekleHavalimani(ad, Ulke, Sehir);
                                if (admin.ekleHavalimani(ad, Ulke, Sehir) == true) {
                                    System.out.println("İşleminiz başarlı bir şekilde tamamlandı!");
                                }
                                break;
                            case 7:
                                if(admin.getirHavalimaniSirketler().isEmpty()!=true){
                                    System.out.println("Kayıtlı Hava Yolu Şirketleri :");
                                }
                                for(int k=0;k<admin.getirHavalimaniSirketler().size();k++){
                                    System.out.println(admin.getirHavalimaniSirketler().get(k).getKod() + " "+ admin.getirHavalimaniSirketler().get(k).getHavaYoluSirkeiAdi());
                                }

                                break;
                            case 8:
                                String Sirketad;int kod;
                                System.out.println("Hava Yolu şirketin adı girin:");
                                Sirketad = scn1.nextLine();
                                System.out.println("kodu girin:");
                                kod = scn.nextInt();
                                admin.ekleHavalimaniSirketler(kod,Sirketad);
                                break;
                            case 9:
                                UcusRezervasyonu r= new UcusRezervasyonu();
                                for(int k=0;k<r.getirYolcu(";").size();k++){
                                    System.out.println(r.getirYolcu(";").get(k).getKisiTC()+" "+r.getirYolcu(";").get(k).getAdSoyad()+" "+r.getirYolcu(";").get(k).getKategori());
                                }

                                break;
                            default:
                                System.out.println("\nTeşekkürler ... İyi Günler!");
                                break;
                        }
                        yonetici();

                        IslemKodu=scn.nextInt();
                    }
                }
                break;
            case 3:
                KabinUyesi uye1= new KabinUyesi();
                kabinUyesi();
                int IslemSec;
                IslemSec=scn.nextInt();
                while (IslemSec >=1 && IslemSec <=2) {
                    switch (IslemSec) {
                        case 1:
                            String ucakTipi,havayolusirketi;
                            System.out.println("ucak tipi girin:");
                            ucakTipi=scn1.nextLine();
                            System.out.println("hava yolu şirketin adı girin:");
                            havayolusirketi=scn1.nextLine();
                            System.out.println("toplam ucus sayısı:");
                            System.out.println(uye1.setandgetToplamUcus(ucakTipi,havayolusirketi));
                            break;
                        case 2:
                            String ucakTipi1,havayolusirketi1;
                            System.out.println("ucak tipi girin:");
                            ucakTipi1=scn1.nextLine();
                            System.out.println("hava yolu şirketin adı girin:");
                            havayolusirketi1=scn1.nextLine();
                            System.out.println("toplam ucus saat:");
                            System.out.println(uye1.setandgetToplamSaat(ucakTipi1,havayolusirketi1));
                            break;
                    }
                    kabinUyesi();
                    IslemSec=scn.nextInt();
                }



                break;
        }
    }
    public void yonetici(){
        System.out.println("İslemi seçiniz....\n1->Mevcut Ucakları Listele\n2->Yeni Uçak Ekle\n3->Mevcut Ucuslar Listele\n4->Yeni Ucus Ekle \n5->Mevcut Havalimanıları Listele\n6->Yeni Havalimani Ekle \n7->Mevcut HavaYolu şirketleri Listele\n8->Yeni HavaYolu şirketi Ekle\n9->yolcular listeleme ");
        System.out.println("\nSeçeneğiniz:");
    }
    public void kabinUyesi(){
        System.out.println("İslemi seçiniz....\n1->toplam ucus sayısı sorgulama \n2->toplam ucus sure sorgulama ");
        System.out.println("\nSeçeneğiniz:");
    }
    public void YolucuislemListesi(){
        System.out.println("İslemi seçiniz....\n1->Rezervasyon yaptırma \n2->Rezervasyon sorgulama \n3->Rezervasyonu iptal et\n4->Bilgilerimi göster");
        System.out.println("\nSeçeneğiniz:");
    }
    public boolean SifreKontrolu(int kulAd,int Sifre){
        ArrayList<Integer > kullaniciAd= new ArrayList();
        kullaniciAd.add(111);
        kullaniciAd.add(561);
        kullaniciAd.add(109);

        ArrayList <Integer >sifre= new ArrayList();
        sifre.add(124);
        sifre.add(123);
        sifre.add(109);
        for(int i=0;i<3;i++){

            if(kulAd== kullaniciAd.get(i).intValue() && Sifre== sifre.get(i).intValue()){
                return true ;
            }
        }
        return false;
    }
}
