package com.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/suggest")
public class SearchSuggest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		// http://suggestion.baidu.com/su?wd=souji&cb=con
		String q = request.getParameter("q");
		String ans = BAIDUSuggest.getRes("http://suggestion.baidu.com/su?wd=" + q + "&cb=con");
		PrintWriter out = response.getWriter();
		String resText = new String(ans.getBytes(), "utf-8");
		System.out.println(resText);
		out.println(resText);
	}
	
}
