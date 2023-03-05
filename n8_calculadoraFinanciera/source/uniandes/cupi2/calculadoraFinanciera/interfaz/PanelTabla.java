/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelTabla.java 1343 2008-10-16 16:59:39Z ju-cort1 $
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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Es el panel que muestra la tabla con los datos del cr�dito segregados por a�o
 */
public class PanelTabla extends JPanel implements ActionListener
{
    // --------------------------------------------------------
    // Constantes
    // --------------------------------------------------------

    /**
     * Comando para obtener el anterior a�o
     */
    private final static String ANTERIOR = "ANTERIOR";

    /**
     * Comando para obtener el siguiente a�o
     */
    private final static String SIGUIENTE = "SIGUIENTE";

    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    /**
     * Panel principal de este panel
     */
    private PanelCentral principal;

    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------

    /**
     * Modelo de la tabla
     */
    private DefaultTableModel modeloTabla;

    /**
     * Tabla con los datos de los meses
     */
    private JTable tabla;

    /**
     * Etiqueta para el a�o
     */
    private JLabel lbAnio;

    /**
     * Campo de texto para indicar el a�o
     */
    private JTextField txtAnio;

    /**
     * Bot�n anterior
     */
    private JButton btnAnterior;

    /**
     * Bot�n siguiente
     */
    private JButton btnSiguiente;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo panel tabla
     * @param principalP es el panel principal de este panel
     */
    public PanelTabla( PanelCentral principalP )
    {
        principal = principalP;

        setBorder( BorderFactory.createTitledBorder( "Datos por mes" ) );
        setSize( 400, 400 );

        modeloTabla = new DefaultTableModel( );
        tabla = new JTable( modeloTabla )
        {
            public boolean isCellEditable( int col, int row )
            {
                return false;
            }
        };
        setLayout( new BorderLayout( ) );

        modeloTabla.addColumn( "Mes" );
        modeloTabla.addColumn( "Intereses a  pagar" );
        modeloTabla.addColumn( "Abono a capital" );
        modeloTabla.addColumn( "Saldo Obligaci�n" );

        for( int i = 1; i <= 12; i++ )
        {
            String[] fila = { "" + i, "", "", "" };
            modeloTabla.addRow( fila );
        }

        tabla.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        tabla.getColumnModel( ).getColumn( 0 ).setPreferredWidth( getWidth( ) * 1 / 14 );
        tabla.getColumnModel( ).getColumn( 1 ).setPreferredWidth( getWidth( ) * 3 / 14 );
        tabla.getColumnModel( ).getColumn( 2 ).setPreferredWidth( getWidth( ) * 3 / 14 );

        JScrollPane panelScroll = new JScrollPane( tabla );
        add( panelScroll, BorderLayout.CENTER );

        JPanel panelInferior = new JPanel( new GridLayout( 1, 4 ) );

        lbAnio = new JLabel( "A�o" );
        panelInferior.add( lbAnio );

        txtAnio = new JTextField( 10 );
        txtAnio.setEditable( false );
        panelInferior.add( txtAnio );

        btnAnterior = new JButton( "<<" );
        btnAnterior.setActionCommand( ANTERIOR );
        btnAnterior.addActionListener( this );
        panelInferior.add( btnAnterior );

        btnSiguiente = new JButton( ">>" );
        btnSiguiente.setActionCommand( SIGUIENTE );
        btnSiguiente.addActionListener( this );
        panelInferior.add( btnSiguiente );

        add( panelInferior, BorderLayout.SOUTH );
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
        if( e.getActionCommand( ).equals( ANTERIOR ) )
        {
            String anio = txtAnio.getText( );
            if( anio != null && !anio.equals( "" ) )
                principal.actualizarAnterioresDatos( Integer.parseInt( anio ) );
        }
        else if( e.getActionCommand( ).equals( SIGUIENTE ) )
        {
            String anio = txtAnio.getText( );
            if( anio != null && !anio.equals( "" ) )
                principal.actualizarSiguientesDatos( Integer.parseInt( txtAnio.getText( ) ) );
        }

    }

    /**
     * Actualiza los datos del a�o
     * @param anio nuevo valor del a�o
     * @param tabla datos del a�o
     */
    public void cambiarAnio( int anio, String[][] tabla )
    {
        txtAnio.setText( "" + anio );
        int cantFilas = modeloTabla.getRowCount( );

        // Eliminamos las filas
        for( int i = 0; i < cantFilas; i++ )
        {
            modeloTabla.removeRow( 0 );
        }

        // Agregamos las filas
        cantFilas = tabla.length;
        for( int i = 0; i < cantFilas; i++ )
        {
            modeloTabla.addRow( tabla[ i ] );
        }
    }

    /**
     * Retorna el valor del a�o actual
     * @return el valor del a�o actual
     */
    public int darAnioActual( )
    {
        String anioS = txtAnio.getText( );
        int anio = Integer.valueOf( anioS ).intValue( );
        return anio;
    }
}
