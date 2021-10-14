package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.ConfigDB;
import data.DBOperations;

public class BusinessLogic {

	BusinessLogic() {
		ConfigDB config = new ConfigDB();
		config.config();
	}

	public void importMarksheet(String url) {
		String[] token = url.split("\\");
		String tableName = token[token.length - 1];

		int sumSH = 0;
		int sumENB = 0;

		// Create a table for the internal marks
		DBOperations dbOp = new DBOperations();
		dbOp.createInternalMarksTable(tableName);

		// Read the data from excel and put it in the table created
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(url);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade", "root", "qwertyuiop1234[]");

			Statement st = con.createStatement();
			st.execute("TRUNCATE TABLE " + tableName);

			String sql = "INSERT INTO " + url + " (roll_no, agg_sh, agg_enb, overall) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);

			// Getting the indices of sh and enb marks
			Row header = rowIterator.next();
			ArrayList<Integer> shNdx = new ArrayList<Integer>();
			ArrayList<Integer> enbNdx = new ArrayList<Integer>();

			Iterator<Cell> cellIt = header.cellIterator();
			while (cellIt.hasNext()) {
				Cell nextCell = cellIt.next();

				if (nextCell.getStringCellValue().contains("SH"))
					shNdx.add(nextCell.getColumnIndex());

				if (nextCell.getStringCellValue().contains("ENB"))
					enbNdx.add(nextCell.getColumnIndex());
			}

			// Setting the data in the database
			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();

				int roll_no = Integer.valueOf(nextRow.getCell(0).toString());

				for (int i : shNdx) {
					sumSH += Integer.valueOf(nextRow.getCell(i).toString());
				}

				for (int i : enbNdx) {
					sumENB += Integer.valueOf(nextRow.getCell(i).toString());
				}

				float shAgg = sumSH / shNdx.size();
				float enbAgg = sumENB / enbNdx.size();
				float overall = shAgg + enbAgg;

				statement.setInt(0, roll_no);
				statement.setFloat(1, shAgg);
				statement.setFloat(2, enbAgg);
				statement.setFloat(3, overall);
			}

			statement.close();
			st.close();
			con.close();
			workbook.close();

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
