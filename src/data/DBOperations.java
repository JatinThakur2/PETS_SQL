package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperations {

	public void createInternalMarksTable(String subject) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade", "root", "qwertyuiop1234[]");
			Statement st = con.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS `grade`.`" + subject + "(" + "  `roll_no` int NOT NULL,"
					+ "  `agg_sh` float DEFAULT NULL," + "  `agg_enb` float DEFAULT NULL,"
					+ "  `overall` float DEFAULT NULL," + "  PRIMARY KEY (`roll_no`),"
					+ "  CONSTRAINT `roll` FOREIGN KEY (`roll_no`) REFERENCES `student` (`roll_no`) ON DELETE CASCADE) ";

			st.execute(sql);

			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void excelToDB(String file) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade", "root", "qwertyuiop1234[]");
			Statement st = con.createStatement();

			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
