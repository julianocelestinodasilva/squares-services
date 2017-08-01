package vittahealth.hiring.challenge.acceptancetests;

import org.hibernate.Session;
import vittahealth.hiring.challenge.JPAUtil;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataBase {

    public static void persistTerritory(Territory territory) throws IOException {
        final Session session = JPAUtil.session();
        session.getTransaction().begin();
        session.createNativeQuery("DELETE FROM Territory").executeUpdate();
        session.persist(territory);
        session.flush();
        session.getTransaction().commit();
    }

    public static List<Territory> persistTerritories(Territory territoryA, Territory territoryB) throws IOException {
        List<Territory> territories = new ArrayList<Territory>();
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
        territories.add(territoryA);
        Territory territoryB = new Territory("B",new Node(51,51),new Node(101,101));
        List<Node> squaresToPaint = createSquaresToPaint(paintedAreaTerritory1);
        for (Node squareToPaint : squaresToPaint) {
            territoryB.paint(squareToPaint);
        }
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
        territories.add(territoryA);
        Territory territoryB = new Territory("B",new Node(51,51),new Node(101,101));
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

    public static void deleteTerritories() throws IOException {
        final Session session = JPAUtil.session();
        session.getTransaction().begin();
        session.createNativeQuery("DELETE FROM Territory").executeUpdate();
        session.getTransaction().commit();
    }

    private static List<Node> createSquaresToPaint(Long paintedAreaTerritory1) {
        List<Node> squaresToPaint = new ArrayList<Node>();
        int i = 1;
        while (i <= paintedAreaTerritory1) {
            squaresToPaint.add(new Node(i,i+1));
            i++;
        }
        return squaresToPaint;
    }
}
