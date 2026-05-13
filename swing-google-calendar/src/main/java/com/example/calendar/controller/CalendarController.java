package com.example.calendar.controller;

import com.example.calendar.model.CalendarEvent;
import com.example.calendar.service.CalendarService;
import com.example.calendar.ui.EventFormDialog;
import com.example.calendar.ui.MainFrame;
import com.example.calendar.util.DateUtils;

import javax.swing.*;
import java.time.LocalDate;

/**
 * 控制器：连接 UI 与服务层，处理全部按钮点击与事件逻辑。
 */
public class CalendarController {
    private final MainFrame frame;
    private final CalendarService service;

    public CalendarController(MainFrame frame, CalendarService service) {
        this.frame = frame;
        this.service = service;
        initEvents();
        setTodayAndReload();
    }

    private void initEvents() {
        frame.getTodayBtn().addActionListener(e -> setTodayAndReload());
        frame.getQueryBtn().addActionListener(e -> reloadByInputDate());
        frame.getAddBtn().addActionListener(e -> createEvent());
        frame.getEditBtn().addActionListener(e -> editEvent());
        frame.getDeleteBtn().addActionListener(e -> deleteEvent());
    }

    private void setTodayAndReload() {
        frame.getDateField().setText(LocalDate.now().format(DateUtils.DATE_FORMATTER));
        reloadByInputDate();
    }

    private void reloadByInputDate() {
        try {
            LocalDate date = frame.getDateFromInput();
            frame.setEvents(service.getEventsByDate(date));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "日期格式错误，请输入 yyyy-MM-dd", "输入错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createEvent() {
        EventFormDialog dialog = new EventFormDialog(frame, null, frame.getDateFromInput());
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            CalendarEvent event = dialog.toEvent();
            service.addEvent(event);
            frame.getDateField().setText(event.getDate().format(DateUtils.DATE_FORMATTER));
            reloadByInputDate();
        }
    }

    private void editEvent() {
        CalendarEvent selected = frame.getSelectedEvent();
        if (selected == null) {
            JOptionPane.showMessageDialog(frame, "请先选择要编辑的日程", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        LocalDate oldDate = selected.getDate();
        EventFormDialog dialog = new EventFormDialog(frame, selected, selected.getDate());
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            CalendarEvent updated = dialog.toEvent();
            // 为保留原 ID，这里直接回填字段。
            selected.setTitle(updated.getTitle());
            selected.setDate(updated.getDate());
            selected.setStartTime(updated.getStartTime());
            selected.setEndTime(updated.getEndTime());
            selected.setLocation(updated.getLocation());
            selected.setDescription(updated.getDescription());
            service.updateEvent(oldDate, selected);
            frame.getDateField().setText(selected.getDate().format(DateUtils.DATE_FORMATTER));
            reloadByInputDate();
        }
    }

    private void deleteEvent() {
        CalendarEvent selected = frame.getSelectedEvent();
        if (selected == null) {
            JOptionPane.showMessageDialog(frame, "请先选择要删除的日程", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame, "确认删除: " + selected.getTitle() + " ?", "确认删除", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            service.deleteEvent(selected.getDate(), selected.getId());
            reloadByInputDate();
        }
    }
}
