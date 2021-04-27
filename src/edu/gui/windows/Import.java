package edu.gui.windows;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import edu.gui.components.DFrame;

public class Import extends DFrame {
    private int aprovedInt;
    private JLabel path;
    private JButton slctBtn;
    private JTextArea txtErrors;
    private JScrollPane scrollPanel;
    private JFileChooser slctFile;
    private FileNameExtensionFilter filter;

    public Import() {
        super("Import zone", 535, 590);
        JPanel win = (JPanel) (this.getContentPane());
        win.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));

        path = new JLabel();
        path.setPreferredSize(new Dimension(385, 30));
        win.add(path);

        slctBtn = new JButton("Select File");
        slctBtn.addActionListener(this);
        win.add(slctBtn);
        
        txtErrors = new JTextArea(3, 3);
        txtErrors.setEditable(false);
        
        scrollPanel = new JScrollPane(txtErrors);
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        scrollPanel.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        win.add(scrollPanel);

        filter = new FileNameExtensionFilter("Text files", "txt");

        slctFile = new JFileChooser();
        slctFile.setFileFilter(filter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == slctBtn) {
            aprovedInt = slctFile.showOpenDialog(this);
            if (aprovedInt == JFileChooser.APPROVE_OPTION) {
                path.setText(slctFile.getSelectedFile().getName());
                try (FileInputStream fileIn = new FileInputStream(slctFile.getSelectedFile());) {
                    InputStreamReader reader = new InputStreamReader(fileIn);
                    BufferedReader br = new BufferedReader(reader);
                    String line;
    
                    txtErrors.setText("");
                    while ((line = br.readLine()) != null) {
                        if (txtErrors.getText().equals("")) {
                            txtErrors.setText(line);
                        } else {
                            txtErrors.setText(txtErrors.getText() + "\n" + line);
                        }
                    }
    
                } catch (FileNotFoundException exec) {
                    JOptionPane.showMessageDialog(this, "Error 404, file not fount",
                                            "Error 404", JOptionPane.ERROR_MESSAGE);
                } catch (IOException exec) {
                    JOptionPane.showMessageDialog(this, "Error trying to close the file.",
                                            "File Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
}
