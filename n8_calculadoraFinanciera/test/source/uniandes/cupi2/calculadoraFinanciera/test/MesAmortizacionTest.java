/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: MesAmortizacionTest.java 1328 2008-10-11 05:30:36Z ju-cort1 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_calculadoraFinanciera
 * Autor: Juan Camilo Cort�s Medina - 05-ago-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.calculadoraFinanciera.test;

import uniandes.cupi2.calculadoraFinanciera.mundo.MesAmortizacion;
import junit.framework.TestCase;

/**
 * Esta es la clase para verificar los m�todos de la clase MesAmortizacion
 */
public class MesAmortizacionTest extends TestCase
{
    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    /**
     * Mes usado para los casos de prueba
     */
    private MesAmortizacion mesAmortizacion;

    // --------------------------------------------------------
    // M�todos
    // --------------------------------------------------------

    /**
     * Construye un mes de amortizaci�n
     */
    private void setupEscenario1( )
    {
        mesAmortizacion = new MesAmortizacion( 2, 0.1, 2000.5, 1500000 );
    }

    /**
     * Verifica el constructor. <br>
     * <b> M�todos a probar: </b> <br>
     * MesAmortizacion (constructor). <br>
     * <b> Objetivo: </b> Probar que el constructor crea un mes de amortizaci�n de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un mes de amortizaci�n los atributos del objeto deben quedar con el valor correcto.
     */
    public void testMesAmortizacion( )
    {
        setupEscenario1( );

        assertEquals( "El n�mero del mes no fue inicializado", 2, mesAmortizacion.darNumeroMes( ) );
        assertEquals( "El inter�s a pagar no fue inicializado", 0.1, mesAmortizacion.darInteresAPagar( ) );
        assertEquals( "El abono a capital no fue inicializado", 2000.5, mesAmortizacion.darAbonoACapital( ) );
        assertEquals( "El saldo no fue inicializado", 1500000.0, mesAmortizacion.darSaldoObligacion( ) );
    }

}
