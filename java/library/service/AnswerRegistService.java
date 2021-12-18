package library.service;

import library.dao.HopebookanswerDAO;
import library.exception.AlreadyAnswerException;
import library.vo.AnswerCommand;
import library.vo.Hopebookanswer;

public class AnswerRegistService {

	private HopebookanswerDAO dao;

	public AnswerRegistService(HopebookanswerDAO dao) {
		this.dao = dao;
	}

	public void addAnswer(AnswerCommand command) {
		Hopebookanswer answer = dao.selectByNum(command.getNum());
		if (answer != null) {
			throw new AlreadyAnswerException("이미 존재하는 댓글입니다." + command.getAnswer());
		}
		Hopebookanswer newAnswer = new Hopebookanswer(command.getNum(), command.getAnswer());
		dao.insert(newAnswer);
	}
}
