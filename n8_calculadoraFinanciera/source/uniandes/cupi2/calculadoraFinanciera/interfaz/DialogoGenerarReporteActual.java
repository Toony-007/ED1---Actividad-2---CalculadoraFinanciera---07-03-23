/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoGenerarReporteActual.java 1412 2008-12-12 16:39:06Z jua-gome $
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

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Di�logo para generar el reporte del cr�dito actual. Se especifican los datos del cr�dito y un resumen de los pagos seg�n el a�o.
 */
public class DialogoGenerarReporteActual extends JDialog implements ActionListener
{
    // --------------------------------------------------------
    // Constantes
    // --------------------------------------------------------

    /**
     * Comando para buscar la ruta del archivo de salida
     */
    private final static String BUSCAR_SALIDA = "BUSCAR_SALIDA";

    /**
     * Comando para generar el reporte
     */
    private final static String GENENAR = "GENERAR";

    /**
     * Comando para cancelar la operaci�n
     */
    private final static String CANCELAR = "CANCELAR";

    // -------------------------------------------------------
    // Atributos
    // -------------------------------------------------------

    /**
     * Panel padre de este di�logo
     */
    private PanelOperaciones principal;

    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------

    /**
     * Etiqueta del archivo de salida
     */
    private JLabel lbArchivoSalida;

    /**
     * Campo de texto del archivo de salida
     */
    private JTextField txtArchivoSalida;

    /**
     * Bot�n para buscar el archivo de salida
     */
    private JButton btnBuscarSalida;

    /**
     * Bot�n para generar el reporte
     */
    private JButton btnGenerar;

    /**
     * Bot�n para cancelar la operaci�n
     */
    private JButton btnCancelar;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo di�logo para generar el reporte de un cr�dito
     * @param principalP es el Panel padre de este di�logo.
     */
    public DialogoGenerarReporteActual( PanelOperaciones principalP )
    {
        principal = principalP;
        this.setSize( 466, 200 );
        this.setTitle( "Generar reporte cr�dito actual" );
        setLayout( new GridBagLayout( ) );

        lbArchivoSalida = new JLabel( );
        lbArchivoSalida.setText( "Ruta archivo de salida" );
        lbArchivoSalida.setHorizontalAlignment( SwingConstants.LEFT );

        GridBagConstraints gridBagConstraints5 = new GridBagConstraints( );
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 2;

        GridBagConstraints gridBagConstraints4 = new GridBagConstraints( );
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 2;

        GridBagConstraints gridBagConstraints3 = new GridBagConstraints( );
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.insets = new Insets( 5, 5, 5, 5 );
        gridBagConstraints3.gridy = 1;

        GridBagConstraints gridBagConstraints2 = new GridBagConstraints( );
        gridBagConstraints2.fill = GridBagConstraints.BOTH;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets( 5, 5, 5, 5 );
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.gridx = 0;

        GridBagConstraints gridBagConstraints1 = new GridBagConstraints( );
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.insets = new Insets( 5, 5, 0, 0 );


        add( lbArchivoSalida, gridBagConstraints1 );
        add( darTxtArchivoSalida( ), gridBagConstraints2 );
        add( darBtnBuscarSalida( ), gridBagConstraints3 );
        add( darBtnGenerar( ), gridBagConstraints4 );
        add( darBtnCancelar( ), gridBagConstraints5 );

        setModal( true );
        setLocationRelativeTo( null );
    }

    /**
     * Inicializa el campo de texto txtArchivoSalida
     * @return el campo de texto txtArchivoSalida
     */
    private JTextField darTxtArchivoSalida( )
    {
        if( txtArchivoSalida == null )
        {
            txtArchivoSalida = new JTextField( );
            txtArchivoSalida.setEditable( false );
            txtArchivoSalida.setEnabled( false );
        }
        return txtArchivoSalida;
    }

    /**
     * Inicializa el bot�n btnBuscarSalida
     * @return el bot�n btnBuscarSalida
     */
    private JButton darBtnBuscarSalida( )
    {
        if( btnBuscarSalida == null )
        {
            btnBuscarSalida = new JButton( );
            btnBuscarSalida.setText( "Buscar" );
            btnBuscarSalida.setActionCommand( BUSCAR_SALIDA );
            btnBuscarSalida.addActionListener( this );
        }
        return btnBuscarSalida;
    }

    /**
     * Inicializa el bot�n btnGenerar
     * @return el bot�n btnGenerar
     */
    private JButton darBtnGenerar( )
    {
        if( btnGenerar == null )
        {
            btnGenerar = new JButton( );
            btnGenerar.setText( "Generar" );
            btnGenerar.setActionCommand( GENENAR );
            btnGenerar.addActionListener( this );
        }
        return btnGenerar;
    }

    /**
     * Inicializa el bot�n btnCancelar
     * @return el bot�n btnCancelar
     */
    private JButton darBtnCancelar( )
    {
        if( btnCancelar == null )
        {
            btnCancelar = new JButton( );
            btnCancelar.setText( "Cancelar" );
            btnCancelar.setActionCommand( CANCELAR );
            btnCancelar.addActionListener( this );
        }
        return btnCancelar;
    }

    // --------------------------------------------------------
    // M�todos
    // --------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( GENENAR ) )
        {
            if( txtArchivoSalida.getText( ) != null && !txtArchivoSalida.getText( ).equals( "" ) )
            {
                if( principal.generarReporteActual( txtArchivoSalida.getText( ) ) )
                    JOptionPane.showMessageDialog( null, "El reporte fue generado con �xito", "�xito", JOptionPane.INFORMATION_MESSAGE );

                dispose( );
            }
            else
                JOptionPane.showMessageDialog( null, "Debe especificar la rutas del archivo de salida", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if( e.getActionCommand( ).equals( CANCELAR ) )
        {
            dispose( );
        }
        else if( e.getActionCommand( ).equals( BUSCAR_SALIDA ) )
        {
            File archivoSalida = null;
            JFileChooser fc = new JFileChooser( "./data" );
            fc.setDialogTitle( "Seleccionar carpeta de salida" );
            fc.setSelectedFile( new File( "./reporte.txt" ) );

            int resultado = fc.showOpenDialog( this );

            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                archivoSalida = fc.getSelectedFile( );
                txtArchivoSalida.setText( archivoSalida.getAbsolutePath( ) );
            }

        }
    }

}
