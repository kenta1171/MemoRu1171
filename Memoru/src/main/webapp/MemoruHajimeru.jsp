<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memoru HajimeRu</title>
</head>
<body>
	<h2 style="text-align: center">MemoRu HajimeRu</h2>
	<hr style="height: 3; background-color: #0000ff" />

	<form method='get' action='HajimeruServlet'>
		<div>
			Your Name?
			<input type='text' name='userName'><br >
			Your Password?
			<input type='text' name='password'>
			Page Number
			<input type='text' name='pageNumber'>
			<input type='submit' value='start'>
		</div>
	</form>
</body>
</html>