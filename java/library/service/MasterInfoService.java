package library.service;

import library.dao.MasterDAO;
import library.exception.IdPwNotMatchingException;
import library.exception.MemberNotFoundException;
import library.vo.Master;
import library.vo.MasterInfo;
import library.vo.MemberInfo;

public class MasterInfoService {
	
	private MasterDAO masterdao;

	public void setMasterdao(MasterDAO masterdao) {
		this.masterdao = masterdao;
	}

	public MasterInfo login(String id,String pw) {
		Master master = masterdao.selectById(id);
		if(master==null) {
			throw new MemberNotFoundException();
		}
		if(!master.getPw().equals(pw)) {
			throw new IdPwNotMatchingException();
		}
		
		return new MasterInfo(master.getId(), master.getEmail(), master.getName());
	}
	

}
