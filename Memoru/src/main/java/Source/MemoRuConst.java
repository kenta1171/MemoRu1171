package Source;

public class MemoRuConst {
	// Common
	public final static String EMPTY = "";
	public final static String MemoRuDekiTa = "MemoRuDekiTa.jsp";
	public final static String MemoRuTable = "MemoruTable.jsp";
	
	// DB接続
	public final static String DB_URL = "jdbc:mysql://localhost:3306/memoru_master?";
	public final static String ROOT_USER = "root";
	public final static String PASS = "Jgtwnj15uhd46@";
	
	// SQL
	// 初期表示
	public final static String SELECTALL = "select memo_id, memo_contents, create_datetime, update_datetime from memo_master where delete_datetime is null order by update_datetime desc";
}
