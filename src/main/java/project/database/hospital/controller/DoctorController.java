package project.database.hospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.database.hospital.domain.Examination;
import project.database.hospital.dto.LoginMember;
import project.database.hospital.service.ExaminationService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoctorController {

    private final ExaminationService examinationService;

    @GetMapping("/doctor")
    public String getExamination(Model model,
                                 HttpServletRequest request) {
        if (request.getSession().getAttribute("loginMember") == null) {
            return "redirect:/login";
        }
        LoginMember loginMember = (LoginMember) request.getSession().getAttribute("loginMember");

        if (loginMember.getRole().equals("doctor")) {
            List<Examination> examinations = examinationService.findByDoctorId(loginMember.getId());
            model.addAttribute("examinations", examinations);
            if (request.getSession().getAttribute("searchResults") != null) {
                List<Examination> searchResults = (List<Examination>) request.getSession().getAttribute("searchResults");
                model.addAttribute("searchResults", searchResults);
            }
            return "examination";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/api/examination")
    public String updateExamination(
            @RequestParam("id") Integer id,
            @RequestParam("detail") String detail,
            @RequestParam("datetime") String phoneNumber,
            @RequestParam("action") String action) {

        if ("update".equals(action)) {
            System.out.printf("updateExamination() 실행\n");
            examinationService.updateExamination(id, detail, phoneNumber);

        } else if ("delete".equals(action)) {
            System.out.printf("deleteExamination() 실행\n");
            // Use DoctorService to delete the doctor by ID
            examinationService.deleteExamination(id);
        }

        return "redirect:/doctor";
    }

    @PostMapping("/api/addExamination")
    public String addExamination(
            @RequestParam("id") Integer id,
            @RequestParam("detail") String detail,
            HttpServletRequest request) {

        LoginMember loginMember = (LoginMember) request.getSession().getAttribute("loginMember");
        examinationService.addExamination(loginMember.getId(), id, detail);

        return "redirect:/doctor";
    }

    @PostMapping("/api/searchExamination")
    public String searchPatient(@RequestParam("patientName") String patientName,
                                HttpServletRequest request) {
        List<Examination> searchResults = examinationService.findByPatientName(patientName);

        // 세션에 검색 결과 저장
        HttpSession session = request.getSession();
        session.setAttribute("searchResults", searchResults);

        return "redirect:/doctor";
    }

}
