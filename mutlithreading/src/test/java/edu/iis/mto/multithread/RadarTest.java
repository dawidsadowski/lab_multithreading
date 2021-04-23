package edu.iis.mto.multithread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;
    @Mock
    private ExecutorService executorServiceMock;

    @BeforeEach
    void setUp() {
        when(executorServiceMock.submit(any(Runnable.class))).thenAnswer(invocation -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run();
            return null;
        });
    }

    @RepeatedTest(30)
    void launchPatriotOnceWhenNoticesAScudMissile() {
        int count = 1;

        BetterRadar radar = new BetterRadar(batteryMock, count, executorServiceMock);
        Scud enemyMissile = new Scud();

        radar.notice(enemyMissile);

        verify(batteryMock, times(count)).launchPatriot(enemyMissile);
    }

    @RepeatedTest(25)
    void launchPatriotTenTimesWhenNoticesAScudMissile() {
        int count = 10;

        BetterRadar radar = new BetterRadar(batteryMock, count, executorServiceMock);
        Scud enemyMissile = new Scud();

        radar.notice(enemyMissile);

        verify(batteryMock, times(count)).launchPatriot(enemyMissile);
    }

}
