package services.dbobjects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by daniel on 25.08.16.
 */
public class BookDaoImpl {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

/*    @Override*/
    public void save(Book b) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(b);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
  /*  @Override*/
    public List<Book> list() {
        Session session = this.sessionFactory.openSession();
        List<Book> personList = session.createQuery("from book").list();
        session.close();
        return personList;
    }
}
