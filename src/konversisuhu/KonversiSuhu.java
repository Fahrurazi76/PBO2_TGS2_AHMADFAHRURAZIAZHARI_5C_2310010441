package konversisuhu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KonversiSuhu extends JFrame {

    private JTextField txtInput;
    private JButton btnKonversi;
    private JLabel lblHasil;
    private JComboBox<String> cmbSkala;
    private JRadioButton rbKeC, rbKeF, rbKeK, rbKeR;
    private ButtonGroup grupArah;

    public KonversiSuhu() {
        setTitle("Aplikasi Konversi Suhu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));
        add(panel);

        JLabel lblJudul = new JLabel("Konversi Suhu", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblJudul);

        txtInput = new JTextField();
        txtInput.setHorizontalAlignment(JTextField.CENTER);
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != '-') {
                    e.consume();
                }
            }
        });
        panel.add(txtInput);

        cmbSkala = new JComboBox<>(new String[]{"Celcius", "Fahrenheit", "Kelvin", "Reamur"});
        panel.add(cmbSkala);

        JPanel panelArah = new JPanel();
        rbKeC = new JRadioButton("Ke Celcius");
        rbKeF = new JRadioButton("Ke Fahrenheit");
        rbKeK = new JRadioButton("Ke Kelvin");
        rbKeR = new JRadioButton("Ke Reamur");
        grupArah = new ButtonGroup();
        grupArah.add(rbKeC);
        grupArah.add(rbKeF);
        grupArah.add(rbKeK);
        grupArah.add(rbKeR);
        rbKeC.setSelected(true);

        panelArah.add(rbKeC);
        panelArah.add(rbKeF);
        panelArah.add(rbKeK);
        panelArah.add(rbKeR);
        panel.add(panelArah);

        btnKonversi = new JButton("Konversi");
        panel.add(btnKonversi);

        lblHasil = new JLabel("Hasil: -", SwingConstants.CENTER);
        lblHasil.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(lblHasil);

        btnKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                konversiSuhu();
            }
        });

        txtInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtInput.setText("");
                lblHasil.setText("Hasil: -");
            }
        });
    }

    private void konversiSuhu() {
        try {
            double nilai = Double.parseDouble(txtInput.getText());
            String dari = cmbSkala.getSelectedItem().toString();
            double hasil = 0;
            String ke = "";

            double c;
            switch (dari) {
                case "Fahrenheit": c = (nilai - 32) * 5 / 9; break;
                case "Kelvin": c = nilai - 273.15; break;
                case "Reamur": c = nilai * 5 / 4; break;
                default: c = nilai; break;
            }

            if (rbKeC.isSelected()) { hasil = c; ke = "Celcius"; }
            else if (rbKeF.isSelected()) { hasil = (c * 9 / 5) + 32; ke = "Fahrenheit"; }
            else if (rbKeK.isSelected()) { hasil = c + 273.15; ke = "Kelvin"; }
            else if (rbKeR.isSelected()) { hasil = c * 4 / 5; ke = "Reamur"; }

            lblHasil.setText(String.format("Hasil: %.2f %s", hasil, ke));
            JOptionPane.showMessageDialog(this, "Hasil Konversi: " + hasil + " " + ke,
                    "Hasil", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan nilai suhu yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KonversiSuhu().setVisible(true);
        });
    }
}
