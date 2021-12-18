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
import org.springframework.transaction.annotation.Transactional;

import library.vo.Member;

// 데이터베이스 연결과 쿼리 전송
public class MemberDAO {

	private JdbcTemplate jdbcTemplate;

	public MemberDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	// 공통의 RowMapper를 꺼내봅시다
	private RowMapper<Member> rowMapper = new RowMapper<Member>() {
		Member m = null;

		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			m = new Member(rs.getString("name"), rs.getString("id"), rs.getString("pw"), rs.getTimestamp("birthday"),
					rs.getString("email"), rs.getString("call"));
			m.setMemberNum(rs.getLong("memberNum"));

			return m;
		}
	};

	// 회원 가입 / 현재 저장되는 memberNum 시퀀스를 담는건 KeyHolder
	public void insert(Member member) {
		KeyHolder key = new GeneratedKeyHolder();
		String sql = "insert into member values(member_seq.nextval,?,?,?,?,?,?)";

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "memberNum" });
				psmt.setString(1, member.getName());
				psmt.setString(2, member.getId());
				psmt.setString(3, member.getPw());
				psmt.setTimestamp(4, new Timestamp(member.getBirthday().getTime()));
				psmt.setString(5, member.getEmail());
				psmt.setString(6, member.getCall());
				return psmt;
			}
		}, key);
		Number keyValue = key.getKey();
		member.setMemberNum(keyValue.longValue());
	}

	// memberNum값으로 회원 정보 읽어오기
	public Member selectByMemberNum(Long memberNum) {
		String sql = "select * from member where memberNum=?";

		List<Member> mList = jdbcTemplate.query(sql, rowMapper, memberNum);
		return mList.isEmpty() ? null : mList.get(0);
	}

	// id값으로 회원 정보 읽어오기
	public Member selectById(String id) {
		String sql = "select * from member where id=?";

		List<Member> mList = jdbcTemplate.query(sql, rowMapper, id);
		return mList.isEmpty() ? null : mList.get(0);
	}

	// 전체 회원 조회
	public List<Member> selectAll() {
		String sql = "select * from member order by memberNum";
		List<Member> members = jdbcTemplate.query(sql, rowMapper);
		return members;
	}

	// 회원 정보 수정 (memberNum이용)
	public int updateByMemberNum(String name, String pw, Date birthday, String email, String call, Long memberNum) {
		String sql = "update member set name=?,pw=?,birthday=?,email=?,call=? where memberNum=?";
		int result  = jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] { "memberNum" });
				psmt.setString(1, name);
				psmt.setString(2, pw);
				psmt.setTimestamp(3, new Timestamp(birthday.getTime()));
				psmt.setString(4, email);
				psmt.setString(5, call);
				psmt.setLong(6, memberNum);
				return psmt;
			}
		});
		return result;
	}

	// 회원 정보 수정 (id이용)
	public int updateById(String name, String pw, Date birthday, String email, String call, String id) {
		String sql = "update member set name=?,pw=?,birthday=?,email=?,call=? where id=?";
		int result  = jdbcTemplate.update(new PreparedStatementCreator() {

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
	public void delete(String memberNum) {
		String sql = "delete from member where memberNum=?";
		jdbcTemplate.update(sql,memberNum);
	}

	// 아이디 중복 확인
	public Member idCheck(String id) {
		String sql = "select * from member where id = ?";
		List<Member> member = jdbcTemplate.query(sql, rowMapper, id);
		return member.isEmpty() ? null : member.get(0);
	}

	// 이름과 이메일로 아이디 찾기
	public Member findId(String name, String email) {
		String sql = "select * from member where name=? and  email=?";
		List<Member> mList = jdbcTemplate.query(sql, rowMapper, name, email);
		return mList.isEmpty() ? null : mList.get(0);
	}

	// 이름과 이메일로 아이디 찾기
	public Member findpw(String name, String email,String id) {
		String sql = "select * from member where name=? and  email=? and id=?";
		List<Member> mList = jdbcTemplate.query(sql, rowMapper, name, email,id);
		return mList.isEmpty() ? null : mList.get(0);
	}
	
	//비밀번호 변경
	public int changePw(String pw,String id) {
		String sql = "update member set pw=? where id=?";
		int result = jdbcTemplate.update(sql,new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String [] {"id"});
				psmt.setString(1, pw);
				psmt.setString(2, id);
				return psmt;
			}
		});
		return result;
	}

}
