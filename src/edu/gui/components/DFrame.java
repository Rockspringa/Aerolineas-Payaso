package edu.gui.components;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import edu.main.Init;

import java.awt.*;
import java.awt.event.*;

/**
 * Clase para crear JFrame predeterminado
 */
public abstract class DFrame extends JFrame implements ActionListener {
    public static final Font JBRAINS_BOLD = new Font("JetBrainsMono Nerd Font Mono", 1, 15);
    public static final Font JBRAINS = new Font("JetBrainsMono NF", 1, 13);
    public static final Color BACKG_COLOR = new Color(30, 36, 46);
    public static final Color FOCUS_COLOR = new Color(48, 56, 69);
    public static final Color SHADOW_BLUE = new Color(3, 7, 30);
    public static final Color AQUA = new Color(35, 195, 180);
    private final int WIDTH;
    private final int HEIGHT;
    private DFrame openFrame = Init.winInit;
    
    static {
        /* Modification of a part of the predefined features of all labels'. */
        UIManager.put("Label.font", JBRAINS);
        UIManager.put("Label.background", BACKG_COLOR);
        UIManager.put("Label.foreground", AQUA);
        UIManager.put("Label.focus", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all labels'. */
        UIManager.put("ComboBox.font", JBRAINS);
        UIManager.put("ComboBox.background", AQUA);
        UIManager.put("ComboBox.foreground", FOCUS_COLOR);
        UIManager.put("ComboBox.selectionBackground", AQUA);
        UIManager.put("ComboBox.selectionForeground", SHADOW_BLUE);
        UIManager.put("ComboBox.focus", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all text areas'. */
        UIManager.put("TextArea.font", JBRAINS);
        UIManager.put("TextArea.background", AQUA);
        UIManager.put("TextArea.foreground", SHADOW_BLUE);
        UIManager.put("TextArea.border", BorderFactory.createEmptyBorder(5, 5, 5, 5));
        UIManager.put("TextArea.focus", FOCUS_COLOR);
        
        /* Modification of a part of the predefined features of all scroll bars'. */
        UIManager.put("ScrollBar.border", BorderFactory.createEmptyBorder());
        UIManager.put("ScrollBar.focus", FOCUS_COLOR);
        UIManager.put("ScrollBar.background", BACKG_COLOR);
        UIManager.put("ScrollBar.thumb", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all scroll panes'. */
        UIManager.put("ScrollPane.border", BorderFactory.createLineBorder(SHADOW_BLUE));
        UIManager.put("ScrollPane.focus", FOCUS_COLOR);
        UIManager.put("ScrollPane.background", BACKG_COLOR);

        /* Modification of a part of the predefined features of all panels'. */
        UIManager.put("Panel.focus", FOCUS_COLOR);
        UIManager.put("Panel.background", BACKG_COLOR);

        /* Modification of a part of the predefined features of all text fields'. */
        UIManager.put("TextField.font", JBRAINS);
        UIManager.put("TextField.foreground", SHADOW_BLUE);
        UIManager.put("TextField.background", AQUA);
        UIManager.put("TextField.border", BorderFactory.createLineBorder(SHADOW_BLUE));

        /* Modification of a part of the predefined features of all buttons'. */
        UIManager.put("Button.focus", FOCUS_COLOR);
        UIManager.put("Button.font", JBRAINS_BOLD);
        UIManager.put("Button.background", SHADOW_BLUE);
        UIManager.put("Button.foreground", AQUA);
        UIManager.put("Button.border", BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AQUA, 1),
                        BorderFactory.createLineBorder(new Color(35, 40, 50), 1)));
    }

    /**
     * Coloca las medidas de la ventana, le quita el cambio de tamaño, y pone que se cierre solo la
     * ventana al presionar el boton de cerrar.
     */
    private void init() {
        this.setResizable(false);
        this.setBounds(0, 0, this.WIDTH, this.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(BACKG_COLOR);
        this.getContentPane().setLayout(new FlowLayout());
    }

    /**
     * Pone el titulo que se ingresa, y las medidas predeterminadas 450 x 620.
     * @param tittle es el titulo de la ventana.
     */
    public DFrame(String tittle) {
        super(tittle);
        this.WIDTH = 450;
        this.HEIGHT = 620;
        this.init();
    }

    /**
     * Coloca el tamaño y el titulo proporcionados.
     * @param tittle es el titulo de la ventana.
     * @param width es el ancho de la ventana.
     * @param height es la altura de la ventana.
     */
    public DFrame(String tittle, int width, int height) {
        super(tittle);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.init();
    }
    
    /**
     * Abre la ventana y la hace visible, la pone en el centro de la pantalla.
     */
    public void open() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (openFrame != null && !openFrame.isActive()) {
            openFrame.setVisible(true);
        }
    }

    public void dispose(boolean openInitial) {
        super.dispose();
        if (openInitial && openFrame != null && !openFrame.isActive()) {
            openFrame.setVisible(true);
        }
    }

    public void setOpenFrame(DFrame window) {
        this.openFrame = window;
    }
}
