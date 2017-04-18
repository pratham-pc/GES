package ges;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ShapesManager {


    private ArrayList<ArrayList<Shape>> history;


    int historyIndex;


    private static ShapesManager instance = null;


    private ShapesManager() {
        history = new ArrayList<>();
        history.add(new ArrayList<Shape>());
        historyIndex = 0;
    }


    public static ShapesManager getInstance() {
        if (instance == null) {
            instance = new ShapesManager();
        }
        return instance;
    }


    public ArrayList<Shape> undo() {
        if (historyIndex == 0) {
            return null;
        }
        GUI.getInstance().undo.setEnabled(historyIndex - 1 == 0 ? false : true);
        GUI.getInstance().redo.setEnabled(true);
        ArrayList<Shape> temp = new ArrayList<>();
        for (int i = 0; i < history.get(historyIndex - 1).size(); i++) {
            temp.add(history.get(historyIndex - 1).get(i).clone());
        }
        historyIndex--;

        return new ArrayList<Shape>(temp);
    }


    public ArrayList<Shape> redo() {
        if (historyIndex == history.size() - 1) {
            return null;
        }
        GUI.getInstance().undo.setEnabled(true);
        GUI.getInstance().redo.setEnabled(historyIndex + 1 == history.size() - 1 ? false : true);
        ArrayList<Shape> temp = new ArrayList<>();
        for (int i = 0; i < history.get(historyIndex + 1).size(); i++) {
            temp.add(history.get(historyIndex + 1).get(i).clone());
        }
        historyIndex++;
        return new ArrayList<Shape>(temp);
    }


    public void addToHistory() {
        while (historyIndex != history.size() - 1) {
            history.remove(history.size() - 1);
        }
        ArrayList<Shape> temp = new ArrayList<>();
        for (int i = 0; i < GUI.getInstance().shapes.size(); i++) {
            temp.add(GUI.getInstance().shapes.get(i).clone());
        }

        history.add(temp);
        historyIndex++;
        GUI.getInstance().undo.setEnabled(true);
        GUI.getInstance().redo.setEnabled(false);
    }


    public Class<?> loadClass(File source, String name) {

        try {

            String path = new File("").getAbsolutePath() + File.separatorChar + "eg" + File.separatorChar + "edu"
                    + File.separatorChar + "alexu" + File.separatorChar + "csd" + File.separatorChar + "oop"
                    + File.separatorChar + "paint" + File.separatorChar;
            new File(path).mkdirs();
            Files.copy(new File(source.toPath() + "" +  File.separatorChar + name).toPath(), new File(path + name).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            source = new File(new File("").getAbsolutePath());
            java.net.URL url = source.toURI().toURL();
            java.net.URL[] urls = new java.net.URL[]{url};

            // load this folder into Class loader
            ClassLoader cl = new URLClassLoader(urls);

            Class<?> cls = cl.loadClass("ges." + name.substring(0, name.indexOf('.')));

            try {
                File temp = new File(path + name);
                while (temp.getPath().contains("eg")) {
                    Files.delete(temp.toPath());
                    temp = temp.getParentFile();
                }
            } catch (Exception e) {
            }

            return cls;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:\n" + e);
        }
        return null;
    }

    public void saveJPEG(File file) {
        BufferedImage img = new BufferedImage(GUI.getInstance().canvas.getWidth(), GUI.getInstance().canvas.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        GUI.getInstance().canvas.print(g2d);
        g2d.dispose();

        try {
            ImageIO.write(img, "jpeg", file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

}
