package vittahealth.hiring.challenge;

import org.hibernate.query.Query;

import static vittahealth.hiring.challenge.JPAUtil.session;

public class SquareRepository {

    public Square find() {
        Query<Square> query =
                session().createNativeQuery("SELECT * FROM square u WHERE 1 = 1", Square.class);

        Square usuario = null;
        try {
            usuario = query.getSingleResult();
        } catch (Exception e) {
            return usuario;
        }
        session().close();
        return usuario;
    }
}
