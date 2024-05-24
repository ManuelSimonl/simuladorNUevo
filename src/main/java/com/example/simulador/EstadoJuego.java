import java.io.Serializable;

public class EstadoJuego implements Serializable {
    private static final long serialVersionUID = 1L;
    private int PosicionX;
    private int PosicionY;
    private int score;

    // Constructor, getters y setters
    public EstadoJuego(int PosicionX, int PosicionY, int score){
        this.PosicionX = PosicionX;
        this.PosicionY = PosicionY;
        this.score = score;}

    public int getPosicionX() {
        return PosicionX;}

    public int getPosicionY() {
        return PosicionY;}

    public int getScore() {
        return score;}

    public void setPosicionX(int PosicionX) {
        this.PosicionX = PosicionX;}

    public void setPosicionY(int PosicionY) {
        this.PosicionY = PosicionY;
    }

    public void setScore(int score) {
        this.score = score;
    }
}