package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigDB {

	public ConfigDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "qwertyuiop1234[]");
			Statement st = con.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS grade";
			st.execute(sql);

			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void config() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grade", "root", "qwertyuiop1234[]");
			Statement st = con.createStatement();

			String sql1 = "CREATE TABLE IF NOT EXISTS `grade`.`gradebook` (" + "  `sub_code` VARCHAR(100) NOT NULL,"
					+ "  `sub_name` VARCHAR(100) NULL," + "  `total_sh` INT NULL," + "  `total_enb` INT NULL,"
					+ "  `weight_sh` INT NOT NULL," + "  `weight_enb` INT NOT NULL,"
					+ "  `weight_project` INT NOT NULL," + "  `weight_final` INT NOT NULL,"
					+ "  `total` INT NOT NULL DEFAULT 100," + "  PRIMARY KEY (`sub_code`))";
			st.execute(sql1);

			String sql2 = "CREATE TABLE IF NOT EXISTS `grade`.`student` (" + "  `roll_no` INT NOT NULL,"
					+ "  `name` VARCHAR(150) NULL," + "  `father_name` VARCHAR(150) NULL,"
					+ "  PRIMARY KEY (`roll_no`))";

			st.execute(sql2);

			String sql3 = "CREATE TABLE IF NOT EXISTS `grade`.`subject` (" + "  `sub_code` VARCHAR(50) NOT NULL,"
					+ "  `sub_name` VARCHAR(100) NULL," + "  `max_marks` INT NOT NULL," + "  PRIMARY KEY (`sub_code`))";

			st.execute(sql3);

			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
