package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import library.vo.Bookinginfo;

public class BookinginfoDAO {

	private JdbcTemplate jdbcTemplate;

	public BookinginfoDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	// 공통의 RowMapper를 꺼내봅시다
	private RowMapper<Bookinginfo> rowMapper = new RowMapper<Bookinginfo>() {
		Bookinginfo b = null;

		@Override
		public Bookinginfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			b = new Bookinginfo(rs.getInt("memberNum"), rs.getInt("bookNum"), rs.getTimestamp("bookingDate"), rs.getInt("reservation"));
			b.setBookingNum(rs.getInt("bookingNum"));

			return b;
		}
	};

	// 예약 전체 조회
	public List<Bookinginfo> selectforAll(int sec, int page) {
		String sql = "select * from " + " (select ROWNUM as rNum,bookingNum,memberNum,bookNum,bookingDate,reservation from "
				+ " (select * from bookinginfo order by bookingNum desc)) "
				+ " where rNum between (?-1)*100+(?-1)*10+1 AND (?-1)*100+(?)*10";
		List<Bookinginfo> books = jdbcTemplate.query(sql, rowMapper, sec, page, sec, page);
		return books;
	}

	// 예약 전체 수
	public int allCount() {
		Integer cnt = jdbcTemplate.queryForObject("select count(*) from bookinginfo", Integer.class);
		return cnt;
	}
	
	//memberNum으로 bookinginfo 조회
	public List<Bookinginfo> selectByMemberNum(int memberNum){
		String sql = "select * from bookinginfo where memberNum = ?";
		List<Bookinginfo> bList = jdbcTemplate.query(sql, rowMapper,memberNum);
		return bList;
	}
	
	
	//bookNum으로 bookinginfo 조회
		public Bookinginfo selectByBookNum(int bookNum){
			String sql = "select * from bookinginfo where bookNum = ?";
			List<Bookinginfo> bList = jdbcTemplate.query(sql, rowMapper,bookNum);
			return bList.isEmpty() ? null : bList.get(0);
		}
		
	//memberNum으로 bookinginfo 총 레코드 수 조회
		public int allCountByMemberNum(int memberNum) {
			Integer cnt = jdbcTemplate.queryForObject("select count(*)  from bookinginfo where memberNum=?",Integer.class,memberNum);
			return cnt;
		}

	// 예약 추가
	public void resorvation(Bookinginfo booking) {
		String sql = "insert into bookinginfo values (BOOKINGINFO_SEQ.nextval,?,?,sysdate,'0')";
		KeyHolder key = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql,new String[] {"bookingNum"});
				psmt.setInt(1,booking.getMemberNum());
				psmt.setInt(2,booking.getBookNum());
				return psmt;
			}
		},key);
		Number keyValue = key.getKey();
		booking.setBookingNum(keyValue.intValue());
	}
	
	//예약 취소
	public void cancel(int bookingNum) {
		String sql = "delete from bookinginfo where bookingNum=?";
		jdbcTemplate.update(sql,bookingNum);
	}
}
