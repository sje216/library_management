package library.service;


import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import library.dao.MemberDAO;
import library.exception.MemberNotFoundException;
import library.vo.Member;
import library.vo.MemberUpdateCommand;

public class MemberUpdateService {

	private MemberDAO dao;

	public MemberUpdateService(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void memberupdateById(MemberUpdateCommand UpdateCommand,String id) {
		Member member = dao.selectById(id);
		
		if(member==null) {
			throw new MemberNotFoundException();
		}
		
		int result = dao.updateById(UpdateCommand.getName(),UpdateCommand.getPw(),(Date) UpdateCommand.getBirthday(),UpdateCommand.getEmail(),UpdateCommand.getCall(), id);
		
	}
	
	@Transactional
	public void memberupdateBymemberNum(MemberUpdateCommand UpdateCommand,Long memberNum) {
		Member member = dao.selectByMemberNum(memberNum);
		
		if(member==null) {
			throw new MemberNotFoundException();
		}
		
		int result = dao.updateByMemberNum(UpdateCommand.getName(),UpdateCommand.getPw(),(Date) UpdateCommand.getBirthday(),UpdateCommand.getEmail(),UpdateCommand.getCall(), memberNum);
		
	}
	
}
