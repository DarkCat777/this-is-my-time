package edu.app.server.service;

import edu.app.server.model.Periodicity;
import edu.app.server.repository.PeriodicityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class PeriodicityService {
    private final PeriodicityRepository periodicityRepository;

    public PeriodicityService(PeriodicityRepository periodicityRepository) {
        this.periodicityRepository = periodicityRepository;
    }

    @Transactional(readOnly = true)
    public Periodicity getById(Long id) {
        log.info("Buscando periodicidad por su id: " + id);
        return this.periodicityRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Periodicity> getAllPeriodicity() {
        log.info("Obteniendo todas las tareas periodicas.");
        return this.periodicityRepository.findAll();
    }

    @Transactional
    public Periodicity savePeriodicity(Periodicity periodicity) {
        log.info("Guardando periodicidad" + periodicity.toString());
        return this.periodicityRepository.save(periodicity);
    }

    @Transactional
    public Periodicity updatePeriodicity(Periodicity periodicity) {
        log.info("Buscando periodicidad.");
        Periodicity oldPeriodicity = this.periodicityRepository.findById(periodicity.getId()).orElse(null);
        if (oldPeriodicity != null) {
            log.info("Actualizando periodicidad: " + periodicity.toString());
            oldPeriodicity.setInitDate(periodicity.getInitDate());
            oldPeriodicity.setInitTime(periodicity.getInitTime());
            oldPeriodicity.setPeriodicityType(periodicity.getPeriodicityType());
            oldPeriodicity.setQuantityOfPeriodicityType(periodicity.getQuantityOfPeriodicityType());
            return this.periodicityRepository.save(oldPeriodicity);
        } else {
            log.info("No se encontro una periodicidad previa, no se guardan los datos.");
            return null;
        }
    }

    @Transactional
    public String deletePeriodicity(Periodicity periodicity) {
        Periodicity oldPeriodicity = this.periodicityRepository.findById(periodicity.getId()).orElse(null);
        if (oldPeriodicity == null) {
            log.info("No se borro la periodicidad.");
            return "Unsuccessful delete periodicity";
        } else {
            log.info("Borrando la periodicidad: " + oldPeriodicity.toString());
            this.periodicityRepository.delete(oldPeriodicity);
            return "Success delete periodicity";
        }
    }
}
