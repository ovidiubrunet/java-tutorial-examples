package org.o7planning.springjdbc.demo;
 
import java.sql.SQLException;
import java.util.List;
 
import org.o7planning.springjdbc.config.AppConfiguration;
import org.o7planning.springjdbc.dao.QueryForListReturnListDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
 
public class QueryForListReturnList_Test {
 
    public static void main(String[] args) throws SQLException {
 
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
 
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
 
        List<String> names = dao.getDepartmentNames("A");       
        
        for (String name : names) {
 
            System.out.println("Dept Name: " + name);
        }
    }
 
}