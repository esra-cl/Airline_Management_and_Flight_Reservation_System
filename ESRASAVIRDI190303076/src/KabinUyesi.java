import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KabinUyesi extends Kisi{
    private float toplamSaat;
    private int toplamUcus;
    private boolean pilot;
     //done
    public float setandgetToplamSaat(String Ucaktipi,String havaYoluSirketi) throws IOException {
        BufferedReader br= new BufferedReader(new FileReader("Ucuslar.txt"));
        List<String> ucuslar = new ArrayList<String >();

        String satir;int i;
        while((satir= br.readLine())!=null){
            if (satir.indexOf(Ucaktipi)!=-1){
                if(satir.indexOf(havaYoluSirketi)!=-1){
                    String temp = "";
                    char a[] = satir.toCharArray();
                    for (i = 1; i <= a.length; i++) {
                        boolean flage = true;
                        while (flage == true && i < a.length) {
                            temp = temp + a[i - 1];
                            if (a[i] == ';') {
                                ucuslar.add(temp);
                                temp = "";
                                flage = false;
                            }
                            i++;
                        }
                    }
                    toplamSaat +=Integer.parseInt(ucuslar.get(2));
                    ucuslar.clear();
                }
            }
        }
        br.close();
        return toplamSaat;
    }
    //done

    public int setandgetToplamUcus(String Ucaktipi,String havaYoluSirketi) throws IOException {
        BufferedReader br= new BufferedReader(new FileReader("Ucuslar.txt"));
        String satir;
        while((satir= br.readLine())!=null){
            if (satir.indexOf(Ucaktipi)!=-1){
                if(satir.indexOf(havaYoluSirketi)!=-1){
                    toplamUcus++;
                }
            }
        }
        br.close();
        return toplamUcus;
    }
    public boolean getPilot(){
        return pilot;
    }

    public void setPilot(boolean pilot) {
        this.pilot = pilot;
    }


}
