package poa;

import fss.FSS;
import java.util.Random;

public class POA {
    private Pelican[] populasi;
    private Pelican bestPelican;
    private int[][] jadwal;

    public POA(int ukuranPopulasi, int[][] jadwal, int maksIterasi) {
        this.jadwal = jadwal;
        this.populasi = new Pelican[ukuranPopulasi];
        
        for (int i = 0; i < ukuranPopulasi; i++) {
            populasi[i] = new Pelican(jadwal[0].length, jadwal.length);
            evaluasi(populasi[i]);
        }
        
        updateBestPelican();
        jalankanAlgoritma(maksIterasi);
    }

    private void evaluasi(Pelican p) {
        FSS penghitung = new FSS(p.getUrutanPekerjaan(), p.getUrutanMesin(), jadwal);
        p.setMakespan(penghitung.getMakespan());
        p.setTotalFlowTime(penghitung.getTotalFlowTime());
    }

    private boolean isLebihBaik(Pelican p1, Pelican p2) {
        double bobotMS = 0.5;
        double bobotTFT = 0.5;
        
        double skor1 = (bobotMS * p1.getMakespan()) + (bobotTFT * p1.getTotalFlowTime());
        double skor2 = (bobotMS * p2.getMakespan()) + (bobotTFT * p2.getTotalFlowTime());
        
        return skor1 < skor2;
    }

    private void updateBestPelican() {
        if (bestPelican == null) {
            bestPelican = clonePelican(populasi[0]);
        }
        for (Pelican p : populasi) {
            if (isLebihBaik(p, bestPelican)) {
                bestPelican = clonePelican(p);
            }
        }
    }

    private Pelican clonePelican(Pelican source) {
        Pelican clone = new Pelican(source.getSolusi().arr.length, source.getUrutanMesin().length);
        System.arraycopy(source.getSolusi().arr, 0, clone.getSolusi().arr, 0, source.getSolusi().arr.length);
        clone.setMakespan(source.getMakespan());
        clone.setTotalFlowTime(source.getTotalFlowTime());
        return clone;
    }

    private void jalankanAlgoritma(int maksIterasi) {
        Random rand = new Random();
        PelicanExploitation eksploitasi = new PelicanExploitation();

        for (int t = 1; t <= maksIterasi; t++) {
            for (int i = 0; i < populasi.length; i++) {
                Pelican current = populasi[i];
                Pelican candidate = clonePelican(current);
                
                int preyIndex = rand.nextInt(populasi.length);
                Pelican prey = populasi[preyIndex];
                int I = rand.nextInt(2) + 1;
                
                for (int j = 0; j < candidate.getSolusi().arr.length; j++) {
                    if (isLebihBaik(prey, current)) {
                        candidate.getSolusi().arr[j] = current.getSolusi().arr[j] + rand.nextDouble() * (prey.getSolusi().arr[j] - I * current.getSolusi().arr[j]);
                    } else {
                        candidate.getSolusi().arr[j] = current.getSolusi().arr[j] + rand.nextDouble() * (current.getSolusi().arr[j] - prey.getSolusi().arr[j]);
                    }
                }
                
                evaluasi(candidate);
                if (isLebihBaik(candidate, current)) {
                    populasi[i] = clonePelican(candidate);
                    current = populasi[i];
                }
                
                candidate = clonePelican(current);
                eksploitasi.hitung(candidate, current, t, maksIterasi, rand);
                evaluasi(candidate);
                
                if (isLebihBaik(candidate, current)) {
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