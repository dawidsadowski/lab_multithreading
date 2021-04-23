package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;

public class BetterRadar {

    private final PatriotBattery patriotBattery;
    private final int rocketCount;
    private final ExecutorService executorService;

    public BetterRadar(PatriotBattery patriotBattery, int rocketCount, ExecutorService executorService) {
        this.patriotBattery = patriotBattery;
        this.rocketCount = rocketCount;
        this.executorService = executorService;
    }

    private void launchPatriot(Scud enemyMissile, int rocketCount) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < rocketCount; i++) {
                patriotBattery.launchPatriot(enemyMissile);
            }
        };

        executorService.submit(launchPatriotTask);
    }

    public void notice(Scud enemyMissile) {
        launchPatriot(enemyMissile, rocketCount);
    }
}
