MATCH (n) RETURN (n);
OPTIONAL MATCH (n) RETURN (n);
MATCH (n) USING INDEX var:Label(prop) RETURN (n);
MATCH (n) USING JOIN ON var RETURN (n);
MATCH (n) USING JOIN ON var, var RETURN (n);
MATCH (n) USING SCAN var:Label RETURN (n);
MATCH (n) WHERE n.prop = 1 RETURN (n);
MATCH p = ((head:Topic)<-[:FOLLOWS*]-(t:Topic))
  WHERE NOT (head)-[:FOLLOWS]->(:Topic)
RETURN nodes(p), length(p)
  ORDER BY length(p) DESC
  LIMIT 1 RETURN (p);