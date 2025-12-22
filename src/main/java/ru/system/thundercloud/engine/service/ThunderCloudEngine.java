package ru.system.thundercloud.engine.service;

import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;

import java.util.List;

/**
 *
 * @author DRakovskiy
 */
@Service
public class ThunderCloudEngine {

    private final List<ThunderCloudProcess> processes;

    public ThunderCloudEngine(List<ThunderCloudProcess> processes) {
        this.processes = processes;
    }
}
