package edu.gui.windows;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import edu.gui.components.DFrame;
import edu.maker.imp.*;

public class Import extends DFrame {
    private JLabel path;
    private JButton slctBtn;
    private JEditorPane txtMsg;
    private JScrollPane scrollPanel;

    public Import() {
        super("Import zone", 535, 590);
        JPanel win = (JPanel) (this.getContentPane());
        win.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));

        path = new JLabel();
        path.setForeground(DFrame.AQUA);
        path.setPreferredSize(new Dimension(385, 30));
        win.add(path);

        slctBtn = new JButton("Select File");
        slctBtn.addActionListener(this);
        win.add(slctBtn);
        
        txtMsg = new JEditorPane();
		txtMsg.setContentType("text/html");
        txtMsg.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        txtMsg.setEditable(false);
        
        scrollPanel = new JScrollPane(txtMsg);
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        scrollPanel.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        win.add(scrollPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == slctBtn) {
            ObjectMaker.cargarObjetos(this, txtMsg);
            try {path.setText(ObjectMaker.getFileName());} catch(NullPointerException ex) {}
        }
    }
    
}
