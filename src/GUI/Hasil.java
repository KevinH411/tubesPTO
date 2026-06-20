package GUI;

import Main.Taillard;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class Hasil extends JFrame {
    public Hasil(Taillard taillard, int soalTerpilih) {
        setTitle("Hasil Eksperimen");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea teksHasil = new JTextArea();
        teksHasil.setEditable(false);
        
        StringBuilder hasil = new StringBuilder();
        hasil.append("Urutan pekerjaan terbaik : \n");
        for (int j = 0; j < taillard.getPemenangTiapSoal()[soalTerpilih].getUrutanPekerjaan().length; j++) {
            hasil.append(taillard.getPemenangTiapSoal()[soalTerpilih].getUrutanPekerjaan()[j]).append(" ");
        }
        
        hasil.append("\n\nMakespan : ").append(taillard.getPemenangTiapSoal()[soalTerpilih].getMakespan());
        hasil.append("\nTotal Flow-Time : ").append(taillard.getPemenangTiapSoal()[soalTerpilih].getTotalFlowTime());
        teksHasil.setText(hasil.toString());

        String[] kolom = {"No. Soal", "Makespan", "Total Flow-Time"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0);
        
        for (int i = 0; i < taillard.getPemenangTiapSoal().length; i++) {
            int ms = taillard.getPemenangTiapSoal()[i].getMakespan();
            int tft = taillard.getPemenangTiapSoal()[i].getTotalFlowTime();
            model.addRow(new Object[]{i + 1, ms, tft});
        }
        
        JTable tabelHasil = new JTable(model);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(teksHasil), new JScrollPane(tabelHasil));
        splitPane.setDividerLocation(150);
        add(splitPane, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel();
        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.addActionListener(e -> {
            new Masukan().setVisible(true);
            dispose();
        });
        panelBawah.add(kembaliButton);
        add(panelBawah, BorderLayout.SOUTH);
    }
}