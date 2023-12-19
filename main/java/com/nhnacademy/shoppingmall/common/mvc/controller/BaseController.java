package com.nhnacademy.shoppingmall.common.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BaseController {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException;
}