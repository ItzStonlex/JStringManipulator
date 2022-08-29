import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.StringManipulatorSession;
import com.itzstonlex.stringmanipulator.StringQuery;

public class CollectionsTest {

    public static void main(String[] args) {
        StringManipulatorContext context = new StringManipulatorContext();

        StringManipulatorSession session = context.createSession();

        StringQuery collectionsQuery =
                session.makeQuery("new @Collection as $collect")
                        .next("add 'Misha' in $collect")
                        .next("add 'Egor' in $collect")
                        .next("print $collect ]")
                        .next("print ]");

        session.execute(collectionsQuery);

        session.execute(session.makeQuery("print 'Hello!' ]"));
        session.execute(session.makeQuery("print 'Queries:' 3 ]"));

        session.execute(session.makeQuery("print 'PI:' $PI ]"));

        // Commit & execute session queries.
        session.commit();
    }
}
