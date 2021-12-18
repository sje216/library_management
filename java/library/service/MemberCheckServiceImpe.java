package library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.MemberDAO;
import library.vo.Member;
@Service("memberService")
public class MemberCheckServiceImpe implements MemberCheckService{

	private MemberDAO memberdao;
	
	public void setMemberdao(MemberDAO memberdao) {
		this.memberdao = memberdao;
	}
	@Override
	public Member idcheck(String id) {
		return memberdao.idCheck(id);
	}


}
