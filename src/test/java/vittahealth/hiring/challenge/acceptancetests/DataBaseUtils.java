package vittahealth.hiring.challenge.acceptancetests;

import org.hibernate.Session;
import vittahealth.hiring.challenge.JPAUtil;
import vittahealth.hiring.challenge.NodeJson;
import vittahealth.hiring.challenge.domain.Territory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataBaseUtils {

    public static List<Territory> persistTerritories(Long paintedAreaTerritory1) throws IOException {
        List<Territory> territories = new ArrayList<Territory>();
        Territory territoryA = new Territory("A",new NodeJson(0,0),new NodeJson(50,50));
        territoryA.setArea(2500L);
        territoryA.setPaintedArea(0L);
        territories.add(territoryA);
        Territory territoryB = new Territory("B",new NodeJson(51,51),new NodeJson(101,101));
        territoryB.setArea(2500L);
        territoryB.setPaintedArea(paintedAreaTerritory1);
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

    public static List<Territory> persistTerritories() throws IOException {
        List<Territory> territories = new ArrayList<Territory>();
        Territory territoryA = new Territory("A",new NodeJson(0,0),new NodeJson(50,50));
        territoryA.setArea(2500L);
        territoryA.setPaintedArea(0L);
        territories.add(territoryA);
        Territory territoryB = new Territory("B",new NodeJson(51,51),new NodeJson(101,101));
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
