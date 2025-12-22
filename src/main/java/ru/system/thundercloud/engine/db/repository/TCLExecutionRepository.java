/*
 * VTB Group. Do not reproduce without permission in writing.
 *
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.system.thundercloud.engine.db.tables.TCLExecution;

/**
 *
 * @author DRakovskiy
 */
public interface TCLExecutionRepository extends ListCrudRepository<TCLExecution, String> {
}
