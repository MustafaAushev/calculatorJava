package com.github.AushevMus;
import java.awt.*;
import javax.swing.*;

public class Calculator
{
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CalculatorFrame frame = new CalculatorFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
