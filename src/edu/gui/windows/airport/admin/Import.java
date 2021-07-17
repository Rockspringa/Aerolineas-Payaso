package edu.gui.windows.airport.admin;

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

    public Import(DFrame frame) {
        super("Import zone", 535, 590);
        setOpenFrame(frame);
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
            DFrame win = this;
            try {
                new Thread() {
                    @Override
                    public void run() {
                        win.setCharging(true);
                        path.setText(ObjectImp.impObj(win, txtMsg));
                    }
                }.start();
            } catch(NullPointerException ex) {}
        }
    }
}
