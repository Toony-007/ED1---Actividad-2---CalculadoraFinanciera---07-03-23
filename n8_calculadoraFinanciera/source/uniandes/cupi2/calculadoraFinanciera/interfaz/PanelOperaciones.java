/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelOperaciones.java 1343 2008-10-16 16:59:39Z ju-cort1 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_calculadoraFinanciera
 * Autor: Juan Camilo Cort�s Medina - 27-ago-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.calculadoraFinanciera.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelOperaciones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando generar reporte seg�n archivo
     */
    private static final String GENERAR_REPORTE_ARCHIVO = "GENERAR_REPORTE_ARCHIVO";

    /**
     * Comando generar reporte de cr�dito actual
     */
    private static final String GENERAR_REPORTE_ACTUAL = "GENERAR_REPORTE_ACTUAL";

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCalculadoraFinanciera principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Generar Reporte
     */
    private JButton btnGenerarReporteArchivo;

    /**
     * Bot�n generar reporte de cr�dito actual
     */
    private JButton btnGenerarReporteActual;

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param elPadre Ventana principal
     */
    public PanelOperaciones( InterfazCalculadoraFinanciera elPadre )
    {
        principal = elPadre;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 2 ) );

        // Bot�n generar reporte seg�n archivo
        btnGenerarReporteArchivo = new JButton( "Reporte Seg�n Archivo" );
        btnGenerarReporteArchivo.setActionCommand( GENERAR_REPORTE_ARCHIVO );
        btnGenerarReporteArchivo.addActionListener( this );
        add( btnGenerarReporteArchivo );

        // Bot�n generar reporte de cr�dito actual
        btnGenerarReporteActual = new JButton( "Reporte Cr�dito Actual" );
        btnGenerarReporteActual.setActionCommand( GENERAR_REPORTE_ACTUAL );
        btnGenerarReporteActual.addActionListener( this );
        add( btnGenerarReporteActual );

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Cargar listado de creditos." );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Reporte de los créditos." );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( GENERAR_REPORTE_ARCHIVO.equals( e.getActionCommand( ) ) )
        {
            DialogoGenerarReporteArchivo dialogo = new DialogoGenerarReporteArchivo( this );
            dialogo.setVisible( true );
        }
        else if( GENERAR_REPORTE_ACTUAL.equals( e.getActionCommand( ) ) )
        {
            boolean hay = principal.hayCreditoActual( );
            if( hay )
            {
                DialogoGenerarReporteActual dialogo = new DialogoGenerarReporteActual( this );
                dialogo.setVisible( true );
            }
            else
                JOptionPane.showMessageDialog( null, "No hay ning�n cr�dito seleccionado", "Atenci�n", JOptionPane.INFORMATION_MESSAGE );
        }
        else if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
    }

    /**
     * Genera el reporte seg�n un archivo de entrada sobre un cr�dito a un archivo de salida seleccionado.
     * @param rutaArchivoEntrada es la ruta del archivo de entrada
     * @param rutaArchivoSalida es la ruta del archivo de salida
     * @return true si se pudo generar el reporte y false de lo contrario.
     */
    public boolean generarReporte( String rutaArchivoEntrada, String rutaArchivoSalida )
    {
        return principal.generarReporte( rutaArchivoEntrada, rutaArchivoSalida );
    }

    /**
     * Genera el reporte seg�n un archivo de entrada sobre un cr�dito a un archivo de salida seleccionado.
     * @param rutaArchivoSalida es la ruta del archivo de salida
     * @return true si se pudo generar el reporte y false de lo contrario.
     */
    public boolean generarReporteActual( String rutaArchivoSalida )
    {
        return principal.generarReporteActual( rutaArchivoSalida );
    }

}
