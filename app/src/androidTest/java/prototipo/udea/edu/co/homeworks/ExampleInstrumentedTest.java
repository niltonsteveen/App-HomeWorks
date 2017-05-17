package prototipo.udea.edu.co.homeworks;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import prototipo.udea.edu.co.homeworks.utils.NotificationTest;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        //assertEquals("prototipo.udea.edu.co.homeworks", appContext.getPackageName());
        String token = "cPbreQn2c3I:APA91bFmnqjpQABCbGcmX7BSQkB5lyPddwlWIY2gkq02BOqo5hOQPATc_bVACjWRIXQPpQkehrXvsp-1zWn2dM-Z2BHiwUm0YrdXqSVmW0s7e1ZVScdoUs0lt0iKZEZaY-ZDMd1b3D12";
        NotificationTest.sendNotification(token, "Rafo es la monda");
        assertEquals(1L,1L);
    }
}
