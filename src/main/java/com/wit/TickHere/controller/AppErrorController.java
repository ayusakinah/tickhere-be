package com.wit.TickHere.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController {
    @RequestMapping("/custom-error")
    public String handleError(HttpServletRequest request, Model model){
        Object statusCode = request.getAttribute("jakarta.servlet.error.status_code");
        Object exception = request.getAttribute("jakarta.servlet.error.exception");
        Object path = request.getAttribute("jakarta.servlet.error.request_uri");

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("path", path);

        if(statusCode != null && statusCode.toString().equals("404")){
            return "custom-404";
        }

        model.addAttribute("exception", exception);
        return "error-page";
    }
}
