MATCH (n);
OPTIONAL MATCH (n);
MATCH (n) USING INDEX var:Label(prop);
MATCH (n) USING JOIN ON var;
MATCH (n) USING JOIN ON var, var;
MATCH (n) USING SCAN var:Label;
MATCH (n) WHERE n.prop = 1;
