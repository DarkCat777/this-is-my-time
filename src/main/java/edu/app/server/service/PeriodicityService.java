package edu.app.server.service;

import edu.app.server.model.Periodicity;
import edu.app.server.repository.PeriodicityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Es el modulo de servicio que implementa las logicas de negocio o transformaciones a los datos de Periodicity.
 *
 * @author Erick David Carpio Hachiri
 * @see Periodicity
 */
@Log4j2
@Service
public class PeriodicityService {
    /**
     * Es el repositorio de Periodicity.
     *
     * @see PeriodicityRepository
     */
    private final PeriodicityRepository periodicityRepository;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param periodicityRepository Es el repositorio que sera injectado por Spring.
     */
    public PeriodicityService(PeriodicityRepository periodicityRepository) {
        this.periodicityRepository = periodicityRepository;
    }

    /**
     * Obtiene la Periodicity mediante su id, mediante una transacción de solo lectura.
     *
     * @param id Es el id del Periodicity.
     * @return Retorna el valor existe o no existe que es retornado por el repositorio.
     * @see PeriodicityRepository
     */
    @Transactional(readOnly = true)
    public Periodicity getById(Long id) {
        log.info("Buscando periodicidad por su id: " + id);
        return this.periodicityRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las periodicidades, mediante una transacción de solo lectura.
     *
     * @return Son todas las periodicidades realizadas por el repositorio.
     * @see PeriodicityRepository
     */
    @Transactional(readOnly = true)
    public List<Periodicity> getAllPeriodicity() {
        log.info("Obteniendo todas las tareas periodicas.");
        return this.periodicityRepository.findAll();
    }

    /**
     * Guarda la periodicidad, mediante una transacción de escritura.
     *
     * @param periodicity Es la periodicidad a guardar.
     * @return Es la periodicidad con el id asignado en la base de datos.
     * @see PeriodicityRepository
     */
    @Transactional
    public Periodicity savePeriodicity(Periodicity periodicity) {
        log.info("Guardando periodicidad" + periodicity.toString());
        return this.periodicityRepository.save(periodicity);
    }

    /**
     * Actualiza la periodicidad, mediante una transacción de escritura.
     *
     * @param periodicity Es la periodicidad a actualizar.
     * @return Es la periodicidad con el los valores actualizados en la base de datos.
     * @see PeriodicityRepository
     */
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

    /**
     * Elimina la periodicidad, mediante una transacción de escritura.
     *
     * @param periodicity Es la periodicidad a eliminar.
     * @return Es una cadena con la confirnación.
     * @see PeriodicityRepository
     */
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
