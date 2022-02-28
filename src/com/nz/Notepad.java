package com.nz;

import javax.swing.*;

public class Notepad extends JFrame {
    Notepad(){
        setBounds(0,0,1050,1050);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu help = new JMenu("Help");

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        setJMenuBar(menuBar);
    }
    public static void main(String[] args) {
       new Notepad().setVisible(true);
    }
}
