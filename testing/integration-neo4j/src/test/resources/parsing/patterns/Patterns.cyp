MATCH ()
MATCH (n);
MATCH (n), (n);
MATCH p=(n);
MATCH (n), p=(n);
MATCH SHORTESTPATH((n));
MATCH ALLSHORTESTPATHS((n));
MATCH (n:Label)
MATCH (n {param})
MATCH (n {ref: "value"})
MATCH (n {ref: "value", ref: "value"})
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
MATCH (n)-[var {ref: "value"}]-(n);
MATCH (n)-[var {ref: "value", ref: "value"}]-(n);
MATCH (n) - [:Type] -> (m)
