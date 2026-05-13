package com.example.calendar.ui;

import com.example.calendar.model.CalendarEvent;
import com.example.calendar.util.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 事件编辑弹窗：支持新建和编辑。
 */
public class EventFormDialog extends JDialog {
    private final JTextField titleField = new JTextField();
    private final JTextField dateField = new JTextField();
    private final JTextField startField = new JTextField("09:00");
    private final JTextField endField = new JTextField("10:00");
    private final JTextField locationField = new JTextField();
    private final JTextArea descriptionArea = new JTextArea(4, 20);
    private boolean confirmed = false;

    public EventFormDialog(Frame owner, CalendarEvent existingEvent, LocalDate selectedDate) {
        super(owner, existingEvent == null ? "新建日程" : "编辑日程", true);
        initLayout();
        if (existingEvent != null) {
            bindEvent(existingEvent);
        } else {
            dateField.setText(selectedDate.format(DateUtils.DATE_FORMATTER));
        }
    }

    private void initLayout() {
        setSize(480, 420);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(6, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        form.add(new JLabel("标题:")); form.add(titleField);
        form.add(new JLabel("日期(yyyy-MM-dd):")); form.add(dateField);
        form.add(new JLabel("开始(HH:mm):")); form.add(startField);
        form.add(new JLabel("结束(HH:mm):")); form.add(endField);
        form.add(new JLabel("地点:")); form.add(locationField);
        form.add(new JLabel("描述:")); form.add(new JScrollPane(descriptionArea));
        add(form, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelBtn = new JButton("取消");
        JButton saveBtn = new JButton("保存");
        cancelBtn.addActionListener(e -> dispose());
        saveBtn.addActionListener(e -> confirmed = true);
        saveBtn.addActionListener(e -> dispose());
        actions.add(cancelBtn); actions.add(saveBtn);
        add(actions, BorderLayout.SOUTH);
    }

    private void bindEvent(CalendarEvent event) {
        titleField.setText(event.getTitle());
        dateField.setText(event.getDate().format(DateUtils.DATE_FORMATTER));
        startField.setText(event.getStartTime().format(DateUtils.TIME_FORMATTER));
        endField.setText(event.getEndTime().format(DateUtils.TIME_FORMATTER));
        locationField.setText(event.getLocation());
        descriptionArea.setText(event.getDescription());
    }

    public boolean isConfirmed() { return confirmed; }

    public CalendarEvent toEvent() {
        LocalDate date = DateUtils.parseDate(dateField.getText().trim());
        LocalTime start = DateUtils.parseTime(startField.getText().trim());
        LocalTime end = DateUtils.parseTime(endField.getText().trim());
        return new CalendarEvent(titleField.getText().trim(), date, start, end,
                locationField.getText().trim(), descriptionArea.getText().trim());
    }
}
