package data;

import java.io.*;
import java.sql.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
	public static void main(String arg[]) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("C:/data.pdf"));
		document.open();

		PdfPTable table = new PdfPTable(4);
		table.addCell("RollNo");
		table.addCell("Name");
		table.addCell("Deptno");
		table.addCell("Location");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "qwertyuiop1234[]");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from student");
		while (rs.next()) {
			table.addCell(rs.getString("rollno"));
			table.addCell(rs.getString("name"));
			table.addCell(rs.getString("deptno"));
			table.addCell(rs.getString("address"));
		}
		document.add(table);
		document.close();
	}
}