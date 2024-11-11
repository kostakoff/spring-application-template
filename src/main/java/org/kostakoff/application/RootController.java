package org.kostakoff.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
   
    @GetMapping("/")
    public String root() {
        // Return the name of the view to be displayed at the root URL (e.g., index.html)
        return "index.html";
    }
}