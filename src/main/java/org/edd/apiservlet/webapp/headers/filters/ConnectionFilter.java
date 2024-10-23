package org.edd.apiservlet.webapp.headers.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.edd.apiservlet.webapp.headers.configs.MysqlConn;
import org.edd.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {
  /*  @Inject
    @MysqlConn
    private Connection conn;*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//       try {
//           Connection connRequest = this.conn;
//           if (connRequest.getAutoCommit())
//               connRequest.setAutoCommit(false);

           try {
               filterChain.doFilter(servletRequest, servletResponse);
               //connRequest.commit();
           } catch (ServiceJdbcException e) {
               //connRequest.rollback();
               ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
               e.printStackTrace();
           }
//       } catch (SQLException e) {
//           e.printStackTrace();
//       }
    }
}
