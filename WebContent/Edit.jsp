<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
</head>
<body>
<form name="edit" method="post" action="../board/edit.do?idx=${ dto.idx }">
	<table border="1" width="90%">
	<colgroup>
		<col width="15%">
	</colgroup>
		<tr>
			<td>작성자</td>
			<td>${ dto.name }</td>
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
			<td colspan="2" align="center">
			<button type="submit">수정</button>
			<button type="button" onclick="location.href='../board/list.do';">목록으로</button>
			</td>
		</tr>
	
	
	</table>
</form>
</body>
</html>