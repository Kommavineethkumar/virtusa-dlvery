package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.DeliveryReport;
import com.virtusa.dlvery.exceptions.DeliveryReportNotFoundException;
import com.virtusa.dlvery.repositories.DeliveryReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryReportServiceImpl implements DeliveryReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryReportServiceImpl.class);

    @Autowired
    private DeliveryReportRepository deliveryReportRepository;

    @Override
    public List<DeliveryReport> getAllDeliveryReports() {
        LOGGER.info("Fetching all delivery reports");
        return deliveryReportRepository.findAll();
    }

    @Override
    public DeliveryReport getDeliveryReportById(UUID reportId) {
        LOGGER.info("Fetching delivery report with ID: {}", reportId);
        return deliveryReportRepository.findById(reportId)
                .orElseThrow(() -> new DeliveryReportNotFoundException("Delivery report not found with ID: " + reportId));
    }

    @Override
    public List<DeliveryReport> getDeliveryReportsByDeliveryId(UUID deliveryId) {
        LOGGER.info("Fetching delivery reports by delivery ID: {}", deliveryId);
        return deliveryReportRepository.findByDeliveryID(deliveryId);
    }

    @Override
    public List<DeliveryReport> getDeliveryReportsByDeliveryDate(LocalDate deliveryDate) {
        LOGGER.info("Fetching delivery reports by delivery date: {}", deliveryDate);
        return deliveryReportRepository.findByDeliveryDate(deliveryDate);
    }

    @Override
    public List<DeliveryReport> getDeliveryReportsByDeliveryIdAndDeliveryDate(UUID deliveryId, LocalDate deliveryDate) {
        LOGGER.info("Fetching delivery reports by delivery ID {} and date: {}", deliveryId, deliveryDate);
        return deliveryReportRepository.findByDeliveryIDAndDeliveryDate(deliveryId, deliveryDate);
    }

    @Override
    public DeliveryReport createDeliveryReport(DeliveryReport deliveryReport) {
        LOGGER.info("Creating a new delivery report");
        return deliveryReportRepository.save(deliveryReport);
    }

    @Override
    public DeliveryReport updateDeliveryReport(UUID reportId, DeliveryReport deliveryReport) {
        LOGGER.info("Updating delivery report with ID: {}", reportId);
        DeliveryReport existingReport = deliveryReportRepository.findById(reportId)
                .orElseThrow(() -> new DeliveryReportNotFoundException("Delivery report not found with ID: " + reportId));

        // Update delivery report details
        existingReport.setDeliveryID(deliveryReport.getDeliveryID());
        existingReport.setDamagedGoods(deliveryReport.getDamagedGoods());
        existingReport.setPastPendingDeliveries(deliveryReport.getPastPendingDeliveries());
        existingReport.setDeliveryDate(deliveryReport.getDeliveryDate());

        return deliveryReportRepository.save(existingReport);
    }

    @Override
    public void deleteDeliveryReport(UUID reportId) {
        LOGGER.info("Deleting delivery report with ID: {}", reportId);
        deliveryReportRepository.deleteById(reportId);
    }
}
