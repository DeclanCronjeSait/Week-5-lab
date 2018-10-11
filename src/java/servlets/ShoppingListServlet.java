/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
         doEverything(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doEverything(request, response);
    }
    
    protected void doEverything(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        ArrayList<String> items = new ArrayList<>();
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action != null) 
        {
            if(action.equals("register"))
            {
                String username = request.getParameter("username");
                session = request.getSession();
                session.setAttribute("items", items);
                session.setAttribute("username", username);
                
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
            
            if(action.equals("add"))
            {
                items = (ArrayList<String>) session.getAttribute("items");
                items.add(request.getParameter("itemadd"));
                session.setAttribute("items", items);
                request.setAttribute("items", items);
                
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
            
            if(action.equals("delete"))
            {
                String selected = request.getParameter("radio");
                items = (ArrayList<String>) session.getAttribute("items");
                
                for(int i = 0; i < items.size(); i++)
                {
                    if(items.get(i).equals(selected))
                    {
                        items.remove(i);
                    }
                }
                session.setAttribute("items", items);
                request.setAttribute("items", items);
                
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
        }
        

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
