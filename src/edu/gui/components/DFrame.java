package edu.gui.components;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import edu.main.Init;
import edu.obj.User;
import edu.gui.files.Icon;

import java.awt.*;
import java.awt.event.*;

/**
 * Clase para crear JFrame predeterminado
 */
public abstract class DFrame extends JFrame implements ActionListener, KeyListener {
    
    public static final Font JBRAINS_BOLD = new Font("JetBrainsMono Nerd Font Mono", 1, 15);
    public static final Font JBRAINS = new Font("JetBrainsMono NF", 1, 13);
    public static final Color BACKG_COLOR = new Color(18, 18, 24);
    public static final Color FOCUS_COLOR = new Color(20, 20, 40);
    public static final Color SHADOW_BLUE = new Color(50, 50, 65);
    public static final Color WHITE = new Color(238, 238, 238);
    public static final Color AQUA = new Color(20, 235, 175);
    private final int WIDTH;
    private final int HEIGHT;

    private static ImageIcon icon = Icon.VACIO.getIcon();

    private JLabel chargeLbl;
    private JPanel tittlePane;
    private JLabel tittleLbl;
    private JButton atrasBtn;

    private JPanel principalPanel;

    private JButton keyBtn;

    private DFrame openFrame = Init.winInit;
    private static User user;
    private static DFrame actualFrame;
    
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

        /* Modification of a part of the predefined features of all Menu'. */
        UIManager.put("MenuItem.font", JBRAINS);
        UIManager.put("MenuItem.background", BACKG_COLOR);
        UIManager.put("MenuItem.foreground", WHITE);
        UIManager.put("MenuItem.selectionBackground", FOCUS_COLOR);
        UIManager.put("MenuItem.selectionForeground", WHITE);
        UIManager.put("MenuItem.border", BorderFactory.createLineBorder(SHADOW_BLUE));
        UIManager.put("MenuItem.selectionFocus", FOCUS_COLOR);
        UIManager.put("MenuItem.focus", FOCUS_COLOR);

        /* Modification of a part of the predefined features of all Menu'. */
        UIManager.put("MenuItem.background", BACKG_COLOR);
        UIManager.put("MenuItem.border", BorderFactory.createLineBorder(SHADOW_BLUE));

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
        DFrame win = this;
        DFrame.actualFrame = this;
        this.getContentPane().setLayout(new BorderLayout());

        this.principalPanel = new JPanel();
        super.getContentPane().add(principalPanel, BorderLayout.CENTER);

        this.tittlePane = new JPanel(new FlowLayout(FlowLayout.LEFT, 35, 0));
        this.tittlePane.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 0, SHADOW_BLUE),
                        BorderFactory.createEmptyBorder(5, 0, 5, 10))
                    );
        super.getContentPane().add(tittlePane, BorderLayout.PAGE_START);

        this.tittleLbl = new JLabel(win.getTitle());
        this.tittleLbl.setForeground(AQUA);

        this.chargeLbl = new JLabel(icon);
        this.chargeLbl.setOpaque(true);

        this.atrasBtn = new JButton(Icon.ATRAS.getIcon());
        this.atrasBtn.setBackground(null);
        this.atrasBtn.setBorder(null);
        this.atrasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                win.dispose();
            }
        });
        this.atrasBtn.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                atrasBtn.setIcon(Icon.ATRAS_P.getIcon());
                if (openFrame != null)
                    tittleLbl.setText(openFrame.getTitle());
                else {
                    if (win == Init.winInit)
                        tittleLbl.setText("Cerrar Programa");
                    else
                        tittleLbl.setText("Cerrar Ventana");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                atrasBtn.setIcon(Icon.ATRAS.getIcon());
                tittleLbl.setText(win.getTitle());
            }
        });
        this.tittlePane.add(this.atrasBtn);
        this.tittlePane.add(this.chargeLbl);
        this.tittlePane.add(this.tittleLbl);

        this.setResizable(false);
        this.setBounds(0, 0, this.WIDTH, this.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());
    }

    /**
     * Pone el titulo que se ingresa, y las medidas predeterminadas 450 x 620.
     * @param tittle es el titulo de la ventana.
     */
    public DFrame(String tittle) {
        super(tittle);
        this.WIDTH = 450;
        this.HEIGHT = 660;
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
        this.HEIGHT = height + 37;
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
            actualFrame = openFrame;
            actualFrame.chargeLbl.setIcon(icon);
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

    public void back() {
        this.atrasBtn.doClick();
    }

    public JButton getBackBtn() {
        return this.atrasBtn;
    }

    public void setCharging(boolean b) {
        if (b) {
            icon = Icon.CARG.getIcon();
        } else {
            icon = Icon.VACIO.getIcon();
        }
        actualFrame.chargeLbl.setIcon(icon);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        actualFrame = this;
        actualFrame.chargeLbl.setIcon(icon);
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

    @Override
    public Container getContentPane() {
        return (principalPanel == null) ? super.getContentPane() : principalPanel;
    }

    public void setKeyBtn(JButton btn) {
        this.keyBtn = btn;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (keyBtn != null && e.getKeyChar() == KeyEvent.VK_ENTER) {
            keyBtn.doClick();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        DFrame.user = user;
    }
}
