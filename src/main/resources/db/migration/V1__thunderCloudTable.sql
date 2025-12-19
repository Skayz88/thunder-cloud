CREATE TABLE IF NOT EXISTS cl_process
(
    id               VARCHAR(64) PRIMARY KEY,
    name             VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS cl_execution
(
    id               VARCHAR(64) PRIMARY KEY,
    completed        BOOLEAN DEFAULT FALSE,
    process_id       VARCHAR(64) REFERENCES cl_process (id)
);

CREATE TABLE IF NOT EXISTS cl_task
(
    id               VARCHAR(64) PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    completed        BOOLEAN DEFAULT FALSE,
    execution_id     VARCHAR(64) REFERENCES cl_execution (id)
);