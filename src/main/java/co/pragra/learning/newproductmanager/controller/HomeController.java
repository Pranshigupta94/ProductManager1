package co.pragra.learning.newproductmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to Product Manager");
        model.addAttribute("heroTitle", "Our Awesome Product Manager");
        return "home";
    }

}
