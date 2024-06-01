/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import DBContext.OrderDB;
import Model.Order;
import Model.OrderDetail;
import Shared.Utils;

import java.util.ArrayList;
import javax.swing.UIManager;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class Form_Order extends javax.swing.JFrame {

   ArrayList<Order> orders = new ArrayList<>();
    ArrayList<OrderDetail> orderDetails = new ArrayList<>();
    
    
    public Form_Order() {
        initComponents();
        renderOrderTable();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_order = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuAccount = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        MenuHome = new javax.swing.JMenu();
        MenuProduct = new javax.swing.JMenu();
        MenuSupplier = new javax.swing.JMenu();
        MenuImportProduct = new javax.swing.JMenu();
        MenuOrder = new javax.swing.JMenu();
        MenuCustomer = new javax.swing.JMenu();
        MenuStats = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 173, 173));
        jLabel1.setText("Danh sách đơn hàng");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        table_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã đơn hàng", "Trạng thái", "Ngày đặt", "Ngày giao", "Tổng tiền"
            }
        ));
        table_order.setEditingRow(0);
        table_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_orderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_order);

        MenuAccount.setText("Tài khoản");

        jMenuItem1.setText("Đăng xuất");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuAccount.add(jMenuItem1);

        jMenuBar1.add(MenuAccount);

        MenuHome.setText("Trang chủ");
        MenuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuHomeMouseClicked(evt);
            }
        });
        jMenuBar1.add(MenuHome);

        MenuProduct.setText("Sản phẩm");
        MenuProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuProductMouseClicked(evt);
            }
        });
        MenuProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProductActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuProduct);

        MenuSupplier.setText("Nhà cung cấp");
        MenuSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuSupplierMouseClicked(evt);
            }
        });
        MenuSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSupplierActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuSupplier);

        MenuImportProduct.setText("Nhập hàng");
        MenuImportProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuImportProductMouseClicked(evt);
            }
        });
        MenuImportProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuImportProductActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuImportProduct);

        MenuOrder.setText("Đơn hàng");
        MenuOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuOrderMouseClicked(evt);
            }
        });
        MenuOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOrderActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuOrder);

        MenuCustomer.setText("Khách hàng");
        MenuCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuCustomerMouseClicked(evt);
            }
        });
        MenuCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCustomerActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuCustomer);

        MenuStats.setText("Thống kê");
        MenuStats.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuStatsMouseClicked(evt);
            }
        });
        MenuStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuStatsActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuStats);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_orderMouseClicked

        boolean test = table_order.isEditing();
        
        if (test == false) {
            int currentIndex = table_order.getSelectedRow();
            Order crrOrder = orders.get(currentIndex);
            
            new Form_OrderDetail(crrOrder).setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_table_orderMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MenuProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProductActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Product().setVisible(true);
    }//GEN-LAST:event_MenuProductActionPerformed

    private void MenuSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSupplierActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Supplier().setVisible(true);
    }//GEN-LAST:event_MenuSupplierActionPerformed

    private void MenuImportProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuImportProductActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_ImportProduct().setVisible(true);
    }//GEN-LAST:event_MenuImportProductActionPerformed

    private void MenuOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOrderActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Order().setVisible(true);
    }//GEN-LAST:event_MenuOrderActionPerformed

    private void MenuCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCustomerActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Customer().setVisible(true);
    }//GEN-LAST:event_MenuCustomerActionPerformed

    private void MenuStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuStatsActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Stats().setVisible(true);
    }//GEN-LAST:event_MenuStatsActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void MenuProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuProductMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Product().setVisible(true);
    }//GEN-LAST:event_MenuProductMouseClicked

    private void MenuSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuSupplierMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Supplier().setVisible(true);
    }//GEN-LAST:event_MenuSupplierMouseClicked

    private void MenuImportProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuImportProductMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_ImportProductList().setVisible(true);
    }//GEN-LAST:event_MenuImportProductMouseClicked

    private void MenuOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuOrderMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Order().setVisible(true);
    }//GEN-LAST:event_MenuOrderMouseClicked

    private void MenuCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuCustomerMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Customer().setVisible(true);
    }//GEN-LAST:event_MenuCustomerMouseClicked

    private void MenuStatsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuStatsMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Form_Stats().setVisible(true);
    }//GEN-LAST:event_MenuStatsMouseClicked

    private void MenuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuHomeMouseClicked
        // TODO add your handling code here:
          this.setVisible(false);
        new Form_Dashboard().setVisible(true);
    }//GEN-LAST:event_MenuHomeMouseClicked

    private void renderOrderTable() {
//        table_order.setEnabled(false);
        DefaultTableModel tableModel = (DefaultTableModel) table_order.getModel();
        orders.clear();
        tableModel.setRowCount(0);
        
        
        try {
            orders = OrderDB.getOrders();
            
             for (Order order : orders) {
                tableModel.addRow(new Object[]{
                    order.getId(),
                    order.getStatus(),
                    order.getOrderDate(),
                    order.getReceiveDate(),
                    Utils.formatVNCurrency(order.getTotalPayment()),
                });
            }
        } catch (Exception e) {
        }
    }
    
    
    
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                } catch (Exception e) {

                }
                new Form_Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuAccount;
    private javax.swing.JMenu MenuCustomer;
    private javax.swing.JMenu MenuHome;
    private javax.swing.JMenu MenuImportProduct;
    private javax.swing.JMenu MenuOrder;
    private javax.swing.JMenu MenuProduct;
    private javax.swing.JMenu MenuStats;
    private javax.swing.JMenu MenuSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_order;
    // End of variables declaration//GEN-END:variables
}
