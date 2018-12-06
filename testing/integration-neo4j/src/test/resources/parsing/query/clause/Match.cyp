MATCH (n);
OPTIONAL MATCH (n);
MATCH (n) USING INDEX var:Label(prop);
MATCH (n) USING JOIN ON var;
MATCH (n) USING JOIN ON var, var;
MATCH (n) USING SCAN var:Label;
MATCH (n) WHERE n.prop = 1;
MATCH p = ((head:Topic)<-[:FOLLOWS*]-(t:Topic))
  WHERE NOT (head)-[:FOLLOWS]->(:Topic)
RETURN nodes(p), length(p)
  ORDER BY length(p) DESC
  LIMIT 1;