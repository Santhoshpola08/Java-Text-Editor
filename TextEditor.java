import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class TextEditor extends JFrame implements ActionListener {
    
    JFrame jf;
    JTextArea text_area;
    JMenuBar menu_bar;
    TextEditor() {
        jf= new JFrame("Java Text Editor");
        text_area= new JTextArea();
        menu_bar= new JMenuBar();
        
        JMenu file_menu= new JMenu("File");
        file_menu.setBackground(Color.GRAY);

        JMenuItem f_item_1= new JMenuItem("New");
        JMenuItem f_item_2= new JMenuItem("Open");
        JMenuItem f_item_3= new JMenuItem("Save");
        JMenuItem f_item_4= new JMenuItem("Save as");
        JMenuItem f_item_5= new JMenuItem("Exit");

        f_item_1.addActionListener(this);
        f_item_2.addActionListener(this);
        f_item_3.addActionListener(this);
        f_item_4.addActionListener(this);
        f_item_5.addActionListener(this);
        
        file_menu.add(f_item_1);
        file_menu.add(f_item_2);
        file_menu.add(f_item_3);
        file_menu.add(f_item_4);
        file_menu.add(f_item_5);
        
        menu_bar.add(file_menu);

        JMenu edit_menu= new JMenu("Edit");
        edit_menu.setBackground(Color.GRAY);

        JMenuItem e_item_1= new JMenuItem("Cut");
        JMenuItem e_item_2= new JMenuItem("Copy");
        JMenuItem e_item_3= new JMenuItem("Paste");
        JMenuItem e_item_4= new JMenuItem("Delete");
        
        e_item_1.addActionListener(this);
        e_item_2.addActionListener(this);
        e_item_3.addActionListener(this);
        e_item_4.addActionListener(this);

        edit_menu.add(e_item_1);
        edit_menu.add(e_item_2);
        edit_menu.add(e_item_3);
        edit_menu.add(e_item_4);
        
        menu_bar.add(edit_menu);

        JMenu view_menu= new JMenu("View");
        view_menu.setBackground(Color.GRAY);

        JMenuItem v_item_1= new JMenuItem("Zoom");
        JMenuItem v_item_2= new JMenuItem("Status bar");
        JMenuItem v_item_3= new JMenuItem("Word wrap");

        v_item_1.addActionListener(this);
        v_item_2.addActionListener(this);
        v_item_3.addActionListener(this);

        view_menu.add(v_item_1);
        view_menu.add(v_item_2);
        view_menu.add(v_item_3);

        menu_bar.add(view_menu);
        
        jf.setJMenuBar(menu_bar);
        jf.add(text_area);
        jf.setSize(500,500);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cur_item= e.getActionCommand();

        if(cur_item.equals("Cut")){
            text_area.cut();
        }
        else if(cur_item.equals("Copy")) {
            text_area.copy();
        }
        else if(cur_item.equals("Paste")) {
            text_area.paste();
        }
        else if(cur_item.equals("Delete")) {
            text_area.replaceSelection("");
        }
        else if(cur_item.equals("Open")) {
            JFileChooser file_ch= new JFileChooser();
            int res= file_ch.showOpenDialog(jf);
            if(res==JFileChooser.APPROVE_OPTION) {
                File file= new File(file_ch.getSelectedFile().getAbsolutePath());

                try (FileReader fr = new FileReader(file)) {
                    BufferedReader br= new BufferedReader(fr);

                    String line="", file_content= "";

                    while((line= br.readLine())!=null) {
                        file_content= file_content+"\n"+line;
                    }
                    text_area.setText(file_content);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        }
        else if(cur_item.equals("New")) {
            text_area.setText("");

        }
        else if(cur_item.equals("Save")) {
            JFileChooser file_chooser= new JFileChooser();
            int result= file_chooser.showSaveDialog(jf);
            if(result==JFileChooser.APPROVE_OPTION) {
                File file= new File(file_chooser.getSelectedFile().getAbsolutePath());

                try (FileWriter fw = new FileWriter(file, false)) {
                    BufferedWriter bw= new BufferedWriter(fw);

                    bw.write(text_area.getText());
                    bw.flush();
                    bw.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        else if(cur_item.equals("Save")|| cur_item.equals("Save as")) {
            JFileChooser file_chooser= new JFileChooser();
            int result= file_chooser.showSaveDialog(jf);
            if(result==JFileChooser.APPROVE_OPTION) {
                File file= new File(file_chooser.getSelectedFile().getAbsolutePath());

                try (FileWriter fw = new FileWriter(file, false)) {
                    BufferedWriter bw= new BufferedWriter(fw);

                    bw.write(text_area.getText());
                    bw.flush();
                    bw.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        
        else if(cur_item.equals("Exit")) {
            int response= JOptionPane.showConfirmDialog(null, "Do you want to save your file?","Confirm Exit", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(response==JOptionPane.YES_OPTION){
                
                JFileChooser file_chooser= new JFileChooser();
            int result= file_chooser.showSaveDialog(jf);
            if(result==JFileChooser.APPROVE_OPTION) {
                File file= new File(file_chooser.getSelectedFile().getAbsolutePath());

                try (FileWriter fw = new FileWriter(file, false)) {
                    BufferedWriter bw= new BufferedWriter(fw);
                    bw.write(text_area.getText());
                    bw.flush();
                    bw.close();
                } catch (IOException e1) {
                 // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

                System.exit(0);
            }
            else if(response== JOptionPane.NO_OPTION){
                System.exit(0);
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
