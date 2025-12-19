CREATE TABLE IF NOT EXISTS tcl_process
(
    id               VARCHAR(64) PRIMARY KEY,
    name             VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS tcl_execution
(
    id               VARCHAR(64) PRIMARY KEY,
    completed        BOOLEAN DEFAULT FALSE,
    process_id       VARCHAR(64) REFERENCES tcl_process (id)
);

CREATE TABLE IF NOT EXISTS tcl_task
(
    id               VARCHAR(64) PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    completed        BOOLEAN DEFAULT FALSE,
    execution_id     VARCHAR(64) REFERENCES tcl_execution (id)
);