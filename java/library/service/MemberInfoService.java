package library.service;

import library.dao.MemberDAO;
import library.exception.IdPwNotMatchingException;
import library.exception.MemberNotFoundException;
import library.vo.Member;
import library.vo.MemberInfo;

public class MemberInfoService {
	
	private MemberDAO memberdao;

	public void setMemberdao(MemberDAO memberdao) {
		this.memberdao = memberdao;
	}
	
	public MemberInfo login(String id,String pw) {
		Member member = memberdao.selectById(id);
		if(member==null) {
			throw new MemberNotFoundException();
		}
		if(!member.getPw().equals(pw)) {
			throw new IdPwNotMatchingException();
		}
		
		return new MemberInfo(member.getId(), member.getEmail(), member.getName());
	}
	

}
