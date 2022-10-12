import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyConverter extends JFrame {

    public CurrencyConverter(){
        initComponents();
    }
    private void initComponents(){
        JComboBox<String> jComboBox2 = new JComboBox<>();
        JPanel jPanel1 = new JPanel();
        JButton jButton1 = new JButton();
        txtAmount = new JTextField();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JButton convert = new JButton();
        txtFrom = new JComboBox<>();
        txtTo = new JComboBox<>();

        jComboBox2.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new Font("Tahoma", Font.BOLD, 18));
        jButton1.setText("Currency Converter");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        txtAmount.addActionListener(this::txtAmountActionPerformed);
        txtAmount.setToolTipText("Use only numbers. Remember to write the amount in the form of: 123.45");
        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel1.setText("Amount");

        jLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel2.setText("From");

        jLabel3.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel3.setText("To");

        convert.setBackground(new Color(255, 204, 102));
        convert.setText("Convert");
        convert.addActionListener(this::ConvertActionPerformed);

        txtFrom.setModel(new DefaultComboBoxModel<>(new String[] { "USD", "PLN", "GBP", "EUR" }));
        txtFrom.setToolTipText("");

        txtTo.setModel(new DefaultComboBoxModel<>(new String[] { "PLN", "USD", "EUR", "GBP" }));
        txtTo.addActionListener(this::txtToActionPerformed);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtAmount)
                                                        .addComponent(txtFrom, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtTo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(151, 151, 151)
                                                .addComponent(convert, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFrom, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtTo)
                                                .addGap(18, 18, 18)))
                                .addGap(78, 78, 78)
                                .addComponent(convert, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Enter the amount you want to convert and select the currencies.");
    }

    private void txtAmountActionPerformed(ActionEvent evt) {
    }

    private void ConvertActionPerformed(ActionEvent evt) {
        int precision = 2;
        BigDecimal convert;
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(txtAmount.getText()));

        if(Objects.equals(Objects.requireNonNull(txtFrom.getSelectedItem()).toString(), "USD") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "PLN")){
            convert = (amount.multiply(BigDecimal.valueOf(5.04))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" PLN");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "PLN") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "USD")){
            convert = (amount.multiply(BigDecimal.valueOf(0.2))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" USD");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "PLN") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "GBP")){
            convert = (amount.multiply(BigDecimal.valueOf(0.18))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" GBP");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "PLN") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "EUR")){
            convert = (amount.multiply(BigDecimal.valueOf(0.21))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" EUR");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "USD") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "EUR")){
            convert = (amount.multiply(BigDecimal.valueOf(1.03))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" EUR");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "USD") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "GBP")){
            convert = (amount.multiply( BigDecimal.valueOf(0.9))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" GBP");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "EUR") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "USD")){
            convert = (amount.multiply( BigDecimal.valueOf(0.97))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" USD");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "EUR") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "PLN")){
            convert = (amount.multiply( BigDecimal.valueOf(4.87))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" PLN");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "EUR") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "GBP")){
            convert = (amount.multiply( BigDecimal.valueOf(0.98))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" GBP");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "GBP") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "USD")){
            convert = (amount.multiply(BigDecimal.valueOf(1.11))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" USD");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "GBP") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "EUR")){
            convert = (amount.multiply(BigDecimal.valueOf(1.14))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" EUR");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "GBP") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "PLN")){
            convert = (amount.multiply(BigDecimal.valueOf(5.56))).setScale(precision, RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(this, "The amount is: "+ convert +" PLN");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "PLN") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "PLN")){
            JOptionPane.showMessageDialog(this, "The amount is: "+amount+" PLN");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "GBP") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "GBP")){
            JOptionPane.showMessageDialog(this, "The amount is: "+amount+" GBP");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "EUR") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "EUR")){
            JOptionPane.showMessageDialog(this, "The amount is: "+amount+" EUR");
        }
        else if(Objects.equals(txtFrom.getSelectedItem().toString(), "USD") && Objects.equals(Objects.requireNonNull(txtTo.getSelectedItem()).toString(), "USD")){
            JOptionPane.showMessageDialog(this, "The amount is: "+amount+" USD");
        }

    }

    private void txtToActionPerformed(ActionEvent evt) {
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
           Logger.getLogger(CurrencyConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new CurrencyConverter().setVisible(true));
    }

    private JTextField txtAmount;
    private JComboBox<String> txtFrom;
    private JComboBox<String> txtTo;

}

