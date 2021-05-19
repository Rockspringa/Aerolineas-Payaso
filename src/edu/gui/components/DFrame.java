package edu.gui.components;

import javax.swing.*;

import edu.main.Init;

import java.awt.*;
import java.awt.event.*;

/**
 * Clase para crear JFrame predeterminado
 */
public abstract class DFrame extends JFrame implements ActionListener {
    
    public static final Font JBRAINS_BOLD = new Font("JetBrainsMono Nerd Font Mono", 1, 15);
    public static final Font JBRAINS = new Font("JetBrainsMono NF", 1, 13);
    public static final Color BACKG_COLOR = new Color(18, 18, 24);
    public static final Color FOCUS_COLOR = new Color(20, 20, 40);
    public static final Color SHADOW_BLUE = new Color(50, 50, 65);
    public static final Color WHITE = new Color(238, 238, 238);
    public static final Color AQUA = new Color(20, 235, 175);
    private final int WIDTH;
    private final int HEIGHT;
    private DFrame openFrame = Init.winInit;
    
    static {
        /* Modification of a part of the predefined features of all labels'. */
        UIManager.put("Label.font", JBRAINS);
        UIManager.put("Label.background", BACKG_COLOR);
        UIManager.put("Label.foreground", WHITE);
        UIManager.put("Label.focus", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all separators'. */
        
        UIManager.put("Separators.background", BACKG_COLOR);

        /* Modification of a part of the predefined features of all spinners'. */
        UIManager.put("Spinner.font", JBRAINS);
        UIManager.put("Spinner.border", BorderFactory.createLineBorder(SHADOW_BLUE));
        UIManager.put("Spinner.focus", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all Combo Boxes'. */
        UIManager.put("ComboBox.font", JBRAINS);
        UIManager.put("ComboBox.background", BACKG_COLOR);
        UIManager.put("ComboBox.foreground", WHITE);
        UIManager.put("ComboBox.selectionBackground", FOCUS_COLOR);
        UIManager.put("ComboBox.selectionForeground", WHITE);
        UIManager.put("ComboBox.buttonBackground", SHADOW_BLUE);
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(SHADOW_BLUE));
        UIManager.put("ComboBox.selectionFocus", FOCUS_COLOR);
        UIManager.put("ComboBox.focus", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all editor panes'. */
        UIManager.put("EditorPane.font", JBRAINS);
        UIManager.put("EditorPane.caretForeground", WHITE);
        UIManager.put("EditorPane.background", BACKG_COLOR);
        UIManager.put("EditorPane.foreground", WHITE);
        UIManager.put("EditorPane.border", BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(SHADOW_BLUE, 2),
                        BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        UIManager.put("EditorPane.focus", FOCUS_COLOR);
        
        /* Modification of a part of the predefined features of all scroll bars'. */
        UIManager.put("ScrollBar.border", BorderFactory.createEmptyBorder());
        UIManager.put("ScrollBar.focus", FOCUS_COLOR);
        UIManager.put("ScrollBar.width", 12);
        UIManager.put("ScrollBar.background", BACKG_COLOR);
        UIManager.put("ScrollBar.thumbHighlight", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all scroll panes'. */
        UIManager.put("ScrollPane.border", BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(SHADOW_BLUE, 2),
                        BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        UIManager.put("ScrollPane.focus", FOCUS_COLOR);
        UIManager.put("ScrollPane.background", BACKG_COLOR);

        /* Modification of a part of the predefined features of all panels'. */
        UIManager.put("Panel.focus", FOCUS_COLOR);
        UIManager.put("Panel.background", BACKG_COLOR);

        /* Modification of a part of the predefined features of all text fields'. */
        UIManager.put("TextField.font", JBRAINS);
        UIManager.put("TextField.caretForeground", WHITE);
        UIManager.put("TextField.foreground", WHITE);
        UIManager.put("TextField.background", BACKG_COLOR);
        UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(SHADOW_BLUE, 2),
                        BorderFactory.createEmptyBorder(2, 2, 2, 2)));

        /* Modification of a part of the predefined features of all buttons'. */
        UIManager.put("Button.focus", FOCUS_COLOR);
        UIManager.put("Button.font", JBRAINS_BOLD);
        UIManager.put("Button.select", SHADOW_BLUE);
        UIManager.put("Button.background", AQUA);
        UIManager.put("Button.foreground", BACKG_COLOR);
        UIManager.put("Button.border", BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(WHITE), 
                            BorderFactory.createEmptyBorder(3, 3, 3, 3)
                            ));

        
        UIManager.put("OptionPane.background", BACKG_COLOR);
        UIManager.put("OptionPane.messageBackground", BACKG_COLOR);
        UIManager.put("OptionPane.messageForeground", WHITE);
    }

    /**
     * Coloca las medidas de la ventana, le quita el cambio de tamaño, y pone que se cierre solo la
     * ventana al presionar el boton de cerrar.
     */
    private void init() {
        this.setResizable(false);
        this.setBounds(0, 0, this.WIDTH, this.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    public DFrame getOpenFrame() {
        return this.openFrame;
    }

    public JPanel createSeparatorPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(5, 0, 5, 0),
                    BorderFactory.createMatteBorder(1, 0, 1, 0, SHADOW_BLUE)
                    ));
        return panel;
    }
}
