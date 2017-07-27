package vittahealth.hiring.challenge.domain;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static vittahealth.hiring.challenge.JPAUtil.session;

public class TerritoryRepository {

    private static final String GET_ALL_TERRITORIES = "SELECT * FROM territory u";

    public List<Territory> find() {
        final Session session = session();
        Query<Territory> query = session.createNativeQuery(GET_ALL_TERRITORIES, Territory.class);
        final List<Territory> territories = query.list();
        session.close();
        return territories;
    }

    public List<Territory> findOrderedByMostPaintedArea() {
        final Session session = session();
        Query<Territory> query = session.createNativeQuery(GET_ALL_TERRITORIES + " ORDER BY paintedArea DESC", Territory.class);
        final List<Territory> territories = query.list();
        session.close();
        return territories;
    }
}
