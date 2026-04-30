CREATE TABLE IF NOT EXISTS jobs (
    id           VARCHAR(36)  NOT NULL,
    title        VARCHAR(255) NOT NULL,
    company      VARCHAR(255) NOT NULL,
    location     VARCHAR(255) NOT NULL,
    remote       VARCHAR(50)  NOT NULL,
    contract_type VARCHAR(50) NOT NULL,
    min          INT,
    max          INT,
    currency     VARCHAR(10),
    status       VARCHAR(50)  NOT NULL,
    applied_at   DATE,
    updated_at   DATETIME     NOT NULL,
    url          TEXT,
    notes        TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS job_tags (
    job_id VARCHAR(36)  NOT NULL,
    tag    VARCHAR(255) NOT NULL,
    FOREIGN KEY (job_id) REFERENCES jobs (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS contacts (
    id       VARCHAR(36)  NOT NULL,
    name     VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL,
    email    VARCHAR(255),
    phone    VARCHAR(50),
    linkedin VARCHAR(255),
    job_id   VARCHAR(36)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (job_id) REFERENCES jobs (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS interviews (
    id     VARCHAR(36) NOT NULL,
    date   DATE        NOT NULL,
    type   VARCHAR(50) NOT NULL,
    notes  TEXT,
    job_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (job_id) REFERENCES jobs (id) ON DELETE CASCADE
);
