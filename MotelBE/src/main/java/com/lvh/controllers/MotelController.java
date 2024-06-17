/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;

import com.lvh.pojo.Motel;
import com.lvh.services.MotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/**
 *
 * @author levan
 */
@Controller
public class MotelController {
    @Autowired
    private MotelService motelService;
    
    @GetMapping("/motels")
    public String createView(Model model) {
        model.addAttribute("motel", new Motel());
        return "motels"; // Tên định nghĩa trong tiles.xml
    }
    
    @PostMapping("/motels")
    public String createProduct(@ModelAttribute(value ="motel")Motel m) {
        try {
                this.motelService.addMotel(m);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        return "motels";
    }
    
     @GetMapping("/motels/{motelId}")
    public String updateView(Model model, @PathVariable(value = "motelId") Long id) {
        model.addAttribute("motel", this.motelService.getMotelByIdObject(id));
        return "motels";
    }
    
}