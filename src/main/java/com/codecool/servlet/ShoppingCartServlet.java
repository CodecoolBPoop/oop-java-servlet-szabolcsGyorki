package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShoppingCart", urlPatterns = {"/cart"})
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        List<Item> itemList = ItemStore.getItemList(session);

        StringBuffer buffer = new StringBuffer();
        double totalPrice = 0;
        buffer.append("<table cellpadding=\"10\" border = \"1\">");
        if (itemList != null) {
            for (Item item : itemList) {
                buffer.append("<div>");
                buffer.append("<tr>");
                buffer.append("<td>");
                buffer.append(item.getName());
                buffer.append("</td>");
                buffer.append("<td>");
                buffer.append(" Price: ").append(item.getPrice()).append("USD");
                buffer.append("</td>");
                buffer.append("</tr>");
                buffer.append("</div>");
                totalPrice += item.getPrice();
            }
        }
        buffer.append("</table>");

        out.println(
                "<html>\n" +
                        "<head><title>Another page</title></head>\n" +
                        "<body>\n" +
                        "<h1>Hello CodeCooler!</h1>" +
                        "<br/>" +
                        "<div>" + buffer.toString() + "</div>" +
                        "<br>" +
                        "<div>The total price: " + totalPrice + " USD</div>" +
                        "<br>" +
                        "<div>Back to home<a href=\"/\">Back to home</a></div>" +
                        "</body></html>"
        );
    }
}
