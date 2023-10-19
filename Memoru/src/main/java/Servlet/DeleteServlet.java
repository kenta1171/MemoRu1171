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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = MemoRuConst.EMPTY;
		crudMemo cr = new crudMemo();
		// レコード削除（論理）してページ再表示　
		try {
			result = cr.deleteMemo(request);
			request.setAttribute("MemoResult", cr.readMemo(request));
		} catch (Exception e) {
			e.printStackTrace();
			result = "oh fail";
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher(MemoRuConst.MemoRuTable).forward(request, response);
	}

}
