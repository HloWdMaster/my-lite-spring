import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.test.v1.V1AllTest;
import org.litespring.test.v2.V2AllTest;
import org.litespring.test.v3.V3AllTest;
import org.litespring.test.v4.V4AllTest;
import org.litespring.test.v5.V5AllTest;
import org.litespring.test.v6.V6AllTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({V1AllTest.class, V2AllTest.class, V3AllTest.class, V4AllTest.class,
        V5AllTest.class, V6AllTest.class})
public class AllTests {
}
