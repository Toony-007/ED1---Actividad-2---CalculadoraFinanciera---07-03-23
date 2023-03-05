/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Credito.java 1365 2008-10-23 00:25:35Z jua-gome $
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
 * Esta clase representa un cr�dito. Dos cr�ditos son iguales si la c�dula del cliente es igual<br>
 * <b> inv: </b> <br>
 * monto > 0 <br>
 * plazo > 0 <br>
 * tasa > 0 <br>
 * cuota > 0 <br>
 * nombre != null && nombre != ""<br>
 * cedula != null && cedula != ""<br>
 * meses != null <br>
 * La cuota del cr�dito debe ser siempre igual al inter�s a pagar m�s el abono a capital de cada mes de amortizaci�n.
 */
public class Credito implements Serializable
{
    // --------------------------------------------------------
    // Constantes
    // --------------------------------------------------------

    /**
     * Indicador de versi�n para la serializaci�n
     */
    private static final long serialVersionUID = 100L;

    /**
     * Indicador de cantidad de decimales luego de la coma
     */
    public static final int NUM_DECIMALES = 2;

    /**
     * Indicador de meses en un a�o
     */
    public static final int CANT_MESES = 12;

    /**
     * Etiqueta Mes para el reporte
     */
    public static final String MES = "Mes";

    /**
     * Etiqueta Inter�s para el reporte
     */
    public static final String INTERES = "Inter�s a pagar";

    /**
     * Etiqueta abono para el reporte
     */
    public static final String ABONO = "Abono a capital";

    /**
     * Etiqueta saldo para el reporte
     */
    public static final String SALDO = "Saldo Obligaci�n";

    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    /**
     * Es el monto total del cr�dito
     */
    private double monto;

    /**
     * Es el plazo en meses que se tiene para cancelar el cr�dito
     */
    private int plazo;

    /**
     * Es la tasa efectiva mensual del cr�dito.
     */
    private double tasa;

    /**
     * Es la cuota fija a pagar durante los meses del plazo del cr�dito.
     */
    private double cuota;

    /**
     * Contenedora para los meses de amortizaci�n del cr�dito
     */
    private MesAmortizacion[] meses;

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * C�dula del cliente
     */
    private String cedula;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Crea un nuevo cr�dito con los datos b�sicos
     * @param montoP es el monto del cr�dito. montoP > 0
     * @param plazoP es el plazo en meses para cancelar el cr�dito. plazoP > 0
     * @param tasaP es la tasa efectiva mensual del cr�dito. tasaP > 0
     * @param nombreP es el nombre del cliente. nombreP != null
     * @param cedulaP es la c�dula del cliente. cedulaP != null
     */
    public Credito( double montoP, int plazoP, double tasaP, String nombreP, String cedulaP )
    {
        monto = montoP;
        plazo = plazoP;
        tasa = tasaP;
        nombre = nombreP;
        cedula = cedulaP;

        cuota = calcularCuota( );
        meses = inicializarMeses( );

        verificarInvariante( );
    }

    // --------------------------------------------------------
    // M�todos
    // --------------------------------------------------------

    /**
     * Retorna la cantidad de dinero pagado durante un a�o dado. Corresponde a la cantidad de cuotas pagadas durante el a�o.
     * @param anio es el a�o del cual se quiere conocer cuanto se pago
     * @return cantidad pagada por el cr�dito.
     */
    public double darDineroPagadoPorAnio( int anio )
    {
        return cuota * CANT_MESES;
    }

    /**
     * Retorna la cantidad de dinero pagado por conceptos de inter�s durante el a�o dado
     * @param anio es el n�mero del a�o.
     * @return cantidad de dinero pagado.
     */
    public double darInteresPagadoPorAnio( int anio )
    {
        double dineroPagado = 0.0;
        int indice = darMesIniAnio( anio );

        for( int i = 0; i < CANT_MESES; i++ )
        {
            MesAmortizacion elMes = meses[ indice ];
            dineroPagado += elMes.darInteresAPagar( );
            indice++;
        }
        return dineroPagado;
    }

    /**
     * Retorna la cantidad de dinero pagado por abonos durante el a�o dado
     * @param anio es el n�mero del a�o.
     * @return cantidad de dinero pagado.
     */
    public double darAbonoPagadoPorAnio( int anio )
    {
        double dineroPagado = 0.0;
        int indice = darMesIniAnio( anio );

        for( int i = 0; i < CANT_MESES; i++ )
        {
            MesAmortizacion elMes = meses[ indice ];
            dineroPagado += elMes.darAbonoACapital( );
            indice++;
        }
        return dineroPagado;
    }

