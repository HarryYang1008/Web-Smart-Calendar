package com.example.calendar.ui;

import com.example.calendar.model.CalendarEvent;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * 主窗口：提供按钮、列表、日期选择等完整 UI。
 */
public class MainFrame extends JFrame {
    private final JTextField dateField = new JTextField(12);
    private final JButton todayBtn = new JButton("今天");
    private final JButton queryBtn = new JButton("查询");
    private final JButton addBtn = new JButton("新增");
    private final JButton editBtn = new JButton("编辑");
    private final JButton deleteBtn = new JButton("删除");
    private final DefaultListModel<CalendarEvent> listModel = new DefaultListModel<>();
    private final JList<CalendarEvent> eventList = new JList<>(listModel);

    public MainFrame() {
        super("Google Calendar Desktop Demo - Swing");
        initLayout();
    }

    private void initLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBorder(BorderFactory.createTitledBorder("日期与操作"));
        top.add(new JLabel("日期(yyyy-MM-dd):"));
        top.add(dateField);
        top.add(todayBtn); top.add(queryBtn);
        top.add(addBtn); top.add(editBtn); top.add(deleteBtn);

        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.toDisplayText());
            label.setOpaque(true);
            label.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
            if (isSelected) {
                label.setBackground(new Color(66, 133, 244));
                label.setForeground(Color.WHITE);
            }
            return label;
        });

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(eventList), BorderLayout.CENTER);
    }

    public JTextField getDateField() { return dateField; }
    public JButton getTodayBtn() { return todayBtn; }
    public JButton getQueryBtn() { return queryBtn; }
    public JButton getAddBtn() { return addBtn; }
    public JButton getEditBtn() { return editBtn; }
    public JButton getDeleteBtn() { return deleteBtn; }
    public CalendarEvent getSelectedEvent() { return eventList.getSelectedValue(); }

    public void setEvents(List<CalendarEvent> events) {
        listModel.clear();
        events.forEach(listModel::addElement);
    }

    public LocalDate getDateFromInput() {
        return LocalDate.parse(dateField.getText().trim());
    }
}
