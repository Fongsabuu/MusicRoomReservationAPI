package com.rest.api.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rest.api.model.Reservation;

@Service
public class CreatePDFBill {

	public int generatePDF(List<Map<String, Object>> list) {
		Document document = new Document();
		try {
			Font font = new Font(BaseFont.createFont("THSARABUNNEW.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
			for (Map<String, Object> row : list) {
				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream("src/main/resources/static/bill/bill"+(int) row.get("id")+".pdf"));
				document.open();
				
				Paragraph title = new Paragraph("ใบเสร็จรับเงิน", font);
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);
				Paragraph sub_title = new Paragraph("88BoyMusic", font);
				sub_title.setAlignment(Element.ALIGN_CENTER);
				document.add(sub_title);
				Paragraph sub2_title = new Paragraph("51/75 ซ.คลองหลวง 23 คลองหนึ่ง คลองหลวง ปทุมธานี 12120", font);
				sub2_title.setAlignment(Element.ALIGN_CENTER);
				document.add(sub2_title);
				Paragraph bill_title = new Paragraph("เลขที่ใบเสร็จ", font);
				document.add(bill_title);
				Paragraph bill_detail = new Paragraph(addBillid((int) row.get("id")) + " วันที่ :  " + (String) row.get("date"),
						font);
				document.add(bill_detail);
				Paragraph user = new Paragraph("ข้อมูลผู้จอง", font);
				document.add(user);
				Paragraph user_name = new Paragraph("ชื่อ : " + (String) row.get("firstname"), font);
				document.add(user_name);
				Paragraph user_email = new Paragraph("email : " + (String) row.get("email"), font);
				document.add(user_email);
				Paragraph user_tel = new Paragraph("เบอร์โทรศัพท์ : " + (String) row.get("tel"), font);
				document.add(user_tel);
				Paragraph user_address = new Paragraph("ที่อยู่ : " + (String) row.get("address"), font);
				document.add(user_address);
				Paragraph tap = new Paragraph(" ", font);
				document.add(tap);
				document.add(tap);

				PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1, 1, 1 });
				PdfPCell no_room = new PdfPCell(new Paragraph("No.", font));
				table.addCell(no_room);
				PdfPCell head_room = new PdfPCell(new Paragraph("ห้อง", font));
				table.addCell(head_room);
				PdfPCell head_date = new PdfPCell(new Paragraph("วันที่", font));
				table.addCell(head_date);
				PdfPCell head_time = new PdfPCell(new Paragraph("เวลา", font));
				table.addCell(head_time);
				PdfPCell head_hours = new PdfPCell(new Paragraph("จำนวน ชม.", font));
				table.addCell(head_hours);
				PdfPCell head_price = new PdfPCell(new Paragraph("ราคา", font));
				table.addCell(head_price);
				table.setHeaderRows(1);
				PdfPCell[] cells = table.getRow(0).getCells();
				for (int j = 0; j < cells.length; j++) {
					cells[j].setBackgroundColor(BaseColor.GRAY);
				}
				
				PdfPCell cell_no = new PdfPCell(new Paragraph("1", font));
				table.addCell(cell_no);
				PdfPCell cell_room = new PdfPCell(new Paragraph((String) row.get("room_name"), font));
				table.addCell(cell_room);
				PdfPCell cell_date = new PdfPCell(new Paragraph((String) row.get("date"), font));
				table.addCell(cell_date);
				PdfPCell cell_time = new PdfPCell(new Paragraph((String) row.get("time"), font));
				table.addCell(cell_time);
				PdfPCell cell_hours = new PdfPCell(new Paragraph((String) row.get("hours"), font));
				table.addCell(cell_hours);
				PdfPCell cell_price = new PdfPCell(new Paragraph((String) row.get("totalprice"), font));
				table.addCell(cell_price);
				//table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setTotalWidth(PageSize.A4.getWidth());
			    table.setLockedWidth(true);
				document.add(table);

				Paragraph pay_total = new Paragraph("ยอดชำระ : "+(String) row.get("totalprice") + " บาท", font);
				pay_total.setAlignment(Element.ALIGN_RIGHT);
				document.add(pay_total);
				Paragraph pay_rew = new Paragraph("ชำระเเล้ว : "+(String) row.get("totalprice")+ " บาท", font);
				pay_rew.setAlignment(Element.ALIGN_RIGHT);
				document.add(pay_rew);
				Paragraph pay_cal = new Paragraph("คงเหลือ : 0 บาท", font);
				pay_cal.setAlignment(Element.ALIGN_RIGHT);
				document.add(pay_cal);
				
				document.add(tap);
				document.add(tap);
				Paragraph sign_cash = new Paragraph("ลงชื่อ ........................................ ผู้ชำระเงิน", font);
				sign_cash.setAlignment(Element.ALIGN_CENTER);
				document.add(sign_cash);
				Paragraph con_cash = new Paragraph("( ........................................ )", font);
				con_cash.setAlignment(Element.ALIGN_CENTER);
				document.add(con_cash);
				Paragraph date_cash = new Paragraph("วันที่ (....../....../......)", font);
				date_cash.setAlignment(Element.ALIGN_CENTER);
				document.add(date_cash);
				
				document.add(tap);
				document.add(tap);
				Paragraph sign_cus = new Paragraph("ลงชื่อ ........................................ ผู้รับเงิน", font);
				sign_cus.setAlignment(Element.ALIGN_CENTER);
				document.add(sign_cus);
				Paragraph con_cus = new Paragraph("( ........................................ )", font);
				con_cus.setAlignment(Element.ALIGN_CENTER);
				document.add(con_cus);
				Paragraph date_cus = new Paragraph("วันที่ (....../....../......)", font);
				date_cus.setAlignment(Element.ALIGN_CENTER);
				document.add(date_cus);
				
				document.close();
				writer.close();
			}
			return 1;
		} catch (DocumentException e) {
			e.printStackTrace();
			return 0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public String addBillid(int id) {
		String bill_id = "0";
		String bill_code = "b";
		if(id < 10) {
			bill_id = "00000"+id;
		}else if(id < 100) {
			bill_id = "0000"+id;
		}else if(id < 1000) {
			bill_id = "000"+id;
		}else if(id < 10000) {
			bill_id = "00"+id;
		}else if(id < 100000) {
			bill_id = "0"+id;
		}
		return bill_code+bill_id;
	}
}
