package vittahealth.hiring.challenge.acceptancetests;

import org.hibernate.Session;
import vittahealth.hiring.challenge.JPAUtil;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataBaseUtils {

    public static List<Territory> persistTerritories(Territory territoryA, Territory territoryB) throws IOException {
        List<Territory> territories = new ArrayList<Territory>();
        territoryA.setPaintedArea(0L);
        territories.add(territoryA);
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

    public static List<Territory> persistTerritories(Long paintedAreaTerritory1) throws IOException {
        List<Territory> territories = new ArrayList<Territory>();
        Territory territoryA = new Territory("A",new Node(0,0),new Node(50,50));
        territoryA.setPaintedArea(0L);
        territories.add(territoryA);
        Territory territoryB = new Territory("B",new Node(51,51),new Node(101,101));
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
        Territory territoryA = new Territory("A",new Node(0,0),new Node(50,50));
        territoryA.setPaintedArea(0L);
        territories.add(territoryA);
        Territory territoryB = new Territory("B",new Node(51,51),new Node(101,101));
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
