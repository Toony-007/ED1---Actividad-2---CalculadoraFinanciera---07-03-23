/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: MesAmortizacion.java 1363 2008-10-22 21:50:11Z jua-gome $
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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Esta clase representa un mes de la amortizaci�n de un cr�dito.<br>
 * <b> inv: </b> <br>
 * numeroMes >= 1 && numeroMes <= 60 <br>
 * interesAPagar >= 0 <br>
 * abonoACapital >= 0 <br>
 * saldoObligacion >= 0 <br>
 */
public class MesAmortizacion implements Serializable
{
    // --------------------------------------------------------
    // Constantes
    // --------------------------------------------------------

    /**
     * Indicador de versi�n para la serializaci�n
     */
    private static final long serialVersionUID = 200L;

    /**
     * Indicador de cantidad de decimales luego de la coma
     */
    private static final int NUM_DECIMALES = 2;

    /**
     * Indicador de cantidad de datos que tiene un mes
     */
    public static final int CANT_DATOS = 4;

    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    /**
     * N�mero del mes
     */
    private int numeroMes;

    /**
     * Valor del inter�s a pagar para ese mes.
     */
    private double interesAPagar;

    /**
     * Amortizaci�n al cr�dito
     */
    private double abonoACapital;

    /**
     * Saldo del cr�dito que queda por pagar
     */
    private double saldoObligacion;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Crea un nuevo mes de amortizaci�n con sus datos b�sicos
     * @param numeroMesP el n�mero del mes.
     * @param interesAPagarP el inter�s a pagar
     * @param abonoACapitalP el abono a capital
     * @param saldoObligacionP el saldo del pr�stamo a�n si pagar
     */
    public MesAmortizacion( int numeroMesP, double interesAPagarP, double abonoACapitalP, double saldoObligacionP )
    {
        numeroMes = numeroMesP;
        interesAPagar = interesAPagarP;
        abonoACapital = abonoACapitalP;
        saldoObligacion = saldoObligacionP;

        verificarInvariante( );
    }

    // --------------------------------------------------------
    // M�todos
    // --------------------------------------------------------

    /**
     * Retorna el numero del mes
     * @return el numero del mes
     */
    public int darNumeroMes( )
    {
        return numeroMes;
    }

    /**
     * Retorna el inter�s a pagar
     * @return el inter�s a pagar
     */
    public double darInteresAPagar( )
    {
        return interesAPagar;
    }

    /**
     * Retorna el abono a capital
     * @return el abono a capital
     */
    public double darAbonoACapital( )
    {
        return abonoACapital;
    }

    /**
     * Retorna el saldo de obligaci�n
     * @return el saldo de obligaci�n
     */
    public double darSaldoObligacion( )
    {
        return saldoObligacion;
    }

    /**
     * Genera el reporte de salida con los datos de este mes. <br>
     * <b> post: </b> Los datos del mes fueron escritos en el reporte de salida <br>
     * @param escritor es el escritor del reporte de salida. escritor != null
     * @throws IOException si ocurre una excepci�n en la escritura del reporte
     */
    public void generarReporteSalida( BufferedWriter escritor ) throws IOException
    {
        escritor.write( "" + numeroMes );
        String espacio = darEspacios( Credito.MES, "" + numeroMes );
        escritor.write( espacio );
        escritor.write( "\t" );

        escritor.write( "" + redondear( interesAPagar ) );
        espacio = darEspacios( Credito.INTERES, "" + redondear( interesAPagar ) );
        escritor.write( espacio );
        escritor.write( "\t" );

        escritor.write( "" + redondear( abonoACapital ) );
        espacio = darEspacios( Credito.ABONO, "" + redondear( abonoACapital ) );
        escritor.write( espacio );
        escritor.write( "\t" );

        escritor.write( "" + redondear( saldoObligacion ) );
        espacio = darEspacios( Credito.SALDO, "" + redondear( saldoObligacion ) );
        escritor.write( espacio );
        escritor.write( "\t" );
    }

    // --------------------------------------------------------
    // M�todos auxiliares
    // --------------------------------------------------------

    /**
     * Retorna una cadena de caracteres para establecer el espacio entre los datos del reporte
     * @param tituloColumna cadena de caracteres del t�tulo de la columna. tituloColumna != null
     * @param dato cadena de caracteres del dato que se quiere escribir. dato != null
     * @return la cadena de caracteres con los espacios
     */
    private String darEspacios( String tituloColumna, String dato )
    {
        String retorno = "";
        int tamTitulo = tituloColumna.length( );
        int tamDato = dato.length( );

        int diferencia = tamTitulo - tamDato;

        for( int i = 0; i < diferencia; i++ )
        {
            retorno += " ";
        }
        return retorno;
    }

    /**
     * Redondea un n�mero real con el n�mero de decimales expl�cito de la clase
     * @param numero es el n�mero real
     * @return el n�mero redondeado
     */
    private double redondear( double numero )
    {
        double aux0 = Math.pow( 10, NUM_DECIMALES );
        double aux = numero * aux0;
        int tmp = ( int )aux;

        return ( double ) ( tmp / aux0 );
    }

    // --------------------------------------------------------
    // Invariantes
    // --------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * numeroMes >= 1 && numeroMes <= 60 <br>
     * interesAPagar >= 0 <br>
     * abonoACapital >= 0 <br>
     * saldoObligacion >= 0 <br>
     */
    private void verificarInvariante( )
    {
        assert ( numeroMes >= 1 && numeroMes <= 60 ) : "El n�mero del mes debe estar entre 1 y 60";
        assert ( interesAPagar >= 0 ) : "El inter�s a pagar debe ser positivo";
        assert ( abonoACapital >= 0 ) : "El abono a capital debe de ser positivo";
        assert ( saldoObligacion >= 0 ) : "El saldo de la obligaci�n debe de ser positivo";
    }

}
