package poa;

public class Pelican {
    private Solution solusi;
    private int[] urutanMesin;
    private int makespan;
    private int totalFlowTime;

    public Pelican(int banyakPekerjaan, int banyakMesin) {
        this.solusi = new Solution(banyakPekerjaan);
        this.solusi.randomize(new java.util.Random());
        
        this.urutanMesin = new int[banyakMesin];
        for (int i = 0; i < banyakMesin; i++) {
            this.urutanMesin[i] = i + 1;
        }
    }

    public Solution getSolusi() {
        return solusi;
    }

    public int[] getUrutanPekerjaan() {
        int[] urutanAsli = solusi.returnJobOrder();
        int[] urutanDisesuaikan = new int[urutanAsli.length];
        for (int i = 0; i < urutanAsli.length; i++) {
            urutanDisesuaikan[i] = urutanAsli[i] + 1;
        }
        return urutanDisesuaikan;
    }

    public int[] getUrutanMesin() { return urutanMesin; }
    public int getMakespan() { return makespan; }
    public void setMakespan(int makespan) { this.makespan = makespan; }
    public int getTotalFlowTime() { return totalFlowTime; }
    public void setTotalFlowTime(int totalFlowTime) { this.totalFlowTime = totalFlowTime; }
}