package com.example.form.Controller;

import com.example.form.model.Greeting;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class GreetingController {

    private List<Greeting> greetingList = new ArrayList<>();
    private long currentId = 1;

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        greeting.setId(currentId);
    	greetingList.add(greeting);
    	greetingList.sort(Comparator.comparingLong(Greeting::getScore).reversed());
    	for(int i=0;i<greetingList.size();i++) {
    		greetingList.get(i).setRank(i+1);
    	}
    	
        model.addAttribute("greetingList", greetingList);
        return "result";
    }

    @PostMapping("/delete")
    public String deleteGreeting(@RequestParam long id,Model model) {
        greetingList.removeIf(greeting -> greeting.getId()== id);
        for(int i=0;i<greetingList.size();i++) {
    		greetingList.get(i).setRank(i+1);
    	}
        model.addAttribute("greetingList", greetingList);
        return "result";
    }

    @GetMapping("/edit/{id}")
    public String editGreeting(@PathVariable long id, Model model) {
    	Greeting greetingToEdit = greetingList.stream()
                .filter(greeting -> greeting.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("greeting", greetingToEdit);
        return "edit";
    }

    @PostMapping("/update")
    public String updateGreeting(@ModelAttribute(
    		"greeting")Greeting updatedGreeting, Model model) {
        // Find the existing greeting by id
        Greeting existingGreeting = greetingList.stream()
                .filter(greeting -> greeting.getId() == updatedGreeting.getId())
                .findFirst()
                .orElse(null);

        if (existingGreeting != null) {
            // Update the existing greeting
            existingGreeting.setName(updatedGreeting.getName());
            existingGreeting.setScore(updatedGreeting.getScore());
        }

        model.addAttribute("greetingList", greetingList);
        return "result";
    }
}



