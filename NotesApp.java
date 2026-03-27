import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotesApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Mini Notes App");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // TEXT AREA
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);

        // TOP PANEL (Buttons)
        JPanel topPanel = new JPanel();

        JButton newBtn = new JButton("New");
        JButton openBtn = new JButton("Open");
        JButton saveBtn = new JButton("Save");
        JButton clearBtn = new JButton("Clear");

        topPanel.add(newBtn);
        topPanel.add(openBtn);
        topPanel.add(saveBtn);
        topPanel.add(clearBtn);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // NEW
        newBtn.addActionListener(e -> textArea.setText(""));

        // CLEAR
        clearBtn.addActionListener(e -> textArea.setText(""));

        // SAVE
        saveBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int option = fileChooser.showSaveDialog(frame);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(textArea.getText());
                    JOptionPane.showMessageDialog(frame, "File Saved!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // OPEN
        openBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int option = fileChooser.showOpenDialog(frame);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                    textArea.setText("");
                    String line;

                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }
}
