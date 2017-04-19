package frontend;

import com.csc.OrderWriter;
import com.csc.SqlDatabase;
import com.csc.model.Order;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TrackPackage {

  private JFrame frame;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;

  private SqlDatabase db;
  private int cid;

  /**
   * Create the application.
   */
  public TrackPackage(SqlDatabase db, int cid) {
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
        TrackPackage window = new TrackPackage(db, cid);
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
    frame.getContentPane().setBackground(new Color(255, 248, 220));
    frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
    frame.setBounds(100, 100, 599, 507);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);


    OrderWriter writer = new OrderWriter(db);
    List<Order> allOrders = new ArrayList<>();

    try {
      allOrders = writer.allOrdersByCustomer(cid);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    List<Integer> oid = new ArrayList<>();

    for (Order allOrder : allOrders) {
      oid.add(allOrder.getOid());
    }

    oid.sort((integer, t1) -> t1 - integer);

    Integer[] holder = new Integer[oid.size()];
    holder = oid.toArray(holder);

    JComboBox<Integer> comboBox = new JComboBox<>(holder);
    comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
    comboBox.setBounds(237, 45, 109, 32);
    List<Order> finalAllOrders = allOrders;
    comboBox.addActionListener(actionEvent -> getCurrentId(finalAllOrders, comboBox));
    frame.getContentPane().add(comboBox);

    JLabel lblPleaseSelectThe = new JLabel("Please Select the package ");
    lblPleaseSelectThe.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblPleaseSelectThe.setBounds(177, 21, 228, 23);
    frame.getContentPane().add(lblPleaseSelectThe);

    JLabel lblNewLabel = new JLabel("Order status");
    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblNewLabel.setBounds(198, 126, 91, 14);
    frame.getContentPane().add(lblNewLabel);

    textField = new JTextField();
    textField.setFont(new Font("Tahoma", Font.BOLD, 14));
    textField.setEditable(false);
    textField.setBounds(299, 120, 86, 30);
    frame.getContentPane().add(textField);
    textField.setColumns(10);

    JLabel lblOrigenZip = new JLabel("Origin Zip");
    lblOrigenZip.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblOrigenZip.setBounds(198, 183, 91, 14);
    frame.getContentPane().add(lblOrigenZip);

    textField_1 = new JTextField();
    textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
    textField_1.setEditable(false);
    textField_1.setColumns(10);
    textField_1.setBounds(299, 177, 86, 30);
    frame.getContentPane().add(textField_1);

    JLabel lblDestinationZip = new JLabel("Destination ");
    lblDestinationZip.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblDestinationZip.setBounds(198, 235, 91, 14);
    frame.getContentPane().add(lblDestinationZip);

    textField_2 = new JTextField();
    textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
    textField_2.setEditable(false);
    textField_2.setColumns(10);
    textField_2.setBounds(299, 229, 86, 30);
    frame.getContentPane().add(textField_2);

    JLabel lblLocation = new JLabel("Location");
    lblLocation.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblLocation.setBounds(198, 292, 91, 14);
    frame.getContentPane().add(lblLocation);

    textField_3 = new JTextField();
    textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
    textField_3.setEditable(false);
    textField_3.setColumns(10);
    textField_3.setBounds(299, 286, 86, 30);
    frame.getContentPane().add(textField_3);

    JLabel label_3 = new JLabel("Order status");
    label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
    label_3.setBounds(198, 345, 91, 14);
    frame.getContentPane().add(label_3);

    textField_4 = new JTextField();
    textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
    textField_4.setEditable(false);
    textField_4.setColumns(10);
    textField_4.setBounds(299, 339, 86, 30);
    frame.getContentPane().add(textField_4);

    JTextPane textPane = new JTextPane();
    textPane.setBackground(new Color(204, 255, 204));
    textPane.setBounds(100, 403, 383, 54);
    frame.getContentPane().add(textPane);

    JLabel lblCustomerInfo = new JLabel("Customer Information");
    lblCustomerInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblCustomerInfo.setBounds(204, 378, 175, 14);
    frame.getContentPane().add(lblCustomerInfo);

    getCurrentId(finalAllOrders, comboBox);
  }

  private void getCurrentId(List<Order> orders, JComboBox<Integer> bc) {
    int oid = bc.getItemAt(bc.getSelectedIndex());

    Order currentOrder = orders.stream()
        .filter(e -> e.getOid() == oid)
        .collect(Collectors.toList())
        .get(0);

    textField.setText(currentOrder.getStatus());
    textField_1.setText(String.valueOf(currentOrder.getOriginZip()));
    textField_2.setText(String.valueOf(currentOrder.getDestinationZip()));
    textField_3.setText(String.valueOf(currentOrder.getActualZip()));
    textField_4.setText(currentOrder.getStatus());
  }
}
