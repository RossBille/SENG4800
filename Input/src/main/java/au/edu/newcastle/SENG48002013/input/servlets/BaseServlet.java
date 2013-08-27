/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.input.servlets;

import java.io.IOException;
import au.edu.newcastle.SENG48002013.input.SecurityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ross
 */
public abstract class BaseServlet extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //security check here
        SecurityManager secMan = (SecurityManager) getServletContext().getAttribute("securityManager");
        processRequest(request, response, secMan);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        //do security stuff here
        SecurityManager secMan = (SecurityManager) getServletContext().getAttribute("securityManager");
        processRequest(request, response, secMan);
    }


    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response, SecurityManager secMan) throws IOException;

}
