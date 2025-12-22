package ru.system.thundercloud.engine.db.service;

import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.db.repository.TCLProcessRepository;
import ru.system.thundercloud.engine.db.tables.TCLProcess;
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

    public TCLProcess getProcessByName(String processName) {
        return tclProcessRepository.getTCLProcessByName(processName);
    }

    public void checkProcessesInDatabase(Map<String, ThunderCloudProcess> processMap) {
        processMap.forEach((processName, thunderCloudProcess) -> {
            if (!isThisProcessInDatabase(processName))
                saveProcessIsThisNeeded(thunderCloudProcess);
        });
    }

    private void saveProcessIsThisNeeded(ThunderCloudProcess process) {
            tclProcessRepository.insertProcess(process.getId(), process.getName());
            log.debug("Сохранение процесса {} в таблицу Базы данных", process);
    }

    private boolean isThisProcessInDatabase(String processName) {
        log.debug("Проверка процесса - {} в таблице Базы данных", processName);
        return tclProcessRepository.existsByName(processName);
    }
}
