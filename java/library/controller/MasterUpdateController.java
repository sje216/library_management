package library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import library.dao.MasterDAO;
import library.exception.IdPwNotMatchingException;
import library.service.MasterUpdateService;
import library.validator.MemberUpdateCommandValidator;
import library.vo.Master;
import library.vo.MasterInfo;
import library.vo.MemberUpdateCommand;

@Controller
public class MasterUpdateController {

	private MasterDAO dao;
	private MasterUpdateService masterUpdateSvc;
	
	public void setDao(MasterDAO dao) {
		this.dao = dao;
	}

	public void setMasterUpdateSvc(MasterUpdateService masterUpdateSvc) {
		this.masterUpdateSvc = masterUpdateSvc;
	}

	@RequestMapping("/masterinfo")
	public ModelAndView memberInfoForm(@ModelAttribute("updateCommand")MemberUpdateCommand updateCommand,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		MasterInfo masterinfo  = (MasterInfo) session.getAttribute("masterinfo");
		Master master = dao.selectById(masterinfo.getId());
		mv.addObject("master",master);
		mv.setViewName("/master/masterUpdate");
		return mv;
	}
	
	@RequestMapping(value = "/changeMaster",method = RequestMethod.POST)
	public ModelAndView MemberUpdate(@ModelAttribute("updateCommand") MemberUpdateCommand updateCommand,HttpServletRequest request,
			Errors err) {
		ModelAndView mv = new ModelAndView();
		new MemberUpdateCommandValidator().validate(updateCommand, err);
		String id = request.getParameter("id");
		if(err.hasErrors()) {
			mv.setViewName("/master/masterUpdate");
		}
		
		try {
			masterUpdateSvc.masterupdateById(updateCommand, id);
			mv.setViewName("redirect:/");
		}catch (IdPwNotMatchingException e) {
			err.rejectValue("pw", "required");
			mv.setViewName("/master/masterUpdate");
		}
		
		return mv;
	}
	
	
}
