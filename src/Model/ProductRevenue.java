/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI
 */
public class ProductRevenue extends Product{

    public int getQuantitySold() {
        return QuantitySold;
    }

    public void setQuantitySold(int QuantitySold) {
        this.QuantitySold = QuantitySold;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }
    public int QuantitySold;
    public double TotalAmount;
}
