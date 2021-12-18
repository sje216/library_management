package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.RequestParam;

import library.vo.BookDate;
import library.vo.Member;

public class BookDateDAO {
	// 데이터베이스 연결과 쿼리 전송

	private JdbcTemplate jdbcTemplate;

	public BookDateDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	// 공통의 RowMapper를 꺼내봅시다
	private RowMapper<BookDate> rowMapper = new RowMapper<BookDate>() {
		BookDate b = null;

		@Override
		public BookDate mapRow(ResultSet rs, int rowNum) throws SQLException {
			b = new BookDate(rs.getString("title"), rs.getString("author"), rs.getString("company"),
					rs.getInt("condition"), rs.getString("coverImg"), rs.getString("content"));
			b.setBookNum(rs.getInt("bookNum"));

			return b;
		}
	};

	// 전체 책 조회
	public List<BookDate> selectAll() {
		String sql = "select * from bookdate order by bookNum";
		List<BookDate> books = jdbcTemplate.query(sql, rowMapper);
		return books;
	}

	// 책 전체 책 조회
	public List<BookDate> selectForAllBook(int sec, int page) {
		String sql = "select * from "
				+ " (select ROWNUM as rNum,bookNum,title,author,company,content,coverImg,condition from "
				+ " (select * from bookdate order by bookNum desc)) "
				+ " where rNum between (?-1)*100+(?-1)*10+1 AND (?-1)*100+(?)*10";
		List<BookDate> books = jdbcTemplate.query(sql, rowMapper, sec, page, sec, page);
		return books;
	}

	// 책 전체 책 레코드 수
	public int allBookcount() {
		Integer cnt = jdbcTemplate.queryForObject("SELECT count(*) from bookdate",
				Integer.class);
		return cnt;
	}

	// bestseller 책 조회
	public List<BookDate> selectBestSeller() {
		String sql = "select * from bookdate where bookNum<11 and bookNum>0 order by bookNum";
		List<BookDate> books = jdbcTemplate.query(sql, rowMapper);
		return books;
	}

	// 책 검색 책 조회
	public List<BookDate> searchForBook(String search, int sec, int page) {
		String sql = "select * from "
				+ " (select ROWNUM as rNum,bookNum,title,author,company,content,coverImg,condition from "
				+ " (select * from bookdate where title like ? order by bookNum)) "
				+ " where rNum between (?-1)*100+(?-1)*10+1 AND (?-1)*100+(?)*10";
		String wrappedKeyword = "%" + search + "%";
		List<BookDate> books = jdbcTemplate.query(sql, rowMapper, wrappedKeyword, sec, page, sec, page);
		return books;
	}

	// 책 검색 책 레코드 수
	public int bookcount(String search) {
		String wrappedKeyword = "%" + search + "%";
		Integer cnt = jdbcTemplate.queryForObject("SELECT count(*) from bookdate where title like ? order by bookNum",
				Integer.class, wrappedKeyword);
		return cnt;
	}

	// 페이지 정보와 섹션정보를 통해서 추출한 10개의 게시물을 얻어오는 쿼리 - 책 검색 - 작가 검색 책 조회
	public List<BookDate> searchForAuthor(String search, int sec, int page) {
		String sql = "select * from "
				+ "(select ROWNUM as rNum,bookNum,title,author,company,content,coverImg,condition from "
				+ "(select * from bookdate where author like ? order by bookNum)) "
				+ "where rNum between (?-1)*100+(?-1)*10+1 AND (?-1)*100+(?)*10";
		String wrappedKeyword = "%" + search + "%";
		List<BookDate> books = jdbcTemplate.query(sql, rowMapper, wrappedKeyword, sec, page, sec, page);
		return books;
	}

	// 작가 검색 책 레코드 수
	public int authorcount(String search) {
		String wrappedKeyword = "%" + search + "%";
		Integer cnt = jdbcTemplate.queryForObject("SELECT count(*) from bookdate where author like ? order by bookNum",
				Integer.class, wrappedKeyword);
		return cnt;
	}

	// 선택된 책 상세보기 - bookNum
	public BookDate bookSelectedByBookNum(int bookNum) {
		String sql = "select * from bookdate where bookNum = ?";
		List<BookDate> book = jdbcTemplate.query(sql, rowMapper, bookNum);
		return book.isEmpty() ? null : book.get(0);
	}
	
	// 선택된 책 상세보기 - title
		public BookDate bookSelectedByTitle(String title) {
			String sql = "select * from bookdate where title = ?";
			List<BookDate> book = jdbcTemplate.query(sql, rowMapper, title);
			return book.isEmpty() ? null : book.get(0);
		}
	
	// 선택된 책 정보 수정
	public int updateBybookNum(String title,String author,String company,int condition, String coverImg,String content,int bookNum) {
		String sql = "update bookdate set title=?,author=?,company=?,condition=?,coverImg=?,content=? where bookNum=?";
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] {"bookNum"});
				psmt.setString(1, title);
				psmt.setString(2, author);
				psmt.setString(3, company);
				psmt.setInt(4, condition);
				psmt.setString(5, coverImg);
				psmt.setString(6, content);
				psmt.setInt(7, bookNum);
				return psmt;
			}
		});
		return result;
		
	}
	
	// 선택된 책 삭제
	public void delete(int bookNum) {
		String sql = "delete from bookdate where bookNum=?";
		jdbcTemplate.update(sql,bookNum);
	}
	
	
	// 책 추가
	public void insert(BookDate book) {
		String sql = "insert into bookdate values(bookdate_seq.nextval,?,?,?,?,?,?)";
		KeyHolder key = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] {"bookNum"});
				psmt.setString(1, book.getTitle());
				psmt.setString(2, book.getAuthor());
				psmt.setString(3, book.getCompany());
				psmt.setString(4, book.getContent());
				psmt.setString(5, book.getCoverImg());
				psmt.setInt(6, book.getCondition());
				return psmt;
			}
		}, key);	
		Number keyValue = key.getKey();
		book.setBookNum(keyValue.intValue());
	}
	

}
