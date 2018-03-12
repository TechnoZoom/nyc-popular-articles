package mostpopular.kapil.com.nycmostpopular;

import mostpopular.kapil.com.nycmostpopular.custom.RecyclerViewMatcher;

public class TestUtils {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
