package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "WebShop", urlPatterns = {"/"})
public class WebShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        List<Item> itemList;
        if (session.getAttribute("itemsToSell") == null) {
            itemList = initItems(session);
        } else {
            itemList = (ArrayList<Item>)session.getAttribute("itemsToSell");
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("<table cellpadding=\"10\" border = \"1\">");
        for (Item item : itemList) {
            buffer.append("<div>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(item.getName())
                    .append(" ");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append(item.getPrice())
                    .append("USD");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<form action = \"WebShopServlet\" method = \"POST\">\n" + "<input type = \"hidden\" name = \"add\" value = \"")
                    .append(item.getName())
                    .append("\">")
                    .append("<input type = \"submit\" value = \"Add\" />\n")
                    .append("</form>");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<form action = \"WebShopServlet\" method = \"POST\">\n" + "<input type = \"hidden\" name = \"remove\" value = \"")
                    .append(item.getName())
                    .append("\">")
                    .append("<input type = \"submit\" value = \"Remove\">\n")
                    .append("</form>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</div>");
            buffer.append("<br>");
        }
        buffer.append("</table>");

        out.println(
                "<html>\n" +
                        "<head><title>This is my webshop</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">This is my webshop</h1>\n" +
                        "<div>" + buffer.toString() + "</div>" +
                        "<br>" +
                        "<div>Go to cart: <a href=\"/cart\">Go to cart</a></div>" +
                        "</body></html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String itemToAdd = request.getParameter("add");
        String itemToRemove = request.getParameter("remove");

        List<Item> itemList = (ArrayList<Item>)session.getAttribute("itemsToSell");
        for (Item item : itemList) {
            if (itemToAdd != null) {
                if (item.getName().equals(itemToAdd)) {
                    ItemStore.add(item, session);
                    break;
                }
            }
            if (itemToRemove != null && !ItemStore.listIsEmpty(session)) {
                if (item.getName().equals(itemToRemove)) {
                    ItemStore.remove(item, session);
                    break;
                }
            }
        }
        response.sendRedirect("/");
    }

    private List<Item> initItems(HttpSession session) {
        List<Item> itemList = new ArrayList<>();

        Item pixel2 = new Item();
        pixel2.setName("Google Pixel 2");
        pixel2.setPrice(799);
        itemList.add(pixel2);

        Item galaxyS9 = new Item();
        galaxyS9.setName("Samsung Galaxy S9");
        galaxyS9.setPrice(899);
        itemList.add(galaxyS9);

        Item huaweiP20 = new Item();
        huaweiP20.setName("Huawei P20");
        huaweiP20.setPrice(699);
        itemList.add(huaweiP20);

        Item iphoneX = new Item();
        iphoneX.setName("Apple iPhone X");
        iphoneX.setPrice(999);
        itemList.add(iphoneX);

        session.setAttribute("itemsToSell", itemList);
        return itemList;
    }

}
