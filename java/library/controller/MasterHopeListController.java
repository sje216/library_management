package library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import library.dao.HopebookanswerDAO;
import library.dao.HopebookinginfoDAO;
import library.service.AnswerRegistService;
import library.vo.AnswerCommand;
import library.vo.Hopebookanswer;
import library.vo.Hopebookinginfo;

@Controller
public class MasterHopeListController {

	private HopebookinginfoDAO dao;
	private HopebookanswerDAO answerdao;
	private AnswerRegistService answerRegSvc;

	public void setAnswerRegSvc(AnswerRegistService answerRegSvc) {
		this.answerRegSvc = answerRegSvc;
	}

	public void setDao(HopebookinginfoDAO dao) {
		this.dao = dao;
	}

	public void setAnswerdao(HopebookanswerDAO answerdao) {
		this.answerdao = answerdao;
	}

	// 희망 도서 게시판 목록 보여주기
	@RequestMapping(value = "/hopelist", method = RequestMethod.GET)
	public String hopelist(HttpServletRequest request, Model model) {
		String section = request.getParameter("section");
		String pageNum = request.getParameter("pageNum");
		int sec = Integer.parseInt((section == null) ? "1" : section);
		int page = Integer.parseInt((pageNum == null) ? "1" : pageNum);
		List<Hopebookinginfo> hopes = dao.selectAll(sec, page);
		int fullPage = dao.allHopes();
		model.addAttribute("section", sec);
		model.addAttribute("pageNum", page);
		model.addAttribute("fullPage", fullPage);
		model.addAttribute("hopes", hopes);
 
		return "/master/hopelist";
	}

	// 희망 도서 상세보기
	@RequestMapping(value = "/hope_detail", method = RequestMethod.GET)
	public ModelAndView hopeDetail(@ModelAttribute("answerCommand") AnswerCommand answerCommand,
			@RequestParam("num") int num) {
		ModelAndView mv = new ModelAndView();
		Hopebookinginfo hopes = dao.booksSelectByNum(num);
		Hopebookanswer answer = answerdao.selectByNum(num);
		dao.increViewCnt(num);
		mv.addObject("hopes", hopes);
		if(answer!=null) {
			mv.addObject("ans", answer);
		}
		mv.setViewName("/master/hopeanswer");
		return mv;
	}
	
	//희망 도서 삭제
	@RequestMapping("/deleteHopebook")
	public String deleteHopebook(@RequestParam("num")int num) {
		Hopebookanswer answer = answerdao.selectByNum(num);
		answerdao.delete(answer.getNum());
		dao.delete(num);
		return "redirect:/hopelist";
	}

	// 댓글 달아주기
	@RequestMapping(value = "/add_answer")
	public ModelAndView addanswer(@ModelAttribute("answerCommand") AnswerCommand answerCommand) {
		ModelAndView mv = new ModelAndView();
		answerRegSvc.addAnswer(answerCommand);
		mv.addObject("num", answerCommand.getNum());
		mv.setViewName("redirect:/hope_detail");
		return mv;
	}
	
	//댓글 수정 페이지로 넘기기
	@RequestMapping("updateAnsF")
	public String updateAnsF(@RequestParam("ano")int ano,Model model) {
		Hopebookanswer answer = answerdao.selectByAno(ano);
		model.addAttribute("ans", answer);
		return "/master/answerUpdate";
	}
	

	// 댓글 수정하기
	@RequestMapping("updateAns")
	public String updateAns(@RequestParam("ano")int ano,@RequestParam("answer")String answer,Model model) {
		answerdao.updateByNum(answer, ano);
		Hopebookanswer answers = answerdao.selectByAno(ano);
		int num = answers.getNum();
		model.addAttribute("num",num);
		dao.decreViewCnt(num);
		return "redirect:/hope_detail";
	}
	
	//댓글 삭제하기
	@RequestMapping(value = "/delete_answer")
	public String delete(@RequestParam("num")int num,@RequestParam("ano")int ano,Model model) {
		
		model.addAttribute("num", num);
		System.out.println(num);
		Hopebookanswer answer = answerdao.selectByAno(num);
		answerdao.delete(ano);
		System.out.println("삭제 완료");
		dao.decreViewCnt(num);
		
		return "redirect:/hope_detail";
	}
}
