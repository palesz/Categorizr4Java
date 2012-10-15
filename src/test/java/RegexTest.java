import net.palesz.categorizr4java.Categorizr;
import net.palesz.categorizr4java.Category;
import net.palesz.categorizr4java.ICategoryHolder;
import net.palesz.categorizr4java.IClientInfo;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static net.palesz.categorizr4java.Category.*;

public class RegexTest {

	@Test
	public void testRegex() {
		assertEquals(DESKTOP, cat("Windows NT 6.1"));
		assertEquals(TABLET, cat("iPad"));
		assertEquals(MOBILE, cat("Windows Phone"));
	}

	public Category cat(String userAgent) {
		return new Categorizr(new MockClientInfo(userAgent), new MockCategoryHolder()).categorize();
	}

	private static class MockClientInfo implements IClientInfo {

		private Category preference;
		private String userAgent;

		public MockClientInfo(String userAgent) {
			this(null, userAgent);
		}

		public MockClientInfo(Category preference, String userAgent) {
			this.preference = preference;
			this.userAgent = userAgent;
		}

		public Category preference() {
			return preference;
		}

		public String userAgent() {
			return userAgent;
		}
	}

	private static class MockCategoryHolder implements ICategoryHolder {
		private Category category;

		private MockCategoryHolder() {
			this(null);
		}

		private MockCategoryHolder(Category category) {
			this.category = category;
		}

		public Category get() {
			return category;
		}

		public void set(Category category) {
			this.category = category;
		}
	}

}
