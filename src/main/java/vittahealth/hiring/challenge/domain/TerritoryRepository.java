package vittahealth.hiring.challenge.domain;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

import static vittahealth.hiring.challenge.JPAUtil.session;

public class TerritoryRepository {

    private static final String GET_ALL_TERRITORIES = "SELECT * FROM territory u";

    public void create(Territory newTerritory) throws TerritoryOverlaysException {
        // TODO verifyFieldsNotNull(territory);
        final Session session = session();
        final List<Territory> allTerritories = session.createNativeQuery(GET_ALL_TERRITORIES, Territory.class).list(); // TODO se tiver muitos dados
        List<Territory> territoriesWithSameArea = sameArea(newTerritory, allTerritories);
        if (territoriesWithSameArea != null && !territoriesWithSameArea.isEmpty()) {
            throw new TerritoryOverlaysException();
        }
        session.getTransaction().begin();
        session.persist(newTerritory);
        session.getTransaction().commit();
        session.close();
    }

    private List<Territory> sameArea(Territory newTerritory, List<Territory> allTerritories) {
        return allTerritories.stream()
                .filter(territory -> territory.isSameArea(newTerritory))
                .collect(Collectors.toList());
    }

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
