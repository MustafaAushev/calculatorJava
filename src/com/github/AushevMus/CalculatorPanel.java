package com.github.AushevMus;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CalculatorPanel extends JPanel {
    public CalculatorPanel() {
        setLayout(new BorderLayout());

        result = 0.0;
        lastCommand = "=";
        start=true;

        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);

        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("/", command);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("*", command);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("-", command);

        addButton("0", insert);
        addButton(".", insert);
        addButton("=", command);
        addButton("+", command);

        add(panel, BorderLayout.CENTER);
    }
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }
    private class InsertAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String input = event.getActionCommand();
            if(start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }
    private class CommandAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String command = event.getActionCommand();
            calculate(Double.parseDouble(display.getText()));
            lastCommand = command;
            start=true;
        }
    }

    int temp = 1000000;
    double temp2 = 1000000.0;
    public void calculate(double x)
    {
        if(lastCommand.equals("+")) result += x;
        else if(lastCommand.equals("-")) result = (Math.abs(Math.round((result - x)*temp)/temp2 - (result - x)) < (1/temp2)) ? Math.round((result - x)*temp)/temp2 : (result - x);
        else if(lastCommand.equals("*")) result = (Math.abs(Math.round((result * x)*temp)/temp2 - (result * x)) < (1/temp2)) ? Math.round((result * x)*temp)/temp2 : (result * x);
        else if(lastCommand.equals("/"))
        {
            if (x == 0.0)
                {
                    display.setText("Error");
                    return;
                }
            result = (Math.abs(Math.round((result / x)*temp)/temp2 - (result / x)) < (1/temp2)) ? Math.round((result / x)*temp)/temp2 : (result / x);
        }
        else if(lastCommand.equals("=")) result = x;
        display.setText("" + result);
    }
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
}
