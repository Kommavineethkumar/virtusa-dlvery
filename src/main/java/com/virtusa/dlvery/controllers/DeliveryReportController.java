package com.virtusa.dlvery.controllers;

import com.virtusa.dlvery.entities.DeliveryReport;
import com.virtusa.dlvery.exceptions.DeliveryReportNotFoundException;
import com.virtusa.dlvery.services.DeliveryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delivery-reports")
public class DeliveryReportController {

    @Autowired
    private DeliveryReportService deliveryReportService;

    @GetMapping
    public ResponseEntity<List<DeliveryReport>> getAllDeliveryReports() {
        List<DeliveryReport> deliveryReports = deliveryReportService.getAllDeliveryReports();
        return new ResponseEntity<>(deliveryReports, HttpStatus.OK);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<DeliveryReport> getDeliveryReportById(@PathVariable UUID reportId) {
        try {
            DeliveryReport deliveryReport = deliveryReportService.getDeliveryReportById(reportId);
            return new ResponseEntity<>(deliveryReport, HttpStatus.OK);
        } catch (DeliveryReportNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<DeliveryReport> createDeliveryReport(@RequestBody DeliveryReport deliveryReport) {
        try {
            DeliveryReport createdReport = deliveryReportService.createDeliveryReport(deliveryReport);
            return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<DeliveryReport> updateDeliveryReport(@PathVariable UUID reportId,
                                                               @RequestBody DeliveryReport deliveryReport) {
        try {
            DeliveryReport updatedReport = deliveryReportService.updateDeliveryReport(reportId, deliveryReport);
            return new ResponseEntity<>(updatedReport, HttpStatus.OK);
        } catch (DeliveryReportNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteDeliveryReport(@PathVariable UUID reportId) {
        try {
            deliveryReportService.deleteDeliveryReport(reportId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DeliveryReportNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
