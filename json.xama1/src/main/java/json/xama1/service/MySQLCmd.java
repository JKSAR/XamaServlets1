package json.xama1.service;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.text.SimpleDateFormat;

public class MySQLCmd {
	public static java.sql.Connection mySQLCnx = null;
	public static MySQLCnx _mySQLCnx = null;

	public static Connection mySqlCnx2;

//  = MAIN =====================================================================================	
	public static void main(String args[]) {
		// - N�O REMOVER
		// -------------------------------------------------------------------------
		try {
			mySQLCnx = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// -------------------------------------------------------------------------------------

		teste();

		// String rfid = "ff:ff:bf:17:95:00";

		// boolean teste = false;
		// teste = hasAluno(rfid);
		// teste = insertAlunoRfid(rfid);

		// - C�DIGO TESTE
		// ----------------------------------------------------------------------
		/*
		 * Aluno aluno = new Aluno(); //int i = selectCountPSC("fc:58:fa:29:80:d5");
		 * 
		 * 
		 * java.sql.Timestamp sqlTimestmp = Util.getCurrentTimeStamp(); aluno =
		 * getAluno("fc:58:fa:29:80:d5");
		 */
		/*
		 * Timestamp timestamp = null;
		 * 
		 * try { mySQLCnx = MySQLCnx.getConexaoMySQL(); } catch (ClassNotFoundException
		 * e) { e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * timestamp = new Timestamp(System.currentTimeMillis());
		 * 
		 * Aluno aluno = new Aluno(); aluno.setIDALU(1);
		 * aluno.setUltCom(timestamp.getTime());
		 * 
		 * boolean ret = insAlunoPSC(aluno);
		 */

		// ResultSet rs = null;

		// rs = getAluno("15571549", );

		/*
		 * java.util.Date date = new java.util.Date();
		 * 
		 * String RFID = new String("3912017617");
		 * 
		 * if (mySQLCnx == null) { mySQLCnx = mySQLCnx.getConexaoMySQL(); }
		 * 
		 * try { java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		 * java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
		 * 
		 * PreparedStatement ps = mySQLCnx
		 * .prepareStatement("INSERT INTO `swimy`.`rfidproc` (`RFID`, `DATA`, `HHMMSS` ) VALUES(?, ?, ?);"
		 * );
		 * 
		 * ps.setString(1, RFID); ps.setDate(2, sqlDate); ps.setTimestamp(3, sqlTime);
		 * ps.executeUpdate(); ps.close();
		 * 
		 * mySQLCnx.close(); } catch (Exception e) { e.printStackTrace(); }
		 */
	}

/// ==============================================================================================	

	public MySQLCmd() {
		try {
			mySQLCnx = MySQLCnx.getConexaoMySQL();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * private static java.sql.Timestamp getCurrentTimeStamp() { java.util.Date
	 * today = new java.util.Date(); return new java.sql.Timestamp(today.getTime());
	 * }
	 */

	@SuppressWarnings("deprecation")
	private Timestamp getSQLTimsetamp(java.sql.Date dtini, Time hrini) {

		Date date = new Date();
		date.setHours(hrini.getHours());

		long milliseconds = dtini.getTime();

		date.setTime(milliseconds);

		Timestamp ts = new Timestamp(date.getTime());

		return ts;
	}

	/*
	 * public void RFIDPRC(int RFID) { mySQLCnx = mySQLCnx.getConexaoMySQL();
	 * 
	 * try { /* SELECT `rfidproc`.`HHMMSS`, MAX(`rfidproc`.`HHMMSS`) AS 'Max Value'
	 * FROM `swimy`.`rfidproc` WHERE `rfidproc`.`RFID` = '15571549' AND
	 * `rfidproc`.`DATA` = '2020-06-05';
	 * 
	 * PreparedStatement ps = mySQLCnx
	 * .prepareStatement("INSERT INTO `swimy`.`rfidproc` (`RFID`, `DATA`, `HHMMSS` ) VALUES(?, ?, ?);"
	 * );
	 * 
	 * ps.setString(1, RFID); ps.setDate(2, sqlDate); ps.setTimestamp(3, sqlTime);
	 * ps.executeUpdate(); ps.close();
	 * 
	 * mySQLCnx.close(); } catch (Exception e) { e.printStackTrace(); return false;
	 * } }
	 */

//  Seleciona ALUNO ---------------------------------------------------------------------------	
	public static boolean hasAluno(String rFid) {
		// Aluno aLuno = new Aluno();

		int numberOfRows = -1;

		PreparedStatement pstms = null;
		ResultSet rs = null;

		if (mySQLCnx == null) {
			try {
				mySQLCnx = MySQLCnx.getConexaoMySQL();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String sql = " SELECT COUNT( * )" + " FROM `swimy`.`aluno` " + " INNER JOIN `swimy`.`alurfid`"
				+ " ON `swimy`.`alurfid`.`idalu` = `swimy`.`aluno`.`idalu`" + " WHERE (`swimy`.`alurfid`.`rfid` = ? );";

		try {
			pstms = mySQLCnx.prepareStatement(sql);
			pstms.setString(1, rFid);

			rs = pstms.executeQuery();
			if (rs.next()) {
				numberOfRows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numberOfRows > 0) {
			return true;
		} else {
			return false;
		}
	}

	// Seleciona COUNT ALUNOPSC
	// ---------------------------------------------------------------------
	public static int selectCountPSC(String rFid) {
		// boolean res = false;
		int numberOfRows = -1;

		PreparedStatement pstms = null;
		ResultSet rs = null;

		java.sql.Timestamp sqlTimestmp = Util.getCurrentTimeStamp();

		String sql = " SELECT COUNT( * )" + " FROM `swimy`.`alunopsc`" + " INNER JOIN `swimy`.`alurfid`"
				+ " ON `swimy`.`alurfid`.`idalu` = `swimy`.`alunopsc`.`idalu`"
				+ " WHERE ( `swimy`.`alurfid`.`rfid` = ? );";

		try {
			pstms = mySQLCnx.prepareStatement(sql);
			pstms.setString(1, rFid);

			rs = pstms.executeQuery();
			if (rs.next()) {
				numberOfRows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfRows;
	}

	public static boolean checkRFIDOk(String rFid) {
		boolean res = false;
		int numberOfRows = -1;

		PreparedStatement pstms = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT( * ) FROM `alurfid` WHERE (`RFID` = ? AND `INAT` = '000');";

		try {
			mySQLCnx = Util.getConnection();
			pstms = mySQLCnx.prepareStatement(sql);
			pstms.setString(1, rFid);

			rs = pstms.executeQuery();
			if (rs.next()) {
				numberOfRows = rs.getInt(1);
				if (numberOfRows <= 0) {
					res = false;
				} else {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// JCS
	public boolean insertAlunoRfid(String macAddress, String rfid_rssi_tmstp) {
		int rssi = 0;

		String rfid = "";
		String milisec_aux = "";
		String rfid_aux = "";
		// String rssi_aux = "";

		PreparedStatement pstms = null;
		ResultSet rs = null;

		java.sql.Timestamp sqlTimestmp = null;

		if (macAddress != null || rfid_rssi_tmstp != null) {
			// FIXME: REMOVER CODIGO APOS TESTES ------------------------------
			if (mySQLCnx == null) {
				try {
					mySQLCnx = Util.getConnection();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// ----------------------------------------------------------------

			sqlTimestmp = Util.getCurrentTimeStamp();

			String[] elements = rfid_rssi_tmstp.split("!");

			for (int i = 0; i < elements.length; i++) {
				String[] rfidRssi = elements[i].split(";");
				rfid_aux = rfidRssi[0];
				milisec_aux = rfidRssi[1].substring(0, 1);

				String[] rfid_Rssi = rfid_aux.split("-");
				rfid = rfid_Rssi[0];
				rssi = Integer.valueOf(rfid_Rssi[1]);

				int l_count = 0;

				String macesp_rfid_arr[] = new String[99];
				String l_macesp = new String();
				String l_rfid = new String();

				String l_timestamp_max = null;
				String l_timestamp_min = String.valueOf(sqlTimestmp);

				l_timestamp_min = l_timestamp_min.substring(0, 10) + " 00:00:00";
				l_timestamp_max = l_timestamp_min.substring(0, 10) + " 23:59:59";

				l_timestamp_min = "2021-04-17 00:00:00";
				l_timestamp_max = "2021-04-17 23:59:59";

				String sql = " SELECT DISTINCT MACESP, RFID FROM swimy.rfidproc" + " WHERE ( rfidproc.DTHRREG >= '"
						+ l_timestamp_min + "' )" + " AND ( rfidproc.DTHRREG <= '" + l_timestamp_max + "' )"
						+ " GROUP BY MACESP, RFID;";

				try {
					mySQLCnx = Util.getConnection();
					pstms = mySQLCnx.prepareStatement(sql);

					rs = pstms.executeQuery();

					while (rs.next()) {
						System.out.println(rs.getString(1));
						System.out.println(rs.getString(2));
						String l_tst = rs.getString(1) + rs.getString(2) + l_timestamp_min + l_timestamp_max;
						macesp_rfid_arr[rs.getRow() - 1] = rs.getString(1) + rs.getString(2) + l_timestamp_min
								+ l_timestamp_max;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				while (l_macesp != null) {
					l_macesp = macesp_rfid_arr[l_count];
					if (l_macesp != null) {
						l_macesp = macesp_rfid_arr[l_count].substring(0, 17);
						l_rfid = macesp_rfid_arr[l_count].substring(17, 34);

						// FIXME: CONTINUE ----------------------------------------------------------
						// Check if the record already exists on DB with ACTIVE = TRUE
						if (hasRecord(l_macesp, l_rfid, true) == true) {
							// If it's OUT device, field ACTIVE = FALSE
							if (l_macesp == "7C:9E:BD:47:6D:E0") {
								if (updateRecord(l_macesp, l_rfid, false) == true) {

								}
							}
						} else {
							// Insert record with ACTIVE = TRUE
							// insRfidDetect(l_macesp, l_rfid, l_firstdet, 60, l_lastdet, 60,
							// l_timestamp_max, l_timestamp_min);

							int rssiFR = 0;
							int rssiLR = 0;
							int rssiHD = 0;

							Timestamp l_timestamp_fr = null;
							Timestamp l_timestamp_lr = null;
							Timestamp l_timestamp_hd = null;

							if (insRfidDetect(l_macesp, l_rfid, l_timestamp_fr, rssiFR, l_timestamp_lr, rssiLR,
									l_timestamp_hd, rssiHD) == true) {

							}
						}
					}
				}

//				try {
//					pstms = mySQLCnx
//							.prepareStatement(" INSERT INTO swimy.rfidproc (MACESP, RFID, DTHRREG, RSSI, MILISEC)"
//									+ " VALUES (?, ?, ?, ?, ?);");
//
//					// sqlTimestmp = Util.getCurrentTimeStamp();
//					sqlTimestmp = Util.subCurrentTimeStamp(sqlTimestmp, milisec_aux);
//
//					pstms.setString(1, macAddress);
//					pstms.setString(2, rfid);
//					pstms.setTimestamp(3, sqlTimestmp);
//					pstms.setInt(4, rssi);
//					pstms.setInt(5, Integer.valueOf(milisec_aux));
//
//					// pstms.addBatch();
//
//					pstms.executeUpdate();
//					pstms.close();
//
//				} catch (Exception e) {
//					e.printStackTrace();
//					return false;
//				}
			}
		}

		return true;
	}

	public static boolean hasRecord(String macESP, String rFid, boolean active) {
		int numberOfRows = -1;

		PreparedStatement pstms = null;
		ResultSet rs = null;

		if (mySQLCnx == null) {
			try {
				mySQLCnx = MySQLCnx.getConexaoMySQL();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String sql = "SELECT COUNT( * )" + " FROM balladd.rfidproc " + " WHERE ( rfidproc.MACESP = '" + macESP + "' )"
				+ " AND ( rfidproc.RFID = '" + rFid + "' )" + " AND (rfidproc.ACTIVE = '" + active + "' );";

		try {
			pstms = mySQLCnx.prepareStatement(sql);
			pstms.setString(1, rFid);

			rs = pstms.executeQuery();
			if (rs.next()) {
				numberOfRows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numberOfRows > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean updateRecord(String macESP, String rFid, boolean active) {
		PreparedStatement pstms = null;

		try {
			pstms = mySQLCnx.prepareStatement(" UPDATE `balladd.rfidproc SET ACTIVE = " + active
					+ " WHERE ( rfidproc.MACESP = '" + macESP + "' )" + " AND ( rfidproc.RFID = '" + rFid + "' );");

			pstms.executeQuery();
			pstms.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void teste() {
		java.sql.Timestamp sqlTimestmp = Util.getCurrentTimeStamp();

		int l_count = 0;

		String macesp_rfid_arr[] = new String[99];
		String l_macesp = new String();
		String l_rfid = new String();

		String l_timestamp_max = null;
		String l_timestamp_min = String.valueOf(sqlTimestmp);

		l_timestamp_min = l_timestamp_min.substring(0, 10) + " 00:00:00";
		l_timestamp_max = l_timestamp_min.substring(0, 10) + " 23:59:59";

		l_timestamp_min = "2021-04-17 00:00:00";
		l_timestamp_max = "2021-04-17 23:59:59";

		PreparedStatement pstms = null;
		ResultSet rs = null;

		String sql = " SELECT DISTINCT MACESP, RFID FROM swimy.rfidproc" + " WHERE ( rfidproc.DTHRREG >= '"
				+ l_timestamp_min + "' )" + " AND ( rfidproc.DTHRREG <= '" + l_timestamp_max + "' )"
				+ " GROUP BY MACESP, RFID;";

		try {
			mySQLCnx = Util.getConnection();
			pstms = mySQLCnx.prepareStatement(sql);

			rs = pstms.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				String l_tst = rs.getString(1) + rs.getString(2) + l_timestamp_min + l_timestamp_max;
				macesp_rfid_arr[rs.getRow() - 1] = rs.getString(1) + rs.getString(2) + l_timestamp_min
						+ l_timestamp_max;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (l_macesp != null) {
			l_macesp = macesp_rfid_arr[l_count];
			if (l_macesp != null) {
				l_macesp = macesp_rfid_arr[l_count].substring(0, 17);
				l_rfid = macesp_rfid_arr[l_count].substring(17, 34);

				// FIXME: CONTINUE ----------------------------------------------------------
				// Check if the record already exists on DB with ACTIVE = TRUE
				if (hasRecord(l_macesp, l_rfid, true) == true) {
					// If it's OUT device, field ACTIVE = FALSE
					if (l_macesp == "7C:9E:BD:47:6D:E0") {
						if (updateRecord(l_macesp, l_rfid, false) == true) {

						}
					}
				} else {
					// Insert record with ACTIVE = TRUE
					// insRfidDetect(l_macesp, l_rfid, l_firstdet, 60, l_lastdet, 60,
					// l_timestamp_max, l_timestamp_min);

					int rssiFR = 0;
					int rssiLR = 0;
					int rssiHD = 0;

					Timestamp l_timestamp_fr = null;
					Timestamp l_timestamp_lr = null;
					Timestamp l_timestamp_hd = null;

					if (insRfidDetect(l_macesp, l_rfid, l_timestamp_fr, rssiFR, l_timestamp_lr, rssiLR, l_timestamp_hd,
							rssiHD) == true) {

					}
				}
			}
		}
	}
	
	public static boolean insFluxo(int idCliente, int mesa, int atend, HttpServletResponse response) 
	{
		PreparedStatement pstms = null;

		try {
			if (mySQLCnx == null) {
				mySQLCnx = Util.getConnection();
			}
			
			if(Util.getLocalExec() == true)
			{
				pstms = mySQLCnx.prepareStatement("INSERT INTO callme.fluxo (idCliente, mesa, DtHr, atend) "
                        						+ "VALUES(?, ?, ?, ?);");
			} else {
				pstms = mySQLCnx.prepareStatement("INSERT INTO julio2021_CallMe.fluxo (idCliente, mesa, DtHr, atend) "
												+ "VALUES(?, ?, ?, ?);");
			}

			pstms.setInt(1, idCliente);
			pstms.setInt(2, mesa);			
			pstms.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
			pstms.setInt(4, atend);

			pstms.executeUpdate();
			pstms.close();

		} catch (Exception e) {
			//e.printStackTrace();
			String error = e.getMessage();
			try {
				response.getWriter().append(error);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}	

	private static boolean insRfidDetect(String MACESP, String RFID, java.sql.Timestamp FIRSTDET, int FIRSTRSSI,
			java.sql.Timestamp LASTDET, int LASTRSSI, java.sql.Timestamp HIGHDET, int HIGHRSSI) {
		PreparedStatement pstms = null;

		try {
			if (mySQLCnx == null) {
				mySQLCnx = Util.getConnection();
			}

			pstms = mySQLCnx.prepareStatement("INSERT INTO balladd.rfidetect "
					+ "(MACESP, RFID, FIRSTDET, FIRSTRSSI, LASTDET, LASTRSSI, HIGHDET, HIGHRSSI) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);");

			pstms.setString(1, MACESP);
			pstms.setString(2, RFID);
			pstms.setTimestamp(3, FIRSTDET);
			pstms.setInt(4, FIRSTRSSI);
			pstms.setTimestamp(5, LASTDET);
			pstms.setInt(6, LASTRSSI);
			pstms.setTimestamp(7, HIGHDET);
			pstms.setInt(8, HIGHRSSI);

			pstms.executeUpdate();
			pstms.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static boolean lapCount(String sqlTimestmpFR, String sqlTimestmpLR) {
		Timestamp l_timestamp_fr = null;
		Timestamp l_timestamp_lr = null;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(sqlTimestmpFR);
			l_timestamp_fr = new java.sql.Timestamp(parsedDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(sqlTimestmpLR);
			l_timestamp_lr = new java.sql.Timestamp(parsedDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO: DIFF (maior tmstp - menor tmstp) deve ser uma vari�vel global
		long diff = l_timestamp_lr.getTime() - l_timestamp_fr.getTime();

		if (diff >= 30000) {
			return true;
		} else {
			return false;
		}
	}

	public static String firstRead(String macesp, String rfid, String dthrMin, String dthrMax) {
		String sqlTimestmp = null; // Util.getCurrentTimeStamp();

		PreparedStatement pstms = null;
		ResultSet rs = null;

		// String sqlTimestmpTst = null;

		String sql = "SELECT min( DTHRREG )" + " FROM swimy.rfidproc " + " WHERE ( rfidproc.MACESP = '" + macesp + "' )"
				+ " AND ( rfidproc.RFID = '" + rfid + "' )" + " AND ( rfidproc.DTHRREG >= '" + dthrMin + "' )"
				+ " AND ( rfidproc.DTHRREG <= '" + dthrMax + "' );";

		try {
			if (mySQLCnx == null) {
				mySQLCnx = Util.getConnection();
			}

			pstms = mySQLCnx.prepareStatement(sql);

			rs = pstms.executeQuery();

			while (rs.next()) {
				// System.out.println(rs.getString(1));
				// sqlTimestmp = rs.getString(1);
				sqlTimestmp = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sqlTimestmp;
	}

	public static String lastRead(String macesp, String rfid, String dthrMin, String dthrMax) {
		String sqlTimestmp = null; // Util.getCurrentTimeStamp();

		PreparedStatement pstms = null;
		ResultSet rs = null;

		// String sqlTimestmpTst = null;

		// TODO: RSSI minimo deve ser uma vari�vel global
		int min_rssi = 60;

		String sql = "SELECT max( DTHRREG )" + " FROM swimy.rfidproc " + " WHERE ( rfidproc.MACESP = '" + macesp + "' )"
				+ " AND ( rfidproc.RFID = '" + rfid + "' )" + " AND ( rfidproc.DTHRREG >= '" + dthrMin + "' )"
				+ " AND ( rfidproc.DTHRREG <= '" + dthrMax + "' )" + " AND ( rfidproc.RSSI >= '" + min_rssi + "' )";

		try {
			if (mySQLCnx == null) {
				mySQLCnx = Util.getConnection();
			}

			pstms = mySQLCnx.prepareStatement(sql);

			rs = pstms.executeQuery();

			while (rs.next()) {
				// System.out.println(rs.getString(1));
				sqlTimestmp = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sqlTimestmp;
	}

}