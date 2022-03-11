package iducs.springboot.member_201712045.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db.properties")
// @MapperScan("iducs.springboot.blog.mapper")
public class DBConfig {
    // @Component : 개발자가 정의한 클래스로 부터 Bean 객체를 생성하는 애노테이션
    // DataSource 클래스형의 Bean 객체를 생성하는 애노테이션
    // 이름을 지정하여 Bean Id는 지정한 이름으로 등록됨
    // 이름의 지정하지 않는 경우 메소드 이름, getHikariDataSource 로 등록됨

    @Bean(name="dataSource1", destroyMethod = "close")
    public HikariDataSource getHikariDataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        hikariConfig.setUsername("system");
        hikariConfig.setPassword("cometrue");
        //hikariConfig.setMaximumPoolSize(5);
        //hikariConfig.setConnectionTestQuery("SELECT 1");
        //hikariConfig.setPoolName("springHikariCP");
        return new HikariDataSource(hikariConfig);
    }
/*
    @Primary
    @Bean("dataSource2")
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public DataSource getDataSourceBuilder() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }

    @Bean(name="dataSource3")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource getDataSourceBuilderUrl() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        // url -> jdbc-url로 변경하는 경우 생락 가능
        dataSourceBuilder.url("jdbc:oracle:thin:@localhost:1521:XE");
        return dataSourceBuilder.build();
    }

    @ConfigurationProperties("spring.datasource")

    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    private final Environment environment;
    @Autowired
    public DBConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name="dataSource4")
    public DataSource getDriverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url")); //"jdbc:oracle:thin:@localhost:1521:XE");
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }


    @Bean("dataSource5")
    public DataSource getSimpleDriverDataSource() {
        SimpleDriverDataSource dataSource= new SimpleDriverDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
        dataSource.setUsername("system");
        dataSource.setPassword("cometrue");
        return dataSource;
    }
        */

    /*

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getHikariDataSource());
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public MapperFactoryBean<BlogMapper> blogMapper() throws Exception {
        MapperFactoryBean<BlogMapper> factoryBean = new MapperFactoryBean<>(BlogMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        return factoryBean;
    }
*/
/*
    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("iducs.springboot.blog.domain");
        bean.setMapperLocations(applicationContext.getResources("classpath:/mappers/*.xml"));
        return bean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
*/
}
