MATCH (n)
WHERE n.renamedProperty = 'test'
RETURN n.renamedProperty;
