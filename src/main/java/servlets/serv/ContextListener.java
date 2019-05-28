package servlets.serv;

//@WebListener
//public class ContextListener implements ServletContextListener {
//    private AtomicReference<DatabaseHandler> dao;
//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        dao = new AtomicReference<>(new DatabaseHandler());
//        final ServletContext sc = event.getServletContext();
//        String url = sc.getInitParameter("URL");
//        String database = sc.getInitParameter("dbName");
//        String add = sc.getInitParameter("addition");
//        String User = sc.getInitParameter("dbUser");
//        String Pass = sc.getInitParameter("dbPass");
//        DatabaseHandler db = new DatabaseHandler(url+database+add, User, Pass);
//        sc.setAttribute("db", db);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//}
