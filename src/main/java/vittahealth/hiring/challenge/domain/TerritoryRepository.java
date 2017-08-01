package vittahealth.hiring.challenge.domain;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

import static vittahealth.hiring.challenge.persistence.JPAUtil.session;

public class TerritoryRepository {

    private static final String GET_ALL_TERRITORIES = "SELECT * FROM territory u";

    public Territory find(Long id) {
        final Session session = session();
        final Territory territory = session.find(Territory.class, id);
        session.close();
        return territory;
    }

    public void create(Territory newTerritory) throws TerritoryOverlaysException,IncompleteDataException {
        if (incompleteData(newTerritory)) {
            throw new IncompleteDataException();
        }
        final Session session = session();
        final List<Territory> allTerritories = session.createNativeQuery(GET_ALL_TERRITORIES, Territory.class).list(); // TODO se tiver muitos dados
        verifyTerritoryOverlays(newTerritory, allTerritories);
        session.getTransaction().begin();
        session.persist(newTerritory);
        session.getTransaction().commit();
        session.close();
    }

    public List<Territory> find() {
        final Session session = session();
        Query<Territory> query = session.createNativeQuery(GET_ALL_TERRITORIES, Territory.class);
        final List<Territory> territories = query.list();
        session.close();
        return territories;
    }

    private void verifyTerritoryOverlays(Territory newTerritory, List<Territory> allTerritories) throws TerritoryOverlaysException {
        List<Territory> territoriesWithSameArea = allTerritories.stream()
                .filter(territory -> territory.isSameArea(newTerritory))
                .collect(Collectors.toList());
        if (territoriesWithSameArea != null && !territoriesWithSameArea.isEmpty()) {
            throw new TerritoryOverlaysException();
        }
    }

    private boolean incompleteData(Territory territory) {
        boolean incompleteData = false;
        if (territory.getName() == null || territory.getName().equals("")) {
            incompleteData = true;
        } else if (territory.getStartArea() == null) {
            incompleteData = true;
        } else if (territory.getEndArea() == null) {
            incompleteData = true;
        }
        return incompleteData;
    }
}
