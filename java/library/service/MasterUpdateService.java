package library.service;


import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import library.dao.MasterDAO;
import library.exception.MemberNotFoundException;
import library.vo.Master;
import library.vo.MemberUpdateCommand;

public class MasterUpdateService {

	private MasterDAO dao;

	public MasterUpdateService(MasterDAO dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void masterupdateById(MemberUpdateCommand UpdateCommand,String id) {
		Master master = dao.selectById(id);
		
		if(master==null) {
			throw new MemberNotFoundException();
		}
		int result = dao.updateById(UpdateCommand.getName(),UpdateCommand.getPw(),(Date) UpdateCommand.getBirthday(),UpdateCommand.getEmail(),UpdateCommand.getCall(), id);
		
	}
	
	@Transactional
	public void masterupdateBymemberNum(MemberUpdateCommand UpdateCommand,Long masterNum) {
		Master master = dao.selectByMemberNum(masterNum);
		
		if(master==null) {
			throw new MemberNotFoundException();
		}
		
		int result = dao.updateByMasterNum(UpdateCommand.getName(),UpdateCommand.getPw(),(Date) UpdateCommand.getBirthday(),UpdateCommand.getEmail(),UpdateCommand.getCall(), masterNum);
		
	}
	
}
