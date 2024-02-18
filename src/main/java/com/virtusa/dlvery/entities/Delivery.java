package com.virtusa.dlvery.entities;

import com.virtusa.dlvery.enums.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery_tbl")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID deliveryID;

    @NotNull(message = "ProductID is required")
    @ManyToOne
    private Products product;

    @NotNull(message = "AgentID is required")
    @ManyToOne
    private DeliveryAgent deliveryAgent;

    @NotBlank(message = "CustomerName is required")
    @Size(min = 1, max = 255, message = "CustomerName must be between 1 and 255 characters")
    private String customerName;

    private String signature;

    @NotNull(message = "DeliveryStatus is required")
    private DeliveryStatus deliveryStatus;

    @NotNull(message = "DeliveryDate is required")
    private LocalDate deliveryDate;

    private String missedDeliveryReason;

    private String returnReason;

    // Constructor without ID
    public Delivery(Products product, DeliveryAgent deliveryAgent, String customerName, String signature,
                    DeliveryStatus deliveryStatus, LocalDate deliveryDate, String missedDeliveryReason, String returnReason) {
        this.product = product;
        this.deliveryAgent = deliveryAgent;
        this.customerName = customerName;
        this.signature = signature;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.missedDeliveryReason = missedDeliveryReason;
        this.returnReason = returnReason;
    }
}
