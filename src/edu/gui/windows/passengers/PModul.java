package edu.gui.windows.passengers;

import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.gui.components.DFrame;

public class PModul extends DFrame {
    private JLabel textLbl;
    private JButton unTicketBtn;
    private JButton ticketsBtn;

    public PModul() {
        super("Passengers Modul", 400, 170);
        JPanel win = (JPanel) (this.getContentPane());
        win.setBorder(BorderFactory.createEmptyBorder(4, 5, 4, 5));

        textLbl = new JLabel("<html><br><div>¿Desea comprar boletos solo para usted? o,</div>"
                        + "<div>¿Desea comprar boletos para mas personas?</div><br>");
        win.add(textLbl);

        unTicketBtn = new JButton("  Solo para uno  ");
        unTicketBtn.addActionListener(this);
        win.add(unTicketBtn);

        win.add(new JSeparator(JSeparator.VERTICAL));
        win.add(new JSeparator(JSeparator.VERTICAL));

        ticketsBtn = new JButton(" Para mas de uno ");
        ticketsBtn.addActionListener(this);
        win.add(ticketsBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == unTicketBtn) {
            this.dispose(false);
            new TicketSale(this).open();
        }
    }
    
}
