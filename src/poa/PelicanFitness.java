package poa;

import fss.FSS;

public class PelicanFitness {

    private int[][] jadwal;

    public PelicanFitness(int[][] jadwal) {
        this.jadwal = jadwal;
    }

    // menghitung makespan dan total flow time untuk satu pelikan
    // dan menyimpan hasilnya ke dalam object Pelican itu sendiri.
    public void evaluasi(Pelican p) {
        FSS penghitung = new FSS(p.getUrutanPekerjaan(), p.getUrutanMesin(), jadwal);
        p.setMakespan(penghitung.getMakespan());
        p.setTotalFlowTime(penghitung.getTotalFlowTime());
    }

    // membandingkan dua pelikan yang sudah dievaluasi (sudah punya nilai makespan &
    // totalFlowTime)
    // mengembalikan true jika skor p1 < p2

    public boolean isLebihBaik(Pelican p1, Pelican p2) {
        double bobotMS = 0.5;
        double bobotTFT = 0.5;

        double skor1 = (bobotMS * p1.getMakespan()) + (bobotTFT * p1.getTotalFlowTime());
        double skor2 = (bobotMS * p2.getMakespan()) + (bobotTFT * p2.getTotalFlowTime());

        return skor1 < skor2;
    }
}