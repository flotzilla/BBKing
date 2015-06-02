package com.flyonthewall.app;

import com.flyonthewall.entity.Approach;
import com.flyonthewall.entity.Day;
import com.flyonthewall.entity.Week;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: obyte
 * Date: 10.06.13
 * Time: 3:28
 * To change this template use File | Settings | File Templates.
 */
public class Gui extends javax.swing.JFrame {
    private JFileChooser jFileChooser;
    private File file;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;

    public Gui() {
        super("BBKing");
        initUImanager();
        initComponents();
    }

    private void initUImanager() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextArea1.setFont(new Font(Font.SANS_SERIF, 3, 12));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(300, 200);
        setResizable(false);

        jButton1.setText("Открыть");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser = new JFileChooser();
                int ret = jFileChooser.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = jFileChooser.getSelectedFile();
                    jLabel1.setText(file.getName());
                }
            }
        });

        jButton2.setText("Посчитать");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jTextArea1.setText("");
                Parser p = new Parser(file);
                if (p.validate(file)) {
                    Week week = p.parse();

                    List<Day> trainingDaysList = week.getTrainingDaysList();
                    for (int i = 0; i < trainingDaysList.size(); i++) {
                        jTextArea1.append("Day " + "\n");
                        List<Approach> approachList = trainingDaysList.get(i).getApproachList();
                        for (int j = 0; j < approachList.size(); j++) {
                            jTextArea1.append((j + 1) + ") " + approachList.get(j).toString());
                            jTextArea1.append("\n");
                        }
                        trainingDaysList.get(i).calculate();
                        jTextArea1.append("Tonnage: " + trainingDaysList.get(i).getMinTonnage() + " - "
                                + trainingDaysList.get(i).getMaxTonnage());
                        jTextArea1.append("\n");
                        jTextArea1.append("Lifts: " + trainingDaysList.get(i).getMinLifts() + " - "
                                + trainingDaysList.get(i).getMaxLifts());
                        jTextArea1.append("\n");
                        jTextArea1.append("\n");

                    }
                } else {
                    jLabel1.setText("Cannot read this file");
                }

            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                                                .addComponent(jButton1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
        setVisible(true);
    }


}

