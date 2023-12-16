package project.database.hospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.database.hospital.dto.DoctorResponseDto;
import project.database.hospital.dto.LoginMember;
import project.database.hospital.dto.NurseResponseDto;
import project.database.hospital.service.DoctorService;
import project.database.hospital.service.NurseService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final DoctorService doctorService;
    private final NurseService nurseService;

    @GetMapping("/admin")
    public String admin(Model model,
                        HttpServletRequest request) {

        if (request.getSession().getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        LoginMember loginMember = (LoginMember) request.getSession().getAttribute("loginMember");
        if (loginMember.getRole().equals("admin")) {
            List<DoctorResponseDto> doctors = doctorService.getDoctors();
            model.addAttribute("doctors", doctors);

            List<NurseResponseDto> nurses = nurseService.getNurses();
            model.addAttribute("nurses", nurses);

            return "admin";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/api/doctor")
    public String updateDoctor(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("departmentName") String departmentName,
            @RequestParam("action") String action) {

        System.out.printf("id: %d, name: %s, address: %s, phoneNumber: %s, action: %s\n",
                id, name, address, phoneNumber, action);
        if ("update".equals(action)) {
            System.out.printf("updateDoctor() 실행\n");
            doctorService.updateDoctor(id, name, address, phoneNumber, departmentName);
        } else if ("delete".equals(action)) {
            System.out.printf("deleteDoctor() 실행\n");
            // Use DoctorService to delete the doctor by ID
            doctorService.deleteDoctor(id);
        }

        return "redirect:/admin";
    }

    @PostMapping("/api/nurse")
    public String updateNurse(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("departmentName") String departmentName,
            @RequestParam("action") String action) {

        System.out.printf("id: %d, name: %s, address: %s, phoneNumber: %s, action: %s\n",
                id, name, address, phoneNumber, action);
        if ("update".equals(action)) {
            System.out.printf("updateNurse() 실행\n");
            nurseService.updateNurse(id, name, address, phoneNumber, departmentName);
        } else if ("delete".equals(action)) {
            System.out.printf("deleteNurse() 실행\n");
            // Use DoctorService to delete the doctor by ID
            nurseService.deleteNurse(id);
        }

        return "redirect:/admin";
    }

    @PostMapping("/api/addDoctor")
    public String addDoctor(@RequestParam("name") String name,
                            @RequestParam("address") String address,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("password") String password,
                            @RequestParam("departmentName") String departmentName) {

        System.out.printf("name: %s, address: %s, phoneNumber: %s, password: %s, departmentName: %s\n",
                name, address, phoneNumber, password, departmentName);

        doctorService.addDoctor(name, address, phoneNumber, password, departmentName);

        return "redirect:/admin";
    }

    @PostMapping("/api/addNurse")
    public String addNurse(@RequestParam("name") String name,
                            @RequestParam("address") String address,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("password") String password,
                            @RequestParam("departmentName") String departmentName) {

        System.out.printf("name: %s, address: %s, phoneNumber: %s, password: %s, departmentName: %s\n",
                name, address, phoneNumber, password, departmentName);

        nurseService.addNurse(name, address, phoneNumber, password, departmentName);

        return "redirect:/admin";
    }
}
