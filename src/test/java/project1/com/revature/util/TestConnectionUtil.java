package project1.com.revature.util;

import java.sql.Connection;

import org.junit.Test;

import jdbc.project1.Util.ConnectionUtil;
import junit.framework.TestCase;

public class TestConnectionUtil extends TestCase {
	@Test
	public void testConnection() {
		Connection connection = ConnectionUtil.getConnection();
		assertNotNull(connection);
	}

}
