package one.digitalinnovation.cloudparking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.repository.ParkingRepository;

@Service
public class ParkingService {
    
    @Autowired
    private ParkingRepository parkingRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }
    
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        return parkingRepository.save(parkingCreate);
    }
    
    @Transactional
    public void delete(String id) {
         findById(id);
         parkingRepository.deleteById(id);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setLicense(parkingCreate.getLicense());
        parking.setModel(parkingCreate.getModel());
        parking.setState(parkingCreate.getState());
        return parkingRepository.save(parking);
    }

    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        return parkingRepository.save(parking);
    }
}
