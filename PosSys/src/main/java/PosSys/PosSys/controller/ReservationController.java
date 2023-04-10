package PosSys.PosSys.controller;

import PosSys.PosSys.domain.*;
import PosSys.PosSys.service.MemberService;
import PosSys.PosSys.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;

    public ReservationController(ReservationService reservationService, MemberService memberService) {
        this.reservationService = reservationService;
        this.memberService = memberService;
    }

    @GetMapping("/reservation/{tableid}/new")
    public String createForm(@PathVariable("tableid") Long tableid , Model model, HttpSession session) {
        String memberId = (String) session.getAttribute("id");
        Member member = memberService.findMemberById(memberId);
        System.out.println(session.getAttribute("id"));
        model.addAttribute("name", member.getName());
        model.addAttribute("phone", member.getPhoneNumber());
        model.addAttribute("tables", tableid);
        return "/reservation/reservationForm";
    }

    @PostMapping("/reservation/reservationnew")
    public String create(@RequestParam("tableid") Long tableid,
                         @RequestParam("name") String name,
                         @RequestParam("people") Integer people,
                         @RequestParam("phone") String phone,
                         HttpSession session) {

        String memberId = (String) session.getAttribute("id");
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
        Member member = memberService.findMemberById(memberId);

        List<Reservation> existingReservations = reservationService.getReservationsByMemberAndRestaurant(member, restaurant);// 멤버 id, 식당id 로 예약내력찾음.
        if (!existingReservations.isEmpty()) {
            return "redirect:/reservation/reservationcheck";
        }

        Reservation reservation = new Reservation();

        System.out.println(tableid +"/"+ name +"/"+ people +"/"+phone +"/"+ restaurant.getId() +"/"+ member.getId());
        reservation.setTableNumber(tableid);
        reservation.setPeople(people);
        reservation.setReservationstatus(Reservationstatus.NOTRESERVED);
        reservation.setRestaurant(restaurant);
        reservation.setMember(member);
        reservationService.saveReservation(reservation);

        return "/home";

    }
    @PostMapping("/reservationstatus")
    public ResponseEntity sendreservation (@RequestParam("tableid") Long tableid,
                                  @RequestParam("name") String name,
                                  @RequestParam("people") Integer people,
                                  @RequestParam("phone") String phone,
                                  HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        response.put("name", name);
        response.put("people", people);
        response.put("phone", phone);
        response.put("tableid", tableid);

        // HTTP 요청 처리 결과와 함께 JSON 데이터 전송
        return ResponseEntity.ok(response);
    }


    @GetMapping("/reservation/reservationcheck")
    public String list(Model model, HttpSession session) {
        String memberId = (String) session.getAttribute("id");
        Member member = memberService.findMemberById(memberId);
        List<Reservation> reservations = reservationService.getReservationsByMember(member);
        model.addAttribute("reservations", reservations);
        return "reservation/reservationList";
    }

}
/*
    @PostMapping("/reservation/reservationnew")     // 클라이언트로 보내는거.
    public ResponseEntity<?> create(@RequestParam("tableid") Long tableid,
                                    @RequestParam("name") String name,
                                    @RequestParam("people") Integer people,
                                    @RequestParam("phone") String phone,
                                    HttpSession session) {

        Map<String, Object> response = new HashMap<>();
        response.put("name", name);
        response.put("people", people);
        response.put("phone", phone);
        response.put("tableid", tableid);

        return ResponseEntity.ok(response);
    }
*/



/*
    @GetMapping("/{reservationId}/approve")
    public String approve(@PathVariable Long reservationId) {
        reservationService.approveReservation(reservationId);
        return "redirect:/reservation/list";
    }

    @GetMapping("/{reservationId}/cancel")
    public String cancel(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/reservation/list";
    }
}
    @PostMapping("/new")
    public String create(@RequestParam("tableid") Long tableid,
                         HttpSession session, Restaurant restaurant) {
        Member member = (Member) session.getAttribute("id");
        Reservation reservation = new Reservation();
        reservation.setTableNumber(tableid);
        reservation.setPeople(0);
        reservation.setReservationstatus(Reservationstatus.NOTRESERVED);
        reservation.setRestaurant(restaurant);
        reservation.setMember(member);
        reservationService.saveReservation(reservation);
        return "redirect:/reservation/list";
    }
*/