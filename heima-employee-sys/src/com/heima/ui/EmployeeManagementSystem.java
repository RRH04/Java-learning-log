// EmployeeManagementSystem.java
package com.heima.ui;
import com.heima.bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class EmployeeManagementSystem extends JFrame {
    private JTable employeeTable; // 员工表格
    private DefaultTableModel tableModel; // 表格模型
    private JTextField searchField; // 搜索框
    private JButton searchButton; // 搜索按钮
    private JButton addButton; // 添加按钮
    private JPopupMenu popupMenu; // 右键菜单
    private JMenuItem editMenuItem; // 修改菜单项
    private JMenuItem deleteMenuItem; // 删除菜单项
    private static ArrayList<Employee> employees = new ArrayList<>(); // 存储员工对象

    // 列名
    private String[] columnNames = {"ID", "姓名","部门", "职位", "电话", "年龄", "入职时间","薪水"};

    // 构造方法
    public EmployeeManagementSystem(String Username) {
        setTitle("员工信息管理系统 ----------------------用户: "+ Username);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 初始化员工数据
        initEmployees();

        // 初始化组件
        initComponents();

        // 布局组件
        layoutComponents();

        // 添加事件监听器
        addEventListeners();

        setVisible(true);
    }

    // 初始化员工数据
    private void initEmployees() {
        // 使用Employee类创建员工对象
        employees.add(new Employee("1", "张三", "IT", "开发", "13800000001", "25", "2023-01-01", "5000"));

    }

    // 将员工列表转换为表格数据
    private Object[][] convertEmployeesToData() {
        Object[][] data = new Object[employees.size()][columnNames.length];
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            data[i][0] = emp.getId();
            data[i][1] = emp.getName();
            data[i][2] = emp.getDepartment();
            data[i][3] = emp.getPosition();
            data[i][4] = emp.getPhone();
            data[i][5] = emp.getAge();
            data[i][6] = emp.getEntryTime();
            data[i][7] = emp.getSalary();
        }
        return data;
    }

    private void initComponents() {
        // 初始化表格模型和表格
        tableModel = new DefaultTableModel(convertEmployeesToData(), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格不可直接编辑
            }
        };
        employeeTable = new JTable(tableModel);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeTable.getTableHeader().setReorderingAllowed(false);

        // 初始化搜索框和按钮
        searchField = new JTextField(20);
        searchButton = new JButton("搜索");
        //刷新按钮
        addButton = new JButton("添加员工");

        // 初始化右键菜单
        popupMenu = new JPopupMenu();
        editMenuItem = new JMenuItem("修改");
        deleteMenuItem = new JMenuItem("删除");
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // 顶部面板 - 搜索框和按钮
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JLabel("搜索:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // 中间面板 - 表格
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addEventListeners() {
        // 添加按钮事件
        addButton.addActionListener(e -> addEmployee());

        // 搜索按钮事件
        searchButton.addActionListener(e -> searchEmployee());

        // 搜索框回车事件
        searchField.addActionListener(e -> searchEmployee());

        // 表格右键菜单事件
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleTableMouseEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleTableMouseEvent(e);
            }
        });

        // 修改菜单项事件
        editMenuItem.addActionListener(e -> editEmployee());

        // 删除菜单项事件
        deleteMenuItem.addActionListener(e -> deleteEmployee());
    }

    private void handleTableMouseEvent(MouseEvent e) {
        // 检查是否为右键点击
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = employeeTable.rowAtPoint(e.getPoint());
            if (row >= 0 && row < employeeTable.getRowCount()) {
                employeeTable.setRowSelectionInterval(row, row);
                popupMenu.show(employeeTable, e.getX(), e.getY());
            }
        }
    }


    private void addEmployee() {
        // 创建添加员工对话框
        JDialog addDialog = new JDialog(this, "添加员工", true);
        addDialog.setSize(400, 350);
        addDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件之间的间距
        gbc.anchor = GridBagConstraints.WEST; // 将组件居左对齐

        // 创建输入组件
        JTextField idField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField deptField = new JTextField(20);
        JTextField positionField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JTextField hireDateField = new JTextField(20);
        JTextField salaryField = new JTextField(20);

        // 布局组件
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("员工编号:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("姓名:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("部门:"), gbc);
        gbc.gridx = 1;
        panel.add(deptField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("职位:"), gbc);
        gbc.gridx = 1;
        panel.add(positionField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("电话:"), gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("年龄:"), gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("入职时间:"), gbc);
        gbc.gridx = 1;
        panel.add(hireDateField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("薪水:"), gbc);
        gbc.gridx = 1;
        panel.add(salaryField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("保存");
        JButton cancelButton = new JButton("取消");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // 保存按钮事件
        saveButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String dept = deptField.getText().trim();
            String position = positionField.getText().trim();
            String phone = phoneField.getText().trim();
            String age = ageField.getText().trim();
            String hireDate = hireDateField.getText().trim();
            String salary = salaryField.getText().trim();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(addDialog, "员工编号和姓名不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 创建新员工对象并添加到列表
            Employee newEmployee = new Employee(id, name, dept, position, phone, age, hireDate, salary);
            employees.add(newEmployee);

            // 更新表格
            tableModel.addRow(new Object[]{id, name, dept, position, phone, age, hireDate, salary});
            //提示添加成功
            JOptionPane.showMessageDialog(addDialog, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            addDialog.dispose();
        });

        // 取消按钮事件
        cancelButton.addActionListener(e -> addDialog.dispose());

        addDialog.setLayout(new BorderLayout());
        addDialog.add(panel, BorderLayout.CENTER);
        addDialog.add(buttonPanel, BorderLayout.SOUTH);
        addDialog.setVisible(true);
    }

    private void searchEmployee() {
        String searchText = searchField.getText().trim();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String ID = (String) tableModel.getValueAt(i, 0); // // 获取当前行员工编号
            // 判断编号是否包含搜索文本
            if (ID.contains(searchText)) {
                employeeTable.setRowSelectionInterval(i, i);
                //清空搜索框
                searchField.setText("");
                searchField.requestFocus();
                return;
            }

        }
        JOptionPane.showMessageDialog(this, "未找到匹配的员工信息。", "提示", JOptionPane.INFORMATION_MESSAGE);
    }


    private void editEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的员工！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 获取选中行的员工对象
        String employeeId = tableModel.getValueAt(selectedRow, 0).toString();
        // 从列表中获取员工对象
        Employee selectedEmployee = employees.stream().filter(emp -> emp.getId().equals(employeeId)).findFirst().orElse(null);

        if (selectedEmployee == null) {
            JOptionPane.showMessageDialog(this, "未找到员工信息！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 创建修改员工对话框
        JDialog editDialog = new JDialog(this, "修改员工信息 - ID: " + employeeId, true);
        editDialog.setSize(400, 350);
        editDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // 创建输入组件并设置初始值
        JTextField idField = new JTextField(selectedEmployee.getId(), 20);
        idField.setEditable(false); // 员工编号不可修改
        JTextField nameField = new JTextField(selectedEmployee.getName(), 20);
        JTextField deptField = new JTextField(selectedEmployee.getDepartment(), 20);
        JTextField positionField = new JTextField(selectedEmployee.getPosition(), 20);
        JTextField phoneField = new JTextField(selectedEmployee.getPhone(), 20);
        JTextField ageField = new JTextField(selectedEmployee.getAge(), 20);
        JTextField hireDateField = new JTextField(selectedEmployee.getEntryTime(), 20);
        JTextField salaryField = new JTextField(selectedEmployee.getSalary(), 20);

        // 布局组件
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("员工编号:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("姓名:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("部门:"), gbc);
        gbc.gridx = 1;
        panel.add(deptField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("职位:"), gbc);
        gbc.gridx = 1;
        panel.add(positionField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("电话:"), gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("年龄:"), gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("入职时间:"), gbc);
        gbc.gridx = 1;
        panel.add(hireDateField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("薪水:"), gbc);
        gbc.gridx = 1;
        panel.add(salaryField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("保存");
        JButton cancelButton = new JButton("取消");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // 保存按钮事件
        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String dept = deptField.getText().trim();
            String position = positionField.getText().trim();
            String phone = phoneField.getText().trim();
            String age = ageField.getText().trim();
            String hireDate = hireDateField.getText().trim();
            String salary = salaryField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(editDialog, "姓名不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 更新员工对象
            selectedEmployee.setName(name);
            selectedEmployee.setDepartment(dept);
            selectedEmployee.setPosition(position);
            selectedEmployee.setPhone(phone);
            selectedEmployee.setAge(age);
            selectedEmployee.setEntryTime(hireDate);
            selectedEmployee.setSalary(salary);
            // 更新表格数据
            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(dept, selectedRow, 2);
            tableModel.setValueAt(position, selectedRow, 3);
            tableModel.setValueAt(phone, selectedRow, 4);
            tableModel.setValueAt(age, selectedRow, 5);
            tableModel.setValueAt(hireDate, selectedRow, 6);
            tableModel.setValueAt(salary, selectedRow, 7);

            JOptionPane.showMessageDialog(editDialog, "员工信息修改成功！ID: " + employeeId, "提示", JOptionPane.INFORMATION_MESSAGE);
            editDialog.dispose();
        });

        // 取消按钮事件
        cancelButton.addActionListener(e -> editDialog.dispose());

        editDialog.setLayout(new BorderLayout());
        editDialog.add(panel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);
        editDialog.setVisible(true);
    }


    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow(); // 获取选中行索引
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的员工！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 获取选中行的员工编号
        String employeeId = tableModel.getValueAt(selectedRow, 0).toString();
        String employeeName = tableModel.getValueAt(selectedRow, 1).toString();

        int option = JOptionPane.showConfirmDialog(
                this,
                "确定要删除员工 " + employeeName + " (ID: " + employeeId + ") 吗？\n此操作不可恢复！",
                "确认删除",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (option == JOptionPane.YES_OPTION) {
            // 从员工列表中删除
            employees.removeIf(emp -> emp.getId().equals(employeeId));

            // 从表格中删除
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "员工 " + employeeName + " (ID: " + employeeId + ") 删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
