package br.com.bluesoft.votacao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionGenerica(HttpServletRequest request, Exception exception) {
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", request.getRequestURL());
		view.addObject("excecao", exception);
		return view;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
	public ModelAndView noHandlerFoundException(HttpServletRequest request, Exception exception) {
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", "</br> Está pagina não exite para este dominio!");
		view.addObject("url", request.getRequestURL());
		view.addObject("excecao", exception);
		return view;
	}
}