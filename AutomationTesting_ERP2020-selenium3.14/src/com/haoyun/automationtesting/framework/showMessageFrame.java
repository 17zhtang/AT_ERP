package com.haoyun.automationtesting.framework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.test.aadomain.MainStart;

public class showMessageFrame extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel text;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screensize = tk.getScreenSize();
    int height = screensize.height;
    int width = screensize.width;
    
    
    @Test
    public void test(){
    	waitGUI("test",5000);
    }

/**
 * 弹出窗口，time后消失
 * @param str 内容
 * @param time 时间，单位毫秒
 */
    public void waitGUI(String str,int time) {
        try {
            setUndecorated(true);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                text = new JLabel("<html>" + str + "</html>", SwingConstants.CENTER);
                getContentPane().add(text, BorderLayout.CENTER);
                text.setBackground(new java.awt.Color(255, 251, 240));
            }
            pack();
            setBounds(width / 2-180, height - 350, 560, 100);
            try {
            	Thread.sleep(time/4);
            	MainStart.driver.navigate().refresh();
                Thread.sleep(time/4);
                MainStart.driver.navigate().refresh();
                Thread.sleep(time/4);
                MainStart.driver.navigate().refresh();
                Thread.sleep(time/4);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}