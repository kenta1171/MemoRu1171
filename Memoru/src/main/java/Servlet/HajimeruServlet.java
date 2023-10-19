package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Source.MemoRuConst;
import Source.crudMemo;

/**
 * Servlet implementation class HajimeruServlet
 */
@WebServlet("/HajimeruServlet")
public class HajimeruServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HajimeruServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// memo_masterテーブルからメモ情報を取得		
		// MemoRuTableページに遷移してメモデータ表示
		crudMemo cr = new crudMemo();
		request.setAttribute("MemoResult", cr.readMemo(request));
		request.getRequestDispatcher(MemoRuConst.MemoRuTable).forward(request, response);
	}
}
