package vittahealth.hiring.challenge.acceptancetests;


import org.junit.Test;
import vittahealth.hiring.challenge.Territory;

import java.util.List;

public class TerritoryEndPointTest {

    @Test
    public void get_all_territories() throws Exception {

        final List<Territory> territories = DataBaseUtils.persistTerritories();
    }
}
