package edu.app.server.model;

/**
 * Define el tipo de periodicidad, si la cantidad de tiempo que se notificaran en la tarea
 * es decir, si seran dias, semanas, meses o años.
 */
public enum PeriodicityType {
    /**
     * Define cada cuantos dias seran notificadas las tareas.
     */
    DAY,
    /**
     * Defnie cada cuantas semanas seran notificadas las tareas.
     */
    WEEK,
    /**
     * Defnie cada cuantos meses seran notificadas las tareas.
     */
    MONTH,
    /**
     * Defnie cada cuantos años seran notificadas las tareas.
     */
    YEAR
}
