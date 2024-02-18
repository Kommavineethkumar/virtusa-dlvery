package com.virtusa.dlvery.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery_report_tbl")
public class DeliveryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reportID;

    @NotNull(message = "DeliveryID is required")
    private UUID deliveryID;

    @OneToMany
    private LinkedList<DamagedGoods> damagedGoods;

    @OneToMany
    private LinkedList<PastPendingDelivery> pastPendingDeliveries;

    @NotNull(message = "DeliveryDate is required")
    private LocalDate deliveryDate;

    // Constructor without ID
    public DeliveryReport(UUID deliveryID, LinkedList<DamagedGoods> damagedGoods,
                          LinkedList<PastPendingDelivery> pastPendingDeliveries, LocalDate deliveryDate) {
        this.deliveryID = deliveryID;
        this.damagedGoods = damagedGoods;
        this.pastPendingDeliveries = pastPendingDeliveries;
        this.deliveryDate = deliveryDate;
    }
}
