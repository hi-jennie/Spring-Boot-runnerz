DROP TABLE IF EXISTS run;
CREATE TABLE IF NOT EXISTS Run
(
    id           INT          NOT NULL,
    title        varchar(250) NOT NULL,
    started_on   timestamp    NOT NULL,
    completed_on timestamp    NOT NULL,
    miles        INT          NOT NULL,
    location     varchar(10)  NOT NULL,
    PRIMARY KEY (id)
);

-- 	1.	DROP TABLE IF EXISTS run;删除旧表（如果存在），避免表结构冲突。
-- 	2.	确保新表能顺利创建，防止因表已存在而导致 SQL 语句失败。