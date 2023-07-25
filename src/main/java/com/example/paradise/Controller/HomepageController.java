package com.example.paradise.Controller;

import com.example.paradise.Services.UserService;
import com.example.paradise.UserPojo.*;
import com.example.paradise.entity.Booking;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomepageController {
    private  final UserService userService;


    @GetMapping("")
    public String geHomepage(Model model, Principal principal, Authentication authentication) {

        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/list";
                }
            }
        }
        model.addAttribute("feedback", new FeedbackPojo());

        return ("homepage");
    }




    @GetMapping("/rooms")
    public String getRooms(Model model, Principal principal) {
        return "rooms";
    }

    @GetMapping("/cabbook")
    public String getCabBook() {
        return "redirect:/cab/book";
    }



    @GetMapping("/profile")
    public String getUserProfile (Integer id,Model model, Principal principal) {
//        User user= userService.getById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        model.addAttribute("update", new UserPojo());
        model.addAttribute("info",userService.findByEmail(principal.getName()));
        return "user_profile";
    }
    @PostMapping("/updateUser")
    public String updateUser(@Valid UserPojo userpojo) {
        userService.save(userpojo);
        return "redirect:/homepage/profile";
    }
    @GetMapping("/appledjobs/{id}")
    public  String getBookingById(@PathVariable("id") Integer id, Model model, Principal principal){
        List<Booking> bookings= userService.findApplicationById(id);
        model.addAttribute("applicationData",bookings);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        return "seeHistory";

    }
    @GetMapping("/deletebooking/{id}")
    public String deleteUserBooking(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/homepage/profile";
    }


}
