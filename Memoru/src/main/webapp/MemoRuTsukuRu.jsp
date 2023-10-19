<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemoRu TsukuRu</title>
</head>
<body>
	<h2 style="text-align: center">MemoRu TsukuRu</h2>
	<hr style="height: 3; background-color: #0000ff" />
	<br>
	<div style="text-align: left">メモを入力してください。</div>
	<form method="post" action="InsertServlet">
		<table style="width: 90%; height: 300px;">
			<tr>
				<td style="text-align: left; height: 100%;">
					<textarea rows="10" cols="60" wrap="soft" style="width: 100%; height: 100%;" name="memo_contents"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan=2 style="text-align: left">
				<input type="submit" value="Memo"></td>
			</tr>
		</table>
	</form>
	<br>
</body>
</html>