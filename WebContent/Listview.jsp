<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

<table border="1" width="90%">
	<tr>
		<th width = 10%> 번호 </th>
		<th width = 50%> 제목 </th>
		<th width = 15%> 작성자 </th>
		<th width = 15%> 작성일 </th>
		<th width = 10%> 조회수 </th>
	</tr>
		
	<c:forEach items="${ resultList }" var = "row" varStatus="loop">
	<tr align = "center">
		
		<td> ${ totalCount - (pageNum-1)*5 - loop.index }  </td>
		<td align="left"> <a href="../board/view.do?idx=${ row.idx }"> ${ row.title } </a>  </td>
		<td> ${ row.name }  </td>
		<td> ${ row.postdate }  </td>
		<td> ${ row.visitcount }  </td>
		
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="center">
		<button type="button" onclick="location.href='../board/write.do';">글쓰기</button>
		</td>
	
	
</table>

	<table border="1" width="90%">
		<tr align="center">
			<td>
			<form action="../board/list.do">
			<select name = "searchMode">
				<option value = "title"> 제목 </option>
				<option value = "content"> 내용 </option>
			</select>
				<input type="text" name="searchFiled" />
				<button type="submit"> 검색 </button>
			</form>
			</td>
		</tr>
		<tr align="center">
			<td>
				${ paging }
			</td>
		</tr>	
	
	</table>
</body>
</html>