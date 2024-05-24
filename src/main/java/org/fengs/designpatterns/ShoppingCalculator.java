package org.fengs.designpatterns;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ShoppingCalculator extends JFrame {
    private JTextField priceField, quantityField,howMuchIsFullField,howMuchToSubtractField,
    score1,score2;
    private JButton calculateButton, resetButton;
    private JComboBox<String> discountComboBox;
    private JList<String> itemList;
    private DefaultListModel<String> listModel;
    private JLabel totalLabel;
    private double total = 0.0;
    private DecimalFormat df = new DecimalFormat("#.##"); // 格式化小数

    public ShoppingCalculator() {
        // 创建组件
        priceField = new JTextField(10);
        quantityField = new JTextField(10);
        howMuchIsFullField = new JTextField(10);
        howMuchToSubtractField = new JTextField(10);
        score1 = new JTextField(10);
        score2 = new JTextField(10);
        calculateButton = new JButton("确定");
        resetButton = new JButton("重置");
        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        totalLabel = new JLabel("总计: 0.0");

        // 创建一个空边框作为内边距
        Border padding = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        ((JComponent) getContentPane()).setBorder(padding);
        
        // 折扣下拉框
        String[] discounts = {"正常收费", "满300减100", "打8折"};
        discountComboBox = new JComboBox<>(discounts);

        // 布局
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // 创建用于单行显示的Box
        Box priceBox = Box.createHorizontalBox();
        priceBox.add(new JLabel("单价:"));
        priceBox.add(priceField);
        priceBox.add(calculateButton);
        add(priceBox);


        Box quantityBox = Box.createHorizontalBox();
        quantityBox.add(new JLabel("数量:"));
        quantityBox.add(quantityField);
        quantityBox.add(resetButton);
        add(quantityBox);

        Box discountBox = Box.createHorizontalBox();
        discountBox.add(new JLabel("折扣:"));
        discountBox.add(discountComboBox);
        add(discountBox);
//
//        Box returnBox = Box.createHorizontalBox();
//        returnBox.add(new JLabel("满"));
//        returnBox.add(howMuchIsFullField);
//        returnBox.add(new JLabel("减"));
//        returnBox.add(howMuchToSubtractField);
//        add(returnBox);
//
//        Box scoreBox = Box.createHorizontalBox();
//        scoreBox.add(new JLabel("满"));
//        scoreBox.add(score1);
//        scoreBox.add(new JLabel("返"));
//        scoreBox.add(score2);
//        add(scoreBox);

        // 记录
        add(new JScrollPane(itemList));

        // 创建一个水平盒子，用于放置总计标签
        Box totalBox = Box.createHorizontalBox();
        totalBox.add(totalLabel);
        totalBox.add(Box.createHorizontalGlue()); // 添加弹性空间，使得标签靠左对齐
        add(Box.createVerticalStrut(10));

        add(totalBox);

        // 添加按钮的事件监听器
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        // 设置窗口属性
        setTitle("商场收银系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // 居中显示
        setVisible(true);
    }

    private void calculateTotal() {
        // 获取输入
        String priceText = priceField.getText();
        String quantityText = quantityField.getText();
        String discountText = (String) discountComboBox.getSelectedItem();

        try {
            // 解析单价和数量
            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);
//            double discount = 1.0;
//
//            // 根据选择应用折扣
//            switch (discountText) {
//                case "不打折":
//                    discount = 1.0;
//                    break;
//                case "八折":
//                    discount = 0.8;
//                    break;
//                case "七折":
//                    discount = 0.7;
//                    break;
//                case "五折":
//                    discount = 0.5;
//                    break;
//            }

//            CashSuper csuper = CashFactory.createCashAdapter(discountText);
            // 计算费用并应用折扣
//            double cost = csuper.acceptCash(price * quantity);


            double cost = new CashContext(discountText).getResult(price * quantity);

                    // 更新列表和总计
            listModel.addElement("单价: " + price + " 数量: " + quantity + " 折扣: " + discountText + " 费用: " + df.format(cost));
            total += cost;
            totalLabel.setText("总计: " + df.format(total));

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "请输入有效的数字", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        // 重置所有字段
        priceField.setText("");
        quantityField.setText("");
        howMuchIsFullField.setText("");
        howMuchToSubtractField.setText("");
        score1.setText("");
        score2.setText("");
        discountComboBox.setSelectedIndex(0); // 重置折扣选择
        listModel.clear();
        total = 0.0;
        totalLabel.setText("总计: 0.0");
    }

    public static void main(String[] args) {
        // 运行程序
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingCalculator();
            }
        });
    }
}