    /**
     * Retorna los meses del a�o dado
     * @param anio es el a�o especificado
     * @return un arreglo con los meses del a�o
     */
    public MesAmortizacion[] darMeses( int anio )
    {
        int indice = darMesIniAnio( anio );
        MesAmortizacion[] arreglo = new MesAmortizacion[12];
        for( int i = 0; i < CANT_MESES; i++ )
        {
            MesAmortizacion mes = meses[ indice ];
            arreglo[ i ] = mes;
            indice++;
        }
        return arreglo;
    }

    /**
     * Verifica si dos cr�ditos son iguales. Dos cr�ditos son iguales si tienen la misma c�dula.
     * @param credito el cr�dito con el cual comparar. credito != null
     * @return true si son iguales y false de lo contrario
     */
    public boolean comprarCreditos( Credito credito )
    {
        return cedula.equals( credito.darCedula( ) );
    }

    /**
     * Imprime en un archivo los datos b�sicos del cr�dito y los movimientos, mes a mes, del mismo. <br>
     * <b> post: </b> Los datos del cr�dito fueron escritos en el reporte de salida <br>
     * @param escritor es el escritor del reporte de salida
     * @param anio es el n�mero del a�o del cual se quiere escribir el reporte
     * @throws IOException si ocurre una excepci�n en la escritura del reporte
     */
    public void generarSalidaReporte( BufferedWriter escritor, int anio ) throws IOException
    {
        // Escribimos el encabezado
        escritor.write( "Cliente: " + nombre + "\n" );
        escritor.write( "CC: " + cedula + "\n" );
        escritor.write( "Monto: " + redondear( monto ) + "\n" );
        escritor.write( "Plazo: " + plazo + "\n" );
        escritor.write( "Tasa mensual: " + ( tasa * 100 ) + "(%)\n" );
        escritor.write( "A�o amortizaci�n: " + anio + "\n" );
        escritor.newLine( );

        // Escribimos las columnas de la tabla
        String mes = MES;
        escritor.write( mes );
        escritor.write( "\t" );

        String interes = INTERES;
        escritor.write( interes );
        escritor.write( "\t" );

        String abono = ABONO;
        escritor.write( abono );
        escritor.write( "\t" );

        String saldo = SALDO;
        escritor.write( saldo );
        escritor.newLine( );

        int indice = ( anio - 1 ) * CANT_MESES;
        for( int i = 0; i < CANT_MESES; i++ )
        {
            meses[ indice ].generarReporteSalida( escritor );
            escritor.newLine( );
            indice++;
        }
    }

    /**
     * Imprime en un archivo los movimientos, mes a mes del cr�dito. <br>
     * <b> post: </b> Los datos del cr�dito fueron escritos en el reporte de salida <br>
     * @param escritor es el escritor del reporte de salida
     * @param anio es el n�mero del a�o del cual se quiere escribir el reporte
     * @throws IOException si ocurre una excepci�n en la escritura del reporte
     */
    public void generarReporteActual( BufferedWriter escritor, int anio ) throws IOException
    {
        // Escribimos el encabezado
        escritor.newLine( );
        escritor.write( "A�o amortizaci�n: " + anio + "\n" );
        escritor.newLine( );

        // Escribimos las columnas de la tabla
        String mes = MES;
        escritor.write( mes );
        escritor.write( "\t" );

        String interes = INTERES;
        escritor.write( interes );
        escritor.write( "\t" );

        String abono = ABONO;
        escritor.write( abono );
        escritor.write( "\t" );

        String saldo = SALDO;
        escritor.write( saldo );
        escritor.newLine( );

        int indice = ( anio - 1 ) * CANT_MESES;
        for( int i = 0; i < CANT_MESES; i++ )
        {
            meses[ indice ].generarReporteSalida( escritor );
            escritor.newLine( );
            indice++;
        }
    }

    /**
     * Retorna el monto del cr�dito
     * @return el monto del cr�dito
     */
    public double darMonto( )
    {
        return monto;
    }

    /**
     * Retorna el plazo en meses del cr�dito
     * @return el plazo en meses del cr�dito
     */
    public int darPlazo( )
    {
        return plazo;
    }

    /**
     * Retorna la tasa de inter�s del cr�dito
     * @return la tasa de inter�s del cr�dito
     */
    public double darTasa( )
    {
        return tasa;
    }

    /**
     * Retorna la cuota del cr�dito
     * @return la cuota del cr�dito
     */
    public double darCuota( )
    {
        return cuota;
    }

