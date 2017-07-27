package vittahealth.hiring.challenge.domain;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static vittahealth.hiring.challenge.JPAUtil.session;

public class TerritoryRepository {

    public List<Territory> find() {
        final Session session = session();
        Query<Territory> query = session.createNativeQuery("SELECT * FROM territory u", Territory.class);
        return  query.list();
        // TODO session.close();
    }
}
