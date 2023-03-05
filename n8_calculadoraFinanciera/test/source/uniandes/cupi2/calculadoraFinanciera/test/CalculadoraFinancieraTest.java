/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CalculadoraFinancieraTest.java 1365 2008-10-23 00:25:35Z jua-gome $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_calculadoraFinanciera
 * Autor: Juan Camilo Cort�s Medina - 27-ago-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.calculadoraFinanciera.test;

import junit.framework.TestCase;
import uniandes.cupi2.calculadoraFinanciera.mundo.CalculadoraFinanciera;
import uniandes.cupi2.calculadoraFinanciera.mundo.Credito;
import uniandes.cupi2.calculadoraFinanciera.mundo.CreditoYaExisteException;
import uniandes.cupi2.calculadoraFinanciera.mundo.PersistenciaException;
import uniandes.cupi2.calculadoraFinanciera.mundo.ReporteException;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase CalculadoraFinanciera est�n correctamente implementados
 */
public class CalculadoraFinancieraTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private CalculadoraFinanciera calculadoraFinanciera;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva CalculadoraFinanciera vac�a con un archivo de persistencia
     */
    private void setupEscenario1( )
    {
        try
        {
            calculadoraFinanciera = new CalculadoraFinanciera( "./test/data/persistenciaCalculadora1" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debi� generar error al crear una nueva calculadora" );
        }
    }

    /**
     * Construye una nueva CalculadoraFinanciera con los siguientes cr�ditos:<br>
     * (Nombre) - (C�dula) - (Monto) - (Plazo) - (Tasa mensual)<br>
     * Ned - 543 - 100 - 24 - 10 <br>
     * Homero - 123 - 100 - 12 - 10
     */
    private void setupEscenario2( )
    {
        try
        {
            calculadoraFinanciera = new CalculadoraFinanciera( "./test/data/calculadora.dat" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debi� generar error al crear una nueva calculadora" );
        }
    }

    /**
     * Verifica el constructor cuando se inicializa sin datos. <br>
     * <b> M�todos a probar: </b> <br>
     * CalculadoraFinanciera (constructor). <br>
     * <b> Objetivo: </b> Probar que el constructor crea una calculadora de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear una calculadora los atributos del objeto deben quedar con el valor correcto.
     */
    public void testCalculadoraFinanciera( )
    {
        setupEscenario1( );

        assertEquals( "Al inicio no hay cr�ditos en la calculadora", 0, calculadoraFinanciera.darCreditos( ).size( ) );
        assertEquals( "El tama�o de la contenedora de cr�ditos es incorrecta", 0, calculadoraFinanciera.darCreditos( ).size( ) );
    }

    /**
     * Verifica el m�todo crearCredito. <br>
     * <b> M�todos a probar: </b> <br>
     * crearCredtio. <br>
     * <b> Objetivo: </b> Probar que el m�todo inserta un cr�dito correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un cr�dito que no existe se inserta correctamente.
     */
    public void testCrearCredito1( )
    {
        setupEscenario1( );

        try
        {
            calculadoraFinanciera.crearCredito( 100, 12, 0.1, "Homero Simpson", "12345" );
            assertEquals( "El cr�dito no fue insertado en la contenedora", 1, calculadoraFinanciera.darCreditos( ).size( ) );
        }
        catch( CreditoYaExisteException e )
        {
            fail( "El cr�dito no fue ingresado y el cliente no exist�a con anterioridad" );
        }

        try
        {
            calculadoraFinanciera.crearCredito( 200, 24, 0.2, "Ned Flanders", "54321" );
            assertEquals( "El cr�dito no fue insertado en la contenedora", 2, calculadoraFinanciera.darCreditos( ).size( ) );
        }
        catch( CreditoYaExisteException e )
        {
            fail( "El cr�dito no fue ingresado y el cliente no exist�a con anterioridad" );
        }
    }

    /**
     * Verifica el m�todo crearCredito. <br>
     * <b> M�todos a probar: </b> <br>
     * crearCredtio. <br>
     * <b> Objetivo: </b> Probar que el m�todo no inserta cr�ditos con c�dulas repetidas. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un cr�dito que ya existe no es insertado.
     */
    public void testCrearCredito2( )
    {
        setupEscenario1( );

        try
        {
            calculadoraFinanciera.crearCredito( 100, 12, 0.1, "Homero Simpson", "12345" );
            assertEquals( "El cr�dito no fue insertado en la contenedora", 1, calculadoraFinanciera.darCreditos( ).size( ) );
        }
        catch( CreditoYaExisteException e )
        {
            fail( "El cr�dito no fue ingresado y el cliente no exist�a con anterioridad" );
        }

        try
        {
            calculadoraFinanciera.crearCredito( 100, 12, 0.1, "Homero Simpson", "12345" );
            fail( "El cr�dito fue agregado pese a que el cliente ya exist�a" );
        }
        catch( CreditoYaExisteException e )
        {
            assertEquals( "El cr�dito no fue insertado en la contenedora", 1, calculadoraFinanciera.darCreditos( ).size( ) );
        }

    }

    /**
     * Verifica el m�todo darTotalDineroPagado. <br>
     * <b> M�todos a probar: </b> <br>
     * darTotalDineroPagado, crearCredito. <br>
     * <b> Objetivo: </b> Probar que el m�todo retorna el total de dinero pagado correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El valor retornado corresponde al total de dinero pagado para el a�o especificado.
     */
    public void testDarTotalDineroPagado( )
    {
        setupEscenario1( );

        try
        {
            Credito credito1 = calculadoraFinanciera.crearCredito( 100, 12, 0.1, "Homero Simpson", "12345" );
            Credito credito2 = calculadoraFinanciera.crearCredito( 100, 24, 0.1, "Ned Flanders", "54321" );

            assertEquals( "El total de dinero pagado es incorrecto", 176.12, calculadoraFinanciera.darTotalDineroPagado( credito1, 1 ), 0.1 );

            assertEquals( "El total de dinero pagado es incorrecto", 133.56, calculadoraFinanciera.darTotalDineroPagado( credito2, 1 ), 0.1 );
            assertEquals( "El total de dinero pagado es incorrecto", 133.56, calculadoraFinanciera.darTotalDineroPagado( credito2, 2 ), 0.1 );
        }
        catch( CreditoYaExisteException e )
        {
            fail( "El cr�dito no fue ingresado y el cliente no exist�a con anterioridad" );
        }
    }

    /**
     * Verifica el m�todo darTotalInteresPagado. <br>
     * <b> M�todos a probar: </b> <br>
     * darTotalInteresPagado, crearCredito. <br>
     * <b> Objetivo: </b> Probar que el m�todo retorna el total de inter�s pagado correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El valor retornado corresponde al total de inter�s pagado para el a�o especificado.
     */
    public void testDarTotalInteresPagado( )
    {
        setupEscenario1( );

        try
        {
            Credito credito1 = calculadoraFinanciera.crearCredito( 100, 12, 0.1, "Homero Simpson", "12345" );
            Credito credito2 = calculadoraFinanciera.crearCredito( 100, 24, 0.1, "Ned Flanders", "54321" );

            assertEquals( "El total de inter�s pagado es incorrecto", 76.12, calculadoraFinanciera.darTotalInteresPagado( credito1, 1 ), 0.1 );

            assertEquals( "El total de inter�s pagado es incorrecto", 109.40, calculadoraFinanciera.darTotalInteresPagado( credito2, 1 ), 0.1 );
            assertEquals( "El total de inter�s pagado es incorrecto", 57.72, calculadoraFinanciera.darTotalInteresPagado( credito2, 2 ), 0.1 );
        }
        catch( CreditoYaExisteException e )
        {
            fail( "El cr�dito no fue ingresado y el cliente no exist�a con anterioridad" );
        }
    }

    /**
     * Verifica el m�todo darTotalAbonoPagado. <br>
     * <b> M�todos a probar: </b> <br>
     * darTotalAbonoPagado, crearCredito. <br>
     * <b> Objetivo: </b> Probar que el m�todo retorna el total de abono pagado correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El valor retornado corresponde al total de abono pagado para el a�o especificado.
     */
    public void testDarTotalAbonoPagado( )
    {
        setupEscenario1( );

        try
        {
            Credito credito1 = calculadoraFinanciera.crearCredito( 100, 12, 0.1, "Homero Simpson", "12345" );
            Credito credito2 = calculadoraFinanciera.crearCredito( 100, 24, 0.1, "Ned Flanders", "54321" );

            assertEquals( "El total de abono pagado es incorrecto", 100.02, calculadoraFinanciera.darTotalAbonoPagado( credito1, 1 ), 0.1 );

            assertEquals( "El total de abono pagado es incorrecto", 24.16, calculadoraFinanciera.darTotalAbonoPagado( credito2, 1 ), 0.1 );
            assertEquals( "El total de abono pagado es incorrecto", 75.84, calculadoraFinanciera.darTotalAbonoPagado( credito2, 2 ), 0.1 );
        }
        catch( CreditoYaExisteException e )
        {
            fail( "El cr�dito no fue ingresado y el cliente no exist�a con anterioridad" );
        }
    }

    /**
     * Verifica el m�todo generarReporteAmortizacionSegunAnio. <br>
     * <b> M�todos a probar: </b> <br>
     * generarReporteAmortizacionSegunAnio, crearCredito. <br>
     * <b> Objetivo: </b> Probar que el m�todo genera el reporte sin problemas. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El reporte se gener� sin problemas.
     */
    public void testGenerarReporteAmortizacionSegunAnio1( )
    {
        setupEscenario1( );

        try
        {
            calculadoraFinanciera.crearCredito( 100, 12, 0.1, "Homero Simpson", "12345" );
            calculadoraFinanciera.crearCredito( 100, 24, 0.1, "Ned Flanders", "54321" );
        }
        catch( CreditoYaExisteException e )
        {
            fail( "El cr�dito no fue ingresado y el cliente no exist�a con anterioridad" );
        }

        try
        {
            calculadoraFinanciera.generarReporteAmortizacionSegunAnio( "./test/data/datosCredito.txt", "./test/data/reporte.txt" );
        }
        catch( ReporteException e )
        {
            fail( "No debi� ocurrir error al generar el reporte" );
        }
    }

    /**
     * Verifica el m�todo generarReporteAmortizacionSegunAnio. <br>
     * <b> M�todos a probar: </b> <br>
     * generarReporteAmortizacionSegunAnio, crearCredito. <br>
     * <b> Objetivo: </b> Probar que el m�todo no genera el reporte si el archivo de entrada no existe. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Excepci�n lanzada
     */
    public void testGenerarReporteAmortizacionSegunAnio2( )
    {
        setupEscenario1( );

        try
        {
            calculadoraFinanciera.generarReporteAmortizacionSegunAnio( "no existe", "no existe" );
            fail( "Debi� fallar al generar el reporte" );
        }
        catch( ReporteException e )
        {
        }
    }

}