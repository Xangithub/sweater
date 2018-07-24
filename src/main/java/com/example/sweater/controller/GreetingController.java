package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class GreetingController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("name",name);
        return "greeting";
    }

    @GetMapping
    public String main(Model model){
        Iterable<Message> all = messageRepository.findAll();
        model.addAttribute("messages", all);
        return "main";
    }

    @PostMapping
    public  String add(@RequestParam String text, @RequestParam String tag, Model model){
        Message message= new Message(text,tag);
        messageRepository.save(message);
        return "redirect:/";
    }

    @PostMapping("filter")
    public  String filter(@RequestParam String filter, Model model){
        if (filter == null || filter.isEmpty()) {
            main(model);
            return "main";
        }
        System.out.println("Фильтр введен " +filter);
        List<Message> all = messageRepository.findMessagesByTextContaining(filter);
        model.addAttribute("messages", all);
        return "main";
    }
}
