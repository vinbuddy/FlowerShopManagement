/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import DBContext.CategoryDB;
import DBContext.ImportProductDB;
import DBContext.OrderDB;
import DBContext.ProductDB;
import DBContext.SupplierDB;
import Model.Category;
import Model.Product;
import Model.Supplier;
import java.awt.Component;
import java.awt.Image;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class Form_ImportProduct extends javax.swing.JFrame {

    /**
     * Creates new form Form_ImportProduct
     */
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> selectedProducts = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Supplier> suppliers = new ArrayList<>();

    DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 ₫", symbols);

    private static final String search_placeholder = "Tìm kiếm sản phẩm";
    private static final int INIT_QUANTITY = 1;

    public Form_ImportProduct() {
        initComponents();

        renderSupplierCombobox();
        renderCategoryCombobox();
        renderProductTable();
        renderTotalPaymentLabel();
        spinner_quantity.setValue(INIT_QUANTITY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_select = new javax.swing.JButton();
        combobox_category = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        spinner_quantity = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_product = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_selected_product = new javax.swing.JTable();
        txt_searchbox = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        label_total_payement = new javax.swing.JLabel();
        btn_checkout = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        combobox_supplier = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_select.setText("Thêm");
        btn_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selectActionPerformed(evt);
            }
        });

        jLabel2.setText("Chọn số lượng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinner_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spinner_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        table_product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Hình ảnh", "Tên sản phẩm", "Số lượng", "Đơn giá", "Giá giảm"
            }
        ));
        table_product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_productMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_product);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        table_selected_product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(table_selected_product);

        txt_searchbox.setText("Tìm kiếm sản phẩm");
        txt_searchbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchboxFocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tổng tiền:");

        label_total_payement.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_total_payement.setText("90.000 đ");

        btn_checkout.setBackground(new java.awt.Color(255, 173, 203));
        btn_checkout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_checkout.setForeground(new java.awt.Color(255, 255, 255));
        btn_checkout.setText("Nhập hàng");
        btn_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkoutActionPerformed(evt);
            }
        });

        btn_cancel.setText("Hủy");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_total_payement, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(label_total_payement)
                        .addComponent(btn_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-search-32.png"))); // NOI18N
        btn_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_searchMouseClicked(evt);
            }
        });

        btn_back.setText("Quay lại");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        combobox_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_supplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(btn_select, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(combobox_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_searchbox, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combobox_category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_searchbox, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(combobox_category)
                    .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox_supplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_select)
                        .addGap(21, 21, 21))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selectActionPerformed
        int currentIndex = table_product.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table_product.getModel();

        if (currentIndex >= 0) {
            Product currentProduct = products.get(currentIndex);
            int selectedQuantity = (int) spinner_quantity.getValue();

            if (currentProduct.getQuantity() == 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã hết", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedQuantity > currentProduct.getQuantity()) {
                JOptionPane.showMessageDialog(this, "Số lượng mua phải nhỏ hơn số lượng tồn!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product selectedProduct = new Product();
            selectedProduct.Id = currentProduct.getId();
            selectedProduct.Name = currentProduct.getName();
            selectedProduct.Quantity = selectedQuantity;
            selectedProduct.Price = currentProduct.DiscountPrice != 0.0 ? currentProduct.DiscountPrice : currentProduct.getPrice();

            selectedProducts.add(selectedProduct);

            renderSelectedProductTable();
            renderTotalPaymentLabel();
        }
    }//GEN-LAST:event_btn_selectActionPerformed

    private void table_productMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_productMouseClicked

    }//GEN-LAST:event_table_productMouseClicked

    private void txt_searchboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchboxFocusGained
        if (txt_searchbox.getText().equals(search_placeholder)) {
            txt_searchbox.setText("");
        }
    }//GEN-LAST:event_txt_searchboxFocusGained

    private void btn_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkoutActionPerformed
        handleImportProduct();
    }//GEN-LAST:event_btn_checkoutActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        selectedProducts.clear();
        renderSelectedProductTable();
        renderTotalPaymentLabel();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseClicked
        renderProductTable();

        if (txt_searchbox.getText().length() == 0) {
            txt_searchbox.setText(search_placeholder);
        }
    }//GEN-LAST:event_btn_searchMouseClicked

    private void combobox_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_supplierActionPerformed
        renderProductTable();
    }//GEN-LAST:event_combobox_supplierActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        this.setVisible(false);
        new Form_ImportProductList().setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void renderProductTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table_product.getModel();
        products.clear();
        tableModel.setRowCount(0);

        String searchValue = txt_searchbox.getText().toString().equals(search_placeholder) ? "" : txt_searchbox.getText();
        String categoryName = (String) combobox_category.getSelectedItem();

        Category currentCategory = getCategoryByName(categoryName);
        String currentCategoryName = currentCategory == null ? "Tất cả" : currentCategory.getCategoryName();

        try {
            if (currentCategoryName == "Tất cả") {
                if (searchValue == "" || searchValue.equals(search_placeholder)) {
                    products = ProductDB.getProducts();

                    System.out.println(products.size());
                } else {

                    products = (ArrayList<Product>) ProductDB.getProducts().stream().filter(item -> item.getName().toLowerCase().contains(searchValue.toLowerCase())).collect(Collectors.toList());
                }

            } else {
                products = ProductDB.getProductsByCategoryIdAndName(currentCategory.getId(), searchValue);
            }

            String supplierName = (String) combobox_supplier.getSelectedItem();

            Supplier currentSupplier = getSupplierByName(supplierName);

            if (currentSupplier != null) {
                int supplierId = currentSupplier.getId();
                products = (ArrayList<Product>) products.stream()
                        .filter(product -> product.getSupplierId() == supplierId)
                        .collect(Collectors.toList());
            }

            for (Product product : products) {
                tableModel.addRow(new Object[]{
                    product.getImage(),
                    product.getName(),
                    product.getQuantity(),
                    decimalFormat.format(product.getPrice()),
                    decimalFormat.format(product.getDiscountPrice()),});
            }

            table_product.setRowHeight(50);
            table_product.getTableHeader().setReorderingAllowed(false);

            table_product.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());

        } catch (Exception e) {
        }

    }

    private class ImageRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String photoName = value.toString();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/" + photoName).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

            return new JLabel(imageIcon);
        }

    }

    private Category getCategoryByName(String categoryName) {
        return categories.stream()
                .filter(product -> product.getCategoryName().equals(categoryName))
                .findFirst()
                .orElse(null);
    }

    private Supplier getSupplierByName(String name) {
        return suppliers.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private void renderSupplierCombobox() {
        try {
            suppliers = SupplierDB.getSuppliers();
            for (Supplier supplier : suppliers) {
                combobox_supplier.addItem(supplier.getName());
            }
        } catch (Exception e) {
        }
    }

    private void renderCategoryCombobox() {

        try {
            categories = CategoryDB.getCategories();

            combobox_category.addItem("Tất cả");
            for (Category category : categories) {
                combobox_category.addItem(category.getCategoryName());
            }
        } catch (Exception e) {
        }
    }

    private void renderSelectedProductTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table_selected_product.getModel();

        tableModel.setRowCount(0);

        for (Product product : selectedProducts) {
            tableModel.addRow(new Object[]{
                product.getName(),
                product.getQuantity(),
                decimalFormat.format(product.getPrice()),
                decimalFormat.format(product.calculateTotalPrice())
            });
        }
    }

    private void renderTotalPaymentLabel() {
        double totalPayment = 0.0;
        for (Product product : selectedProducts) {
            totalPayment += product.calculateTotalPrice();
        }

        label_total_payement.setText(String.valueOf(decimalFormat.format(totalPayment)));
    }

    private void handleImportProduct() {
        try {
            String supplierName = combobox_supplier.getSelectedItem().toString();
            int supplierId = getSupplierByName(supplierName).getId();
            boolean result = ImportProductDB.ImportProduct(supplierId, selectedProducts);
            if (result) {
                
                int isPrinted = JOptionPane.showConfirmDialog(this,
                        "Tạo thành công, bạn có muốn in hóa đơn không",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                //JOptionPane.showMessageDialog(this, "Tạo đơn nhập hàng thành công");
                
                if(isPrinted == JOptionPane.YES_OPTION){
                    //table_selected_product.print()
                }

                selectedProducts.clear();
                renderSelectedProductTable();
                renderTotalPaymentLabel();

                this.setVisible(false);
                new Form_ImportProductList().setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Form_ImportProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_ImportProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_ImportProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_ImportProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                } catch (Exception e) {

                }
                new Form_ImportProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_checkout;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_select;
    private javax.swing.JComboBox<String> combobox_category;
    private javax.swing.JComboBox<String> combobox_supplier;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_total_payement;
    private javax.swing.JSpinner spinner_quantity;
    private javax.swing.JTable table_product;
    private javax.swing.JTable table_selected_product;
    private javax.swing.JTextField txt_searchbox;
    // End of variables declaration//GEN-END:variables
}
