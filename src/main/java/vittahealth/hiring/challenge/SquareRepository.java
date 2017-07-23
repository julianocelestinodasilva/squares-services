package vittahealth.hiring.challenge;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static vittahealth.hiring.challenge.JPAUtil.session;

public class SquareRepository {

    public List<Square> find() {
        final Session session = session();
        Query<Square> query = session.createNativeQuery("SELECT * FROM square u", Square.class);
        return  query.list();
        // session.close();
    }
}
