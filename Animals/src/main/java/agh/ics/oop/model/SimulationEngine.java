package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private final List<Simulation> simulations;
    private final List<Thread> threads = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);


    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        simulations.forEach(Simulation::run);
    }

    public void runAsync() {
        simulations.forEach(simulation -> {
            Thread newThread = new Thread(simulation);
            newThread.start();
            threads.add(newThread);
        });
    }

    public void awaitSimulationEnd() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }

        executorService.shutdown();
        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            executorService.shutdownNow();
            throw new InterruptedException("System nie przeprowadzil wszystkich symulacji");
        };
    }

    public void runAsyncInThreadPool() throws InterruptedException {
        simulations.forEach(executorService::submit);
    }
}
