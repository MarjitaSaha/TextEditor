import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menubar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;

    TextEditor() {
        frame = new JFrame();
        menubar = new JMenuBar();
        textArea = new JTextArea();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close Window");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        menubar.add(file);
        menubar.add(edit);
        frame.setJMenuBar(menubar);
        frame.setBounds(100, 100, 800, 500);
        frame.setVisible(true);
        frame.add(textArea);
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            System.exit(0);
        }
        if (actionEvent.getSource() == newFile) {
            TextEditor newWindow = new TextEditor();
        }
        if (actionEvent.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\user\\OneDrive\\Desktop");
            int chooseOption = fileChooser.showOpenDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String intermediate = "", output = "";
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output = output + intermediate + "\n";
                    }
                    textArea.setText(output);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\user\\OneDrive\\Desktop");
            int chooseOption = fileChooser.showSaveDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();

                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        }
    }
}