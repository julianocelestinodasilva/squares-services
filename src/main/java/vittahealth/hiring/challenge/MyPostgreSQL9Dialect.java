package vittahealth.hiring.challenge;


import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

public class MyPostgreSQL9Dialect extends PostgreSQL9Dialect {

    public MyPostgreSQL9Dialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }

}
