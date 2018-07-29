package sch.man.com.utility;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sch.man.com.controller.PersonBean;

/**
 *
 * @author Andrew
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

  public AuthorizationFilter() {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

          PersonBean report = new PersonBean();
          
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) 
      throws IOException, ServletException {
    try {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      HttpSession ses = request.getSession(false);

      String reqURI = request.getRequestURI();
      if (reqURI.contains("/index.xhtml")
          ||reqURI.contains("/about.xhtml")
          || reqURI.contains("/personRegForm.xhtml")
          || (ses != null && ses.getAttribute("personId") != null)
          || reqURI.contains("/public/")
          || reqURI.contains("javax.faces.resource")) {
        chain.doFilter(request, servletResponse);
          report.setReport("");
      } else {
          report.setReport("Please Login first");
        response.sendRedirect(request.getContextPath() + "/faces/index.xhtml");
      }
    } catch (IOException | ServletException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void destroy() {

  }
}
