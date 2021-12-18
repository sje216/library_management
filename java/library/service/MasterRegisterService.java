package library.service;

import library.dao.MasterDAO;
import library.exception.AlreadyMemberException;
import library.vo.Master;
import library.vo.Register;

public class MasterRegisterService {

	private static MasterDAO masterdao;

	public MasterRegisterService(MasterDAO masterdao) {
		this.masterdao = masterdao;
	}
	
	public static void registMaster(Register reg) {
		Master master = masterdao.selectById(reg.getId());
		if(master != null) {
			throw new AlreadyMemberException("이미 존재하는 계정입니다. : "+reg.getId());
		}
		Master newMaster = new Master(
				reg.getName(),reg.getId(),reg.getPw(),reg.getBirthday(),reg.getEmail(),reg.getCall()
				);
		masterdao.insert(newMaster); // 회원가입
	}
	
}
