package edu.app.server.model;

/**
 * Son los estados en los que pueden estar las tareas.
 */
public enum State {
    /**
     * Significa que la tarea aun esta en proceso.
     */
    IN_PROCESS,
    /**
     * Significa que la tarea esta como pendiente.
     */
    PENDING,
    /**
     * Significa que la tarea esta suspendida.
     */
    SUSPENDED,
    /**
     * Significa que la tarea termino.
     */
    TERMINATED
}
