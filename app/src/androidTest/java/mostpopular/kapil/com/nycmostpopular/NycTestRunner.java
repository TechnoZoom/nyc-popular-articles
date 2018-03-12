package mostpopular.kapil.com.nycmostpopular;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import mostpopular.kapil.com.nycmostpopular.di.testing.TestNycApp;


public class NycTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader classLoader, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // replace Application class with mock one
        return super.newApplication(classLoader, TestNycApp.class.getName(), context);
    }
}
