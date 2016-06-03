// Line comment

/* Block comment */

// neo4j-shell keywords
BEGIN;
COMMIT;

// Single Cypher query
MATCH (n) RETURN n;

// Multiple Cypher queries
MATCH (n) RETURN n;
MATCH (m) RETURN m;

// Statement options
CYPHER RETURN n;
CYPHER 2.3 RETURN n;
CYPHER key=val RETURN n;
CYPHER key1=val1 key2=val2 RETURN n;
CYPHER 2.3 key=val RETURN n;
CYPHER 2.3 key1=val2 key2=val2 RETURN n;
EXPLAIN RETURN n;
PROFILE RETURN n;
CYPHER 2.3 key1=val2 key2=val2 EXPLAIN PROFILE RETURN n;

// Command
CREATE INDEX ON :Label(property);
DROP INDEX ON :Label(property);
CREATE CONSTRAINT ON (var:Label) ASSERT var.prop IS UNIQUE;
DROP CONSTRAINT ON (var:Label) ASSERT var.prop IS UNIQUE;
CREATE CONSTRAINT ON (var:Label) ASSERT exists(p.property);
DROP CONSTRAINT ON (var:Label) ASSERT exists(p.property);
CREATE CONSTRAINT ON ()-[r:LIKED]-() ASSERT exists(r.prop);
DROP CONSTRAINT ON ()-[r:LIKED]-() ASSERT exists(r.prop);

// Query - Bulk Import
USING PERIODIC COMMIT 100 LOAD CSV WITH HEADERS FROM "url" AS var FIELDTERMINATOR ",";
USING PERIODIC COMMIT LOAD CSV WITH HEADERS FROM "url" AS var FIELDTERMINATOR ",";
USING PERIODIC COMMIT LOAD CSV FROM "url" AS var FIELDTERMINATOR ",";
USING PERIODIC COMMIT LOAD CSV FROM "url" AS var;

// Query - Regular - Union
MATCH (n) RETURN n UNION ALL MATCH (n) RETURN n;
MATCH (n) RETURN n UNION MATCH (n) RETURN n;

// Query - Regular - Clause - LoadCSV
LOAD CSV WITH HEADERS FROM "url" AS var FIELDTERMINATOR ",";
LOAD CSV FROM "url" AS var FIELDTERMINATOR ",";
LOAD CSV FROM "url" AS var;

// Query - Regular - Clause - Start
START var=node(*),var=node(*)
START var=node:Label(prop="string");
START var=node:Label(prop={param});
START var=node:Label("string");
START var=node:Label({param});
START var=node(1)
START var=node(1, 2)
START var=node({param})
START var=node(*)
START var=rel:Label(prop="string");
START var=rel:Label(prop={param});
START var=rel:Label("string");
START var=rel:Label({param});
START var=rel(1)
START var=rel(1, 2)
START var=rel({param})
START var=rel(*)
START var=relationship:Label(prop="string");
START var=relationship:Label(prop={param});
START var=relationship:Label("string");
START var=relationship:Label({param});
START var=relationship(1)
START var=relationship(1, 2)
START var=relationship({param})
START var=relationship(*)

// Query - Regular - Clause - Match
MATCH (n);
OPTIONAL MATCH (n);
MATCH (n) USING INDEX var:Label(prop);
MATCH (n) USING JOIN ON var;
MATCH (n) USING JOIN ON var, var;
MATCH (n) USING SCAN var:Label;
MATCH (n) WHERE n.prop = 1;

// Query - Regular - Clause - Unwind
UNWIND expr AS var;

// Query - Regular - Clause - Merge
MERGE (n);
MERGE (n) ON MATCH SET n.prop = 1;
MERGE (n) ON CREATE SET n.prop = 1;
MERGE (n) ON MATCH SET n.prop = 1 ON CREATE SET n.prop = 1;

// Query - Regular - Clause - Create
CREATE UNIQUE (n);
CREATE (n);

// Query - Regular - Clause - SetClause
SET var.prop = 1;
SET var = 1;
SET var += 1;
SET var:Label;
SET var:Label:Label;

// Query - Regular - Clause - Delete
DELETE n;
DETACH DELETE n;
DELETE n, m;
DETACH DELETE n, m;

// Query - Regular - Clause - Remove
REMOVE var:Label;
REMOVE var:Label:Label;
REMOVE var.prop;
REMOVE var.prop, var:Label, var:Label:Label;

