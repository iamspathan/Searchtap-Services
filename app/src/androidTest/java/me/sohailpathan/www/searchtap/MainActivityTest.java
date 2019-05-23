package me.sohailpathan.www.searchtap;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.jar.Manifest;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;

public class MainActivityTest  {

    @Rule
    ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity=null;
    Instrumentation.ActivityMonitor monitor =getInstrumentation().addMonitor(ToggleRegister.class.getName(),null,false);

    @Before
    public void setUp() throws Exception{
        mainActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testFloatingActionButton(){
        assertNotNull(mainActivity.findViewById(R.id.fab));
        onView(withId(R.id.fab)).perform(click());
        Activity activity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(activity);
        activity.finish();
    }

    @After
    public void tearDown() throws Exception{
        mainActivity=null;
    }

}
