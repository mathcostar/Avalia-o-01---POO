package classes;

public class Aeronave extends Piloto{
    
    private String modelo;
    private String numeroSerie;
    private Piloto pilotoId;
    
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
    public Piloto getPilotoId() {
        return pilotoId;
    }
    public void setPilotoId(Piloto pilotoId) {
        this.pilotoId = pilotoId;
    }
}
