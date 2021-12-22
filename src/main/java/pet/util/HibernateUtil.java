package pet.util;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pet.Runner;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try{
            return sessionForH2();
//            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            System.err.println("Initial session factory create failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }

    private static SessionFactory sessionForH2() throws URISyntaxException {
        Path path = Paths.get(Runner.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        String H2 = "jdbc:h2:" + path + "\\Baza";
        try {
            Configuration conf = new Configuration().configure();
//            conf.setProperty( "hibernate.connection.url","jdbc:h2:D:\\JAVA\\Output\\Baza");
            conf.setProperty( "hibernate.connection.url",H2);
            return conf.buildSessionFactory();
        }catch (Throwable ex){
            System.err.println("Initial session factory for_H2_DB create failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}

