package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.DeliveryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeliveryReportRepository extends JpaRepository<DeliveryReport, UUID> {

    List<DeliveryReport> findByDeliveryID(UUID deliveryID);

    List<DeliveryReport> findByDeliveryDate(LocalDate deliveryDate);

    List<DeliveryReport> findByDeliveryIDAndDeliveryDate(UUID deliveryID, LocalDate deliveryDate);

    List<DeliveryReport> findByDamagedGoodsIsNotEmpty();

    List<DeliveryReport> findByPastPendingDeliveriesIsNotEmpty();

    List<DeliveryReport> findByDeliveryDateAfter(LocalDate deliveryDate);

    List<DeliveryReport> findByDeliveryDateBefore(LocalDate deliveryDate);

    List<DeliveryReport> findByDeliveryDateBetween(LocalDate startDate, LocalDate endDate);

    // Add more custom query methods as needed
}
