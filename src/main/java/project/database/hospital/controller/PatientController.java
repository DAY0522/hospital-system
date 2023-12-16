package project.database.hospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.database.hospital.domain.Reservation;
import project.database.hospital.dto.LoginMember;
import project.database.hospital.service.ReservationService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final ReservationService reservationService;

    @GetMapping("/patient")
    public String getReservation(Model model,
                                 HttpServletRequest request) {
        if (request.getSession().getAttribute("loginMember") == null) {
            return "redirect:/login";
        }
        LoginMember loginMember = (LoginMember) request.getSession().getAttribute("loginMember");

        if (loginMember.getRole().equals("patient")) {
            List<Reservation> reservations = reservationService.findByPatientId(loginMember.getId());
            model.addAttribute("reservations", reservations);
            if (request.getSession().getAttribute("searchResults") != null) {
                List<Reservation> searchResults = (List<Reservation>) request.getSession().getAttribute("searchResults");
                model.addAttribute("searchResults", searchResults);
            }
            return "reservation";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/api/reservation")
    public String updateReservation(
            @RequestParam("id") Integer id) {

        reservationService.deleteReservation(id);

        return "redirect:/patient";
    }

    @PostMapping("/api/addReservation")
    public String addReservation(
            @RequestParam("datetime") String datetime,
            @RequestParam("departmentName") String departmentName,
            HttpServletRequest request) {

        System.out.printf("datetime: %s, departmentName: %s\n", datetime, departmentName);
        LoginMember loginMember = (LoginMember) request.getSession().getAttribute("loginMember");
        reservationService.addReservation(loginMember.getId(), departmentName, datetime);

        return "redirect:/patient";
    }
}
