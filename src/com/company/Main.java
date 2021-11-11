package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static JFrame ventana;
    static JTextArea notas;

    public static void main(String[] args) {
        blocdenotas();
    }

    public static void blocdenotas() {

        ventana = new JFrame("Bloc");


        JMenuBar menu = new JMenuBar();

        JMenu archivo = new JMenu("Archivo");
        JMenu ayuda = new JMenu("Ayuda");
        JMenu copy = new JMenu("Copyright");

        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem abrir = new JMenuItem("Abrir...");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem salir = new JMenuItem("Salir");
        JMenuItem ayudaitem = new JMenuItem("Ayuda");
        JMenuItem copyyy = new JMenuItem("Antocantos");


        copy.add(copyyy);
        archivo.add(nuevo);
        archivo.add(guardar);
        archivo.add(abrir);
        archivo.add(salir);
        ayuda.add(ayudaitem);

        menu.add(archivo);
        menu.add(ayuda);
        menu.add(copy);

        ventana.setJMenuBar(menu);


        notas = new JTextArea();
        JScrollPane scrollNotas = new JScrollPane(notas);
        ventana.add(scrollNotas);


        ventana.setSize(1366, 728);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notas.setText("");
            }
        });

        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo();
            }
        });

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ayudaitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public static void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //Muestra solo los txt archivos
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);

        //Lo guarda en TXT el archivo
        File archivotxt = new File(fileChooser.getSelectedFile().getAbsolutePath()+ ".txt");

        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(ventana)) {
            File archivo = fileChooser.getSelectedFile();
            FileReader lector = null;
            try {
                lector = new FileReader(archivo);
                BufferedReader bfReader = new BufferedReader(lector);

                String lineaFichero;
                StringBuilder contenidoFichero = new StringBuilder();

                //Recuperar contenido fichero
                while ((lineaFichero = bfReader.readLine()) != null) {
                    contenidoFichero.append(lineaFichero);
                    contenidoFichero.append("\n");
                }
                //Pone contenido en el area de texto
                notas.setText(contenidoFichero.toString());


            } catch (FileNotFoundException e) {
                Logger.getLogger(blocdenotas.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException e) {
                Logger.getLogger(blocdenotas.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                try {
                    lector.close();

                } catch (IOException ex) {
                    Logger.getLogger(blocdenotas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }


    public static void guardarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);

        //Lo guarda en TXT el archivo


        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(ventana)) {
            File archivo= new File(fileChooser.getSelectedFile().getAbsolutePath()+ ".txt");
            FileWriter escritor = null;
            try {
                escritor = new FileWriter(archivo);
                escritor.write(notas.getText());
            } catch (FileNotFoundException e) {
                Logger.getLogger(blocdenotas.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException e) {
                Logger.getLogger(blocdenotas.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                try {
                    escritor.close();

                } catch (IOException ex) {
                    Logger.getLogger(blocdenotas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}

