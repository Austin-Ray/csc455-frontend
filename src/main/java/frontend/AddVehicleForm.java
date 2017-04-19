package frontend;

import com.csc.SqlDatabase;
import com.csc.TransportWriter;
import com.csc.model.Vehicle;

import javax.swing.*;
import java.sql.SQLException;

public class AddVehicleForm extends JFrame {

  private Inventory screen;
  private SqlDatabase db;

  public AddVehicleForm(Inventory screen, SqlDatabase db) {
    this.screen = screen;
    this.db = db;
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    JPanel row1 = new JPanel();

    JLabel lbl1 = new JLabel("Vehicle Type: ");
    JTextField tf1 = new JTextField();
    tf1.setColumns(20);

    row1.add(lbl1);
    row1.add(tf1);

    JLabel lbl2 = new JLabel("Mileage: ");
    JTextField tf2 = new JTextField();
    tf2.setColumns(20);

    JPanel row2 = new JPanel();
    row2.add(lbl2);
    row2.add(tf2);

    JPanel row3 = new JPanel();
    JButton cancelButton = new JButton("Cancel");
    JButton okayButton = new JButton("Save");

    okayButton.addActionListener(actionEvent -> {
      TransportWriter writer = new TransportWriter(db);
      try {
        writer.insertVehicle(new Vehicle(-1, tf1.getText(), Integer.valueOf(tf2.getText())));
      } catch (SQLException e) {
        e.printStackTrace();
      }
      dispose();
    });
    cancelButton.addActionListener(actionEvent -> dispose());

    row3.add(okayButton);
    row3.add(cancelButton);

    this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    this.add(row1);
    this.add(row2);
    this.add(row3);
    this.pack();
  }

  @Override
  public void dispose() {
    screen.updateVehicleModel();
    super.dispose();
  }
}
