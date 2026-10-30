package org.route.repository;

import jakarta.annotation.PostConstruct;
import org.route.model.Governorate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class ZoneRepository {

    private final HashMap<String, Governorate> governorates = new HashMap<>();

    @PostConstruct
    public void init() {
        Governorate g = new Governorate("Cairo", org.route.enums.ShippingZone.GREATER_CAIRO);
        governorates.put(g.governorate(), g);
         g = new Governorate("Giza", org.route.enums.ShippingZone.GREATER_CAIRO);
        governorates.put(g.governorate(), g);
        g = new Governorate("Qalyubia", org.route.enums.ShippingZone.GREATER_CAIRO);
        governorates.put(g.governorate(), g);


        g = new Governorate("Alexandria", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);
        g = new Governorate("Beheira", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);
        g = new Governorate("Kafr El Sheikh", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);
         g = new Governorate("Dakahlia", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);
         g = new Governorate("Damietta", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);
         g = new Governorate("Gharbia", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);
         g = new Governorate("Monufia", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);
         g = new Governorate("Sharqia", org.route.enums.ShippingZone.ALEX_DELTA);
        governorates.put(g.governorate(), g);

         g = new Governorate("Port Said", org.route.enums.ShippingZone.CANAL);
        governorates.put(g.governorate(), g);
         g = new Governorate("Ismailia", org.route.enums.ShippingZone.CANAL);
        governorates.put(g.governorate(), g);
         g = new Governorate("Suez", org.route.enums.ShippingZone.CANAL);
        governorates.put(g.governorate(), g);

         g = new Governorate("Faiyum", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);
         g = new Governorate("Beni Suef", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);
         g = new Governorate("Minya", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);
         g = new Governorate("Asyut", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);
         g = new Governorate("Sohag", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);
         g = new Governorate("Qena", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);
         g = new Governorate("Luxor", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);
         g = new Governorate("Aswan", org.route.enums.ShippingZone.UPPER_EGYPT);
        governorates.put(g.governorate(), g);

         g = new Governorate("Matrouh", org.route.enums.ShippingZone.REMOTE);
        governorates.put(g.governorate(), g);
         g = new Governorate("North Sinai", org.route.enums.ShippingZone.REMOTE);
        governorates.put(g.governorate(), g);
         g = new Governorate("South Sinai", org.route.enums.ShippingZone.REMOTE);
        governorates.put(g.governorate(), g);
         g = new Governorate("Red Sea", org.route.enums.ShippingZone.REMOTE);
        governorates.put(g.governorate(), g);
         g = new Governorate("New Valley", org.route.enums.ShippingZone.REMOTE);
        governorates.put(g.governorate(), g);
    }

    public Optional<Governorate> findGovernorate(String governorate) {
        return Optional.ofNullable(governorates.get(governorate.toLowerCase()));
    }


}
