package algo2proje1;

import java.util.*;
import java.io.*;

import java.util.ArrayList;

// genel class.
public class Algo2Proje1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        int MAX_GIRDI = 10; // meta dosyadaki girdi sayisi max 10 alindi.

        int DIGER = 0;
        int MUHENDISLIK = 1;
        int TIP = 2;

        int[][] genelIstatistik = new int[MAX_GIRDI][5]; // istatistiklerin tutulması icin matris oluşturuldu.

        //dosyadan okuma.
        FileInputStream fStream = new FileInputStream("meta.txt");
        DataInputStream dStream = new DataInputStream(fStream);
        BufferedReader breader = new BufferedReader(new InputStreamReader(dStream));

        String path;
        int i = 0;

        while ((path = breader.readLine()) != null) //dosyadan satir satir okuma yapilir ve meta dosyada kac adet girdi varsa hepsi tek tek okunur istatistikler matriste tutulur.
        {
            Text text = new Text();
            text.girdiOku(path);
            int[] istatistik = text.spellCheck(); // hata bulma methodunun sonucu diziye atildi

            genelIstatistik[i][Text.GIRDI] = istatistik[Text.GIRDI]; // her girdinin sonuclari teker teker matrise atildi.
            genelIstatistik[i][Text.DOGRU] = istatistik[Text.DOGRU]; // her girdinin sonuclari teker teker matrise atildi.
            genelIstatistik[i][Text.YANLIS] = istatistik[Text.YANLIS]; // her girdinin sonuclari teker teker matrise atildi.
            genelIstatistik[i][Text.DUZELTILEN] = istatistik[Text.DUZELTILEN]; // her girdinin sonuclari teker teker matrise atildi.
            genelIstatistik[i][4] = DIGER; // her girdinin sonuclari teker teker matrise atildi.
            if ("MuhendislikFakultesi".equals(text.getYazar().getDepartman())) {
                genelIstatistik[i][4] = MUHENDISLIK;                             // eğer girdiyi giren muhendis ise burda tutuldu.
            } else if ("TipFakultesi".equals(text.getYazar().getDepartman())) {
                genelIstatistik[i][4] = TIP;                                     // eğer girdiyi giren tip ise burda tutuldu.
            }

            i++;
        }

        float toplamKelimeSayisi = 0, dogruToplamKelime = 0, duzeltilenToplamKelime = 0, hataliTopamKelime = 0; // kullanilacak degiskenler olusturuldu

        // kafakarisikligini onleme acisindan hesaplamalar icin ayri for kullanildi.
        for (int u = 0; u < genelIstatistik.length; u++) {
            toplamKelimeSayisi += genelIstatistik[u][Text.GIRDI];  // matris kullanilarak istenilen hesaplamalar yapildi.
            dogruToplamKelime += genelIstatistik[u][Text.DOGRU];   // matris kullanilarak istenilen hesaplamalar yapildi.
            duzeltilenToplamKelime += genelIstatistik[u][Text.DUZELTILEN];  // matris kullanilarak istenilen hesaplamalar yapildi.
            hataliTopamKelime += genelIstatistik[u][Text.YANLIS];  // matris kullanilarak istenilen hesaplamalar yapildi.
        }

        // değişken tanımı.
        float muhendisGirdi = 0, muhendisDogru = 0, muhendisSayac = 0, hataliYazimOrani = 0, tipGirdi = 0, tipYanlis = 0, tipSayac = 0, toplamGirdiAdet = 0;
        float dogruYazimOrani = 0, dogruYazimOraniOrt, hataliYazimOraniOrt, basariOraniOrt = 0;
        // kafakarisikligini onleme acisindan hesaplamalar icin ayri for kullanildi.
        for (int k = 0; k < genelIstatistik.length; k++) {
            if (genelIstatistik[k][4] == MUHENDISLIK) {  // girdiyi girenin departmani muhendis ise hesaplamalar.
                muhendisGirdi += genelIstatistik[k][Text.GIRDI];
                muhendisDogru += genelIstatistik[k][Text.DOGRU];
                muhendisSayac++;
            } else if (genelIstatistik[k][4] == TIP) {  // girdiyi girenin departman tip ise hesaplamalar.
                tipGirdi += genelIstatistik[k][Text.GIRDI];
                tipYanlis += genelIstatistik[k][Text.YANLIS];
                tipSayac++;
            }

            toplamGirdiAdet++;

        }
        
        // ekrana basılması istenen sonuçlar hesaplıyor.
        dogruYazimOrani = muhendisDogru / muhendisGirdi;  // ekrana basilmasi istenen sonuclari hesapliyor.
        hataliYazimOrani = tipYanlis / tipGirdi;  // ekrana basilmasi istenen sonuclari hesapliyor.
        dogruYazimOraniOrt = dogruYazimOrani / muhendisSayac;  // ekrana basilmasi istenen sonuclari hesapliyor.
        hataliYazimOraniOrt = hataliYazimOrani / tipSayac;  // ekrana basilmasi istenen sonuclari hesapliyor.
        basariOraniOrt = duzeltilenToplamKelime / hataliTopamKelime;  // ekrana basilmasi istenen sonuclari hesapliyor.

        
        // ekrana basmalar.
        System.out.println("1) Toplam kelime sayisi:" + (int) toplamKelimeSayisi);
        System.out.println("   Dogru yazilmis toplam kelime sayisi:" + (int) dogruToplamKelime);
        System.out.println("   Duzeltilen toplam kelime sayisi:" + (int) duzeltilenToplamKelime);
        System.out.println("2) Muhendislik fakultesinde calisan veri giris operatorlerinin dogru yazim orani ortalamasi:" + dogruYazimOraniOrt);
        System.out.println("3) Tip fakultesinde calisan veri giris operatorlerinin hatali yazim orani ortalamasi:" + hataliYazimOraniOrt);
        System.out.println("4) Okunan tum metinler icin algoritmanin basari orani ortalamasi:" + basariOraniOrt);

    }

}
