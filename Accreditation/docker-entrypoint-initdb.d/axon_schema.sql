-- 1. DOMAIN_EVENT_ENTRY: stores all events in global order
CREATE TABLE IF NOT EXISTS domain_event_entry (
    global_index    BIGSERIAL PRIMARY KEY,                                  -- a unique, ever‐increasing index
    aggregate_identifier VARCHAR(255) NOT NULL,                             -- the aggregate’s id
    sequence_number  BIGINT NOT NULL,                                       -- the event’s sequence number within its aggregate
    type             VARCHAR(255) NOT NULL,                                 -- the event’s type
    timestamp        VARCHAR(255) NOT NULL,                                 -- when the event occurred
    payload          BYTEA NOT NULL,                                        -- serialized event payload
    meta_data        BYTEA NOT NULL,                                        -- serialized metadata
    payload_type     VARCHAR(255) NOT NULL,                                 -- fully qualified class name
    payload_revision VARCHAR(255)                                          -- optional revision
    );
CREATE INDEX IF NOT EXISTS idx_domain_aggregate
    ON domain_event_entry (aggregate_identifier, sequence_number);          -- speeds up replay queries :contentReference[oaicite:1]{index=1}

-- 2. SNAPSHOT_EVENT_ENTRY: stores snapshots to optimize replays
CREATE TABLE IF NOT EXISTS snapshot_event_entry (
    aggregate_identifier VARCHAR(255) PRIMARY KEY NOT NULL,                 -- one snapshot per aggregate
    sequence_number  BIGINT NOT NULL,                                       -- snapshot’s aggregate sequence at creation
    timestamp        VARCHAR(255) NOT NULL,
    payload          BYTEA NOT NULL,
    meta_data        BYTEA NOT NULL,
    payload_type     VARCHAR(255) NOT NULL,
    payload_revision VARCHAR(255)
    );

-- 3. TOKEN_ENTRY: tracks each TrackingEventProcessor’s position
CREATE TABLE IF NOT EXISTS token_entry (
    processor_name   VARCHAR(255) NOT NULL,                                 -- the processor’s name
    segment          INT NOT NULL,                                          -- segment number (for parallel processors)
    token            BYTEA,                                                 -- serialized TrackingToken
    token_type       VARCHAR(255),                                          -- token’s Java type
    timestamp        VARCHAR(255),                                          -- last update time
    PRIMARY KEY (processor_name, segment)                                   -- ensures one row per segment :contentReference[oaicite:2]{index=2}
    );

-- 4. SAGA_ENTRY: stores serialized saga instances
CREATE TABLE IF NOT EXISTS saga_entry (
    saga_id          VARCHAR(255) PRIMARY KEY NOT NULL,                     -- unique saga identifier
    revision         VARCHAR(255),                                          -- saga class revision
    saga_type        VARCHAR(255) NOT NULL,                                 -- fully qualified saga class name
    serialized_saga  BYTEA NOT NULL                                         -- serialized saga state :contentReference[oaicite:3]{index=3}
    );

-- 5. ASSOCIATION_VALUE_ENTRY: lookup table linking events to saga instances
CREATE TABLE IF NOT EXISTS association_value_entry (
    id                 BIGSERIAL PRIMARY KEY,   -- surrogate PK for the @Id field
    association_key    VARCHAR(255) NOT NULL,
    association_value  VARCHAR(255) NOT NULL,
    saga_type          VARCHAR(255) NOT NULL,
    saga_id            VARCHAR(255) NOT NULL,
    CONSTRAINT uq_assoc_value
    UNIQUE (association_key, association_value, saga_type, saga_id)
    );

-- 6. DEAD_LETTER_ENTRY
CREATE TABLE IF NOT EXISTS dead_letter_entry (
    dead_letter_id        VARCHAR(255)      PRIMARY KEY,      -- @Id @Column(name="dead_letter_id") :contentReference[oaicite:0]{index=0}
    processing_group      VARCHAR(255)   NOT NULL,         -- getProcessingGroup() :contentReference[oaicite:1]{index=1}
    sequence_identifier   VARCHAR(255)   NOT NULL,         -- getSequenceIdentifier() :contentReference[oaicite:2]{index=2}
    sequence_index        BIGINT         NOT NULL,         -- getSequenceIndex() :contentReference[oaicite:3]{index=3}

    enqueued_at           TIMESTAMP WITH TIME ZONE NOT NULL,         -- getEnqueuedAt() :contentReference[oaicite:4]{index=4}
    last_touched          TIMESTAMP WITH TIME ZONE,                      -- getLastTouched() :contentReference[oaicite:5]{index=5}
    processing_started    TIMESTAMP WITH TIME ZONE,                      -- getProcessingStarted() :contentReference[oaicite:6]{index=6}
    time_stamp VARCHAR(255),

    cause_type            VARCHAR(255),                      -- getCauseType() :contentReference[oaicite:7]{index=7}
    cause_message         VARCHAR(255),                      -- getCauseMessage() :contentReference[oaicite:8]{index=8}

    diagnostics           OID,                            -- @Lob; serialized diagnostics :contentReference[oaicite:9]{index=9}

-- Embedded DeadLetterEventEntry fields:
    message_type          VARCHAR(255)   NOT NULL,         -- getMessageType() :contentReference[oaicite:10]{index=10}
    event_identifier      VARCHAR(255)   NOT NULL,         -- getEventIdentifier() :contentReference[oaicite:11]{index=11}
    message_timestamp     VARCHAR(255)   NOT NULL,         -- getTimeStamp() :contentReference[oaicite:12]{index=12}

    payload_type          VARCHAR(255),                      -- getPayload().getType() :contentReference[oaicite:13]{index=13}
    payload_revision      VARCHAR(255),                      -- getPayload().getRevision() :contentReference[oaicite:14]{index=14}
    payload               OID,                            -- getPayload().getData() :contentReference[oaicite:15]{index=15}

    meta_data             OID,                            -- getMetaData().getData() :contentReference[oaicite:16]{index=16}

    aggregate_type        VARCHAR(255),                      -- getAggregateType() :contentReference[oaicite:17]{index=17}
    aggregate_identifier  VARCHAR(255),                      -- getAggregateIdentifier() :contentReference[oaicite:18]{index=18}
    sequence_number       BIGINT,                            -- getSequenceNumber() :contentReference[oaicite:19]{index=19}

    token_type            VARCHAR(255),                      -- getTrackingToken().getType() :contentReference[oaicite:20]{index=20}
    token                 OID,                             -- getTrackingToken().getData() :contentReference[oaicite:21]{index=21}
    type                  VARCHAR(255)        NOT NULL
    );

-- Index to quickly find dead letters by processing group & sequence
CREATE INDEX IF NOT EXISTS idx_dlq_processing_group_sequence
    ON dead_letter_entry (processing_group, sequence_identifier, sequence_index);