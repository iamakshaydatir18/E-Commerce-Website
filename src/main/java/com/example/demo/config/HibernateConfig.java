/*
 * package com.example.demo.config;
 * 
 * import java.util.Properties;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.hibernate.SessionFactory; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.jdbc.datasource.DriverManagerDataSource; import
 * org.springframework.orm.hibernate5.HibernateTransactionManager; import
 * org.springframework.orm.hibernate5.LocalSessionFactoryBean;
 * 
 * import com.example.demo.model.Authorities; import
 * com.example.demo.model.BillingAddress; import com.example.demo.model.Cart;
 * import com.example.demo.model.CartItem; import
 * com.example.demo.model.Customer; import com.example.demo.model.CustomerOrder;
 * import com.example.demo.model.Product; import com.example.demo.model.Queries;
 * import com.example.demo.model.ShippingAddress; import
 * com.example.demo.model.User;
 * 
 * @Configuration public class HibernateConfig {
 * 
 * @Bean public DataSource dataSource() { DriverManagerDataSource dataSource =
 * new DriverManagerDataSource();
 * dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
 * dataSource.setUrl("jdbc:mysql://localhost:3306/mustbuy");
 * dataSource.setUsername("root"); dataSource.setPassword("rootroot"); return
 * dataSource; }
 * 
 * @Bean public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
 * sessionFactory.setDataSource(dataSource);
 * sessionFactory.setPackagesToScan("com.model");
 * sessionFactory.setHibernateProperties(hibernateProperties());
 * sessionFactory.setAnnotatedClasses(Product.class, Authorities.class,
 * BillingAddress.class, Cart.class, CartItem.class, Customer.class,
 * CustomerOrder.class, ShippingAddress.class, User.class, Queries.class);
 * return sessionFactory; }
 * 
 * @Bean public HibernateTransactionManager transactionManager(SessionFactory
 * sessionFactory) { HibernateTransactionManager transactionManager = new
 * HibernateTransactionManager();
 * transactionManager.setSessionFactory(sessionFactory); return
 * transactionManager; }
 * 
 * private Properties hibernateProperties() { Properties properties = new
 * Properties(); properties.setProperty("hibernate.dialect",
 * "org.hibernate.dialect.MySQL5InnoDBDialect");
 * properties.setProperty("hibernate.hbm2ddl.auto", "update");
 * properties.setProperty("hibernate.show_sql", "true");
 * properties.setProperty("hibernate.format_sql", "true"); return properties; }
 * }
 */