package com.joana.bundler.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joana.bundler.models.Joy;
import com.joana.bundler.models.LoginUser;
import com.joana.bundler.models.User;
import com.joana.bundler.services.JoyService;
import com.joana.bundler.services.UserService;


//Controller speaks to the service and repository
// @RestController
// @CrossOrigin(origins = "http://localhost:3000")
@Controller
public class MainController {
  // Add once service is implemented:
  @Autowired
  private UserService userServ;
  @Autowired
  private JoyService joyService;

  @GetMapping("/")
  public String index(Model model) {

    // Bind empty User and LoginUser objects to the JSP
    // to capture the form input
    model.addAttribute("newUser", new User());
    model.addAttribute("newLogin", new LoginUser());
    return "index.jsp";
  }

  //handles register form
  @PostMapping("/register")

  //modelAttribute variable "newUser" is used in the form it corresponds to
  public String register(@Valid @ModelAttribute("newUser") User newUser,
  BindingResult result, Model model, HttpSession session) {
    
    // TO-DO Later -- call a register method in the service
    // to do some extra validations and create a new user!
    User user = userServ.register(newUser, result);
    
    if (result.hasErrors()) {
      // Be sure to send in the empty LoginUser before
      // re-rendering the page.
      model.addAttribute("newLogin", new LoginUser());
      return "index.jsp";
    }
    
    // No errors!
    // TO-DO Later: Store their ID from the DB in session,
    // in other words, log them in.
    session.setAttribute("userId", user.getId());
    return "redirect:/home";
  }
  
  @PostMapping("/login")
  public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
  BindingResult result, Model model, HttpSession session) {
    
    // Add once service is implemented:
    User user = userServ.login(newLogin, result);
    
    if (result.hasErrors()) {
      model.addAttribute("newUser", new User());
      return "index.jsp";
    }
    
    // No errors!
    // TO-DO Later: Store their ID from the DB in session,
    // in other words, log them in.
    session.setAttribute("userId", user.getId());
    return "redirect:/home";
  }
  
  @RequestMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  @GetMapping("/home")
  public String dashboard(Model model, HttpSession session) {
    if (session.getAttribute("userId") == null) {
      return "redirect:/";
    }
    Long id = (Long) session.getAttribute("userId");

    List<Joy> names = joyService.allNames();
    System.out.println(names);
    model.addAttribute("names", names);

    User user = userServ.getById(id);
    model.addAttribute("user", user);
    return "dashboard.jsp";
  }

  // *************************************************************** */
  @RequestMapping("/names/new")
  public String newName(@ModelAttribute("thename") Joy name, HttpSession session, Model model) {
  //   if(result.hasErrors()) {	
  //   return "joys/createJoy.jsp";
  // }
  if (session.getAttribute("userId") == null) {
    return "redirect:/";
  }
  User user = userServ.getById((Long) session.getAttribute("userId"));
  model.addAttribute("user", user);
  return "names/createName.jsp";
}

  @PostMapping("/names/create")
  public String createName(@Valid @ModelAttribute("thename") Joy name, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "names/createName.jsp";
    } else {
      joyService.createName(name);
      List<Joy> names = joyService.allNames();
      model.addAttribute("thename", names);
      return "redirect:/home";
    }
  }

  @DeleteMapping("/names/delete/{id}")
  public String delete(@PathVariable("id")Long id){
    Joy name = joyService.findName(id);
      joyService.deleteName(name);
      return "redirect:/home";
  }
  
  @GetMapping("/names/{id}")
  public String display(@PathVariable("id")Long id, Model model){
    System.out.println(id);
    Joy name = joyService.findName(id);
    model.addAttribute("names", name);
    return "names/displayName.jsp";
  }

  
  @RequestMapping("/names/{id}/edit")
  public String showOne(@PathVariable("id")Long id, Model model, HttpSession session){
    // If user isn't logged in, return to login page
  if (session.getAttribute("userId") == null) {
    return "redirect:/";
  }
    Joy names = joyService.findName(id);
    model.addAttribute("thename", names);
    return "names/editName.jsp";
  }


  @PutMapping("/edit/{id}")
  public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("thename") Joy name,  BindingResult result, Model model) {
    if(result.hasErrors()){
      model.addAttribute("thename", name);
      return "names/editName.jsp";

    }else{
          joyService.updateName(name);
          return "redirect:/home";
        }
  }

}