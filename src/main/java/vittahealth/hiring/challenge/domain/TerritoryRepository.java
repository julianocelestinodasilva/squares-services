package vittahealth.hiring.challenge.domain;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static vittahealth.hiring.challenge.JPAUtil.session;

public class TerritoryRepository {

    public List<Territory> find() {
        final Session session = session();
        Query<Territory> query = session.createNativeQuery("SELECT * FROM territory u", Territory.class);
        final List<Territory> territories = query.list();
        session.close();
        return territories;
    }

    public List<Territory> findOrderedByMostPaintedArea() {
        final Session session = session();
        Query<Territory> query = session.createNativeQuery("SELECT * FROM territory u ORDER BY paintedArea DESC", Territory.class);
        final List<Territory> territories = query.list();
        session.close();
        return territories;
    }
}