    /**
     * Retorna el nombre del cliente
     * @return el nombre del cliente
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la c�dula del cliente
     * @return la c�dula del cliente
     */
    public String darCedula( )
    {
        return cedula;
    }

    /**
     * Retorna los meses de plazo del cr�dito
     * @return los meses de plazo del cr�dito
     */
    public MesAmortizacion[] darMeses( )
    {
        return meses;
    }

    /**
     * Representaci�n del cr�dito en string.
     * @return el nombre del cliente.
     */
    public String toString( )
    {
        return cedula;
    }

    // --------------------------------------------------------
    // M�todos auxiliares
    // --------------------------------------------------------

    /**
     * Inicializa los meses de amortizaciones del cr�dito. <b> pre: </b> <br>
     * El monto ya fue inicializado. monto > 0. <br>
     * El plazo ya fue inicializado. plazo > 0. <br>
     * La tasa ya fue inicializada. tasa > 0. <br>
     * @return la lista de los meses de amortizaci�n del cr�dito.
     */
    private MesAmortizacion[] inicializarMeses( )
    {
        MesAmortizacion[] meses = new MesAmortizacion[plazo];
        double tempPrestamo = monto;
        for( int i = 0; i < plazo; i++ )
        {
            double interes = ( tempPrestamo * tasa );
            double abono = cuota - interes;
            double saldo = tempPrestamo - abono;
            tempPrestamo = tempPrestamo - abono;
            if( i == plazo - 1 && ( saldo < 0 && saldo > -0.001 ) )
                saldo = 0;
            meses[ i ] = new MesAmortizacion( ( i + 1 ), interes, abono, saldo );
        }
        return meses;
    }

    /**
     * Calcula la cuota mensual de un cr�dito. <b> pre: </b> <br>
     * El monto ya fue inicializado. monto > 0. <br>
     * El plazo ya fue inicializado. plazo > 0. <br>
     * La tasa ya fue inicializada. tasa > 0. <br>
     * @return la cuota mensual del cr�dito.
     */
    private double calcularCuota( )
    {
        return monto * ( ( Math.pow( 1 + tasa, plazo ) * tasa ) / ( Math.pow( 1 + tasa, plazo ) - 1 ) );
    }

    /**
     * Retorna el �ndice del mes en el marco del cr�dito en el que inicia el a�o.
     * @param anio es el a�o
     * @return n�mero del mes
     */
    private int darMesIniAnio( int anio )
    {
        return ( ( anio - 1 ) * CANT_MESES );
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

    // ------------------------------------------------------
    // Invariantes
    // --------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * monto > 0 <br>
     * plazo > 0 <br>
     * tasa > 0 <br>
     * cuota > 0 <br>
     * nombre != null && nombre != ""<br>
     * cedula != null && cedula != ""<br>
     * meses != null <br>
     * La cuota del cr�dito debe ser siempre igual al inter�s a pagar m�s el abono a capital de cada mes de amortizaci�n.
     */
    private void verificarInvariante( )
    {
        assert ( monto > 0 ) : "El monto debe ser positivo";
        assert ( plazo > 0 ) : "El plazo debe ser positivo";
        assert ( tasa > 0 ) : "La tasa debe de ser positiva";
        assert ( cuota > 0 ) : "La cuota debe de ser positiva";
        assert ( meses != null ) : "Los meses no pueden ser nulos";
        assert ( nombre != null && !nombre.equals( "" ) ) : "El nombre del cliente no debe ser nulo";
        assert ( cedula != null && !cedula.equals( "" ) ) : "La c�dula del cliente no debe ser nula";
        assert ( verificarCuotaMesesAmortizacion( ) ) : "La cuota del cr�dito debe ser igual al abono a capital sumado a los intereses a pagar de cada mes de amortizaci�n.";
    }

    /**
     * Verifica que para cada mes de amortizaci�n, la suma del abono a capital m�s los intereses a pagar debe de ser igual a la cuota del cr�dito.
     * @return true si se verifica para cada mes de amortizaci�n o false de lo contrario.
     */
    private boolean verificarCuotaMesesAmortizacion( )
    {
        boolean esCorrecta = true;
        for( int i = 0; i < meses.length && esCorrecta; i++ )
        {
            MesAmortizacion mes = meses[ i ];
            if( cuota - mes.darAbonoACapital( ) + mes.darInteresAPagar( ) < 0.0001 )
                esCorrecta = false;
        }
        return esCorrecta;
    }
}
