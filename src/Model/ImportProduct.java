/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author MSI
 */
public class ImportProduct {

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    
    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int SupplierId) {
        this.SupplierId = SupplierId;
    }

    public double getTotalPayment() {
        return TotalPayment;
    }

    public void setTotalPayment(double TotalPayment) {
        this.TotalPayment = TotalPayment;
    }
    public int Id;
    public Timestamp CreatedAt;
    public int SupplierId;
    public double TotalPayment;
    public String SupplierName;
    

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }
}
