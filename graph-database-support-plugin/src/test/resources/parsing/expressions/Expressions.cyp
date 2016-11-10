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
AND filter(var IN expr WHERE expr)
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
