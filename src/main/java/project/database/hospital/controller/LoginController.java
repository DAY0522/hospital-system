package project.database.hospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.database.hospital.domain.Doctor;
import project.database.hospital.domain.Nurse;
import project.database.hospital.domain.Patient;
import project.database.hospital.dto.LoginMember;
import project.database.hospital.service.DoctorService;
import project.database.hospital.service.NurseService;
import project.database.hospital.service.PatientService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/login")
public class LoginController {
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final PatientService patientService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam("id") String id,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes,
                        HttpServletRequest request){
        log.info("LoginController.login() 실행");
        log.info("id: {}, password: {}", id, password);

        HttpSession session = request.getSession();
        if (id.equals("admin") && password.equals("1234")) { // 관리자 로그인
            LoginMember loginMember = new LoginMember(0, "admin");
            session.setAttribute("loginMember", loginMember);
            return "redirect:/admin"; // 관리자 메인 페이지로 이동
        }

        try {
            Doctor doctor = doctorService.findDoctorById(Integer.parseInt(id));
            if (doctor != null) { // 의사 로그인
                if (doctor.getPassword().equals(password)){ // 비밀번호가 일치하면
                    LoginMember loginMember = new LoginMember(doctor.getId(), "doctor");
                    session.setAttribute("loginMember", loginMember);
                    return "redirect:/doctor"; // 의사 메인 페이지로 이동
                }
            }

            Nurse nurse = nurseService.findNurseById(Integer.parseInt(id));
            if (nurse != null) { // 간호사 및 환자 로그인
                if (nurse.getPassword().equals(password)) { // 비밀번호가 일치하면
                    LoginMember loginMember = new LoginMember(nurse.getId(), "nurse");
                    session.setAttribute("loginMember", loginMember);
                    return "redirect:/nurse"; // 간호사 메인 페이지로 이동
                }
            }

            Patient patient = patientService.findPatientById(Integer.parseInt(id));
            if (patient != null){ // 환자 로그인
                if (patient.getPassword().equals(password)){ // 비밀번호가 일치하면
                    LoginMember loginMember = new LoginMember(patient.getId(), "patient");
                    session.setAttribute("loginMember", loginMember);
                    return "redirect:/patient"; // 환자 메인 페이지로 이동
                }
            }
        } catch (NumberFormatException e) {
            log.info("아이디가 존재하지 않습니다.");
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/login"; // 로그인 페이지로 이동
        }

        log.info("아이디가 존재하지 않습니다.");
        redirectAttributes.addAttribute("error", "true");
        return "redirect:/login"; // 로그인 페이지로 이동
    }
}
