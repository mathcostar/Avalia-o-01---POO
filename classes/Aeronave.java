package classes;

public class Aeronave extends Piloto{
    
    private String modelo;
    private String numeroSerie;
    private int pilotoId;

    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    public int getPilotoId() {
        return pilotoId;
    }
    public void setPilotoId(int pilotoId) {
        this.pilotoId = pilotoId;
    }
}
