/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelCentral.java 1365 2008-10-23 00:25:35Z jua-gome $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_calculadoraFinanciera
 * Autor: Juan Camilo Cort�s Medina - 05-ago-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.calculadoraFinanciera.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import uniandes.cupi2.calculadoraFinanciera.mundo.Credito;

/**
 * Es el panel central de la aplicaci�n donde se encuentran los paneles de cr�dito, totales y la tabla
 */
public class PanelCentral extends JPanel
{
    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    private InterfazCalculadoraFinanciera principal;

    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------

    /**
     * Panel del cr�dito
     */
    private PanelCredito panelCredito;

    /**
     * Panel totales
     */
    private PanelTotales panelTotales;

    /**
     * Panel tabla
     */
    private PanelTabla panelTabla;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo panel central
     * @param principalP es la interfaz padre de este panel
     */
    public PanelCentral( InterfazCalculadoraFinanciera principalP )
    {
        principal = principalP;

        setLayout( new GridBagLayout( ) );
        setSize( 700, 400 );

        GridBagConstraints gridBagConstraints1 = new GridBagConstraints( );
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.weightx = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = GridBagConstraints.BOTH;

        GridBagConstraints gridBagConstraints2 = new GridBagConstraints( );
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 1;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = GridBagConstraints.BOTH;

        GridBagConstraints gridBagConstraints3 = new GridBagConstraints( );
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridheight = 2;
        gridBagConstraints3.weightx = 1;
        gridBagConstraints3.fill = GridBagConstraints.BOTH;

        panelCredito = new PanelCredito( this );
        panelTotales = new PanelTotales( );
        panelTabla = new PanelTabla( this );

        add( panelCredito, gridBagConstraints1 );
        add( panelTotales, gridBagConstraints2 );
        add( panelTabla, gridBagConstraints3 );
    }

    // --------------------------------------------------------
    // M�todos
    // --------------------------------------------------------

    /**
     * Agrega un nuevo cr�dito al sistema
     * @param monto es el monto del cr�dito
     * @param plazo es el plazo del cr�dito
     * @param tasa es la tasa del cr�dito
     * @param nombre es el nombre del cliente
     * @param cedula es la c�dula del cliente
     */
    public void agregarCredito( double monto, int plazo, double tasa, String nombre, String cedula )
    {
        principal.agregarCredito( monto, plazo, tasa, nombre, cedula );
    }

    /**
     * Actualiza los datos del cr�dito
     * @param monto es el monto del cr�dito
     * @param plazo es el plazo del cr�dito
     * @param tasa es la tasa del cr�dito
     * @param cuota es la cuota mensual del cr�dito
     * @param nombre es el nombre del cliente
     */
    public void actualizarDatosCredito( double monto, int plazo, double tasa, double cuota, String nombre )
    {
        panelCredito.actualizarDatosCredito( monto, plazo, tasa, cuota, nombre );
    }

    /**
     * Actualiza los datos del panel de totales seg�n el a�o
     * @param interes es el nuevo total de inter�s
     * @param abonos es el nuevo total de abonos
     * @param total es el nuevo total de dinero cancelado
     */
    public void actualizarDatosTotales( double interes, double abonos, double total )
    {
        panelTotales.cambiarPagoInteres( interes );
        panelTotales.cambiarPagoAbonos( abonos );
        panelTotales.cambiarPagoTotal( total );
    }

    /**
     * Actualiza los datos del anterior a�o del cr�dito
     * @param anioActual es el a�o actualmente mostrado
     */
    public void actualizarAnterioresDatos( int anioActual )
    {
        Credito credito = panelCredito.darCreditoActual( );
        principal.actualizarAnterioresDatosCredito( credito, anioActual );

    }

    /**
     * Actualiza los datos del siguiente a�o del cr�dito
     * @param anioActual es el a�o actualmente mostrado
     */
    public void actualizarSiguientesDatos( int anioActual )
    {
        Credito credito = panelCredito.darCreditoActual( );
        principal.actualizarSiguientesDatosCredito( credito, anioActual );

    }

    /**
     * Actualiza los datos de la tabla
     * @param anio es el valor del a�o
     * @param tabla es la tabla con los datos
     */
    public void actualizarDatos( int anio, String[][] tabla )
    {
        panelTabla.cambiarAnio( anio, tabla );
    }

    /**
     * Retorna la c�dula del cliente actual
     * @return la c�dula del cliente actual
     */
    public String darCedulaClienteActual( )
    {
        Credito credito = panelCredito.darCreditoActual( );
        return credito.darCedula( );
    }

    /**
     * Retorna el a�o actual
     * @return el a�o actual
     */
    public int darAnioActual( )
    {
        return panelTabla.darAnioActual( );
    }

    /**
     * Actualiza la informaci�n presentada al usuario del cr�dito seleccionado
     * @param credito es el nombre del cliente
     */
    public void actualizarCredito( Credito credito )
    {
        principal.actualizarCredito( credito );
    }

    /**
     * Actualiza las c�dulas mostradas en el panelCredito
     * @param creditos Cr�ditos registrados en el sistema
     */
    public void actualizarCedulas( ArrayList creditos )
    {
        panelCredito.actualizarCedulas( creditos );
        panelCredito.validate( );
    }

    /**
     * Selecciona el �ltimo cr�dito de la lista
     */
    public void seleccionarUltimoCredito( )
    {
        panelCredito.seleccionarUltimoCredito( );
    }

}
