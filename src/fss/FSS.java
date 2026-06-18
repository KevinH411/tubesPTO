package fss;

public class FSS {

    private int[][] jadwal;
    private int[][] waktuAkhir;
    private int[] urutanPekerjaan;
    private int[] urutanMesin;

    public FSS(int[] urutanPekerjaan, int[] urutanMesin, int[][] jadwal) {
        this.jadwal = jadwal;
        this.waktuAkhir = new int[this.jadwal.length][this.jadwal[0].length];
        this.urutanPekerjaan = urutanPekerjaan;
        this.urutanMesin = urutanMesin;
        this.hitungJadwal();
    }

    public int[][] getWaktuAkhir() {
        return this.waktuAkhir;
    }

    public int[][] getJadwal() {
        return jadwal;
    }

    public int getMakespan() {
        return this.waktuAkhir[this.waktuAkhir.length - 1][this.waktuAkhir[0].length - 1];
    }

    public int getTotalFlowTime() {
        int tft = 0;
        int mesinTerakhir = this.waktuAkhir.length - 1;
        for (int j = 0; j < this.waktuAkhir[0].length; j++) {
            tft += this.waktuAkhir[mesinTerakhir][j];
        }
        return tft;
    }

    private void hitungJadwal() {
        int pekerjaan = -1;
        int mesin = -1;
        for (int i = 0; i < this.jadwal.length; i++) {
            for (int j = 0; j < this.jadwal[0].length; j++) {
                pekerjaan = this.urutanPekerjaan[j] - 1;
                mesin = this.urutanMesin[i] - 1;
                
                if (i == 0 && j == 0) {
                    this.waktuAkhir[i][j] = this.jadwal[mesin][pekerjaan];
                } else {
                    if (i == 0) {
                        this.waktuAkhir[i][j] = this.jadwal[mesin][pekerjaan] + this.waktuAkhir[i][j - 1];
                    } else {
                        if (j == 0) {
                            this.waktuAkhir[i][j] = this.jadwal[mesin][pekerjaan] + this.waktuAkhir[i - 1][j];
                        } else {
                            if (this.waktuAkhir[i - 1][j] <= this.waktuAkhir[i][j - 1]) {
                                this.waktuAkhir[i][j] = this.jadwal[mesin][pekerjaan] + this.waktuAkhir[i][j - 1];
                            } else {
                                this.waktuAkhir[i][j] = this.jadwal[mesin][pekerjaan] + this.waktuAkhir[i - 1][j];
                            }
                        }
                    }
                }
            }
        }
    }
}