// Query - Regular - Clause - Foreach
FOREACH (var IN list | MATCH (n));

// Query - Regular - Clause - With
WITH *;
WITH DISTINCT *;
WITH *, expr as var, expr;
WITH * ORDER BY var;
WITH * ORDER BY var, var;
WITH * ORDER BY var ASC;
WITH * ORDER BY var ASCENDING;
WITH * ORDER BY var DESC;
WITH * ORDER BY var DESCENDING;
WITH * SKIP expr;
WITH * LIMIT expr;
WITH * ORDER BY var SKIP expr LIMIT expr;
WITH * WHERE n = 1;

// Query - Regular - Clause - Return
RETURN *;
RETURN DISTINCT *;
RETURN *, expr as var, expr;
RETURN * ORDER BY var;
RETURN * ORDER BY var, var;
RETURN * ORDER BY var ASC;
RETURN * ORDER BY var ASCENDING;
RETURN * ORDER BY var DESC;
RETURN * ORDER BY var DESCENDING;
RETURN * SKIP expr;
RETURN * LIMIT expr;
RETURN * ORDER BY var SKIP expr LIMIT expr;

// Query - Regular - Clause - Call
CALL fun(1, 2);
CALL space.fun(1, 2);
CALL space.fun();
CALL space.fun() YIELD var;
CALL space.fun() YIELD expr as var;
CALL space.fun() YIELD var, var;

// Patterns
MATCH ()
MATCH (n);
MATCH (n), (n);
MATCH p=(n);
MATCH (n), p=(n);
MATCH SHORTESTPATH((n));
MATCH ALLSHORTESTPATHS((n));
MATCH (n:Label)
MATCH (n {param})
MATCH (n {key: "value"})
MATCH (n {key: "value", key: "value"})
MATCH (n)--(n);
MATCH (n)<--(n);
MATCH (n)-->(n);
MATCH (n)<-->(n);
MATCH (n)-[]-(n);
MATCH (n)-[var]-(n);
MATCH (n)-[var:Type]-(n);
MATCH (n)-[var:Type]-(n);
MATCH (n)-[var?]-(n);
MATCH (n)-[var?:Type]-(n);
MATCH (n)-[var:Type|:Type]-(n);
MATCH (n)-[var:Type|Type]-(n);
MATCH (n)-[var:Type*]-(n);
MATCH (n)-[var:Type*..]-(n);
MATCH (n)-[var:Type*2..]-(n);
MATCH (n)-[var:Type*..2]-(n);
MATCH (n)-[var:Type*2..2]-(n);
MATCH (n)-[var {param}]-(n);
MATCH (n)-[var {key: "value"}]-(n);
MATCH (n)-[var {key: "value", key: "value"}]-(n);

// Expressions
WITH n
OR n
XOR n
AND n
AND NOT n
AND var = 1
AND var <> 1
AND var != 1
AND var < 1
AND var > 1
AND var <= 1
AND var >= 1
AND var - var
AND var + var
AND var * var
AND var / var
AND var % var
AND var ^ var
AND [var]
AND var [var..var]
AND var [..]
AND var [var..]
AND var [..var]
AND var =~ "s"
AND var IN [1, 2]
AND var STARTS WITH "1"
AND var ENDS WITH "2"
AND var CONTAINS "3"
AND var IS NULL
AND var IS NOT NULL
AND var.prop
AND var:Label
AND 1
AND "2"
AND {param}
AND true
AND false
AND null
AND CASE var WHEN expr THEN expr END
AND CASE WHEN expr THEN expr END
AND CASE WHEN expr THEN expr END
AND var.prop.prop.prop
AND {key: "val"}
AND {key: "val", key: "val"}
AND [var IN expr]
AND [var in expr | expr ]
AND []
AND [expr]
AND [expr, expr]
AND filter(var IN expr)
AND extract(var IN expr)
AND extract(var IN expr | expr)
AND reduce(var = expr, var IN expr | expr)
AND all(var IN expr)
AND any(var IN expr)
AND none(var IN expr)
AND single(var in expr)
AND exists(expr)
AND shortestpath((n))
AND allshortestpaths((n))
AND (n)-[]-()
AND (expr)
AND func()
AND func(*)
AND func(var)
AND func(var, var)
AND func(DISTINCT var)
AND func(DISTINCT var, var)
AND var
AND 1.2
AND 1
AND 'str'
AND "str"
AND "str \n"
AND "str \""
