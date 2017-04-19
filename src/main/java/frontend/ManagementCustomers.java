package frontend;

import com.csc.CustomerWriter;
import com.csc.SqlDatabase;
import com.csc.model.Customer;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author ssb7848
 */
public class ManagementCustomers extends javax.swing.JFrame {

  private SqlDatabase db;
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnExit;
  private javax.swing.JButton btnModify;
  private javax.swing.JButton btnRemoveOrder;
  private javax.swing.JButton btnUpdate;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lblSearch;
  private javax.swing.JTable tblCustomers;
  private javax.swing.JTextField tbxSearch;

  String[] columnHeaders = new String[] {
    "Customer Id", "C_Last Name", "C_Name", "Address", "User Id", "Zip", "Phone", "Email"
  };

  /**
   * Creates new form ManagementCustomers
   */
  public ManagementCustomers(SqlDatabase db) {
    this.db = db;
    initComponents() ;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[], SqlDatabase db) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | javax.swing.UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
      java.util.logging.Logger.getLogger(ManagementCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

        /* Create and display the form */

    java.awt.EventQueue.invokeLater(() -> new ManagementCustomers(db).setVisible(true));
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {
    jScrollPane1 = new javax.swing.JScrollPane();
    tblCustomers = new javax.swing.JTable();
    jLabel1 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    tbxSearch = new javax.swing.JTextField();
    btnUpdate = new javax.swing.JButton();
    btnRemoveOrder = new javax.swing.JButton();
    lblSearch = new javax.swing.JLabel();
    btnModify = new javax.swing.JButton();
    btnExit = new javax.swing.JButton();

    btnModify.setVisible(false);
    btnUpdate.setVisible(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    tblCustomers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null}
        },
        new String[] {
            "Customer Id", "C_Last Name", "C_Name", "Address", "User Id", "Zip", "Phone", "Email"
        }
    ));
    jScrollPane1.setViewportView(tblCustomers);

    jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
    jLabel1.setText("Customers Management");

    jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    //jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
    //jLabel2.setText("Customer Edit");
    //jLabel2.setToolTipText("");

    tbxSearch.addActionListener(this::tbxSearchActionPerformed);

    btnUpdate.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
    btnUpdate.setText("Update");
    btnUpdate.addActionListener(this::btnUpdateActionPerformed);

    btnRemoveOrder.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
    btnRemoveOrder.setText("remove Customer");
    btnRemoveOrder.addActionListener(this::btnRemoveOrderActionPerformed);

    lblSearch.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
    lblSearch.setText("Search");

    btnModify.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
    btnModify.setText("Modify");
    btnModify.addActionListener(this::btnModifyActionPerformed);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate)
                            .addComponent(btnRemoveOrder)
                            .addComponent(btnModify))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(69, 69, 69))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addComponent(lblSearch)
                .addGap(18, 18, 18)
                .addComponent(tbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnRemoveOrder)
                .addGap(18, 18, 18)
                .addComponent(btnModify)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch))
                .addContainerGap(34, Short.MAX_VALUE))
    );

    btnExit.setText("Exit");
    btnExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    btnExit.addActionListener(this::btnExitActionPerformed);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
    );

    tblCustomers.setEnabled(true);
    updateModel();
    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void tbxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbxSearchActionPerformed
    // TODO add your handling code here:
    String value = tbxSearch.getText();

    for (int row = 0; row <= tblCustomers.getRowCount() - 1; row++) {

      for (int col = 0; col <= tblCustomers.getColumnCount() - 1; col++) {

        if (value.equals(tblCustomers.getValueAt(row, col))) {

          // this will automatically set the view of the scroll in the location of the value
          tblCustomers.scrollRectToVisible(tblCustomers.getCellRect(row, 0, true));

          // this will automatically set the focus of the searched/selected row/value
          tblCustomers.setRowSelectionInterval(row, row);

          for (int i = 0; i <= tblCustomers.getColumnCount() - 1; i++) {

            tblCustomers.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
          }
        }
      }
    }

  }//GEN-LAST:event_tbxSearchActionPerformed

  private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    // TODO add your handling code here:
    int sel = tblCustomers.getSelectedRow();
    TableModel model = tblCustomers.getModel();

    CustomerWriter writer = new CustomerWriter(db);
    try {
      Customer cust = writer.getCustomersByCid((Integer)model.getValueAt(sel, 0));
      String ps = cust.getPassword();

      Customer newC = new Customer((String) model.getValueAt(sel, 2), (String) model.getValueAt(sel, 1),
          (String) model.getValueAt(sel, 7),ps, (String)model.getValueAt(sel, 3), (Integer)model.getValueAt(sel, 5),
          (String)model.getValueAt(sel, 6));

      writer.insertCustomer(newC);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    tblCustomers.clearSelection();
    tblCustomers.setEnabled(true);
  }//GEN-LAST:event_btnUpdateActionPerformed

  private void btnRemoveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveOrderActionPerformed
    // TODO add your handling code here:
    CustomerWriter writer = new CustomerWriter(db);
    Integer uid = (Integer)tblCustomers.getModel().getValueAt(tblCustomers.getSelectedRow(), 4);

    try {
      writer.deleteUser(uid);
      updateModel();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }//GEN-LAST:event_btnRemoveOrderActionPerformed

  private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
    // TODO add your handling code here:
    tblCustomers.setEnabled(rootPaneCheckingEnabled);
    tblCustomers.isEditing();
  }//GEN-LAST:event_btnModifyActionPerformed

  private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
    // TODO add your handling code here:
    dispose();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }//GEN-LAST:event_btnExitActionPerformed

  private class HighlightRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

      // everything as usual
      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      // added behavior
      if (row == table.getSelectedRow()) {

        // this will customize that kind of border that will be use to highlight a row
        setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
      }

      return this;
    }
  }
  // End of variables declaration//GEN-END:variables

  public void updateModel() {
    CustomerWriter writer = new CustomerWriter(db);
    List<Customer> customers;
    try {
      customers = writer.getAllCustomers();

      Object[][] cust = new Object[customers.size()][8];

      for (int i = 0; i < cust.length; i++) {
        cust[i] = customerToRow(customers.get(i));
      }

      tblCustomers.setModel(new DefaultTableModel(cust, columnHeaders));

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private Object[] customerToRow(Customer c) {
    return new Object[] {c.getCid(), c.getLastName(), c.getFirstName(), c.getAddress(), c.getUid(),
        c.getZipcode(), c.getPhone(), c.getEmail()};
  }
}