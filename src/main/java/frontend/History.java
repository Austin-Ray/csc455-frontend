package frontend;

import com.csc.OrderWriter;
import com.csc.SqlDatabase;
import com.csc.model.Order;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class History {

  private JFrame frame;
  private SqlDatabase db;
  private int cid;

  /**
   * Create the application.
   */
  public History(SqlDatabase db, int cid) {
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
        History window = new History(db, cid);
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
    frame.getContentPane().setBackground(new Color(238, 232, 170));
    frame.getContentPane().setForeground(new Color(255, 250, 205));
    frame.setBounds(100, 100, 480, 384);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JLabel lblNewLabel = new JLabel("Customer Order History");
    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblNewLabel.setBounds(154, 11, 178, 14);
    frame.getContentPane().add(lblNewLabel);

    OrderWriter writer = new OrderWriter(db);

    StringBuilder orders = new StringBuilder();

    try {
      List<Order> temp = writer.allOrdersByCustomer(cid);

      for (Order e : temp) {
        orders.append(e.getOid()).append(" : ").append(e.getStatus()).append("\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JTextPane textPane = new JTextPane();
    textPane.setBackground(new Color(176, 224, 230));
    textPane.setFont(new Font("Tahoma", Font.BOLD, 14));
    textPane.setBounds(79, 55, 301, 252);
    textPane.setText(orders.toString());
    frame.getContentPane().add(textPane);
  }
}
