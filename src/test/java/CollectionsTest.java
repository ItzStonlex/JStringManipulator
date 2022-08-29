import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.StringManipulatorSession;
import com.itzstonlex.stringmanipulator.StringQuery;

public class CollectionsTest {

    public static void main(String[] args) {
        StringManipulatorContext context = new StringManipulatorContext();

        StringManipulatorSession session = context.createSession();

        StringQuery collectionsQuery =
                session.makeQuery("new @Collection as $collect")
                        .next("add 'Misha' 'Leyn' in $collect")
                        .next("add 'Egor' in $collect")
                        .next("print $collect ]")
                        .next("print ]");

        session.execute(collectionsQuery);
        session.execute(session.makeQuery("print 'PI:' $PI ]"));

        session.execute(
                session.makeQuery("new @String as $hello_world")
                        .next("set $hello_world = 'Hello' 'World!' ]")
                        .next("print $hello_world ]"));

        session.execute(
                session.makeQuery("new @Number as $count")
                        .next("set $count = 4 ]")
                        .next("print 'Queries:' $count ]"));

        session.execute(session.makeQuery("print 3 5 6 ]"));

        // Commit & execute session queries.
        session.commit();
    }
}
