package view;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.*;
import java.time.LocalDate;

//public class DatePickerEjemplo {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("DatePicker Ejemplo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//
//        DatePicker datePicker = new DatePicker();
//        frame.add(datePicker);
//
//        JButton btn = new JButton("Obtener Fecha");
//        btn.addActionListener(e -> {
//            LocalDate fecha = datePicker.getDate();
//            JOptionPane.showMessageDialog(frame, "Fecha seleccionada: " + fecha);
//        });
//
//        frame.add(btn, "South");
//        frame.setVisible(true);
//    }
//}