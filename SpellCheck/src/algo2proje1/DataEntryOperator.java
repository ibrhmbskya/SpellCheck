package algo2proje1;

// projede istenilen class olusturuldu. 
public class DataEntryOperator { 

    private int ID;
    private String adSoyad;
    private String departman;
    
    public DataEntryOperator() {
    }

    
    public DataEntryOperator(int ID, String adSoyad, String departman) {
        this.setID(ID);
        this.setAdSoyad(adSoyad);
        this.setDepartman(departman);
    }
    
    // Set metodu.
    public void setID(int ID) {
        this.ID = ID;
    }
    
    //Get metodu.
    public int getID() {
        return ID;
    }
    
    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public String getDepartman() {
        return departman;
    }
    
    //toString metodu.
    public String toString() {
        return "DataEntryOperator{"
                + "ID=" + ID
                + ", adSoyad='" + adSoyad + '\''
                + ", departman='" + departman + '\''
                + '}';
    }
}
