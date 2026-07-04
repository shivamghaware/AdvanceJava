package com.voting.tester;

import java.sql.*;
import static com.voting.utils.DBUtils.getConnection;

public class TestStatement {

	public static void main(String[] args) {
		try (// cn
				Connection cn = getConnection();
				// st
				Statement st = cn.createStatement();
				// rst
				ResultSet rst = st.executeQuery("select * from users order by dob")
				) {
			// rst curosor - before the 1st row
			while (rst.next()) {
				System.out.printf("User Id %d Name %s %s DoB %s %n",
						rst.getLong(1),rst.getString(2),rst.getString(3),
						rst.getDate(6));
			}
		} // rst.close() , st.close(), cn.close()
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
