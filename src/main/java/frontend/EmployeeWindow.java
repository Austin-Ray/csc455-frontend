package frontend;

import com.csc.OrderWriter;
import com.csc.SqlDatabase;
import com.csc.model.Customer;
import com.csc.model.Employee;
import com.csc.model.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.ietf.jgss.Oid;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeWindow {

  private JFrame frame;
  private SqlDatabase db;
  private Employee currentEmployee;
  JTextPane txtpnOrderInfo;

  /**
   * Create the application.
   */
  public EmployeeWindow(SqlDatabase db, Employee currentEmployee) {
    this.db = db;
    this.currentEmployee = currentEmployee;
    initialize();
  }

  /**
   * Launch the application.
   */
  public static void main(String[] args, SqlDatabase db, Employee employee) {
    EventQueue.invokeLater(() -> {
      try {
        EmployeeWindow window = new EmployeeWindow(db, employee);
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
    frame.setBounds(100, 100, 518, 393);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    List<Order> orders = getOrders();

    List<Integer> oids = new ArrayList<>();

    for (Order order : orders) {
      oids.add(order.getOid());
    }

    Integer[] oidsArr = oids.toArray(new Integer[oids.size()]);

    JLabel lblRetrieveAnOrder = new JLabel(" Retrieve an Order");
    lblRetrieveAnOrder.setBounds(10, 23, 111, 16);
    frame.getContentPane().add(lblRetrieveAnOrder);

    txtpnOrderInfo = new JTextPane();
    txtpnOrderInfo.setEditable(false);
    txtpnOrderInfo.setText("");
    txtpnOrderInfo.setContentType("");
    txtpnOrderInfo.setBounds(315, 32, 177, 251);
    frame.getContentPane().add(txtpnOrderInfo);

    JComboBox<Integer> cmbOrders = new JComboBox<>(oidsArr);
    cmbOrders.setEditable(true);
    cmbOrders.setBounds(133, 20, 115, 22);
    cmbOrders.addActionListener(actionEvent -> {
      Order currentOrder = orders.get(cmbOrders.getSelectedIndex());
      updateOrderDetails(currentOrder);
    });
    frame.getContentPane().add(cmbOrders);


    JLabel lblStat = new JLabel(" Order Status");
    lblStat.setBounds(10, 43, 111, 16);
    frame.getContentPane().add(lblStat);

    JComboBox<String> cmbStatus = new JComboBox<>(new String[] {"Received", "Shipped", "Delivered"});
    cmbStatus.setEditable(true);
    cmbStatus.setBounds(133, 40, 115, 22);
    frame.getContentPane().add(cmbStatus);

    JButton btnSave = new JButton("Save ");
    btnSave.addActionListener(e -> txtpnOrderInfo.setEditable(false));
    btnSave.setBounds(162, 173, 97, 25);
    btnSave.addActionListener(actionEvent -> {
      OrderWriter writer = new OrderWriter(db);
      try {
        int oid = cmbOrders.getItemAt(cmbOrders.getSelectedIndex());
        writer.updateOrder(currentEmployee.getEid(), oid);
        writer.setOrderUpdateStatus(oid,
            cmbStatus.getItemAt(cmbStatus.getSelectedIndex()));

        if (cmbStatus.getItemAt(cmbStatus.getSelectedIndex()).equalsIgnoreCase("SHIPPED")) {
          writer.updateOrderShipDate(oid, new Date(System.currentTimeMillis()));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
    frame.getContentPane().add(btnSave);

    JButton btnExit = new JButton("Exit");
    btnExit.setBounds(162, 220, 97, 25);
    frame.getContentPane().add(btnExit);
    btnExit.addActionListener(arg0 -> frame.dispose());

    JLabel lblOrderDetails = new JLabel("Order Details");
    lblOrderDetails.setVisible(false);
    lblOrderDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblOrderDetails.setBounds(345, 11, 103, 14);
    frame.getContentPane().add(lblOrderDetails);
    txtpnOrderInfo.setVisible(false);

    updateOrderDetails(orders.get(cmbOrders.getSelectedIndex()));
  }

  private void updateOrderDetails(Order order) {
    txtpnOrderInfo.setText("CHANGE ME");
  }

  private List<Order> getOrders() {
    OrderWriter writer = new OrderWriter(db);
    List<Order> orders = new ArrayList<>();
    try {
      orders = writer.allOrdersByStatus("RECEIVED");
      orders.addAll(writer.allOrdersByStatus("SHIPPED"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return orders;
  }
}
