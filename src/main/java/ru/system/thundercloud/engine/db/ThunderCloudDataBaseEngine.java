package ru.system.thundercloud.engine.db;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.repository.TCLProcessRepository;
import ru.system.thundercloud.engine.db.service.TCLExecutionService;
import ru.system.thundercloud.engine.db.service.TCLProcessService;
import ru.system.thundercloud.engine.db.service.TCLTaskService;
import ru.system.thundercloud.engine.db.tables.TCLExecution;
import ru.system.thundercloud.engine.db.tables.TCLProcess;
import ru.system.thundercloud.engine.db.tables.TCLTask;
import ru.system.thundercloud.engine.service.process.ThunderCloudExecution;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;

import java.util.Map;
import java.util.UUID;

import static ru.system.thundercloud.util.Constants.THUNDER_CLOUD_START_DELEGATE;

/**
 *
 * @author DRakovskiy
 */

@Service
public class ThunderCloudDataBaseEngine {

    private final TCLExecutionService tclExecutionService;
    private final TCLProcessService tclProcessService;
    private final TCLTaskService tclTaskService;

    public ThunderCloudDataBaseEngine(TCLExecutionService tclExecutionService, TCLProcessService tclProcessService, TCLTaskService tclTaskService) {
        this.tclTaskService = tclTaskService;
        this.tclExecutionService = tclExecutionService;
        this.tclProcessService = tclProcessService;
    }

    public void checkProcessesInDatabase(Map<String, ThunderCloudProcess> processMap) {
        tclProcessService.checkProcessesInDatabase(processMap);
    }

    public TCLProcess getProcessByName(String processName) {
        return tclProcessService.getProcessByName(processName);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TCLExecution createExecution(ThunderCloudProcess process) {

        TCLProcess tclProcessEntity = getProcessByName(process.getName());

        ThunderCloudExecution execution = process.getExecution();

        String tclExecutionId = UUID.randomUUID().toString();

        TCLExecution tclExecutionEntity = new TCLExecution(
                tclExecutionId,
                execution.getName(),
                tclProcessEntity.id()
        );

        tclExecutionService.insertNewExecution(tclExecutionEntity);

        TCLTask tclTaskEntity = new TCLTask(
                UUID.randomUUID().toString(),
                THUNDER_CLOUD_START_DELEGATE,
                false,
                tclExecutionId
        );

        tclTaskService.insertNewTask(tclTaskEntity);

        return tclExecutionEntity;
    }
}
