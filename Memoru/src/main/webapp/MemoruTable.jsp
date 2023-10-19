<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Source.memoruProperties"%>
<%
List<memoruProperties> memoList = (List<memoruProperties>) request.getAttribute("MemoResult");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memoru Table</title>
</head>
<body>
	<h2 style="text-align: center">MemoRu MiRu</h2>
	<hr style="height: 3; background-color: #0000ff" />

	<!-- 「New Memo」（メモ新規作成）ボタン -->
	<form method='post' action='TsukuRuServlet'>
		<div>
			<input type="submit" value="New Memo">
		</div>
	</form>
	<br>
	<div>
		<div><%=memoList.size() %></div>
		<table style='margin: auto; width: 90vw; border: 1px solid black;'>
			<thead>
				<tr>
					<th scope="col"
						style='text-align: left; width: 5vw; height: 5vh; border: 1px solid black;'>ID</th>
					<th scope="col"
						style='text-align: left; width: 47vw; height: 5vh; border: 1px solid black;'>Memo</th>
					<th scope="col"
						style='text-align: left; width: 15vw; height: 5vh; border: 1px solid black;'>Create</th>
					<th scope="col"
						style='text-align: left; width: 15vw; height: 5vh; border: 1px solid black;'>Update</th>
					<th scope="col"
						style='text-align: left; width: 8vw; height: 5vh; border: 1px solid black;'>Operation</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (memoruProperties memo : memoList) {
				%>
				<tr>
					<!-- 「Edit Memo」、「Delete Memo」（メモ編集、削除）ボタン用にデータ行をformタグに格納-->
					<form method='post' action='EditServlet' onsubmit="return checkExecute()">
						<td style='text-align: right; border: 1px solid black;'><%=memo.getMemoIdValue()%></td>
						<td style='border: 1px solid black;'>
							<div style='height: 6vh; overflow: hidden;'>
								<textarea wrap="soft"style="width: 100%; height: 100%; border: none;" name="memo_contents"><%=memo.getMemoContentsValue()%></textarea>
							</div>
						</td>
						<td style='text-align: right; border: 1px solid black;'><%=memo.getCreateDatetimeValue()%></td>
						<td style='text-align: right; border: 1px solid black;'><%=memo.getUpdateDatetimeValue()%></td>
						<td style='border: 1px solid black;'>
							<div>
								<input style="width: 14.5vh; text-align: center;" type="submit" value="Edit Memo">
							</div>
							<div>
								<input type="hidden" name="memo_id"
									value="<%=memo.getMemoIdValue()%>">
								<input style="width: 14.5vh; text-align: center;" type="submit" value="Delete Memo" onClick="form.action='DeleteServlet';">
							</div>
						</td>
					</form>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
	<script src="js/memoru_js.js"></script>
</body>
	<% if (request.getAttribute("result") != null){
		String result = (String)request.getAttribute("result");
		%>
<script>
	var msg = "<%=result%>";
	alert(msg);
</script>
<%}%>
</html>