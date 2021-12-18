package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import library.vo.Master;
import library.vo.MemberUpdateCommand;

public class MasterDAO {

	private JdbcTemplate jdbcTemplate;

	public MasterDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	// 공통의 RowMapper를 꺼내봅시다
	private RowMapper<Master> rowMapper = new RowMapper<Master>() {
		Master m = null;

		@Override
		public Master mapRow(ResultSet rs, int rowNum) throws SQLException {
			m = new Master(rs.getString("name"), rs.getString("id"), rs.getString("pw"), rs.getTimestamp("birthday"),
					rs.getString("email"), rs.getString("call"));
			m.setMasterNum(rs.getLong("masterNum"));

			return m;
		}
	};

	// 회원 가입 / 현재 저장되는 memberNum 시퀀스를 담는건 KeyHolder
	public void insert(Master master) {
		KeyHolder key = new GeneratedKeyHolder();
		String sql = "insert into master values(master_seq.nextval,?,?,?,?,?,?)";

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "masterNum" });
				psmt.setString(1, master.getName());
				psmt.setString(2, master.getId());
				psmt.setString(3, master.getPw());
				psmt.setTimestamp(4, new Timestamp(master.getBirthday().getTime()));
				psmt.setString(5, master.getEmail());
				psmt.setString(6, master.getCall());
				return psmt;
			}
		}, key);
		Number keyValue = key.getKey();
		master.setMasterNum(keyValue.longValue());
	}

	// masterNum값으로 회원 정보 읽어오기
	public Master selectByMemberNum(Long masterNum) {
		String sql = "select * from master where masterNum=?";

		List<Master> mList = jdbcTemplate.query(sql, rowMapper, masterNum);
		return mList.isEmpty() ? null : mList.get(0);
	}

	// id값으로 회원 정보 읽어오기
	public Master selectById(String id) {
		String sql = "select * from master where id=?";

		List<Master> master = jdbcTemplate.query(sql, rowMapper, id);
		return master.isEmpty() ? null : master.get(0);
	}

	// 전체 회원 조회
	public List<Master> selectAll() {
		String sql = "select * from master order by masterNum";
		List<Master> masters = jdbcTemplate.query(sql, rowMapper);
		return masters;
	}

	// 회원 정보 수정 (memberNum이용)
	public int updateByMasterNum(String name, String pw, Date birthday, String email, String call, Long masterNum) {
		String sql = "update master set name=?,pw=?,birthday=?,email=?,call=? where masterNum=?";
		int result = jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "masterNum" });
				psmt.setString(1, name);
				psmt.setString(2, pw);
				psmt.setTimestamp(3, new Timestamp(birthday.getTime()));
				psmt.setString(4, email);
				psmt.setString(5, call);
				psmt.setLong(6, masterNum);
				return psmt;
			}
		});
		return result;
	}

	// 회원 정보 수정 (id이용)
	public int updateById(String name, String pw, Date birthday, String email, String call, String id) {
		String sql = "update master set name=?,pw=?,birthday=?,email=?,call=? where id=?";
		int result = jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "id" });
				psmt.setString(1, name);
				psmt.setString(2, pw);
				psmt.setTimestamp(3, new Timestamp(birthday.getTime()));
				psmt.setString(4, email);
				psmt.setString(5, call);
				psmt.setString(6, id);
				return psmt;
			}
		});
		return result;
	}

	// 회원 정보 삭제
	public void delete(String masterNum) {
		String sql = "delete from master where masterNum=?";
		jdbcTemplate.update(sql, masterNum);
	}

	// 아이디 중복 확인
	public Master idCheck(String id) {
		String sql = "select * from master where id = ?";
		List<Master> master = jdbcTemplate.query(sql, rowMapper, id);
		return master.isEmpty() ? null : master.get(0);
	}

	// 이름과 이메일로 아이디 찾기
	public Master findId(String name, String email) {
		String sql = "select * from master where name=? and  email=?";
		List<Master> mList = jdbcTemplate.query(sql, rowMapper, name, email);
		return mList.isEmpty() ? null : mList.get(0);
	}

	// 이름과 이메일로 아이디 찾기
	public Master findpw(String name, String email, String id) {
		String sql = "select * from master where name=? and  email=? and id=?";
		List<Master> mList = jdbcTemplate.query(sql, rowMapper, name, email, id);
		return mList.isEmpty() ? null : mList.get(0);
	}

}
