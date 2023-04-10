package PosSys.PosSys.Repository;

import PosSys.PosSys.domain.Member;
import PosSys.PosSys.domain.Reservation;
import PosSys.PosSys.domain.Restaurant;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ReservationRepository {
    private final EntityManager em;

    public ReservationRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(em.find(Reservation.class, id));
    }

    public List<Reservation> findByMember(Member member) {
        return em.createQuery("select r from Reservation r where r.member = :member", Reservation.class)
                .setParameter("member", member)
                .getResultList();
    }

    public List<Reservation> findByMemberAndRestaurant(Member member, Restaurant restaurant) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.member = :member AND r.restaurant = :restaurant", Reservation.class)
                .setParameter("member", member)
                .setParameter("restaurant", restaurant)
                .getResultList();
    }
}
