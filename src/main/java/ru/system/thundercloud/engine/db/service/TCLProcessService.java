package ru.system.thundercloud.engine.db.service;

import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.db.repository.TCLProcessRepository;
import ru.system.thundercloud.engine.db.tables.TCLProcess;
import ru.system.thundercloud.engine.service.ThunderCloudEngine;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;

import java.util.Map;

/**
 *
 * @author DRakovskiy
 */
@Service
public class TCLProcessService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TCLProcessService.class);

    private final TCLProcessRepository tclProcessRepository;

    public TCLProcessService(TCLProcessRepository tclProcessRepository) {
        this.tclProcessRepository = tclProcessRepository;
    }

    public void checkProcessInDatabase(Map<String, ThunderCloudProcess> processMap) {
        processMap.keySet().forEach(processName -> {
            boolean ex = tclProcessRepository.existsByName(processName);
            System.out.println("Proc: " + processName + ", ex= " + ex);
            if (!tclProcessRepository.existsByName(processName)) {
                ThunderCloudProcess process = processMap.get(processName);
                tclProcessRepository.insertWithConflictHandling(process.getId(), process.getName());
                log.info("For process- {}: create in Database", processName);
            } else {
                log.info("For process- {}: has already been entered", processName);
            }
        });
    }
}
