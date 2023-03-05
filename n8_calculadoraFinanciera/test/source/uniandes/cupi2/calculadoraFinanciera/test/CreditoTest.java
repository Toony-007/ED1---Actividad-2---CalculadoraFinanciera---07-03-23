/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CreditoTest.java 1333 2008-10-15 00:17:33Z jua-gome $
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

import junit.framework.TestCase;
import uniandes.cupi2.calculadoraFinanciera.mundo.Credito;

/**
 * Es la clase que verifica el funcionamiento de la clase Credito
 */
public class CreditoTest extends TestCase
{
    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    /**
     * Cr�dito usado para las clases de prueba
     */
    private Credito credito1;

    /**
     * Cr�dito usado para las clases de prueba
     */
    private Credito credito2;

    // --------------------------------------------------------
    // M�todos
    // --------------------------------------------------------

    /**
     * Se construye un cr�dito nuevo
     */
    private void setupEscenario1( )
    {
        credito1 = new Credito( 100, 12, 0.1, "Homero Simpson", "12345" );
    }

    /**
     * Se construyen dos cr�ditos nuevos
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );

        credito2 = new Credito( 100, 24, 0.1, "Ned Flanders", "54321" );
    }

    /**
     * Verifica el constructor. <br>
     * <b> M�todos a probar: </b> <br>
     * Credito (constructor). <br>
     * <b> Objetivo: </b> Probar que el constructor crea un cr�dito de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un cr�dito los atributos del objeto deben quedar con el valor correcto.
     */
    public void testCredito( )
    {
        setupEscenario1( );

        assertEquals( "El monto no fue inicializado", 100.0, credito1.darMonto( ) );
        assertEquals( "El plazo no fue inicializado", 12, credito1.darPlazo( ) );
        assertEquals( "La tasa no fue inicializada", 0.1, credito1.darTasa( ) );
        assertEquals( "El nombre no fue inicializado", "Homero Simpson", credito1.darNombre( ) );
        assertEquals( "La c�dula no fue inicializada", "12345", credito1.darCedula( ) );

        assertEquals( "La cuota no fue inicializada correctamente", 14.68, credito1.darCuota( ), 0.1 );
    }

    /**
     * Verifica el m�todo darDineroPagadoPorAnio. <br>
     * <b> M�todos a probar: </b> <br>
     * darDineroPagadoPorAnio. <br>
     * <b> Objetivo: </b> Probar que el m�todo retorna el dinero pagado por a�o correcto. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El dinero pagado debe ser igual a la cuota multiplicada por 12.
     */
    public void testDarDineroPagadoPorAnio( )
    {
        setupEscenario1( );

        assertEquals( "El total del dinero pagado por a�o es incorrecto", 176.12, credito1.darDineroPagadoPorAnio( 1 ), 0.1 );
    }

    /**
     * Verifica el m�todo darInteresPagadoPorAnio. <br>
     * <b> M�todos a probar: </b> <br>
     * darInteresPagadoPorAnio. <br>
     * <b> Objetivo: </b> Probar que el m�todo retorna el inter�s pagado por a�o correcto. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El inter�s pagado debe ser igual a la suma de los inter�s de los meses.
     */
    public void testDarInteresPagadoPorAnio( )
    {
        setupEscenario2( );

        assertEquals( "El inter�s pagado por a�o es incorrecto", 76.12, credito1.darInteresPagadoPorAnio( 1 ), 0.1 );

        assertEquals( "El inter�s pagado por a�o es incorrecto", 109.40, credito2.darInteresPagadoPorAnio( 1 ), 0.1 );
        assertEquals( "El inter�s pagado por a�o es incorrecto", 57.72, credito2.darInteresPagadoPorAnio( 2 ), 0.1 );
    }

    /**
     * Verifica el m�todo darAbonoPagadoPorAnio. <br>
     * <b> M�todos a probar: </b> <br>
     * darAbonoPagadoPorAnio. <br>
     * <b> Objetivo: </b> Probar que el m�todo retorna el abono pagado por a�o correcto. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El abono pagado debe ser igual a la suma de los abonos de los meses.
     */
    public void testDarAbonoPagadoPorAnio( )
    {
        setupEscenario2( );

        assertEquals( "El abono pagado por a�o es incorrecto", 100.0, credito1.darAbonoPagadoPorAnio( 1 ), 0.1 );

        assertEquals( "El abono pagado por a�o es incorrecto", 24.16, credito2.darAbonoPagadoPorAnio( 1 ), 0.1 );
        assertEquals( "El abono pagado por a�o es incorrecto", 75.84, credito2.darAbonoPagadoPorAnio( 2 ), 0.1 );
    }

    /**
     * Verifica el m�todo equals. <br>
     * <b> M�todos a probar: </b> <br>
     * equals. <br>
     * <b> Objetivo: </b> Probar que el m�todo verifica que dos cr�ditos son iguales.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Debe retornar verdadero
     */
    public void testEquals1( )
    {
        setupEscenario1( );

        Credito credito2 = new Credito( 100, 12, 0.1, "Homero Simpson", "12345" );
        assertTrue( "Los cr�ditos son iguales", credito1.comprarCreditos( credito2 ) );
    }

    /**
     * Verifica el m�todo equals. <br>
     * <b> M�todos a probar: </b> <br>
     * equals. <br>
     * <b> Objetivo: </b> Probar que el m�todo verifica que dos cr�ditos son iguales. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Debe retornar falso
     */
    public void testEquals2( )
    {
        setupEscenario1( );

        Credito credito2 = new Credito( 200, 12, 1.1, "No existe", "No existe" );
        assertFalse( "Los cr�ditos son iguales", credito1.comprarCreditos( credito2 ) );
    }
}
