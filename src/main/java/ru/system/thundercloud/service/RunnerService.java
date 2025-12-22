package ru.system.thundercloud.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.service.ThunderCloudEngine;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;

/**
 *
 * @author DRakovskiy
 */
@Service
public class RunnerService implements CommandLineRunner {

    private final ThunderCloudEngine engine;
    private final ThunderCloudProcess process;

    public RunnerService(ThunderCloudEngine engine, ThunderCloudProcess process) {
        this.engine = engine;
        this.process = process;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Приложение запущено успешно!");
        engine.startProcess(process.getName());
    }



}
