package frontend;

import com.csc.OrderWriter;
import com.csc.SqlDatabase;
import com.csc.ZipcodeWriter;
import com.csc.model.Zipcode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

public class SendPackage {

  private int oid = -1;
  public double small = 10.00;
  public double medium = 20.00;
  public double large = 30.00;
  public double weight = 5.50;
  public double total = 0.00;
  private JFrame frame;
  private JTextField txtTotal;

  private SqlDatabase db;
  private int cid;


  /**
   * Create the application.
   */
  public SendPackage(SqlDatabase db, int cid)
  {
    this.db = db;
    this.cid = cid;
    initialize();
  }

  /**
   * Launch the application.
   */
  public static void main(String[] args, SqlDatabase db, int cid) {
    EventQueue.invokeLater(() -> {
      try {
        SendPackage window = new SendPackage(db, cid);
        window.frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Initialize the contents of the frame.
   */
  @SuppressWarnings("unchecked")
  private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setBackground(new Color(255, 228, 196));
    frame.getContentPane().setFont(new Font("Dialog", Font.BOLD, 14));
    frame.setBounds(100, 100, 586, 527);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);


    ZipcodeWriter writer = new ZipcodeWriter(db);
    List<Zipcode> zipList = new ArrayList<>();

    try {
      zipList = writer.getAllZipcodes();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    List<Integer> iList = new ArrayList<>();

    for (Zipcode zipcode : zipList) {
      iList.add(zipcode.getZip());
    }

    Integer[] zipArr = new Integer[zipList.size()];
    zipArr = iList.toArray(zipArr);


    JComboBox<Integer> cmbDestination = new JComboBox(zipArr);
    cmbDestination.setEnabled(false);
    cmbDestination.setFont(new Font("Tahoma", Font.BOLD, 12));
    cmbDestination.setBounds(297, 216, 100, 20);
    frame.getContentPane().add(cmbDestination);

    JComboBox<Integer> cmbOrigen = new JComboBox(zipArr);
    cmbOrigen.setEnabled(false);
    cmbOrigen.setFont(new Font("Tahoma", Font.BOLD, 12));
    cmbOrigen.setBounds(297, 170, 100, 20);
    frame.getContentPane().add(cmbOrigen);

    JLabel lblDestination = new JLabel("Destination Zip");
    lblDestination.setFont(new Font("Dialog", Font.BOLD, 14));
    lblDestination.setBounds(176, 218, 111, 14);
    frame.getContentPane().add(lblDestination);

    JLabel lblOrigenZip = new JLabel("Origen Zip");
    lblOrigenZip.setFont(new Font("Dialog", Font.BOLD, 14));
    lblOrigenZip.setBounds(176, 172, 87, 14);
    frame.getContentPane().add(lblOrigenZip);

    JComboBox<Object> cmbSize = new JComboBox<>();
    cmbSize.setEnabled(false);
    cmbSize.setFont(new Font("Tahoma", Font.BOLD, 12));
    cmbSize.setBounds(297, 77, 100, 20);
    frame.getContentPane().add(cmbSize);

    JLabel lblBoxWeight = new JLabel("Box Weight");
    lblBoxWeight.setFont(new Font("Dialog", Font.BOLD, 14));
    lblBoxWeight.setBounds(176, 125, 87, 14);
    frame.getContentPane().add(lblBoxWeight);

    JLabel lblBoxSize = new JLabel("Box Size");
    lblBoxSize.setFont(new Font("Dialog", Font.BOLD, 14));
    lblBoxSize.setBounds(176, 79, 87, 14);
    frame.getContentPane().add(lblBoxSize);

    txtTotal = new JTextField();
    txtTotal.setEditable(false);
    txtTotal.setEnabled(false);
    txtTotal.setBounds(298, 361, 99, 20);
    frame.getContentPane().add(txtTotal);
    txtTotal.setColumns(10);

    JLabel lblTotal = new JLabel("Total Price");
    lblTotal.setFont(new Font("Dialog", Font.BOLD, 14));
    lblTotal.setBounds(176, 362, 100, 14);
    frame.getContentPane().add(lblTotal);

    JLabel lblTrackingNumber = new JLabel("Order Number");
    lblTrackingNumber.setFont(new Font("Dialog", Font.BOLD, 14));
    lblTrackingNumber.setBounds(176, 395, 100, 14);
    frame.getContentPane().add(lblTrackingNumber);

    JTextArea txtOrderNumber = new JTextArea();
    txtOrderNumber.setEditable(false);
    txtOrderNumber.setBounds(297, 392, 100, 22);
    frame.getContentPane().add(txtOrderNumber);

    JTextArea txtWeight = new JTextArea();
    txtWeight.setEnabled(false);
    txtWeight.setEditable(false);
    txtWeight.setText("Enter Weight");
    txtWeight.setBounds(297, 122, 100, 22);
    frame.getContentPane().add(txtWeight);


    cmbSize.addItem("small");

    cmbSize.addItem("medium");

    cmbSize.addItem("large");


    JButton btnCompleteOrder = new JButton("Complete Order");
    btnCompleteOrder.setEnabled(false);
    btnCompleteOrder.setFont(new Font("Tahoma", Font.BOLD, 12));
    btnCompleteOrder.addActionListener(e -> {

      if (cmbSize.getSelectedItem() == "small") {

        total = small + Double.parseDouble(txtWeight.getText() + weight);
      } else {
        if (cmbSize.getSelectedItem() == "medium") {
          total = medium + Double.parseDouble(txtWeight.getText() + weight);
        } else {
          if (cmbSize.getSelectedItem() == "large") {
            total = large + Double.parseDouble(txtWeight.getText() + weight);
          }
        }
      }

      addParcelToOrder(Float.valueOf(txtWeight.getText()),
          cmbSize.getItemAt(cmbSize.getSelectedIndex()).toString(),
          Float.valueOf(txtTotal.getText()), 1, oid);

      txtOrderNumber.setText(String.valueOf(oid));
      txtTotal.setText(Double.toString(total));
      cmbSize.setSelectedItem(0);
      cmbSize.setEnabled(false);
      txtTotal.setEditable(false);
      txtOrderNumber.setEditable(false);
      //txtOrderNumber.setEnabled(false);
      btnCompleteOrder.setEnabled(false);
      txtWeight.setText("0");
      txtWeight.setEnabled(false);
      cmbOrigen.setEnabled(false);
      cmbDestination.setEnabled(false);

    });
    btnCompleteOrder.setBounds(176, 294, 162, 23);
    frame.getContentPane().add(btnCompleteOrder);
    JButton btnSendAPackage = new JButton("Send a Package");
    btnSendAPackage.addActionListener(e -> {
      txtTotal.setText(Double.toString(total));
      cmbSize.setSelectedItem(0);
      cmbSize.setEnabled(true);
      txtTotal.setEditable(true);
      txtOrderNumber.setEditable(true);
      txtOrderNumber.setText("0000");
      btnCompleteOrder.setEnabled(true);
      txtWeight.setText("");
      txtWeight.setEnabled(true);
      txtWeight.setEditable(true);
      cmbOrigen.setEnabled(true);
      cmbDestination.setEnabled(true);

      OrderWriter orderWriter = new OrderWriter(db);
      try {
        oid = orderWriter.insertOrder(cid, cmbOrigen.getItemAt(cmbOrigen.getSelectedIndex()),
            cmbDestination.getItemAt(cmbDestination.getSelectedIndex()));
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      txtOrderNumber.setText(String.valueOf(oid));
    });

    btnSendAPackage.setFont(new Font("Dialog", Font.BOLD, 14));
    btnSendAPackage.setBounds(176, 26, 160, 23);
    frame.getContentPane().add(btnSendAPackage);
  }

  private void addParcelToOrder(float weight, String size, float price, int quantity, int oid) {
    OrderWriter writer = new OrderWriter(db);
    try {
      writer.parcelInsert(weight, size, price, oid);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
