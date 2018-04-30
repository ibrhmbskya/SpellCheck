package algo2proje1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Text {

    private DataEntryOperator yazar;
    private String content;

    //ENUM
    public static final int GIRDI = 0; // İki boyutlu dizide(matris) tutabilmek icin ENUM yapisi olusturuldu.
    public static final int DOGRU = 1;
    public static final int YANLIS = 2;
    public static final int DUZELTILEN = 3;

    public Text() {
        this.yazar = new DataEntryOperator();
    }

    public Text(DataEntryOperator yazar, String content) {
        this.setYazar(yazar);
        this.setContent(content);
    }

    public void setYazar(DataEntryOperator yazar) {
        this.yazar = yazar;
    }

    public DataEntryOperator getYazar() {
        return yazar;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    //getWords adli method kullanacagimiz sozlugu dosyadan okuyup stringe atip String[] tipinde deger donduruyor.
    private String[] getWords() throws FileNotFoundException, IOException {

        //dosyadan okuma yapiliyor.
        String[] sozluk = new String[99999];
        FileInputStream fStream = new FileInputStream("words.txt");
        DataInputStream dStream = new DataInputStream(fStream);
        BufferedReader breader = new BufferedReader(new InputStreamReader(dStream));

        // str adlı Stringi kullanarak satir satir okuma yapiliyor.
        String str;
        int sozlukSayac = 0;
        while ((str = breader.readLine()) != null) {
            sozluk[sozlukSayac] = str;  //sozlukSayac index olarak kullanildi satir satir diziye atildi.
            sozlukSayac++;

        }
        return sozluk;  // method geriye String [] sozluk dondurdu.
    }

    // veri giris operatorlerinin girdigi girdileri okumak icin olusturulan method.
    public void girdiOku(String girdiYolu) throws FileNotFoundException, IOException { // geriye deger dondurmuyor.

        FileInputStream fStream = new FileInputStream(girdiYolu); // dosyadan satir satir okuma yapiliyor.
        DataInputStream dStream = new DataInputStream(fStream);
        BufferedReader breader = new BufferedReader(new InputStreamReader(dStream));

        String str;
        String oku = "";
        int sayac = 0;
        while ((str = breader.readLine()) != null) //dosyadan satir satir okuma yapiliyor
        {
            if (sayac == 0) { // eğ-ger ilk satirsa okunan metinler DataEntryOperator un uygun degiskenlerine ataniyor.
                String ilkSatir;
                ilkSatir = str;
                String[] dataEntryArray = ilkSatir.split(","); // ilk satirdaki metinler (,) baz alınarak okunuyor.
                int id = Integer.parseInt(dataEntryArray[0]);  // ilk girdi id e string olarak okundu bu yuzden inte cevrildi.
                this.yazar.setID(id); // set methodu kullanilarak id ye atama.
                this.yazar.setAdSoyad(dataEntryArray[1]); // set methodu kullanilarak AdSoyad atama.
                this.yazar.setDepartman(dataEntryArray[2]); // set methodu kullanilarak Departman atama.
            }

            if (sayac >= 1) {  // ilk satırdan sonrasi oku adli strinde eklendi.
                oku += str;
            }
            sayac++;
        }

        this.setContent(oku);  // oku stringi set methodu kullanilarak Contente atandi.
    }

    // geriye int[] donduren hata bulma methodu.
    public int[] spellCheck() throws IOException {

        int[] istatistik = new int[4]; // istatistik dizisi olusturuldu.

        String[] Sozluk = this.getWords(); // sozluk dizisi olusturulup sozluk atandi.

        // contentparcala contentin parcalanmıs hali.
        String[] contentParcala = this.getContent().replaceAll("[^A-Za-z ]+", "").split(" "); // regex yapisi. kelimelerin sonundan . , ? vb ifadeler cikarildi.
        int pLength = contentParcala.length; // uzunluk bulunuyor.

        for (int i = 0; i < pLength; i++) { // for dongusu ile hatalar bulunacak.

        }
        istatistik[GIRDI] = pLength; // girdi sayisi.

        for (int i = 0; i < pLength; i++) { // for dongusu ile hatalar bulunacak.
            for (int j = 0; j < Sozluk.length; j++) { // sozluk uzunlugu kullaniliyor.
                if (contentParcala[i].equals(Sozluk[j])) { // sozluk ile girdi karsilastiriliyor.
                    istatistik[DOGRU]++; // eger sozlukte var ise dogru olarak dusunuluyor ve dogru sayisi arttiriliyor.
                    break;
                }
            }
        }
        istatistik[YANLIS] = pLength - istatistik[DOGRU]; // sozlukte olmayanlar hatali.
        istatistik[DUZELTILEN] = istatistik[YANLIS]; // yanlıslar düzeltildi.

        return istatistik; // geriye istatistik dizisi donduruluyor.
    }

}
