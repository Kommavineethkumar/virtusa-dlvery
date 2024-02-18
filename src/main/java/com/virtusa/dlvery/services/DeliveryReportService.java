package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.DeliveryReport;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DeliveryReportService {
    List<DeliveryReport> getAllDeliveryReports();
    DeliveryReport getDeliveryReportById(UUID reportId);
    List<DeliveryReport> getDeliveryReportsByDeliveryId(UUID deliveryId);
    List<DeliveryReport> getDeliveryReportsByDeliveryDate(LocalDate deliveryDate);
    List<DeliveryReport> getDeliveryReportsByDeliveryIdAndDeliveryDate(UUID deliveryId, LocalDate deliveryDate);
    DeliveryReport createDeliveryReport(DeliveryReport deliveryReport);
    DeliveryReport updateDeliveryReport(UUID reportId, DeliveryReport deliveryReport);
    void deleteDeliveryReport(UUID reportId);
}
