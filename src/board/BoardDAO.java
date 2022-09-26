package board;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.ArrayList;
import common.DBCP;

public class BoardDAO extends DBCP {
	public BoardDAO() {
		super();
	}
	
	public List<BoardDTO> pageList(int start, int end, String searchMode, String searchFiled) {
		List<BoardDTO> board = new ArrayList<BoardDTO>();
		
		String query = "";
		query += "SELECT * FROM (SELECT db.*, ROWNUM rNum FROM (SELECT * FROM board ";
		if (searchFiled != null)
			query += "WHERE " + searchMode + " LIKE '%" + searchFiled + "%' ";
		query += "ORDER BY idx DESC) db) WHERE rNum BETWEEN ? AND ?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, Integer.toString(start));
			psmt.setString(2, Integer.toString(end));
			rs = psmt.executeQuery();
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setPostdate(rs.getDate(4));
				dto.setPass(rs.getString(5));
				dto.setVisitcount(rs.getString(6));
				dto.setContent(rs.getString(7));
				
				board.add(dto);
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return board;
	}
	
	public BoardDTO selectView(String idx, boolean isVisit) {
		
		BoardDTO dto = new BoardDTO();
		String updateQuery = "";
		String query = "";
		query += "SELECT * FROM board WHERE idx=?";
		
		if(isVisit) {
		
			updateQuery += "UPDATE board SET visitcount=visitcount+1 WHERE idx=?";
		}
		
		try {
			if(isVisit) {
			psmt = conn.prepareStatement(updateQuery);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			}
			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			rs.next();
			
			dto.setIdx(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setTitle(rs.getString(3));
			dto.setPostdate(rs.getDate(4));
			dto.setPass(rs.getString(5));
			dto.setVisitcount(rs.getString(6));
			dto.setContent(rs.getString(7));
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public int searchTotalCount(String searchMode, String searchFiled) {
		int totalCount = 0;
		String query = "";
		query += "SELECT COUNT(*) FROM board";
		if(searchFiled != null) {
			query += " WHERE " + searchMode + " LIKE '%" + searchFiled + "%'";
		}
			
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
		return totalCount;
	}
	
	public int insertWrite(String name, String title, String content, String pass) {
		
		int rs = 0;
		String query = "";
		
		query += "INSERT INTO board (idx, name, title, pass, content) VALUES (SEQ_BOARD_IDX.NEXTVAL,?,?,?,?)";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, title);
			psmt.setString(3, pass);
			psmt.setString(4, content);
			rs = psmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		
		return rs;
	}
	
	public int deleteBoard(String idx, String pass) {
		int rs = 0;
		String query = "";
		query += "DELETE FROM board WHERE idx=? AND pass=?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.setString(2, pass);
			rs = psmt.executeUpdate();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public int editBoard(String idx, String pass, String title, String content) {
		int rs = 0;
		String query = "";
		query += "UPDATE board SET title=?, content=? WHERE idx=? AND pass=?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, idx);
			psmt.setString(4, pass);
			rs = psmt.executeUpdate();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
