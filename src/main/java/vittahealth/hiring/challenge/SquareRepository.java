package vittahealth.hiring.challenge;

import org.hibernate.Session;
import org.hibernate.query.Query;

import static vittahealth.hiring.challenge.JPAUtil.session;

public class SquareRepository {

    public Square find() {
        final Session session = session();
        Query<Square> query =

                session.createNativeQuery("SELECT * FROM square u", Square.class);

        Square usuario = null;
        try {
            usuario = query.getSingleResult();
        } catch (Exception e) {
            return usuario;
        }
        session.close();
        return usuario;
    }
}
