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

import library.vo.Hopebookanswer;
import library.vo.Hopebookinginfo;

public class HopebookanswerDAO {

	private JdbcTemplate jdbcTemplate;

	public HopebookanswerDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	// 공통의 RowMapper를 꺼내봅시다
	private RowMapper<Hopebookanswer> rowMapper = new RowMapper<Hopebookanswer>() {
		Hopebookanswer b = null;

		@Override
		public Hopebookanswer mapRow(ResultSet rs, int rowNum) throws SQLException {
			b = new Hopebookanswer(rs.getInt("num"), rs.getString("answer"), rs.getTimestamp("datetime"));
			b.setAno(rs.getInt("ano"));

			return b;
		}
	};

	// 희망도서 신청에 대한 답변 쓰기
	public void insert(Hopebookanswer answer) {
		KeyHolder key = new GeneratedKeyHolder();
		String sql = "insert into hopebookanswer values (hopebookanswer_seq.nextval,?,?,sysdate)";

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "ano" });
				psmt.setInt(1, answer.getNum());
				psmt.setString(2, answer.getAnswer());
				return psmt;
			}
		}, key);
		Number keyValue = key.getKey();
		answer.setAno(keyValue.intValue());
	}
	
	// 희망 도서에 대한 답변 불러오기
		public List<Hopebookanswer> selectAll() {
			String sql = "select * from hopebookanswer";

			List<Hopebookanswer> hList = jdbcTemplate.query(sql, rowMapper);
			return hList;
		}

	// num값으로 희망 도서에 대한 답변 불러오기
	public Hopebookanswer selectByNum(int num) {
		String sql = "select * from hopebookanswer where num = ?";

		List<Hopebookanswer> hList = jdbcTemplate.query(sql, rowMapper, num);
		return hList.isEmpty() ? null : hList.get(0);
	}
	
	// ano값 num값으로 희망 도서에 대한 답변 불러오기
		public Hopebookanswer selectByAno(int ano) {
			String sql = "select * from hopebookanswer where ano = ?";

			List<Hopebookanswer> hList = jdbcTemplate.query(sql, rowMapper, ano);
			return hList.isEmpty() ? null : hList.get(0);
		}

	// ano으로 희망도서 답변 삭제하기
	public void delete(int ano) {
		String sql = "delete from hopebookanswer where ano=?";
		jdbcTemplate.update(sql, ano);
	}

	// 희망 도서 답변 내용 수정 (ano이용)
	public int updateByNum( String answer, int ano) {
		String sql = "update hopebookanswer set answer=? where ano=?";
		int result = jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "ano" });
				psmt.setString(1, answer);
				psmt.setInt(2, ano);
				return psmt;
			}
		});
		return result;
	}

}
