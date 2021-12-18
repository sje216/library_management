package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import library.vo.Hopebookinginfo;

public class HopebookinginfoDAO {

	private JdbcTemplate jdbcTeplate;

	public HopebookinginfoDAO(DataSource ds) {
		this.jdbcTeplate = new JdbcTemplate(ds);
	}

	// 공통의 RowMapper를 꺼내봅시다
	private RowMapper<Hopebookinginfo> rowMapper = new RowMapper<Hopebookinginfo>() {
		Hopebookinginfo b = null;

		@Override
		public Hopebookinginfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			b = new Hopebookinginfo(rs.getString("id"), rs.getString("title"), rs.getString("content"),
					rs.getTimestamp("datetime"), rs.getInt("viewCnt"));
			b.setNum(rs.getInt("num"));

			return b;
		}
	};

	// 희망 도서 신청하기
	public void insert(Hopebookinginfo hopebook) {
		KeyHolder key = new GeneratedKeyHolder();
		String sql = "insert into hopebookinginfo values (HOPEBOOKINGINFO_SEQ.nextval,?,?,?,sysdate,0)";

		jdbcTeplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "num" });
				psmt.setString(1, hopebook.getId());
				psmt.setString(2, hopebook.getTitle());
				psmt.setString(3, hopebook.getContent());
				return psmt;
			}
		}, key);
		Number keyValue = key.getKey();
		hopebook.setNum(keyValue.intValue());
	}

	// title 값으로 희망 도서 신청현황 불러오기
	public Hopebookinginfo selectById(String title) {
		String sql = "select * from hopebookinginfo where title = ?";

		List<Hopebookinginfo> hList = jdbcTeplate.query(sql, rowMapper, title);
		return hList.isEmpty() ? null : hList.get(0);
	}

	// id 값으로 희망 도서 신청현황 불러오기
	public List<Hopebookinginfo> booksSelectById(String id) {
		String sql = "select * from hopebookinginfo where id = ?";

		List<Hopebookinginfo> hList = jdbcTeplate.query(sql, rowMapper, id);
		return hList;
	}

	// num 값으로 희망 도서 신청현황 불러오기
	public Hopebookinginfo booksSelectByNum(int num) {
		String sql = "select * from hopebookinginfo where num = ?";

		List<Hopebookinginfo> hList = jdbcTeplate.query(sql, rowMapper, num);
		return hList.isEmpty() ? null : hList.get(0);
	}

	// num으로 희망도서 신청내역 삭제하기
	public void delete(int num) {
		String sql = "delete from hopebookinginfo where num=?";
		jdbcTeplate.update(sql, num);
	}

	// 희망 도서 신청 내용 수정 (num이용)
	public int updateByNum(String title, String content, int num) {
		String sql = "update hopebookinginfo set title=?,content=? where num=?";
		int result = jdbcTeplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "num" });
				psmt.setString(1, title);
				psmt.setString(2, content);
				psmt.setInt(3, num);
				return psmt;
			}
		});
		return result;
	}

	// 전체 희망 도서 신청 내역 조회
	public List<Hopebookinginfo> selectAll(int sec, int page) {
		String sql = "select * from " + " (select ROWNUM as rNum,num,id,title,content,datetime,viewCnt from "
				+ " (select * from hopebookinginfo order by num desc)) "
				+ " where rNum between (?-1)*100+(?-1)*10+1 AND (?-1)*100+(?)*10";
		List<Hopebookinginfo> hopelist = jdbcTeplate.query(sql, rowMapper, sec, page, sec, page);
		return hopelist;
	}

	// 전체 희망도서 신청내역 레코드 수
	public int allHopes() {
		Integer cnt = jdbcTeplate.queryForObject("select count(*) from hopebookinginfo", Integer.class);
		return cnt;
	}
	
	//조회수 증가
	public void increViewCnt(int num) {
		String sql = "update hopebookinginfo set viewCnt=viewCnt+1 where num=?";
		jdbcTeplate.update(sql,num);
	}
	
	//조회수 조절
		public void decreViewCnt(int num) {
			String sql = "update hopebookinginfo set viewCnt=viewCnt-1 where num=?";
			jdbcTeplate.update(sql,num);
		}



}
