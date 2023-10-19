package Source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * メモCRUD用クラス
 */
public class crudMemo {
	public String createMemo(HttpServletRequest request) throws Exception {
		Connection conn = null;
		PreparedStatement presmt = null;

		// 実行結果
		String exeResult = "";
		
		String SQL = "INSERT INTO memo_master VALUES(0, ?, ?, ?, NULL );";
		
		// 文字化け防止
		request.setCharacterEncoding("UTF-8");
		
		// SQL実行
		try {
			conn = DriverManager.getConnection(MemoRuConst.DB_URL, MemoRuConst.ROOT_USER, MemoRuConst.PASS);
			presmt = conn.prepareStatement(SQL);
			
			// レコード作成用データ取得
			String memo_contents = request.getParameter("memo_contents");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String create_datetime = timestamp.toString();
			String update_datetime = timestamp.toString();
			
			// SQLの?に割り当て
			presmt.setString(1, memo_contents);
			presmt.setString(2, create_datetime);
			presmt.setString(3, update_datetime);
			
			presmt.executeUpdate();
			
			exeResult = "Success";
		} catch (SQLException e) {
			e.printStackTrace();
			exeResult = "Fail";
		} catch (Exception e) {
			e.printStackTrace();
			exeResult = "Fail";
		}
		presmt.close();
		conn.close();
		
		return exeResult;
	}
	
	public List<memoruProperties> readMemo(HttpServletRequest request) {
		
		// 画面からページ番号を取得
		String pageNumberProto = request.getParameter("pageNumber");
		int pageNumber = 0;
		
		// 入力値が数値以外なら弾いて全件検索に切り替え
		if(isDigitPageNumber(pageNumberProto)) {
			pageNumber = Integer.parseInt(pageNumberProto);
		}
		
		// DBからメモデータ取得
		List<memoruProperties> recList = new ArrayList<memoruProperties>();
		
		// ページ範囲を指定
		String pageRange = " limit 5 offset " + Integer.toString(5 * (pageNumber - 1)) + ";";
		
		// 入力されたページ番号が数値以外（＝無効値）なら全件検索に切り替え
		if (pageNumber == 0) {
			pageRange = ";";
		}
		try(Connection conn = 
				DriverManager.getConnection(MemoRuConst.DB_URL, MemoRuConst.ROOT_USER, MemoRuConst.PASS);
				PreparedStatement ps = conn.prepareStatement(MemoRuConst.SELECTALL + pageRange)){

			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					
					memoruProperties record = new memoruProperties();
					
					record.setMemoIdValue(rs.getInt("memo_id"));
					record.setMemoContentsValue(rs.getString("memo_contents"));
					record.setCreateDatetimeValue(formatDatetime(rs.getTimestamp("create_datetime")));
					record.setUpdateDatetimeValue(formatDatetime(rs.getTimestamp("update_datetime")));
					
					recList.add(record);
				}
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recList;
	}
	
	public String updateMemo(HttpServletRequest request) throws Exception {
		Connection conn = null;
		PreparedStatement presmt = null;

		// 実行結果
		String exeResult = "";
		
		String SQL = "UPDATE memo_master SET memo_contents = ?, update_datetime = ? WHERE memo_id = ?;";
		
		// SQL実行
		try {
			conn = DriverManager.getConnection(MemoRuConst.DB_URL, MemoRuConst.ROOT_USER, MemoRuConst.PASS);
			presmt = conn.prepareStatement(SQL);
			
			// 文字化け防止
			request.setCharacterEncoding("UTF-8");
			// レコード編集用データ取得
			String memo_id = request.getParameter("memo_id");
			String memo_contents = request.getParameter("memo_contents");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String update_datetime = timestamp.toString();
			
			// SQLの?に割り当て
			presmt.setString(1, memo_contents);
			presmt.setString(2, update_datetime);
			presmt.setString(3, memo_id);
			
			presmt.executeUpdate();
			
			exeResult = "Success";
		} catch (SQLException e) {
			e.printStackTrace();
			exeResult = "Fail";
		} catch (Exception e) {
			e.printStackTrace();
			exeResult = "Fail";
		}
		presmt.close();
		conn.close();
		
		return exeResult;
	}
	
	public String deleteMemo(HttpServletRequest request) throws Exception {
		Connection conn = null;
		PreparedStatement presmt = null;
		
		// 実行結果
		String exeResult = MemoRuConst.EMPTY;

		String SQL = "UPDATE memo_master SET delete_datetime = ? WHERE memo_id = ?;";

		// SQL実行
		try {
			conn = DriverManager.getConnection(MemoRuConst.DB_URL, MemoRuConst.ROOT_USER, MemoRuConst.PASS);
			presmt = conn.prepareStatement(SQL);
			
			// レコード論理削除用データ取得
			String memo_id = request.getParameter("memo_id");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String delete_datetime = timestamp.toString();
			
			// SQLの?に割り当て
			presmt.setString(1, delete_datetime);
			presmt.setString(2, memo_id);
			
			presmt.executeUpdate();

			exeResult = "Success";

		} catch (SQLException e) {
			e.printStackTrace();
			exeResult = "Fail";
		} catch (Exception e) {
			e.printStackTrace();
			exeResult = "Fail";
		}
		presmt.close();
		conn.close();
		
		return exeResult;
	}

	/**
	 * ページ番号の数値判定
	 * @param pageNumber
	 * @return
	 */
	private boolean isDigitPageNumber(String pageNumber) {
		
		boolean isDigit = false;
		
		// null？→空文字？→数値？
		if( pageNumber != null && !pageNumber.isEmpty() && pageNumber.matches("[+-]?\\d*(\\.\\d+)?")) {
			isDigit = true;
		}
		
		return isDigit;
	}
	
	/**
	 * 日時形式を（かなり無理やり）成形
	 * 
	 */
	private String formatDatetime (Timestamp dt) {
		// 形式 YYYY-MM-DD HH:mm:ss.f → YYYY/MM/DD HH:mm
		String unformated = dt.toString().replace("-", "/");
		String formated = unformated.substring(0, unformated.length() -5);
		return formated;
	}
}
