CALL fun(1, 2);
CALL space.fun(1, 2);
CALL space.fun();
CALL space.fun() YIELD var;
CALL space.fun() YIELD expr as var;
CALL space.fun() YIELD var, var;
CALL dbms.procedures() YIELD name, signature
WHERE name='dbms.listConfig'
RETURN signature;
CALL db.propertyKeys() YIELD propertyKey AS prop
MATCH (n)
WHERE n[prop] IS NOT NULL RETURN prop, count(n) AS numNodes;