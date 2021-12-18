package library.service;

import library.dao.MemberDAO;
import library.exception.AlreadyMemberException;
import library.vo.Master;
import library.vo.Member;
import library.vo.Register;

public class MemberRegisterService {

	private MemberDAO dao;

	public MemberRegisterService(MemberDAO dao) {
		this.dao = dao;
	}
	
	public void registMember(Register reg) {
		Member member = dao.selectById(reg.getId());
		if(member != null) {
			throw new AlreadyMemberException("이미 존재하는 계정입니다. : "+reg.getId());
		}
		Member newMember = new Member(
				reg.getName(),reg.getId(),reg.getPw(),reg.getBirthday(),reg.getEmail(),reg.getCall()
				);
		dao.insert(newMember); // 회원가입
	}
	
	
}
