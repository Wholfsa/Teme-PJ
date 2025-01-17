package student.group.FirstSpringBootApp;

public class Eveniment {
    private Long id;
    private String denumirea;
    private String locatia;
    private String data;
    private String timp;
    private double pretBilet;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getDenumirea(){
        return denumirea;
    }

    public void setDenumirea(String denumirea){
        this.denumirea=denumirea;
    }

    public String getLocatia(){
        return locatia;
    }

    public void setLocatia(String locatia){
        this.locatia=locatia;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data=data;
    }

    public String getTimp(){
        return timp;
    }

    public void setTimp(String timp){
        this.timp=timp;
    }

    public double getPretBilet(){
        return pretBilet;
    }

    public void setPretBilet(double pretBilet){
        this.pretBilet=pretBilet;

    }
}
