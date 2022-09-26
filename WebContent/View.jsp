<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="board.BoardDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var validate = "${ viewDto.pass }";
	function passCheck(a) {
		var rs = prompt("비밀번호를 입력하세요.");
		if (rs == validate) {
			$.ajax({
				url : '/Pass.jsp',
				type : 'get',
				async : false,
				data : {pass : validate}
			});
			if(a == 1) 
				document.location="../board/delete.do?idx=" + [[${ viewDto.idx }]];
			else
				document.location="../board/edit.do?idx=" + [[${ viewDto.idx }]];
		}
		else {
			alert("비밀번호가 맞지 않습니다.");
		}
			
	}
</script>
	<table border="1" width="90%">
		<colgroup >
			<col width="15%"> <col width="35%"> <col width="15%"> <col width="35%">
		</colgroup>
	<tr>
		<td>번호</td>
		<td>${ viewDto.idx }</td>
		<td>작성자</td>
		<td>${ viewDto.name }</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${ viewDto.postdate }</td>
		<td>조회수</td>
		<td>${ viewDto.visitcount }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="3">${ viewDto.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">${ viewDto.content }</td>
	</tr>
	<tr>
		<td colspan="4" align="center"> <button type="button" onclick="location.href=document.referrer;"> 목록 </button>
		<button type="button" onclick="passCheck(1)">삭제하기</button>
		<button type="button" onclick="passCheck(2)">수정하기</button>
	</tr>
	</table>
</body>
</html>