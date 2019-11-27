package gui;

import base.parse.Parser;
import base.parse.Lexer;

import base.tree.exception.NullBranchException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcWidget extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    
    JTextField _textbox;
    JLabel _output;

    public CalcWidget() {
        setLayout(new GridLayout(2, 1));
        JPanel input = new JPanel();
        input.setLayout(new BorderLayout());

        JButton inputbutton = new JButton("Calculate");
        inputbutton.addActionListener(this);

        // setting up the 'enter' key listener
        Action enter = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                inputbutton.doClick();
            }
        };

        _textbox = new JTextField(20);
        _textbox.addActionListener(enter);
        input.add(_textbox);


        input.add(inputbutton, BorderLayout.EAST);

        add(input);

        _output = new JLabel("\n");
        add(_output);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String result;
        try {
            result = Double.toString(Parser.parse(Lexer.tokens(_textbox.getText())).calculate());
        } catch (NullBranchException f) {
            result = "null branch error";
        } catch (IllegalArgumentException g) {
            result = g.getMessage();
        }
        System.out.println(result);
        _output.setText(result);
    }
    
}