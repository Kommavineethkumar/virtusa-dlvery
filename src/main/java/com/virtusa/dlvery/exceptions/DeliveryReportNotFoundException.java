package com.virtusa.dlvery.exceptions;

public class DeliveryReportNotFoundException extends RuntimeException {
    public DeliveryReportNotFoundException(String message) {
        super(message);
    }
}