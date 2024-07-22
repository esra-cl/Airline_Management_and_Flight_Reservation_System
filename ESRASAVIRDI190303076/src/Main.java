import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Sistem sis = new Sistem();
        sis.Login();
        UcusRezervasyonu rezervasyon= new UcusRezervasyonu();
        //System.out.println(rezervasyon.guncelleRezervasyonDurum("73876545643",35));
        //57;21680680528;esra şavırdi;1;2022/06/15-14:30;Sabiha Gokcen;Esenboga;4;11;1;150;1;
        //rezervasyon.ekleRezevasyon("21680680528","1999/05/08","Esra Şavırdi", "2022/06/15-14:30","Sabiha Gokcen","Esenboga",12,1,1);
        //rezervasyon.guncelleRezervasyonDurum("73876545643",35);
    }
}