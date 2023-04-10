package PosSys.PosSys.service;

import PosSys.PosSys.Repository.MemberRepository;
import PosSys.PosSys.Repository.ReservationRepository;
import PosSys.PosSys.domain.Member;
import PosSys.PosSys.domain.Reservation;
import PosSys.PosSys.domain.Reservationstatus;
import PosSys.PosSys.domain.Restaurant;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;



    @Transactional(readOnly = false)
    public void reserve(Long memberId, Long tableId, String name, String phone, int people) {

        Member member = memberRepository.findById(memberId).get();
        Reservation reservation = new Reservation();

        reservation.setMember(member);
        reservation.setPeople(people);
        reservation.setTableNumber(tableId);
        reservation.setReservationstatus(Reservationstatus.NOTRESERVED);

        reservationRepository.save(reservation);
    }
    @Transactional(readOnly = false)
    public void saveReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getReservationsByMember(Member member) {
        return reservationRepository.findByMember(member);
    }

    public List<Reservation> getReservationsByMemberAndRestaurant(Member member, Restaurant restaurant) {
        return reservationRepository.findByMemberAndRestaurant(member, restaurant);
    }
}
