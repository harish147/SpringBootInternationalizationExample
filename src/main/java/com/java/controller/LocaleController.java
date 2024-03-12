package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LocaleController {

	@GetMapping("/changeLocale")
	public String changeLocale(@RequestParam("lang") String language, HttpServletResponse response) {
		Cookie cookie = new Cookie("localeCookie", language);
		cookie.setMaxAge(3600);
		cookie.setPath("/");

		response.addCookie(cookie);

		return "redirect:/";
	}
}
