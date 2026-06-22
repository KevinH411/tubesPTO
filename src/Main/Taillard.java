package Main;

import poa.POA;
import poa.Pelican;

public class Taillard {
    private TestRead kasusTaillard;
    private POA hitungMS;
    private Pelican[] pemenangTiapSoal;
    private Soal[] tampungSoal;
    private long[] runtimeTiapSoal;
    private int soalTerpilih;

    public Taillard(String lokasiFile, int banyakIndividu, int maksIterasi, int peluangCO, int peluangMutasi,
            int soalTerpilih) {
        this.kasusTaillard = new TestRead(lokasiFile);
        this.pemenangTiapSoal = new Pelican[10];
        this.runtimeTiapSoal = new long[10];
        this.soalTerpilih = soalTerpilih;

        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            this.hitungMS = new POA(banyakIndividu, kasusTaillard.getKumpulanSoal()[i].getSoal(), maksIterasi,
                    kasusTaillard.getSeeds()[i]);
            this.pemenangTiapSoal[i] = hitungMS.getBestPelican();
            long end = System.currentTimeMillis();
            this.runtimeTiapSoal[i] = end-start;
        }

        this.getJadwalPemenang(soalTerpilih);
    }

    public Pelican[] getPemenangTiapSoal() {
        return pemenangTiapSoal;
    }

    public Soal[] getTampungSoal() {
        return tampungSoal;
    }

    public long[] getRuntimeTiapSoal() {
        return runtimeTiapSoal;
    }

    private void getJadwalPemenang(int soalTerpilih) {
        Soal[] periksaSoal = this.kasusTaillard.getKumpulanSoal();
        this.tampungSoal = new Soal[periksaSoal.length];
        for (int i = 0; i < this.tampungSoal.length; i++) {
            this.tampungSoal[i] = new Soal(pemenangTiapSoal[0].getUrutanMesin().length,
                    pemenangTiapSoal[0].getUrutanPekerjaan().length);
        }

        for (int i = 0; i < soalTerpilih + 1; i++) {
            int[][] tampungUrutanKerja = new int[this.kasusTaillard.getBanyakMesin()][this.kasusTaillard
                    .getBanyakPekerjaan()];
            for (int j = 0; j < pemenangTiapSoal[i].getUrutanMesin().length; j++) {
                for (int k = 0; k < pemenangTiapSoal[i].getUrutanPekerjaan().length; k++) {
                    tampungUrutanKerja[j][k] = periksaSoal[i].getSoal()[j][pemenangTiapSoal[i].getUrutanPekerjaan()[k]
                            - 1];
                }
            }
            this.tampungSoal[i].setSoal(tampungUrutanKerja);
        }
    }
}