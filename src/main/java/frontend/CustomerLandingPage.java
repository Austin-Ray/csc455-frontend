package frontend;

import com.csc.OrderWriter;
import com.csc.SqlDatabase;
import com.csc.model.Customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;

public class CustomerLandingPage {

  private JFrame frame;

  private SqlDatabase db;
  private Customer user;

  /**
   * Create the application.
   */
  public CustomerLandingPage(SqlDatabase db, Customer user) {
    this.db = db;
    this.user = user;

    initialize();
  }

  /**
   * Launch the application.
   */
  public static void main(String[] args, SqlDatabase db, Customer user) {
    EventQueue.invokeLater(() -> {
      try {
        CustomerLandingPage window = new CustomerLandingPage(db, user);
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
    frame.getContentPane().setBackground(new Color(255, 255, 224));
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JButton btnHistory = new JButton("Order History");
    btnHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
    btnHistory.setBounds(156, 103, 123, 23);
    btnHistory.addActionListener(arg0 -> History.main(null, db, user.getCid()));
    frame.getContentPane().setLayout(null);

    JTextPane textPane = new JTextPane();
    textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
    textPane.setBackground(new Color(255, 235, 205));
    textPane.setEditable(false);
    textPane.setBounds(54, 37, 326, 55);

    String userInfo = user.getFirstName() + " " + user.getLastName() + ", " + user.getPhone() + "\n"
        + user.getAddress() + " " + user.getZipcode() + "\n"
        + user.getEmail();

    textPane.setText(userInfo);
    frame.getContentPane().add(textPane);
    frame.getContentPane().add(btnHistory);

    JButton btnTrack = new JButton("Track Package");
    btnTrack.setFont(new Font("Tahoma", Font.BOLD, 11));
    btnTrack.setBounds(156, 148, 123, 23);
    btnTrack.addActionListener(e -> TrackPackage.main(null, db, user.getCid()));
    frame.getContentPane().add(btnTrack);

    JButton btncreate = new JButton("Ship Package");
    btncreate.setFont(new Font("Tahoma", Font.BOLD, 11));
    btncreate.setBounds(156, 199, 123, 23);
    btncreate.addActionListener(e -> SendPackage.main(null,db, user.getCid()));
    frame.getContentPane().add(btncreate);

    JLabel lblCustomerInformation = new JLabel("Customer Information");
    lblCustomerInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblCustomerInformation.setBounds(136, 12, 162, 14);
    frame.getContentPane().add(lblCustomerInformation);
  }
}
