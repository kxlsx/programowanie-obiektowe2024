package agh.ics.oop;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SimulationEngine {
    private final static int THREAD_POOL_COUNT = 4;

    private final List<Simulation> simulations;
    private final List<Thread> threads;
    private final ExecutorService threadPool;


    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        threads = simulations.stream().map(Thread::new).collect(Collectors.toList());
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_COUNT);
    }

    public void runSync() {
        simulations.forEach(Simulation::run);
    }

    public void runAsync() {
        threads.forEach(Thread::start);
    }

    public void runAsyncInThreadPool() {
        simulations.forEach(sim -> threadPool.execute(sim));
        threadPool.shutdown();
    }

    public void awaitSimulationsEnd() throws InterruptedException{
        for(Thread thread : threads) {
            thread.join();
        }
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
