/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ReporteException.java 1281 2008-08-31 12:43:47Z ju-cort1 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_calculadoraFinanciera
 * Autor: Juan Camilo Cort�s Medina - 05-ago-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.calculadoraFinanciera.mundo;

/**
 * Esta excepci�n se lanza cuando se presenta un error al generar el reporte de amortizaci�n. <br>
 * El mensaje asociado con la excepci�n debe describir el problema que se present�.
 */
public class ReporteException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n con el mensaje que se pasa como par�metro y que describe la causa del problema
     * @param causa el mensaje que describe el problema
     */
    public ReporteException( String causa )
    {
        super( causa );
    }
}
