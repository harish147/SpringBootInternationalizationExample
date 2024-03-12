package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.entity.Task;
import com.java.service.TaskServiceImpl;

@Controller
public class TaskController {

	@Autowired
	private TaskServiceImpl taskService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/alltasks")
	public String getAllTasks(Model model) {
		model.addAttribute("alltasks", taskService.getAllTasks());
		return "alltasks";
	}

	@GetMapping("/result")
	public String showResultPage(Model model) {
		if (model.getAttribute("message") != null && model.getAttribute("result") != null)
			return "result";
		else
			return "redirect:/";
	}

	@PostMapping("/addtask")
	public String addTask(@ModelAttribute Task task, Model model, RedirectAttributes redirectAttributes) {
		try {
			taskService.saveTask(task);
			redirectAttributes.addFlashAttribute("message",
					messageSource.getMessage("message.added_successfully", null, LocaleContextHolder.getLocale()));
			redirectAttributes.addFlashAttribute("result", taskService.getAllTasks());
			return "redirect:/result";
		} catch (Exception e) {
			model.addAttribute("errorMessage",
					messageSource.getMessage("exception.something_went_wrong", null, LocaleContextHolder.getLocale()));
			return "index";
		}

	}

	@GetMapping("/delete")
	public String deletePage(Model model) {
		model.addAttribute("allTasks", taskService.getAllTasks());
		return "delete";
	}

	@PostMapping("/delete")
	public String deleteTask(@RequestParam("taskId") Integer taskId, Model model, RedirectAttributes redirectAttributes) {
		try {
			taskService.deleteTask(taskId);
			redirectAttributes.addFlashAttribute("message",
					messageSource.getMessage("message.deleted_successfully", null, LocaleContextHolder.getLocale()));
			redirectAttributes.addFlashAttribute("result", taskService.getAllTasks());
			return "redirect:/result";
		} catch (Exception e) {
			model.addAttribute("errorMessage",
					messageSource.getMessage("exception.something_went_wrong", null, LocaleContextHolder.getLocale()));
			return "delete";
		}
	}

	@GetMapping("/update")
	public String updateTask(Model model) {
		model.addAttribute("allTasks", taskService.getAllTasks());
		return "update";
	}

	@PostMapping("/update")
	public String updateTask(@ModelAttribute Task task, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(task);
		try {
			System.out.println("updated task: " + taskService.updateTask(task));
			redirectAttributes.addFlashAttribute("message",
					messageSource.getMessage("message.updated_successfully", null, LocaleContextHolder.getLocale()));
			redirectAttributes.addFlashAttribute("result", taskService.getAllTasks());
			return "redirect:/result";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",
					messageSource.getMessage("exception.something_went_wrong", null, LocaleContextHolder.getLocale()));
			return "update";
		}
	}
}
