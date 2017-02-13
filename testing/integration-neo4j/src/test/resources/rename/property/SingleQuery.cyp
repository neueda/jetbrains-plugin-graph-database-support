MATCH (n)
WHERE n.renameThis = 'test'
RETURN n.rename<caret>This;
