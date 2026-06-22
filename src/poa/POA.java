package poa;

import java.util.Random;

public class POA {
    private Pelican[] populasi;
    private Pelican bestPelican;
    private PelicanFitness fitness;
    private PelicanExploration eksplorasi;
    private PelicanExploitation eksploitasi;
    private Random rand;

    public POA(int ukuranPopulasi, int[][] jadwal, int maksIterasi, long seed) {
        this.fitness = new PelicanFitness(jadwal);
        this.eksplorasi = new PelicanExploration(fitness);
        this.eksploitasi = new PelicanExploitation();
        this.populasi = new Pelican[ukuranPopulasi];
        this.rand = new Random(seed);

        for (int i = 0; i < ukuranPopulasi; i++) {
            populasi[i] = new Pelican(jadwal[0].length, jadwal.length, rand);
            fitness.evaluasi(populasi[i]);
        }

        updateBestPelican();
        jalankanAlgoritma(maksIterasi);
    }

    private void updateBestPelican() {
        if (bestPelican == null) {
            bestPelican = clonePelican(populasi[0]);
        }
        for (Pelican p : populasi) {
            if (fitness.isLebihBaik(p, bestPelican)) {
                bestPelican = clonePelican(p);
            }
        }
    }

    private Pelican clonePelican(Pelican source) {
        Pelican clone = new Pelican(source.getSolusi().arr.length, source.getUrutanMesin().length, rand);
        System.arraycopy(source.getSolusi().arr, 0, clone.getSolusi().arr, 0, source.getSolusi().arr.length);
        clone.setMakespan(source.getMakespan());
        clone.setTotalFlowTime(source.getTotalFlowTime());
        return clone;
    }

    private void jalankanAlgoritma(int maksIterasi) {
        for (int t = 1; t <= maksIterasi; t++) {
            for (int i = 0; i < populasi.length; i++) {
                Pelican current = populasi[i];

                // fase 1, exploration
                Pelican candidate = clonePelican(current);
                int preyIndex = rand.nextInt(populasi.length);
                Pelican prey = populasi[preyIndex];

                eksplorasi.hitung(candidate, current, prey, rand);
                fitness.evaluasi(candidate);

                if (fitness.isLebihBaik(candidate, current)) {
                    populasi[i] = clonePelican(candidate);
                    current = populasi[i];
                }

                // fase 2, exploitation
                candidate = clonePelican(current);
                eksploitasi.hitung(candidate, current, t, maksIterasi, rand);
                fitness.evaluasi(candidate);

                if (fitness.isLebihBaik(candidate, current)) {
                    populasi[i] = candidate;
                }
            }
            updateBestPelican();
        }
    }

    public Pelican getBestPelican() {
        return bestPelican;
    }
}