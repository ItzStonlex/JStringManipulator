import com.itzstonlex.stringmanipulator.StringManipulatorContext;
import com.itzstonlex.stringmanipulator.StringManipulatorSession;

public class CollectionsTest {

    public static void main(String[] args) {
        StringManipulatorContext context = new StringManipulatorContext();
        StringManipulatorSession session = context.createSession();

        long speed = System.currentTimeMillis();

        session.execute(
                session.makeQuery("new @Collection as $collect")
                        .newLine("add 'Misha' into $collect")
                        .newLine("add 'Egor' into $collect")
                        .newLine("print $collect ]")
        );

        session.execute(
                session.makeQuery("print 'Hello!' ]")
        );

        session.execute(
                session.makeQuery("print 'Queries:' 3 ]")
        );

        session.execute(
                session.makeQuery("print 'PI:' 3.1415 ]")
        );

        session.commit();
        System.out.println(System.currentTimeMillis() - speed + "ms");
    }
}
