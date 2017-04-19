package frontend;

import com.csc.CustomerWriter;
import com.csc.SqlDatabase;
import com.csc.ZipcodeWriter;
import com.csc.model.Customer;
import com.csc.model.Zipcode;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class SignUps {

  private JFrame frame;
  private JTextField txtname;
  private JTextField txtLastName;
  private JTextField txtEmail;
  private JTextField txtAddress;
  private JTextField txtCity;
  private JTextField txtPhone;

  private SqlDatabase db;

  /**
   * Create the application.
   */
  public SignUps(SqlDatabase db) {
    this.db = db;
    initialize();
  }

  /**
   * Launch the application.
   */
  public static void main(ActionEvent arg0, SqlDatabase db) {
    EventQueue.invokeLater(() -> {
      try {
        SignUps window = new SignUps(db);
        window.frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 452);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    txtname = new JTextField();
    txtname.setBounds(117, 51, 133, 30);
    frame.getContentPane().add(txtname);
    txtname.setColumns(10);

    JLabel lblLastName = new JLabel("Last Name");
    lblLastName.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblLastName.setBounds(22, 90, 83, 20);
    frame.getContentPane().add(lblLastName);

    JLabel lblname = new JLabel("Name");
    lblname.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblname.setBounds(22, 54, 68, 14);
    frame.getContentPane().add(lblname);

    txtLastName = new JTextField();
    txtLastName.setBounds(117, 92, 133, 30);
    frame.getContentPane().add(txtLastName);
    txtLastName.setColumns(10);

    JLabel labelEmail = new JLabel("Email");
    labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
    labelEmail.setBounds(22, 146, 83, 20);
    frame.getContentPane().add(labelEmail);

    txtEmail = new JTextField();
    txtEmail.setBounds(117, 148, 192, 30);
    frame.getContentPane().add(txtEmail);
    txtEmail.setColumns(10);

    JLabel lblHomeAddress = new JLabel("Address");
    lblHomeAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblHomeAddress.setBounds(22, 198, 75, 20);
    frame.getContentPane().add(lblHomeAddress);

    txtAddress = new JTextField();
    txtAddress.setBounds(117, 200, 192, 30);
    frame.getContentPane().add(txtAddress);
    txtAddress.setColumns(10);

    JLabel lblCity = new JLabel("City");
    lblCity.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblCity.setBounds(22, 241, 83, 20);
    frame.getContentPane().add(lblCity);

    JLabel lblZipcode = new JLabel("Zipcode");
    lblZipcode.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblZipcode.setBounds(22, 281, 83, 20);
    frame.getContentPane().add(lblZipcode);

    JLabel lblPhone = new JLabel("Phone");
    lblPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblPhone.setBounds(22, 325, 83, 20);
    frame.getContentPane().add(lblPhone);

    txtCity = new JTextField();
    txtCity.setBounds(115, 243, 135, 30);
    frame.getContentPane().add(txtCity);
    txtCity.setColumns(10);

    JLabel lblPass = new JLabel("Password");
    lblPass.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblPass.setBounds(22, 366, 83, 20);
    frame.getContentPane().add(lblPass);

    JTextField pass = new JPasswordField();
    pass.setBounds(115, 366, 135, 30);
    frame.getContentPane().add(pass);
    pass.setColumns(12);

    txtPhone = new JTextField();
    txtPhone.setBounds(115, 327, 135, 30);
    frame.getContentPane().add(txtPhone);
    txtPhone.setColumns(10);

    JLabel lblSignUp = new JLabel("Sign Up ");
    lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 16));
    lblSignUp.setBounds(137, 11, 100, 29);
    frame.getContentPane().add(lblSignUp);

    JComboBox<String> cmbZipcode = new JComboBox<>();

    ZipcodeWriter zWriter = new ZipcodeWriter(db);
    List<Zipcode> zipcodes = new ArrayList<>();

    try {
      zipcodes = zWriter.getAllZipcodes();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    List<String> zips = new ArrayList<>();

    zipcodes.forEach(e -> zips.add(String.valueOf(e.getZip())));
    String[] zipArr = new String[zipcodes.size()];
    zipArr = zips.toArray(zipArr);

    final String[] finalArr = zipArr;

    cmbZipcode.setModel(new DefaultComboBoxModel<>(zipArr));
    cmbZipcode.setToolTipText("");
    cmbZipcode.setMaximumRowCount(100);
    cmbZipcode.setBounds(117, 283, 83, 20);
    frame.getContentPane().add(cmbZipcode);

    JButton btnSave = new JButton("Save");
    btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
    btnSave.setBounds(102, 396, 89, 23);
    btnSave.addActionListener(e -> {
      CustomerWriter writer = new CustomerWriter(db);
      Customer customer = new Customer(txtname.getText(), txtLastName.getText(), txtEmail.getText(),
          pass.getText(), txtAddress.getText(),
          Integer.valueOf(finalArr[cmbZipcode.getSelectedIndex()]), txtPhone.getText());
      try {
        writer.insertCustomer(customer);
        this.frame.dispose();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    });
    frame.add(btnSave);

    JButton btnClear = new JButton("Clear");
    btnClear.addActionListener(arg0 -> {
      txtCity.setText("");
      txtPhone.setText("");
      txtEmail.setText("");
      txtLastName.setText("");
      txtname.setText("");
      txtAddress.setText("");
      cmbZipcode.setSelectedIndex(0);
    });
    btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
    btnClear.setBounds(248, 396, 89, 23);
    frame.getContentPane().add(btnClear);
  }
}