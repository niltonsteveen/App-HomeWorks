package prototipo.udea.edu.co.homeworks;

import org.junit.Test;

import prototipo.udea.edu.co.homeworks.utils.NotificationTest;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        String token = "cPbreQn2c3I:APA91bFmnqjpQABCbGcmX7BSQkB5lyPddwlWIY2gkq02BOqo5hOQPATc_bVACjWRIXQPpQkehrXvsp-1zWn2dM-Z2BHiwUm0YrdXqSVmW0s7e1ZVScdoUs0lt0iKZEZaY-ZDMd1b3D12";
        NotificationTest.sendNotification(token, "Rafo es la monda");
        assertEquals(1L,1L);
    }
}