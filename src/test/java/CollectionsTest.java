import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.StringManipulatorSession;
import com.itzstonlex.stringmanipulator.StringQuery;
import com.itzstonlex.stringmanipulator.StringQueryResponse;

import java.util.Collection;

public class CollectionsTest {

    public static void main(String[] args) {
        StringManipulatorContext context = new StringManipulatorContext();

        StringManipulatorSession session = context.createSession();

        StringQuery collectionsQuery =
                session.makeQuery("var @Collection as $collect")
                        .next("add 'Misha' 'Leyn' in $collect")
                        .next("add 'Egor' in $collect")
                        .next("print $collect ]")
                        .next("print ]");

        session.submit(collectionsQuery.resetOnCommit(false));
        session.submit(session.makeQuery("print 'PI:' $PI ]").repeat(3));

        session.submit(
                session.makeQuery("var @String as $hello_world")
                        .next("set $hello_world = 'Hello' 'World!' ]")
                        .next("print $hello_world ]"));

        session.submit(
                session.makeQuery("var @Number as $count")
                        .next("set $count = 4 ]")
                        .next("print 'Queries:' $count ]"));

        session.submit(session.makeQuery("print 3 5 6 ]"));

        // Commit & Handle session queries response.
        StringQueryResponse response = session.commit();

        System.out.println("collection values length: " + response.<Collection<?>>var("collect").size());

        System.out.println("executed queries: " + response.getExecutedQueries());
        System.out.println("flushed queries: " + response.getFlushedQueries());

        System.out.println("Response:");
        System.out.println(response.getConsoleInput());
    }
}
