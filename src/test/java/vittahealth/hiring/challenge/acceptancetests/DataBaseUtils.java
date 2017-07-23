package vittahealth.hiring.challenge.acceptancetests;

import org.hibernate.Session;
import vittahealth.hiring.challenge.JPAUtil;
import vittahealth.hiring.challenge.Territory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliano on 23/07/17.
 */
public class DataBaseUtils {

    public static List<Territory> persistTerritories() throws IOException {
        List<Territory> territories = new ArrayList<Territory>();

        Territory territoryA = new Territory("A","x: 0, y: 0","x: 50, y: 50");
        territoryA.setArea(2500L);
        territoryA.setPaintedArea(0L);
        territories.add(territoryA);

        Territory territoryB = new Territory("B","x: 51, y: 51","x: 101, y: 101");
        territoryB.setArea(2500L);
        territoryB.setPaintedArea(0L);
        territories.add(territoryB);

        final Session session = JPAUtil.session();
        session.getTransaction().begin();
        session.createNativeQuery("DELETE FROM Territory").executeUpdate();
        for (Territory territory : territories) {
            session.persist(territory);
            session.flush();
        }
        session.getTransaction().commit();
        return territories;
    }
}
