/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shared;

import Model.ImportProduct;
import Model.Order;
import Model.Product;
import Model.ProductRevenue;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class PDFGenerator {

    public static void createOrderInvoice(String filePath, Order order, ArrayList<Product> selectedProducts) {
        Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Sử dụng font Roboto hỗ trợ tiếng Việt
                BaseFont baseFont = BaseFont.createFont("Usage/Font/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12, Font.NORMAL);

                // Thêm tiêu đề
                Font titleFont = new Font(baseFont, 18, Font.BOLD);
                Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm thông tin cửa hàng
                Paragraph storeInfo = new Paragraph("Tên cửa hàng: Cửa hàng ABC\nĐịa chỉ: 123 Đường ABC, Quận 1, TP. HCM\nSố điện thoại: 0123456789", font);
                storeInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(storeInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm thông tin khách hàng
//                Paragraph customerInfo = new Paragraph("Thông tin khách hàng:\nTên: Nguyễn Văn A\nĐịa chỉ: 456 Đường DEF, Quận 2, TP. HCM\nSố điện thoại: 0987654321", font);
//                customerInfo.setAlignment(Element.ALIGN_LEFT);
//                document.add(customerInfo);

                // Thêm thông tin đơn hàng
                String orderInfoParagraph = "Thông tin đơn hàng\nMã đơn hàng: " + order.getId() + "\nTrạng thái : " + order.getStatus();
               
                Paragraph customerInfo = new Paragraph(orderInfoParagraph, font);
                customerInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(customerInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm bảng chi tiết hóa đơn
                PdfPTable table = new PdfPTable(5); // 5 cột
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Thêm tiêu đề cột
                Font tableHeaderFont = new Font(baseFont, 12, Font.BOLD);
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Mã sản phẩm", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Tên sản phẩm", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Số lượng", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Đơn giá", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Thành tiền", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // Thêm các hàng dữ liệu
                Font tableDataFont = new Font(baseFont, 12, Font.NORMAL);

                for (Product product: selectedProducts) {
                    table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getId()), tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(product.getName(), tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getQuantity()) ,tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(Utils.formatVNCurrency(product.getPrice()),tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(Utils.formatVNCurrency(product.calculateTotalPrice()),tableDataFont)));

                }

                document.add(table);

                // Thêm tổng cộng
                Paragraph total = new Paragraph("Tổng cộng: " + Utils.formatVNCurrency(order.getTotalPayment()), tableHeaderFont);
                total.setAlignment(Element.ALIGN_RIGHT);
                document.add(total);

                System.out.println("Hóa đơn đã được lưu tại: " + filePath);

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
    }
    
    public static void createImportProductInvoice(String filePath, ImportProduct importProduct, ArrayList<Product> selectedProducts) {
        Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Sử dụng font Roboto hỗ trợ tiếng Việt
                BaseFont baseFont = BaseFont.createFont("Usage/Font/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12, Font.NORMAL);

                // Thêm tiêu đề
                Font titleFont = new Font(baseFont, 18, Font.BOLD);
                Paragraph title = new Paragraph("HÓA ĐƠN NHẬP HÀNG", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm thông tin cửa hàng
                Paragraph storeInfo = new Paragraph("Tên cửa hàng: Cửa hàng ABC\nĐịa chỉ: 123 Đường ABC, Quận 1, TP. HCM\nSố điện thoại: 0123456789", font);
                storeInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(storeInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống


                // Thêm thông tin nhập hàng
                String InfoParagraph = "Thông tin nhập hàng\nMã nhập hàng: " + importProduct.getId() + "\nThời gian tạo: " + importProduct.getCreatedAt() + "\nNhà cung cấp: " + importProduct
                        .getSupplierName();
               
                Paragraph customerInfo = new Paragraph(InfoParagraph, font);
                customerInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(customerInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm bảng chi tiết sản phẩm nhập
                PdfPTable table = new PdfPTable(5); // 5 cột
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Thêm tiêu đề cột
                Font tableHeaderFont = new Font(baseFont, 12, Font.BOLD);
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Mã sản phẩm", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Tên sản phẩm", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Số lượng", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Đơn giá", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Thành tiền", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // Thêm các hàng dữ liệu
                Font tableDataFont = new Font(baseFont, 12, Font.NORMAL);

                for (Product product: selectedProducts) {
                    table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getId()), tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(product.getName(), tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getQuantity()) ,tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(Utils.formatVNCurrency(product.getPrice()),tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(Utils.formatVNCurrency(product.calculateTotalPrice()),tableDataFont)));
                }

                document.add(table);

                // Thêm tổng cộng
                Paragraph total = new Paragraph("Tổng cộng: " + Utils.formatVNCurrency(importProduct.getTotalPayment()), tableHeaderFont);
                total.setAlignment(Element.ALIGN_RIGHT);
                document.add(total);

                System.out.println("Hóa đơn đã được lưu tại: " + filePath);

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
    }

    public static void createProductRevenue(String filePath, ArrayList<ProductRevenue> productRevenues, String fromDate, String toDate) {
        Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Sử dụng font Roboto hỗ trợ tiếng Việt
                BaseFont baseFont = BaseFont.createFont("Usage/Font/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12, Font.NORMAL);

                // Thêm tiêu đề
                Font titleFont = new Font(baseFont, 18, Font.BOLD);
                Paragraph title = new Paragraph("DOANH THU SẢN PHẨM", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm thông tin cửa hàng
                Paragraph storeInfo = new Paragraph("Tên cửa hàng: Cửa hàng ABC\nĐịa chỉ: 123 Đường ABC, Quận 1, TP. HCM\nSố điện thoại: 0123456789", font);
                storeInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(storeInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm thông tin 
                String info = "Thông tin doanh thu\nTừ ngày: " + fromDate + "\nĐến ngày: " + toDate;
               
                Paragraph customerInfo = new Paragraph(info, font);
                customerInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(customerInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm bảng chi tiết hóa đơn
                PdfPTable table = new PdfPTable(4); // 5 cột
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Thêm tiêu đề cột
                Font tableHeaderFont = new Font(baseFont, 12, Font.BOLD);
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Mã sản phẩm", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Tên sản phẩm", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Số lượng bán", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Thành tiền", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                

                // Thêm các hàng dữ liệu
                Font tableDataFont = new Font(baseFont, 12, Font.NORMAL);

                double totalMoney = 0;
                for (ProductRevenue product: productRevenues) {
                    table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getId()), tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(product.getName(), tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getQuantitySold()) ,tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(Utils.formatVNCurrency(product.getTotalAmount()),tableDataFont)));
                    
                     totalMoney += product.getTotalAmount();
                }

                document.add(table);

                // Thêm tổng cộng
                Paragraph total = new Paragraph("Tổng doanh thu: " + Utils.formatVNCurrency(totalMoney), tableHeaderFont);
                total.setAlignment(Element.ALIGN_RIGHT);
                document.add(total);

                System.out.println("Hóa đơn đã được lưu tại: " + filePath);

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
    }
    
    public static void createOrderRevenue(String filePath, ArrayList<Order> orders, String fromDate, String toDate) {
        Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Sử dụng font Roboto hỗ trợ tiếng Việt
                BaseFont baseFont = BaseFont.createFont("Usage/Font/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12, Font.NORMAL);

                // Thêm tiêu đề
                Font titleFont = new Font(baseFont, 18, Font.BOLD);
                Paragraph title = new Paragraph("DOANH THU ĐƠN HÀNG", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm thông tin cửa hàng
                Paragraph storeInfo = new Paragraph("Tên cửa hàng: Cửa hàng ABC\nĐịa chỉ: 123 Đường ABC, Quận 1, TP. HCM\nSố điện thoại: 0123456789", font);
                storeInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(storeInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm thông tin 
                String info = "Thông tin doanh thu\nTừ ngày: " + fromDate + "\nĐến ngày: " + toDate;
               
                Paragraph customerInfo = new Paragraph(info, font);
                customerInfo.setAlignment(Element.ALIGN_LEFT);
                document.add(customerInfo);

                document.add(new Paragraph(" ")); // Thêm khoảng trống

                // Thêm bảng chi tiết hóa đơn
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Thêm tiêu đề cột
                Font tableHeaderFont = new Font(baseFont, 12, Font.BOLD);
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Thời gian", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Doanh thu", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                

                // Thêm các hàng dữ liệu
                Font tableDataFont = new Font(baseFont, 12, Font.NORMAL);

                double totalMoney = 0;
                for (Order order: orders) {
                    table.addCell(new PdfPCell(new Phrase(order.getOrderDate(), tableDataFont)));
                    table.addCell(new PdfPCell(new Phrase(Utils.formatVNCurrency(order.getTotalPayment()),tableDataFont)));
                    
                     totalMoney += order.getTotalPayment();
                }

                document.add(table);

                // Thêm tổng cộng
                Paragraph total = new Paragraph("Tổng doanh thu: " + Utils.formatVNCurrency(totalMoney), tableHeaderFont);
                total.setAlignment(Element.ALIGN_RIGHT);
                document.add(total);

                System.out.println("Hóa đơn đã được lưu tại: " + filePath);

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
    }
}
