package project.database.hospital.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.database.hospital.domain.Treatment;
import project.database.hospital.dto.LoginMember;
import project.database.hospital.service.TreatmentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NurseController {

    private final TreatmentService treatmentService;

    @GetMapping("/nurse")
    public String getTreatment(Model model,
                                 HttpServletRequest request) {
        if (request.getSession().getAttribute("loginMember") == null) {
            return "redirect:/login";
        }
        LoginMember loginMember = (LoginMember) request.getSession().getAttribute("loginMember");

        if (loginMember.getRole().equals("nurse")) {
            List<Treatment> treatments = treatmentService.findByNurseId(loginMember.getId());
            model.addAttribute("treatments", treatments);
            if (request.getSession().getAttribute("searchResults") != null) {
                List<Treatment> searchResults = (List<Treatment>) request.getSession().getAttribute("searchResults");
                model.addAttribute("searchResults", searchResults);
            }
            return "treatment";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/api/treatment")
    public String updateTreatment(
            @RequestParam("id") Integer id,
            @RequestParam("detail") String detail,
            @RequestParam("datetime") String phoneNumber,
            @RequestParam("action") String action) {

        if ("update".equals(action)) {
            treatmentService.updateTreatment(id, detail, phoneNumber);

        } else if ("delete".equals(action)) {
            System.out.printf("deleteTreatment() 실행\n");
            treatmentService.deleteTreatment(id);
        }

        return "redirect:/nurse";
    }

    @PostMapping("/api/addTreatment")
    public String addTreatment(
            @RequestParam("name") String name,
            @RequestParam("detail") String detail,
            HttpServletRequest request) {

        System.out.printf("name: %s, detail: %s\n", name, detail);
        LoginMember loginMember = (LoginMember) request.getSession().getAttribute("loginMember");
        treatmentService.addTreatment(loginMember.getId(), name, detail);

        return "redirect:/nurse";
    }

    @PostMapping("/api/searchTreatment")
    public String searchPatient(@RequestParam("patientName") String patientName,
                                HttpServletRequest request) {
        System.out.printf("patientName: %s\n", patientName);
        List<Treatment> searchResults = treatmentService.findByPatientName(patientName);

        for (Treatment treatment : searchResults) {
            System.out.printf("treatment: %s\n", treatment.getPatient().getName());
        }

        // 세션에 검색 결과 저장
        HttpSession session = request.getSession();
        session.setAttribute("searchResults", searchResults);

        return "redirect:/nurse";
    }

}
