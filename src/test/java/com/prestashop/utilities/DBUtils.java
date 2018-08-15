package com.prestashop.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	public static void createConnection() {
		String url = ConfigurationReader.getProperty("qa1_db_host");
		String user = ConfigurationReader.getProperty("qa1_db_username");
		String password = ConfigurationReader.getProperty("qa1_db_password");

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void destroy() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement!=null) {
				statement.close();
			}
			if (connection!=null) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Map<String, Object>> getQueryResult(String query) {

		List<Map<String, Object>> rowList = new ArrayList<>();

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSetMetaData rsmd;
		try {
			rsmd = resultSet.getMetaData();

			while (resultSet.next()) {

				Map<String, Object> colNameValueMap = new HashMap<>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {

					colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
				}

				rowList.add(colNameValueMap);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowList;

	}

	public static int getRowCount() throws Exception {

		resultSet.last();

		int rowCount = resultSet.getRow();
		return rowCount;

	}

}