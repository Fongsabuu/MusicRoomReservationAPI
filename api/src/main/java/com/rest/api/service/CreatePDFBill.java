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
				Paragraph bill_detail = new Paragraph((int) row.get("id") + " วันที่ :  " + (String) row.get("date"),
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
				Paragraph tap = new Paragraph("", font);
				document.add(tap);

				PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1, 1, 1 });
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell("No.");
				table.addCell("Room");
				table.addCell("Date");
				table.addCell("Time");
				table.addCell("Hours.");
				table.addCell("Price");
				table.setHeaderRows(1);
				PdfPCell[] cells = table.getRow(0).getCells();
				for (int j = 0; j < cells.length; j++) {
					cells[j].setBackgroundColor(BaseColor.GRAY);
				}
				
				table.addCell("1");
				table.addCell((String) row.get("room_name"));
				table.addCell((String) row.get("date"));
				table.addCell((String) row.get("time"));
				table.addCell((String) row.get("hours"));
				table.addCell((String) row.get("totalprice"));
				document.add(table);

				Paragraph pay_total = new Paragraph("ยอดชำระ : "+(String) row.get("totalprice"), font);
				pay_total.setAlignment(Element.ALIGN_RIGHT);
				document.add(pay_total);
				Paragraph pay_rew = new Paragraph("ชำระเเล้ว : "+(String) row.get("totalprice"), font);
				pay_rew.setAlignment(Element.ALIGN_RIGHT);
				document.add(pay_rew);
				Paragraph pay_cal = new Paragraph("คงเหลือ : 0", font);
				pay_cal.setAlignment(Element.ALIGN_RIGHT);
				document.add(pay_cal);
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
}
