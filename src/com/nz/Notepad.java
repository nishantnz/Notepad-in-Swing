package com.nz;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

import static jdk.internal.net.http.common.Utils.close;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area = new JTextArea();
    JScrollPane pane = new JScrollPane(area);
    String text ;

    Notepad(){
        setBounds(0,0,1050,1050);


        area.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(pane, BorderLayout.CENTER);
        //removing border layout
        pane.setBorder(BorderFactory.createEmptyBorder());
        //calling setlinewarp function so that the word comes on the next line ...basically wrapping content
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu help = new JMenu("Help");

        //file
        JMenuItem newFile = new JMenuItem("New");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newFile.addActionListener(this);

        JMenuItem openFile = new JMenuItem("Open");
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        openFile.addActionListener(this);

        JMenuItem saveFile = new JMenuItem("Save");
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveFile.addActionListener(this);

        JMenuItem printFile = new JMenuItem("Print");
        printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printFile.addActionListener(this);

        JMenuItem exitFile = new JMenuItem("Exit");
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        exitFile.addActionListener(this);
        /*you can use  exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        if you directly want to click esc and exit
         */

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(exitFile);

        //edit
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAll.addActionListener(this);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);

        //help
        JMenuItem aboutUs = new JMenuItem("About us");
        help.add(aboutUs);
        aboutUs.addActionListener(this);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        setJMenuBar(menuBar);


    }
    public static void main(String[] args) {
       new Notepad().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New")){
           // new Notepad().setVisible(true);
            area.setText("");
        }else if(e.getActionCommand().equals("Open")){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only choose .txt","txt");
            fileChooser.addChoosableFileFilter(restrict);
            int action  = fileChooser.showOpenDialog(this);
            if(action!=JFileChooser.APPROVE_OPTION){
                    return;
              }
              File file = fileChooser.getSelectedFile();
              BufferedReader readFileData ;

                try {
                readFileData = new BufferedReader(new FileReader(file));
                area.read(readFileData,null);
                setTitle(fileChooser.getSelectedFile().toString());
                 }catch (Exception exception){
                    area.setText(exception.toString());
                }

//           / File fileName = new File(fileChooser.setSelectedFile());
//            //we use buffered writer to write the data...by basically saving it
//            BufferedReader readFileData ;
//            try {
//                readFileData = new BufferedReader(new FileReader(fileName));
//                area.read(readFileData);
//            }catch (Exception exception){
//
//            }

        }else if(e.getActionCommand().equals("Save")){
            //choosing files using class JfileChooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setApproveButtonText("Save");
            int action  = fileChooser.showOpenDialog(this);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            File fileName = new File(fileChooser.getSelectedFile()+".txt");
            //we use buffered writer to write the data...by basically saving it
            BufferedWriter writeFileData ;
            try {
                writeFileData = new BufferedWriter(new FileWriter(fileName));
                area.write(writeFileData);
            }catch (Exception exception){

            }
        }else if(e.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch (Exception exception){

            }
        }else if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }else if(e.getActionCommand().equals("Copy")){
           text =area.getSelectedText();
        }else if(e.getActionCommand().equals("Paste")){
         area.insert(text,area.getCaretPosition());
        }else if(e.getActionCommand().equals("Cut")){
            text =area.getSelectedText();
            area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
        }else if(e.getActionCommand().equals("Select All")){
         area.selectAll();
        }
    }
}
