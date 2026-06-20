package GUI;

import Main.Taillard;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Masukan extends JFrame {
    private JTextField lokasiField;
    private JSpinner individuSpinner;
    private JSpinner iterasiSpinner;
    private JComboBox<String> soalComboBox;
    private JButton cariButton;
    private JButton lanjutButton;

    public Masukan() {
        setTitle("Parameter Input");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lokasiLabel = new JLabel("Lokasi File Taillard:");
        lokasiLabel.setBounds(20, 20, 150, 25);
        add(lokasiLabel);

        lokasiField = new JTextField();
        lokasiField.setBounds(150, 20, 180, 25);
        lokasiField.setEditable(false);
        add(lokasiField);

        cariButton = new JButton("Cari");
        cariButton.setBounds(340, 20, 70, 25);
        add(cariButton);

        JLabel individuLabel = new JLabel("Banyak Individu:");
        individuLabel.setBounds(20, 60, 150, 25);
        add(individuLabel);

        individuSpinner = new JSpinner(new SpinnerNumberModel(20, 1, 1000, 1));
        individuSpinner.setBounds(150, 60, 100, 25);
        add(individuSpinner);

        JLabel iterasiLabel = new JLabel("Maksimum Iterasi:");
        iterasiLabel.setBounds(20, 100, 150, 25);
        add(iterasiLabel);

        iterasiSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 10000, 1));
        iterasiSpinner.setBounds(150, 100, 100, 25);
        add(iterasiSpinner);

        JLabel soalLabel = new JLabel("Pilih Soal:");
        soalLabel.setBounds(20, 140, 150, 25);
        add(soalLabel);

        String[] soalList = new String[10];
        for (int i = 0; i < 10; i++) {
            soalList[i] = "Soal ke-" + (i + 1);
        }
        soalComboBox = new JComboBox<>(soalList);
        soalComboBox.setBounds(150, 140, 100, 25);
        add(soalComboBox);

        lanjutButton = new JButton("Lanjut");
        lanjutButton.setBounds(150, 180, 100, 25);
        lanjutButton.setEnabled(false);
        add(lanjutButton);

        cariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(Masukan.this) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    lokasiField.setText(file.getAbsolutePath());
                    lanjutButton.setEnabled(true);
                }
            }
        });

        lanjutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int populasi = (int) individuSpinner.getValue();
                int iterasi = (int) iterasiSpinner.getValue();
                int soalTerpilih = soalComboBox.getSelectedIndex();
                
                Taillard taillard = new Taillard(lokasiField.getText(), populasi, iterasi, 0, 0, soalTerpilih);
                new Hasil(taillard, soalTerpilih).setVisible(true);
                dispose();
            }
        });
    }
}