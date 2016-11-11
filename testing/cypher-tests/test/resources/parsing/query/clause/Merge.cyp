MERGE (n);
MERGE (n) ON MATCH SET n.prop = 1;
MERGE (n) ON CREATE SET n.prop = 1;
MERGE (n) ON MATCH SET n.prop = 1 ON CREATE SET n.prop = 1;
