<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>

<form name="write" method="post" action="../board/write.do">
	<table border="1" width="90%">
	<colgroup>
		<col width="15%">
	</colgroup>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="name" required/> </td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" required /> </td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" style="width:80%;height:100px;" required></textarea> </td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pass" required /> </td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<button type="submit">작성 완료</button>
			<button type="button" onclick="location.href=document.referrer;">목록으로</button>
			</td>
		</tr>
	
	
	</table>
</form>
</body>
</html>