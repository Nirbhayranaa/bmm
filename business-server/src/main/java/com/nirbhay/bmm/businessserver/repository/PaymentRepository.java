package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Payment;
import com.nirbhay.bmm.model.bs.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBooking_Id(Long id);
}
