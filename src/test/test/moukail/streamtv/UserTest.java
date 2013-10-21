import static org.junit.Assert.*;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "/app-config.xml" })
public class UserTest {
	
	private UserDao dao;

	@Autowired
    public void setDao(UserDao dao) {
            this.dao = dao;
    }

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
