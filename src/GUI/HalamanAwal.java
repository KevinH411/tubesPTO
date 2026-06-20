package GUI;

import javax.swing.*;
import java.awt.BorderLayout;

public class HalamanAwal extends JFrame {
    public HalamanAwal() {
        setTitle("POA Multiobjektif");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel judul = new JLabel("Pelican Optimization Algorithm", SwingConstants.CENTER);
        judul.setFont(new java.awt.Font("Tahoma", 0, 18));
        add(judul, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel();
        JButton tombolMulai = new JButton("Mulai");
        tombolMulai.addActionListener(e -> {
            new Masukan().setVisible(true);
            dispose();
        });
        panelBawah.add(tombolMulai);
        add(panelBawah, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HalamanAwal().setVisible(true));
    }
}