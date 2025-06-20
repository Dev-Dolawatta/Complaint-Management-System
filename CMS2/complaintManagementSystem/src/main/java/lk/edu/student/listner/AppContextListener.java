package lk.edu.student.listner;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lk.edu.student.util.DatabaseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        BasicDataSource dataSource = DatabaseUtil.setupDataSource();
        sce.getServletContext().setAttribute("DATABASE_CONNECTION", dataSource);

        System.out.println("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        BasicDataSource dataSource = (BasicDataSource) sce.getServletContext().getAttribute("DATABASE_CONNECTION");
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("application going home");
    }
}
