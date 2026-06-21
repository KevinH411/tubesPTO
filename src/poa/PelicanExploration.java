package poa;

import java.util.Random;

public class PelicanExploration {

    private PelicanFitness fitness;

    public PelicanExploration(PelicanFitness fitness) {
        this.fitness = fitness;
    }

    /**
     * Menghitung posisi baru "kandidat" berdasarkan posisi pelikan saat ini
     * (saatIni) dan posisi mangsa (mangsa), lalu menyimpan hasilnya ke
     * dalam vektor Solution.arr milik kandidat.
     *
     * Catatan: kandidat, saatIni, dan mangsa harus sudah dievaluasi
     * (sudah punya nilai makespan & totalFlowTime) sebelum method ini
     * dipanggil, karena dipakai untuk menentukan arah pergerakan.
     */
    public void hitung(Pelican kandidat, Pelican saatIni, Pelican mangsa, Random rand) {
        boolean mangsaLebihBaik = fitness.isLebihBaik(mangsa, saatIni);
        int I = rand.nextInt(2) + 1; // bernilai 1 atau 2, sesuai rumus POA asli

        for (int j = 0; j < kandidat.getSolusi().arr.length; j++) {
            double posisiSaatIni = saatIni.getSolusi().arr[j];
            double posisiMangsa = mangsa.getSolusi().arr[j];

            if (mangsaLebihBaik) {
                // bergerak agresif menuju mangsa
                kandidat.getSolusi().arr[j] = posisiSaatIni
                        + rand.nextDouble() * (posisiMangsa - I * posisiSaatIni);
            } else {
                // menjauh dari mangsa yang lebih buruk
                kandidat.getSolusi().arr[j] = posisiSaatIni
                        + rand.nextDouble() * (posisiSaatIni - posisiMangsa);
            }
        }
    }
}