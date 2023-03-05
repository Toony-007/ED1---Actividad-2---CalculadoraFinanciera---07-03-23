/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoAgregarCredito.java 1363 2008-10-22 21:50:11Z jua-gome $
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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Di�logo para agregar un cr�dito en el sistema
 */
public class DialogoAgregarCredito extends JDialog implements ActionListener
{
    // --------------------------------------------------------
    // Constantes
    // --------------------------------------------------------

    /**
     * Comando para agregar un cr�dito
     */
    private final static String ACEPTAR = "ACEPTAR";

    /**
     * Comando para cancelar la operaci�n
     */
    private final static String CANCELAR = "CANCELAR";

    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    /**
     * Panel padre de este di�logo
     */
    private PanelCredito principal;

    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------

    /**
     * Etiqueta nombre
     */
    private JLabel lbNombre;

    /**
     * Etiqueta c�dula
     */
    private JLabel lbCedula;

    /**
     * Etiqueta monto
     */
    private JLabel lbMonto;

    /**
     * Etiqueta plazo
     */
    private JLabel lbPlazo;

    /**
     * Etiqueta tasa
     */
    private JLabel lbTasa;

    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para mostrar la c�dula
     */
    private JTextField txtCedula;

    /**
     * Campo de texto para mostrar el monto
     */
    private JTextField txtMonto;

    /**
     * Campo de texto para mostrar el plazo
     */
    private JComboBox cbPlazo;

    /**
     * Campo de texto para mostrar la tasa
     */
    private JTextField txtTasa;

    /**
     * Bot�n aceptar
     */
    private JButton btnAceptar;

    /**
     * Bot�n cancelar
     */
    private JButton btnCancelar;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo dialogo para agregar un cr�dito
     * @param principalP es la referencia al panel padre del di�logo
     */
    public DialogoAgregarCredito( PanelCredito principalP )
    {
        principal = principalP;

        setLayout( new GridLayout( 6, 2 ) );
        this.setSize( 466, 200 );
        setTitle( "Agregar cr�dito" );

        lbNombre = new JLabel( "Nombre:" );
        txtNombre = new JTextField( );

        lbCedula = new JLabel( "C�dula:" );
        txtCedula = new JTextField( );

        lbMonto = new JLabel( "Monto:" );
        txtMonto = new JTextField( );

        lbPlazo = new JLabel( "Plazo:" );
        cbPlazo = new JComboBox( );
        cbPlazo.addItem( "12" );
        cbPlazo.addItem( "24" );
        cbPlazo.addItem( "36" );
        cbPlazo.addItem( "48" );
        cbPlazo.addItem( "60" );

        lbTasa = new JLabel( "Tasa Mensual (%):" );
        txtTasa = new JTextField( );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );

        add( lbNombre );
        add( txtNombre );

        add( lbCedula );
        add( txtCedula );

        add( lbMonto );
        add( txtMonto );

        add( lbPlazo );
        add( cbPlazo );

        add( lbTasa );
        add( txtTasa );

        add( btnAceptar );
        add( btnCancelar );

        setModal( true );
        setLocationRelativeTo( null );
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
        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            String nombre = txtNombre.getText( );
            String cedula = txtCedula.getText( );
            String montoS = txtMonto.getText( );
            String plazoS = cbPlazo.getSelectedItem( ).toString( );
            String tasaS = txtTasa.getText( );

            if( nombre != null && cedula != null && montoS != null && plazoS != null && tasaS != null && !nombre.equals( "" ) && !cedula.equals( "" ) )
            {
                try
                {
                    double monto = Double.parseDouble( montoS );
                    int plazo = Integer.parseInt( plazoS );
                    double tasa = Double.parseDouble( tasaS );
                    tasa = tasa / 100;

                    if( !cedula.matches( "[0-9]+" ) )
                    {
                        JOptionPane.showMessageDialog( null, "La c�dula no puede contener letras", "Error", JOptionPane.ERROR_MESSAGE );
                    }
                    else
                    {
                        if( monto < 0 || plazo < 0 || tasa < 0 )
                        {
                            JOptionPane.showMessageDialog( null, "El monto, el plazo y la tasa deben ser valores positivos. " );
                        }
                        else
                        {
                            principal.agregarCredito( monto, plazo, tasa, nombre, cedula );
                            dispose( );
                        }
                    }
                }
                catch( NumberFormatException e1 )
                {
                    JOptionPane.showMessageDialog( null, "Los datos de monto, plazo y tasa deben ser valores num�ricos", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
                JOptionPane.showMessageDialog( null, "Todos los datos deben ser ingresados", "Error", JOptionPane.ERROR_MESSAGE );

        }
        else if( e.getActionCommand( ).equals( CANCELAR ) )
        {
            dispose( );
        }

    }
}
