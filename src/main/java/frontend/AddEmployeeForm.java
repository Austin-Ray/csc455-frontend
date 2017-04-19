package frontend;

import com.csc.EmployeeWriter;
import com.csc.SqlDatabase;
import com.csc.model.Employee;

import javax.swing.*;

public class AddEmployeeForm extends JFrame {

  private ManageEmployee form;

  public AddEmployeeForm(ManageEmployee form, SqlDatabase db) {
    this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    this.form = form;
    String lbfirstName = "First Name: ";
    String lblastName = "Last Name: ";
    String lbemail = "Email: ";
    String lbpassword = "Password: ";
    String lbRoleId = "Role ID: ";
    String lbVehicleType = "Vehicle Type: ";

    JPanel p1 = new JPanel();
    JTextField tf1 = new JTextField();
    tf1.setColumns(20);
    p1.add(new JLabel(lbfirstName));
    p1.add(tf1);

    JPanel p2 = new JPanel();
    JTextField tf2 = new JTextField();
    tf2.setColumns(20);
    p2.add(new JLabel(lblastName));
    p2.add(tf2);

    JPanel p3 = new JPanel();
    JTextField tf3 = new JTextField();
    tf3.setColumns(20);
    p3.add(new JLabel(lbemail));
    p3.add(tf3);

    JPanel p4 = new JPanel();
    JPasswordField tf4 = new JPasswordField();
    tf4.setColumns(20);
    p4.add(new JLabel(lbpassword));
    p4.add(tf4);

    JPanel p5 = new JPanel();
    JTextField tf5 = new JTextField();
    tf5.setColumns(20);
    String[] roles = {"Driver", "Pilot", "Admin" };
    JComboBox<String> roleBox = new JComboBox<>(roles);
    p5.add(new JLabel(lbRoleId));
    p5.add(roleBox);

    JPanel p6 = new JPanel();
    JTextField tf6 = new JTextField();
    tf6.setColumns(20);
    p6.add(new JLabel(lbVehicleType));
    p6.add(tf6);

    JPanel p7 = new JPanel();
    JButton btOkay = new JButton("Save");
    btOkay.addActionListener(actionEvent -> {
      EmployeeWriter writer = new EmployeeWriter(db);

      try {
        int role = 1;
        String currentVal = roleBox.getItemAt(roleBox.getSelectedIndex());

        switch (currentVal) {
          case "Admin" :
            role = 1;
            break;
          case "Driver" :
            role = 2;
            break;
          case "Pilot" :
            role = 3;
            break;
        }

        Employee newEmp = new Employee(tf1.getText(), tf2.getText(), tf3.getText(),
            new String(tf4.getPassword()), role, tf6.getText());
        writer.insertEmployee(newEmp);
      } catch (Exception e) {

      }

      dispose();
    });
    JButton close = new JButton("Close");
    close.addActionListener(ActionEvent -> dispose());

    p7.add(btOkay);
    p7.add(close);

    this.add(p1);
    this.add(p2);
    this.add(p3);
    this.add(p4);
    this.add(p5);
    this.add(p6);
    this.add(p7);

    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.pack();
  }

  @Override
  public void dispose() {
    form.updateTable();
    super.dispose();
  }
}
