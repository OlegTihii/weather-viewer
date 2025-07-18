package org.weather.exceptions;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException e, Model model) {
        Throwable root = e.getRootCause();
        if (root instanceof PSQLException psqlException) {
            String sqlState = psqlException.getSQLState();

           switch (sqlState) {
               case "23505":
                   model.addAttribute("error", "Пользователь с таким логином уже существует.");
                   break;

               default:
                   model.addAttribute("error", "Ошибка сохранения данных: " + psqlException.getMessage());
           }
        } else {
            model.addAttribute("error", e.getMessage());
        }

        //todo таймлиф не подхватывает страницу
       return  "sign-up-with-errors.html";
    }

    @ExceptionHandler(Exception.class)
    public String handleAny(Exception ex, Model model) {
        model.addAttribute("error", "Произошла непредвиденная ошибка: " + ex.getMessage());
        return "sign-up-with-errors";
    }
}
