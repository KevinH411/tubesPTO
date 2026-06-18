package Main;

public class Soal {
    private int[][] soal;

    public Soal(int vertikal, int horizontal) {
        soal = new int[vertikal][horizontal];
    }

    public int[][] getSoal() {
        return soal;
    }

    public void setSoal(int[][] soal) {
        this.soal = soal;
    }
}