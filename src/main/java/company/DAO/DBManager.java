package company.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
	
	//private static final Logger log = Logger.getLogger(DBManager.class);
	

	// //////////////////////////////////////////////////////////
	// singleton pattern
	// //////////////////////////////////////////////////////////

	private static DBManager instance;
	private static final String url="jdbc:mysql://localhost:3306/epam?user=root&password=1234567h";

	public static synchronized DBManager getInstance() {
		if (instance == null)
			instance = new DBManager();
		return instance;
	}

	/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in your
	 * WEB_APP_ROOT/META-INF/context.xml file.
	 *
	 * @return A DB connection.
	 */
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/epam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true","root","1234567h");
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		return con;
	}

	private DBManager() {

	}


	// //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Commits and close the given connection.
	 * 
	 * @param con
	 *            Connection to be committed and closed.
	 */
	public void commitAndClose(Connection con) {
		try {
			con.commit();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Rollbacks and close the given connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked and closed.
	 */
	public void rollbackAndClose(Connection con) {
		try {
			con.rollback();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
/**************** THIS METHOD IS NOT USED IN THE PROJECT *******/
	/**
	 * Returns a DB connection. This method is just for a example how to use the
	 * DriverManager to obtain a DB connection. It does not use a pool
	 * connections and not used in this project. It is preferable to use
	 *  method instead.
	 * 
	 * @return A DB connection.
	 */
	/*public Connection getConnectionWithDriverManager() throws SQLException {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = DriverManager
				.getConnection("jdbc:derby://localhost:3306/epam;create=true;user=root;password=1234567h");
		connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		connection.setAutoCommit(false);
		return connection;
	}*/
/**************************************************************/

}