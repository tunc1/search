package app.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View
{
    private JFrame frame;
    private JList<String> jList;
    private JButton button;
    private JTextField textField;
    private DefaultListModel<String> listModel;
    public View()
    {
        createUI();
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
        button.setBounds(330,50,100,30);
        frame.add(button);
        textField=new JTextField();
        textField.setBounds(330,10,150,20);
        frame.add(textField);
    }
    public void show()
    {
        frame.setVisible(true);
    }
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(frame,message);
    }
    public void onSearchButtonClicked(Runnable runnable)
    {
        button.addActionListener(e->runnable.run());
    }
    public void onListItemDoubleClicked(Runnable runnable)
    {
        jList.addMouseListener(new MouseListener()
        {
            public void mouseReleased(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseClicked(MouseEvent event)
            {
                if(event.getClickCount()==2)
                    runnable.run();
            }
        });
    }
    public void clearList()
    {
        listModel.clear();
    }
    public String getSelectedValue()
    {
        return jList.getSelectedValue();
    }
    public void addToList(String item)
    {
        listModel.addElement(item);
    }
    public String getTextToSearch()
    {
        return textField.getText();
    }
    public void setSearchButtonEnabled(boolean enabled)
    {
        button.setEnabled(enabled);
    }
}
