package org.fengs.designpatterns;

import javax.swing.*;
import java.awt.*;

public class SwingLayoutExample {

    private static void createAndShowGUI() {
        // 创建窗体并设置标题
        JFrame frame = new JFrame("信贷收款系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // 使用边界布局

        // 创建上部面板并使用网格布局
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        // 添加标签和文本字段
        topPanel.add(new JLabel("账户："));
        topPanel.add(new JTextField("0.00"));
        topPanel.add(new JLabel("金额："));
        topPanel.add(new JTextField("0"));

        // 创建按钮面板并使用流式布局
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.add(new JButton("确认"));
        buttonPanel.add(new JButton("取消"));

        // 创建底部面板并使用流式布局
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        bottomPanel.add(new JLabel("总计："));
        bottomPanel.add(new JLabel("0.00"));

        // 将面板添加到窗体的不同区域
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // 调整窗体大小以适应内容
        frame.pack();
        // 设置窗体在屏幕中央
        frame.setLocationRelativeTo(null);
        // 设置窗体可见
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 推荐的Swing启动方式
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
