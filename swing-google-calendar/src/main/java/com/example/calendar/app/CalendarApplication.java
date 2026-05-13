package com.example.calendar.app;

import com.example.calendar.controller.CalendarController;
import com.example.calendar.service.CalendarService;
import com.example.calendar.ui.MainFrame;

import javax.swing.*;

/**
 * 应用启动入口。
 */
public class CalendarApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            CalendarService service = new CalendarService();
            new CalendarController(frame, service);
            frame.setVisible(true);
        });
    }
}
