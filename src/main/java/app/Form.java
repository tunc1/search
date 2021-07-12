package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Form
{
    private static Form instance;
    public static Form getInstance()
    {
        if(instance==null)
            instance=new Form();
        return instance;
    }
    private JFrame frame;
    private JList<String> jList;
    private JButton button;
    private JTextField textField;
    private DefaultListModel<String> listModel;
    private String basePath;
    private Chain chain;
    private Form()
    {
        chain=new ChainExcelFile(new ChainTxtFile(null));
        basePath=System.getProperty("user.dir");
        createUI();
        createListeners();
    }
    private void createListeners()
    {
        button.addActionListener(e->search());
        jList.addMouseListener(new MouseListener()
        {
            public void mouseReleased(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseClicked(MouseEvent event)
            {
                if(event.getClickCount()==2)
                    openFile(basePath+"\\"+jList.getSelectedValue());
            }
        });
    }
    private void createUI()
    {
        frame=new JFrame("Search");
        frame.setBounds(100,100,600,400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(10,10,300,300);
        frame.add(panel);
        listModel=new DefaultListModel<>();
        jList=new JList<>(listModel);
        panel.add(jList);
        JScrollPane scrollPane=new JScrollPane(jList);
        panel.add(scrollPane);
        button=new JButton("Search");
        button.setBounds(330,60,100,30);
        frame.add(button);
        textField=new JTextField();
        textField.setBounds(330,10,150,30);
        frame.add(textField);
    }
    public void show()
    {
        frame.setVisible(true);
    }
    private void openFile(String path)
    {
        File file=new File(path);
        try
        {
            Desktop.getDesktop().open(file);
        }
        catch(Exception e)
        {
            errorMessage(e);
        }
    }
    private void errorMessage(Exception e)
    {
        showMessage(e.getClass().getName()+": "+e.getMessage());
    }
    private void showMessage(String message)
    {
        JOptionPane.showMessageDialog(frame,message);
    }
    private void search()
    {
        String text=textField.getText();
        listModel.removeAllElements();
        Runnable runnable=()->
        {
            searchInFolder(text,basePath);
            showMessage("Search Has Finished");
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }
    private void searchInFolder(String text,String path)
    {
        try
        {
            File folder=new File(path);
            if(folder.listFiles()!=null)
            {
                for(File file:folder.listFiles())
                {
                    if(file.isDirectory())
                        searchInFolder(text,file.getAbsolutePath());
                    else
                    {
                        if(chain.doAction(file,text))
                            addFoundedFile(file);
                    }
                }
            }
        }
        catch(Exception e)
        {
            errorMessage(e);
        }
    }
    private void addFoundedFile(File file)
    {
        listModel.addElement(file.getPath().substring(basePath.length()+1));
    }
}