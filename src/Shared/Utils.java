/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shared;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author MSI
 */
public class Utils {
    public static String formatVNCurrency (double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 ₫", symbols);
        
        return decimalFormat.format(value);
    }
}
