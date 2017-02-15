package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.formatting;

import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseFormattingTest;

public class CommunityFormattingTest extends BaseFormattingTest {

    public void testSupplyChainManagementQ1() {
        doTest("CREATE (:Product { name: \"Product\", lat: tan(rand())*100, lon: tan(rand())*100, co2: 200, cost: 100, time: 0 })\n" +
                        "FOREACH (r IN range(0,1)|\n" +
                        "CREATE (:Wholesaler { name:\"Wholesaler\" + r, cost: round(exp(rand()*3)+ 20), co2: round(exp(rand()*8)+ 250), lat: tan(rand())*100, lon: tan(rand())*100, time: round(rand()*5)}))\n" +
                        "FOREACH (r IN range(0,10)|\n" +
                        "CREATE (:Retailer { name:\"Retailer\" + r, cost: round(exp(rand()*3)+ 20), co2: round(exp(rand()*8)+ 250), lat: tan(rand())*100, lon: tan(rand())*100, time: 1}))\n" +
                        "FOREACH (r IN range(0,2)|\n" +
                        "CREATE (:SupplierA { name:\"SupplierA\" + r, cost: round(exp(rand()*3)+ 20), co2: round(exp(rand()*8)+ 250), lat: tan(rand())*100, lon: tan(rand())*100, time: round(rand()*5)}))\n" +
                        "FOREACH (r IN range(0,1)|\n" +
                        "CREATE (:SupplierB { name:\"SupplierB\" + r, cost: round(exp(rand()*3)+ 20), co2: round(exp(rand()*8)+ 250), lat: tan(rand())*100, lon: tan(rand())*100, time: round(rand()*5)}))\n" +
                        "FOREACH (r IN range(0,5)|\n" +
                        "CREATE (:RawSupplierA{ name:\"RawSupplierA\" + r, cost: round(exp(rand()*3)+ 20), co2: round(exp(rand()*8)+ 250), lat: tan(rand())*100, lon: tan(rand())*100, time: round(rand()*5)}))\n" +
                        "FOREACH (r IN range(0,5)|\n" +
                        "CREATE (:RawSupplierB{ name:\"RawSuppplierB\" + r, cost: round(exp(rand()*3)+ 20), co2: round(exp(rand()*8)+ 250), lat: tan(rand())*100, lon: tan(rand())*100, time: round(rand()*5)}))",

                "CREATE (:Product {name: 'Product', lat: tan(rand()) * 100, lon: tan(rand()) * 100, co2: 200, cost: 100, time: 0})\n" +
                        "FOREACH (r IN range(0, 1) |\n" +
                        "  CREATE(:Wholesaler {name: 'Wholesaler' + r, cost: round(exp(rand() * 3) + 20), co2: round(exp(rand() * 8) + 250),\n" +
                        "                      lat:  tan(rand()) * 100, lon: tan(rand()) * 100, time: round(rand() * 5)})\n" +
                        ")\n" +
                        "FOREACH (r IN range(0, 10) |\n" +
                        "  CREATE(:Retailer {name: 'Retailer' + r, cost: round(exp(rand() * 3) + 20), co2: round(exp(rand() * 8) + 250),\n" +
                        "                    lat:  tan(rand()) * 100, lon: tan(rand()) * 100, time: 1})\n" +
                        ")\n" +
                        "FOREACH (r IN range(0, 2) |\n" +
                        "  CREATE(:SupplierA {name: 'SupplierA' + r, cost: round(exp(rand() * 3) + 20), co2: round(exp(rand() * 8) + 250),\n" +
                        "                     lat:  tan(rand()) * 100, lon: tan(rand()) * 100, time: round(rand() * 5)})\n" +
                        ")\n" +
                        "FOREACH (r IN range(0, 1) |\n" +
                        "  CREATE(:SupplierB {name: 'SupplierB' + r, cost: round(exp(rand() * 3) + 20), co2: round(exp(rand() * 8) + 250),\n" +
                        "                     lat:  tan(rand()) * 100, lon: tan(rand()) * 100, time: round(rand() * 5)})\n" +
                        ")\n" +
                        "FOREACH (r IN range(0, 5) |\n" +
                        "  CREATE(:RawSupplierA {name: 'RawSupplierA' + r, cost: round(exp(rand() * 3) + 20), co2: round(exp(rand() * 8) + 250),\n" +
                        "                        lat:  tan(rand()) * 100, lon: tan(rand()) * 100, time: round(rand() * 5)})\n" +
                        ")\n" +
                        "FOREACH (r IN range(0, 5) |\n" +
                        "  CREATE(:RawSupplierB {name: 'RawSuppplierB' + r, cost: round(exp(rand() * 3) + 20), co2: round(exp(rand() * 8) + 250),\n" +
                        "                        lat:  tan(rand()) * 100, lon: tan(rand()) * 100, time: round(rand() * 5)})\n" +
                        ")");
    }

    public void testSupplyChainManagementQ2() {
        doTest("MATCH (p:Product)-[r1]->(w)-[r2]->(re:Retailer)\n" +
                "WITH DISTINCT(substring(w.name, 10)) AS Num,\n" +
                "avg(r1.km + r2.km) AS Average_Distance,\n" +
                "sum(r1.km + r2.km) AS Total_Distance\n" +
                "RETURN \"Wholesaler\" + Num AS Wholesaler, Total_Distance, round(Average_Distance)\n" +
                "ORDER BY Total_Distance",

                "MATCH (p:Product)-[r1]->(w)-[r2]->(re:Retailer)\n" +
                        "WITH DISTINCT (substring(w.name, 10)) AS Num,\n" +
                        "              avg(r1.km + r2.km) AS Average_Distance,\n" +
                        "              sum(r1.km + r2.km) AS Total_Distance\n" +
                        "RETURN 'Wholesaler' + Num AS Wholesaler, Total_Distance, round(Average_Distance)\n" +
                        "  ORDER BY Total_Distance");
    }

    public void testSupplyChainManagementQ3() {
        doTest("MATCH chain=(rs:RawSupplierA)-[r*]->(re:Retailer)\n" +
                "WITH reduce(wait = 0, s IN nodes(chain)| wait + s.time) AS waitTime, chain\n" +
                "WHERE waitTime < 8\n" +
                "WITH extract(n IN nodes(chain)| n.name) AS SupplyChain, waitTime\n" +
                "ORDER BY SupplyChain[1]\n" +
                "RETURN SupplyChain, waitTime",

                "MATCH chain = (rs:RawSupplierA)-[r*]->(re:Retailer)\n" +
                        "WITH reduce(wait = 0, s IN nodes(chain) | wait + s.time) AS waitTime, chain\n" +
                        "  WHERE waitTime < 8\n" +
                        "WITH extract(n IN nodes(chain) | n.name) AS SupplyChain, waitTime\n" +
                        "  ORDER BY SupplyChain[1]\n" +
                        "RETURN SupplyChain, waitTime");
    }

    public void testSupplyChainManagementQ4() {
        doTest("MATCH chain=(rsB:RawSupplierB)-[r*]->(p:Product)<-[r*]-(rsA:RawSupplierA)\n" +
                "WITH reduce(wait = 0, s IN nodes(chain)| wait + s.timeR) AS tRating,\n" +
                "reduce(wait = 0, s IN nodes(chain)| wait + s.costR) AS cRating,\n" +
                "reduce(wait = 0, s IN nodes(chain)| wait + s.wasteR) AS wRating, chain, p\n" +
                "WITH chain, p, ((cRating*0.6) + (wRating*0.2) + (tRating*0.2) ) AS score\n" +
                "WITH score, p, extract(n IN nodes(chain)| n.name) AS SupplyChain1 ORDER BY score DESC\n" +
                "MATCH chain=(p)-[r*]->(re:Retailer)\n" +
                "WITH reduce(wait = 0, s IN nodes(chain)| wait + s.timeR) AS tRating,\n" +
                "reduce(wait = 0, s IN nodes(chain)| wait + s.costR) AS cRating,\n" +
                "reduce(wait = 0, s IN nodes(chain)| wait + s.wasteR) AS wRating, chain, score, SupplyChain1\n" +
                "WITH chain, SupplyChain1, ((cRating*0.6) + (wRating*0.2) + (tRating*0.2) + score) AS totalScore\n" +
                "WITH SupplyChain1, totalScore, extract(n IN nodes(chain)| n.name) AS SupplyChain2 ORDER BY totalScore DESC\n" +
                "RETURN SupplyChain2 + SupplyChain1, totalScore\n" +
                "LIMIT 1",

                "MATCH chain = (rsB:RawSupplierB)-[r*]->(p:Product)<-[r*]-(rsA:RawSupplierA)\n" +
                        "WITH reduce(wait = 0, s IN nodes(chain) | wait + s.timeR) AS tRating,\n" +
                        "     reduce(wait = 0, s IN nodes(chain) | wait + s.costR) AS cRating,\n" +
                        "     reduce(wait = 0, s IN nodes(chain) | wait + s.wasteR) AS wRating, chain, p\n" +
                        "WITH chain, p, ((cRating * 0.6) + (wRating * 0.2) + (tRating * 0.2)) AS score\n" +
                        "WITH score, p, extract(n IN nodes(chain) | n.name) AS SupplyChain1\n" +
                        "  ORDER BY score DESC\n" +
                        "MATCH chain = (p)-[r*]->(re:Retailer)\n" +
                        "WITH reduce(wait = 0, s IN nodes(chain) | wait + s.timeR) AS tRating,\n" +
                        "     reduce(wait = 0, s IN nodes(chain) | wait + s.costR) AS cRating,\n" +
                        "     reduce(wait = 0, s IN nodes(chain) | wait + s.wasteR) AS wRating, chain, score, SupplyChain1\n" +
                        "WITH chain, SupplyChain1, ((cRating * 0.6) + (wRating * 0.2) + (tRating * 0.2) + score) AS totalScore\n" +
                        "WITH SupplyChain1, totalScore, extract(n IN nodes(chain) | n.name) AS SupplyChain2\n" +
                        "  ORDER BY totalScore DESC\n" +
                        "RETURN SupplyChain2 + SupplyChain1, totalScore\n" +
                        "  LIMIT 1");
    }

    public void testBankFraudDetectionQ1() {
        doTest("MATCH (accountHolder:AccountHolder)-[]->(contactInformation)\n" +
                "WITH contactInformation,\n" +
                "count(accountHolder) AS RingSize\n" +
                "MATCH (contactInformation)<-[]-(accountHolder)\n" +
                "WITH collect(accountHolder.UniqueId) AS AccountHolders,\n" +
                "contactInformation, RingSize\n" +
                "WHERE RingSize > 1\n" +
                "RETURN AccountHolders AS FraudRing,\n" +
                "labels(contactInformation) AS ContactType,\n" +
                "RingSize\n" +
                "ORDER BY RingSize DESC",

                "MATCH (accountHolder:AccountHolder)-[]->(contactInformation)\n" +
                        "WITH contactInformation,\n" +
                        "     count(accountHolder) AS RingSize\n" +
                        "MATCH (contactInformation)<-[]-(accountHolder)\n" +
                        "WITH collect(accountHolder.UniqueId) AS AccountHolders,\n" +
                        "     contactInformation, RingSize\n" +
                        "  WHERE RingSize > 1\n" +
                        "RETURN AccountHolders AS FraudRing,\n" +
                        "       labels(contactInformation) AS ContactType,\n" +
                        "       RingSize\n" +
                        "  ORDER BY RingSize DESC");
    }

    public void testBankFraudDetectionQ2() {
        doTest("MATCH (accountHolder:AccountHolder)-[]->(contactInformation)\n" +
                "WITH contactInformation,\n" +
                "count(accountHolder) AS RingSize\n" +
                "MATCH (contactInformation)<-[]-(accountHolder),\n" +
                "(accountHolder)-[r:HAS_CREDITCARD|HAS_UNSECUREDLOAN]->(unsecuredAccount)\n" +
                "WITH collect(DISTINCT accountHolder.UniqueId) AS AccountHolders,\n" +
                "contactInformation, RingSize,\n" +
                "SUM(CASE type(r)\n" +
                "WHEN 'HAS_CREDITCARD' THEN unsecuredAccount.LIMIT\n" +
                "WHEN 'HAS_UNSECUREDLOAN' THEN unsecuredAccount.Balance\n" +
                "ELSE 0\n" +
                "END) AS FinancialRisk\n" +
                "WHERE RingSize > 1\n" +
                "RETURN AccountHolders AS FraudRing,\n" +
                "labels(contactInformation) AS ContactType,\n" +
                "RingSize,\n" +
                "round(FinancialRisk) AS FinancialRisk\n" +
                "ORDER BY FinancialRisk DESC",

                "MATCH (accountHolder:AccountHolder)-[]->(contactInformation)\n" +
                        "WITH contactInformation,\n" +
                        "     count(accountHolder) AS RingSize\n" +
                        "MATCH (contactInformation)<-[]-(accountHolder),\n" +
                        "      (accountHolder)-[r:HAS_CREDITCARD|HAS_UNSECUREDLOAN]->(unsecuredAccount)\n" +
                        "WITH collect(DISTINCT accountHolder.UniqueId) AS AccountHolders,\n" +
                        "     contactInformation, RingSize,\n" +
                        "     sum(CASE type(r)\n" +
                        "       WHEN 'HAS_CREDITCARD' THEN unsecuredAccount.LIMIT\n" +
                        "       WHEN 'HAS_UNSECUREDLOAN' THEN unsecuredAccount.Balance\n" +
                        "       ELSE 0\n" +
                        "       END) AS FinancialRisk\n" +
                        "  WHERE RingSize > 1\n" +
                        "RETURN AccountHolders AS FraudRing,\n" +
                        "       labels(contactInformation) AS ContactType,\n" +
                        "       RingSize,\n" +
                        "       round(FinancialRisk) AS FinancialRisk\n" +
                        "  ORDER BY FinancialRisk DESC");
    }

    public void testGeoptimaQ1() {
        doTest("CREATE\n" +
                "(users {name:\"Users\"}),\n" +
                "(projects {name:\"Projects\"}),\n" +
                "(project:Project {name:\"Operator X\"}),\n" +
                "(craig:User {name:\"Craig\"}),\n" +
                "(jonas:User {name:\"Jonas\"}),\n" +
                "(john:User {name:\"John\"}),\n" +
                "(users)-[:user]->(craig),\n" +
                "(users)-[:user]->(jonas),\n" +
                "(users)-[:user]->(john),\n" +
                "(projects)-[:project]->(project),\n" +
                "(craig)-[:su]->(project),\n" +
                "(jonas)-[:su]->(project)",

                "CREATE\n" +
                        "  (users {name: 'Users'}),\n" +
                        "  (projects {name: 'Projects'}),\n" +
                        "  (project:Project {name: 'Operator X'}),\n" +
                        "  (craig:User {name: 'Craig'}),\n" +
                        "  (jonas:User {name: 'Jonas'}),\n" +
                        "  (john:User {name: 'John'}),\n" +
                        "  (users)-[:user]->(craig),\n" +
                        "  (users)-[:user]->(jonas),\n" +
                        "  (users)-[:user]->(john),\n" +
                        "  (projects)-[:project]->(project),\n" +
                        "  (craig)-[:su]->(project),\n" +
                        "  (jonas)-[:su]->(project)");
    }

    public void testGeoptimaQ2() {
        doTest("MATCH (project:Project)\n" +
                "WHERE project.name = 'Operator X'\n" +
                "CREATE\n" +
                "(filter_plmn:Filter {name:\"Filter PLMN\"}),\n" +
                "(filter_devices:Filter {name:\"Filter Devices\"}),\n" +
                "(f1:FilterPLMN {name:\"Operator X\", mcc:'240', mnc:'08'}),\n" +
                "(f2:FilterPLMN {name:\"My Operator\", mcc:'240', mnc:'18'}),\n" +
                "(f3:FilterPLMN {name:\"XTele 2\", mcc:'240', mnc:'28'}),\n" +
                "(fd:FilterDevices {name:\"Test Devices\", devices:[\n" +
                "'354436058915420','358506046830281','356451041578183','351503053121388','353328059211902'\n" +
                "]}),\n" +
                "(project)-[:filter]->(filter_plmn),\n" +
                "(project)-[:filter]->(filter_devices),\n" +
                "(filter_plmn)-[:filter]->(f1),\n" +
                "(filter_plmn)-[:filter]->(f2),\n" +
                "(filter_plmn)-[:filter]->(f3),\n" +
                "(filter_devices)-[:filter]->(fd)",

                "MATCH (project:Project)\n" +
                        "  WHERE project.name = 'Operator X'\n" +
                        "CREATE\n" +
                        "  (filter_plmn:Filter {name: 'Filter PLMN'}),\n" +
                        "  (filter_devices:Filter {name: 'Filter Devices'}),\n" +
                        "  (f1:FilterPLMN {name: 'Operator X', mcc: '240', mnc: '08'}),\n" +
                        "  (f2:FilterPLMN {name: 'My Operator', mcc: '240', mnc: '18'}),\n" +
                        "  (f3:FilterPLMN {name: 'XTele 2', mcc: '240', mnc: '28'}),\n" +
                        "  (fd:FilterDevices {name: 'Test Devices', devices: [\n" +
                        "    '354436058915420', '358506046830281', '356451041578183', '351503053121388', '353328059211902'\n" +
                        "  ]}),\n" +
                        "  (project)-[:filter]->(filter_plmn),\n" +
                        "  (project)-[:filter]->(filter_devices),\n" +
                        "  (filter_plmn)-[:filter]->(f1),\n" +
                        "  (filter_plmn)-[:filter]->(f2),\n" +
                        "  (filter_plmn)-[:filter]->(f3),\n" +
                        "  (filter_devices)-[:filter]->(fd)");
    }

    public void testGeoptimaQ3() {
        doTest("MATCH (p:Project)-[:filter*]->(f:FilterPLMN)\n" +
                "WHERE p.name = 'Operator X'\n" +
                "RETURN f.name AS Name, f.mcc AS mcc, f.mnc AS mnc",

                "MATCH (p:Project)-[:filter*]->(f:FilterPLMN)\n" +
                        "  WHERE p.name = 'Operator X'\n" +
                        "RETURN f.name AS Name, f.mcc AS mcc, f.mnc AS mnc");
    }

    public void testGeoptimaQ4() {
        doTest("MATCH (craig:User)-[:su]->(project:Project)\n" +
                "WHERE craig.name = 'Craig' and project.name = 'Operator X'\n" +
                "CREATE\n" +
                "(devices {name:'Devices'}),\n" +
                "(sims {name:'SIM Cards'}),\n" +
                "(active_devices {name:'Active Devices'}),\n" +
                "(project)-[:ACTIVE_DEVICES]->(active_devices),\n" +
                "(devices)-[:DEVICE]->(d1:Device {name:'354436058915420'}),\n" +
                "(devices)-[:DEVICE]->(d2:Device {name:'358506046830281'}),\n" +
                "(devices)-[:DEVICE]->(d3:Device {name:'353328059211902'}),\n" +
                "(sims)-[:SIM]->(s1:SIM {name:'240080000000001'}),\n" +
                "(sims)-[:SIM]->(s2:SIM {name:'240080000000002'}),\n" +
                "(sims)-[:SIM]->(s3:SIM {name:'240080000000003'}),\n" +
                "(sims)-[:SIM]->(s4:SIM {name:'240080000000004'}),\n" +
                "(sims)-[:SIM]->(s5:SIM {name:'240080000000005'}),\n" +
                "(d1)-[:ASSOC]->(devsim1:DeviceSIM {imei:'354436058915420',imsi:'240080000000001'}),\n" +
                "(d2)-[:ASSOC]->(devsim2:DeviceSIM {imei:'358506046830281',imsi:'240080000000002'}),\n" +
                "(d2)-[:ASSOC]->(devsim3:DeviceSIM {imei:'358506046830281',imsi:'240080000000003'}),\n" +
                "(d3)-[:ASSOC]->(devsim4:DeviceSIM {imei:'353328059211902',imsi:'240080000000004'}),\n" +
                "(d3)-[:ASSOC]->(devsim5:DeviceSIM {imei:'353328059211902',imsi:'240080000000005'}),\n" +
                "(s1)-[:ASSOC]->(devsim1),\n" +
                "(s2)-[:ASSOC]->(devsim2),\n" +
                "(s3)-[:ASSOC]->(devsim3),\n" +
                "(s4)-[:ASSOC]->(devsim4),\n" +
                "(s5)-[:ASSOC]->(devsim5),\n" +
                "(craig)-[:USED_DEVICE]->(d1),\n" +
                "(craig)-[:USED_SIM]->(s1),\n" +
                "(active_devices)-[:ACTIVE]->(devsim1),\n" +
                "(active_devices)-[:ACTIVE]->(devsim2),\n" +
                "(active_devices)-[:ACTIVE]->(devsim3),\n" +
                "(active_devices)-[:ACTIVE]->(devsim4),\n" +
                "(active_devices)-[:ACTIVE]->(devsim5),\n" +
                "(devsim1)-[:files]->(files {name:'Files'}),\n" +
                "(files)-[:DATE]->(x1:Date {date:'2013-12-30'}),\n" +
                "(files)-[:DATE]->(x2:Date {date:'2013-12-31'}),\n" +
                "(files)-[:DATE]->(x3:Date {date:'2014-01-01'}),\n" +
                "(files)-[:DATE]->(x4:Date {date:'2014-01-02'}),\n" +
                "(files)-[:DATE]->(x5:Date {date:'2014-01-03'}),\n" +
                "(files)-[:DATE]->(x6:Date {date:'2014-01-04'}),\n" +
                "(files)-[:DATE]->(x7:Date {date:'2014-01-05'}),\n" +
                "(files)-[:DATE]->(x8:Date {date:'2014-01-06'}),\n" +
                "(files)-[:DATE]->(x9:Date {date:'2014-01-07'}),\n" +
                "(x5)-[:JSON]->(f1:File {\n" +
                "name:'354436058915420_12345_12345.json',\n" +
                "start:'2014-01-03 12:12:12 GMT+01',\n" +
                "first:'2014-01-03 12:12:12.01 GMT+01',\n" +
                "last:'2014-01-03 12:17:32.57 GMT+01',\n" +
                "events:321,mcc:'240',mnc:'08',carrier:'Operator X'\n" +
                "}),\n" +
                "(x5)-[:JSON]->(f2:File {\n" +
                "name:'354436058915420_12346_12346.json',\n" +
                "start:'2014-01-03 12:17:33 GMT+01',\n" +
                "first:'2014-01-03 12:17:33.01 GMT+01',\n" +
                "last:'2014-01-03 12:23:21.76 GMT+01',\n" +
                "events:405,mcc:'240',mnc:'08',carrier:'Operator X'\n" +
                "}),\n" +
                "(x5)-[:JSON]->(f3:File {\n" +
                "name:'354436058915420_12347_12347.json',\n" +
                "start:'2014-01-03 12:21:22 GMT+01',\n" +
                "first:'2014-01-03 12:21:22.01 GMT+01',\n" +
                "last:'2014-01-03 12:27:13.17 GMT+01',\n" +
                "events:279,mcc:'240',mnc:'08',carrier:'Operator X'\n" +
                "}),\n" +
                "(x1)-[:NEXT]->(x2),\n" +
                "(x2)-[:NEXT]->(x3),\n" +
                "(x3)-[:NEXT]->(x4),\n" +
                "(x4)-[:NEXT]->(x5),\n" +
                "(x5)-[:NEXT]->(x6),\n" +
                "(x6)-[:NEXT]->(x7),\n" +
                "(x7)-[:NEXT]->(x8),\n" +
                "(x8)-[:NEXT]->(x9),\n" +
                "(f1)-[:NEXT]->(f2),\n" +
                "(f2)-[:NEXT]->(f3)",

                "MATCH (craig:User)-[:su]->(project:Project)\n" +
                        "  WHERE craig.name = 'Craig' AND project.name = 'Operator X'\n" +
                        "CREATE\n" +
                        "  (devices {name: 'Devices'}),\n" +
                        "  (sims {name: 'SIM Cards'}),\n" +
                        "  (active_devices {name: 'Active Devices'}),\n" +
                        "  (project)-[:ACTIVE_DEVICES]->(active_devices),\n" +
                        "  (devices)-[:DEVICE]->(d1:Device {name: '354436058915420'}),\n" +
                        "  (devices)-[:DEVICE]->(d2:Device {name: '358506046830281'}),\n" +
                        "  (devices)-[:DEVICE]->(d3:Device {name: '353328059211902'}),\n" +
                        "  (sims)-[:SIM]->(s1:SIM {name: '240080000000001'}),\n" +
                        "  (sims)-[:SIM]->(s2:SIM {name: '240080000000002'}),\n" +
                        "  (sims)-[:SIM]->(s3:SIM {name: '240080000000003'}),\n" +
                        "  (sims)-[:SIM]->(s4:SIM {name: '240080000000004'}),\n" +
                        "  (sims)-[:SIM]->(s5:SIM {name: '240080000000005'}),\n" +
                        "  (d1)-[:ASSOC]->(devsim1:DeviceSIM {imei: '354436058915420', imsi: '240080000000001'}),\n" +
                        "  (d2)-[:ASSOC]->(devsim2:DeviceSIM {imei: '358506046830281', imsi: '240080000000002'}),\n" +
                        "  (d2)-[:ASSOC]->(devsim3:DeviceSIM {imei: '358506046830281', imsi: '240080000000003'}),\n" +
                        "  (d3)-[:ASSOC]->(devsim4:DeviceSIM {imei: '353328059211902', imsi: '240080000000004'}),\n" +
                        "  (d3)-[:ASSOC]->(devsim5:DeviceSIM {imei: '353328059211902', imsi: '240080000000005'}),\n" +
                        "  (s1)-[:ASSOC]->(devsim1),\n" +
                        "  (s2)-[:ASSOC]->(devsim2),\n" +
                        "  (s3)-[:ASSOC]->(devsim3),\n" +
                        "  (s4)-[:ASSOC]->(devsim4),\n" +
                        "  (s5)-[:ASSOC]->(devsim5),\n" +
                        "  (craig)-[:USED_DEVICE]->(d1),\n" +
                        "  (craig)-[:USED_SIM]->(s1),\n" +
                        "  (active_devices)-[:ACTIVE]->(devsim1),\n" +
                        "  (active_devices)-[:ACTIVE]->(devsim2),\n" +
                        "  (active_devices)-[:ACTIVE]->(devsim3),\n" +
                        "  (active_devices)-[:ACTIVE]->(devsim4),\n" +
                        "  (active_devices)-[:ACTIVE]->(devsim5),\n" +
                        "  (devsim1)-[:files]->(files {name: 'Files'}),\n" +
                        "  (files)-[:DATE]->(x1:Date {date: '2013-12-30'}),\n" +
                        "  (files)-[:DATE]->(x2:Date {date: '2013-12-31'}),\n" +
                        "  (files)-[:DATE]->(x3:Date {date: '2014-01-01'}),\n" +
                        "  (files)-[:DATE]->(x4:Date {date: '2014-01-02'}),\n" +
                        "  (files)-[:DATE]->(x5:Date {date: '2014-01-03'}),\n" +
                        "  (files)-[:DATE]->(x6:Date {date: '2014-01-04'}),\n" +
                        "  (files)-[:DATE]->(x7:Date {date: '2014-01-05'}),\n" +
                        "  (files)-[:DATE]->(x8:Date {date: '2014-01-06'}),\n" +
                        "  (files)-[:DATE]->(x9:Date {date: '2014-01-07'}),\n" +
                        "  (x5)-[:JSON]->(f1:File {\n" +
                        "    name:   '354436058915420_12345_12345.json',\n" +
                        "    start:  '2014-01-03 12:12:12 GMT+01',\n" +
                        "    first:  '2014-01-03 12:12:12.01 GMT+01',\n" +
                        "    last:   '2014-01-03 12:17:32.57 GMT+01',\n" +
                        "    events: 321, mcc: '240', mnc: '08', carrier: 'Operator X'\n" +
                        "  }),\n" +
                        "  (x5)-[:JSON]->(f2:File {\n" +
                        "    name:   '354436058915420_12346_12346.json',\n" +
                        "    start:  '2014-01-03 12:17:33 GMT+01',\n" +
                        "    first:  '2014-01-03 12:17:33.01 GMT+01',\n" +
                        "    last:   '2014-01-03 12:23:21.76 GMT+01',\n" +
                        "    events: 405, mcc: '240', mnc: '08', carrier: 'Operator X'\n" +
                        "  }),\n" +
                        "  (x5)-[:JSON]->(f3:File {\n" +
                        "    name:   '354436058915420_12347_12347.json',\n" +
                        "    start:  '2014-01-03 12:21:22 GMT+01',\n" +
                        "    first:  '2014-01-03 12:21:22.01 GMT+01',\n" +
                        "    last:   '2014-01-03 12:27:13.17 GMT+01',\n" +
                        "    events: 279, mcc: '240', mnc: '08', carrier: 'Operator X'\n" +
                        "  }),\n" +
                        "  (x1)-[:NEXT]->(x2),\n" +
                        "  (x2)-[:NEXT]->(x3),\n" +
                        "  (x3)-[:NEXT]->(x4),\n" +
                        "  (x4)-[:NEXT]->(x5),\n" +
                        "  (x5)-[:NEXT]->(x6),\n" +
                        "  (x6)-[:NEXT]->(x7),\n" +
                        "  (x7)-[:NEXT]->(x8),\n" +
                        "  (x8)-[:NEXT]->(x9),\n" +
                        "  (f1)-[:NEXT]->(f2),\n" +
                        "  (f2)-[:NEXT]->(f3)");
    }

    public void testPanamaPapersQ1() {
        doTest("CREATE (ilham:Person {name:\"ilham aliyev\"})\n" +
                "CREATE (heydar:Person {name:\"heydar aliyev\"})\n" +
                "WITH ilham, heydar\n" +
                "MATCH (mehriban:Person {name:\"mehriban aliyeva\"})\n" +
                "\n" +
                "MATCH (leyla:Person {name:\"leyla aliyeva\"})\n" +
                "MATCH (arzu:Person {name:\"arzu aliyeva\"})\n" +
                "\n" +
                "FOREACH (child in [leyla,arzu,heydar] | CREATE (ilham)-[:CHILD_OF]->(child) CREATE (mehriban)-[:CHILD_OF]->(child))\n" +
                "CREATE (leyla)-[:SIBLING_OF]->(arzu)\n" +
                "CREATE (leyla)-[:SIBLING_OF]->(heydar)\n" +
                "CREATE (arzu)-[:SIBLING_OF]->(heydar)\n" +
                "CREATE (ilham)-[:MARRIED_TO]->(mehriban)",

                "CREATE (ilham:Person {name: 'ilham aliyev'})\n" +
                        "CREATE (heydar:Person {name: 'heydar aliyev'})\n" +
                        "WITH ilham, heydar\n" +
                        "MATCH (mehriban:Person {name: 'mehriban aliyeva'})\n" +
                        "\n" +
                        "MATCH (leyla:Person {name: 'leyla aliyeva'})\n" +
                        "MATCH (arzu:Person {name: 'arzu aliyeva'})\n" +
                        "\n" +
                        "FOREACH (child IN [leyla, arzu, heydar] |\n" +
                        "  CREATE (ilham)-[:CHILD_OF]->(child)\n" +
                        "  CREATE (mehriban)-[:CHILD_OF]->(child)\n" +
                        ")\n" +
                        "CREATE (leyla)-[:SIBLING_OF]->(arzu)\n" +
                        "CREATE (leyla)-[:SIBLING_OF]->(heydar)\n" +
                        "CREATE (arzu)-[:SIBLING_OF]->(heydar)\n" +
                        "CREATE (ilham)-[:MARRIED_TO]->(mehriban)");
    }

    public void testPanamaPapersQ2() {
        doTest("MATCH (p:Person) WHERE p.name CONTAINS \"aliyev\"\n" +
                "OPTIONAL MATCH (c:Company)<--(o:Officer)-[:IDENTITY]-(p)\n" +
                "RETURN c,o,p",

                "MATCH (p:Person)\n" +
                        "  WHERE p.name CONTAINS 'aliyev'\n" +
                        "OPTIONAL MATCH (c:Company)<--(o:Officer)-[:IDENTITY]-(p)\n" +
                        "RETURN c, o, p");
    }

    public void testDegreesOfOviedoQ1() {
        doTest("MATCH (b)-[:TYPE]-(d:Degree)<-[:TEACHES]-(f)-[:LOCATED_IN]-(c:Campus) WHERE c.city='Oviedo' and b.name='Health Sciences' RETURN d.name AS Name, d.mark_cut_off AS Mark_cut_off ORDER BY d.mark_cut_off;",

                "MATCH (b)-[:TYPE]-(d:Degree)<-[:TEACHES]-(f)-[:LOCATED_IN]-(c:Campus)\n" +
                        "  WHERE c.city = 'Oviedo' AND b.name = 'Health Sciences'\n" +
                        "RETURN d.name AS Name, d.mark_cut_off AS Mark_cut_off\n" +
                        "  ORDER BY d.mark_cut_off;");
    }

    public void testDegreesOfOviedoQ2() {
        doTest("MATCH (f:Faculty)-[:TEACHES]->(d:Degree)-[:TYPE]->(b:Branch) WITH f,d,b MATCH (f2:Faculty)-[:TEACHES]->(d2:Degree)-[:TYPE]->(b2:Branch) WHERE b.name <> b2.name and f.name =~ f2.name RETURN DISTINCT f.name AS Faculty;",
                "MATCH (f:Faculty)-[:TEACHES]->(d:Degree)-[:TYPE]->(b:Branch)\n" +
                        "WITH f, d, b\n" +
                        "MATCH (f2:Faculty)-[:TEACHES]->(d2:Degree)-[:TYPE]->(b2:Branch)\n" +
                        "  WHERE b.name <> b2.name AND f.name =~ f2.name\n" +
                        "RETURN DISTINCT f.name AS Faculty;");
    }

    public void testOffshoreJurisdictionQ1() {
        doTest("MATCH (e:Entity)\n" +
                "OPTIONAL MATCH (e)-[:LOCATED]->(location)-[:PARTOF]->(jurisdiction)\n" +
                "OPTIONAL MATCH (jurisdiction)-[:PARTOF]->(main_jurdisdiction)\n" +
                "RETURN e.label AS Entity, e.type AS Type, e.status AS Status, e.incorporated AS Incorporated, jurisdiction.label AS Jurisdiction, main_jurdisdiction.label AS `Main Jurisdiction`",

                "MATCH (e:Entity)\n" +
                        "OPTIONAL MATCH (e)-[:LOCATED]->(location)-[:PARTOF]->(jurisdiction)\n" +
                        "OPTIONAL MATCH (jurisdiction)-[:PARTOF]->(main_jurdisdiction)\n" +
                        "RETURN e.label AS Entity, e.type AS Type, e.status AS Status, e.incorporated AS Incorporated,\n" +
                        "       jurisdiction.label AS Jurisdiction, main_jurdisdiction.label AS `Main Jurisdiction`");
    }

    public void testDrugRepurposingQ1() {
        doTest("// Find all drug-disease pairs\n" +
                "MATCH (n0:Drug), (n2:Disease)\n" +
                "// Extract paths where the drug targets a gene associated with the disease\n" +
                "OPTIONAL MATCH paths = (n0:Drug)-[:TARGETS]-(n1:Gene)-[:ASSOCIATES]-(n2:Disease)\n" +
                "RETURN\n" +
                "// Retrieve the name of the drug and disease\n" +
                "n0.name AS drug,\n" +
                "n2.name AS disease,\n" +
                "// Retrieve whether the drug treats the disease\n" +
                "size((n0)-[:TREATS]-(n2)) AS treatment,\n" +
                "// Count the number of paths between the drug and disease\n" +
                "count(paths) AS path_count\n" +
                "// Sort the rows\n" +
                "ORDER BY path_count DESC, treatment DESC",

                "// Find all drug-disease pairs\n" +
                        "MATCH (n0:Drug), (n2:Disease)\n" +
                        "// Extract paths where the drug targets a gene associated with the disease\n" +
                        "OPTIONAL MATCH paths = (n0:Drug)-[:TARGETS]-(n1:Gene)-[:ASSOCIATES]-(n2:Disease)\n" +
                        "RETURN\n" +
                        "// Retrieve the name of the drug and disease\n" +
                        "  n0.name AS drug,\n" +
                        "  n2.name AS disease,\n" +
                        "// Retrieve whether the drug treats the disease\n" +
                        "  size((n0)-[:TREATS]-(n2)) AS treatment,\n" +
                        "// Count the number of paths between the drug and disease\n" +
                        "  count(paths) AS path_count\n" +
                        "// Sort the rows\n" +
                        "  ORDER BY path_count DESC, treatment DESC");
    }

    public void testDrugRepurposingQ2() {
        doTest("// Find all drug-disease pairs\n" +
                "MATCH (n0:Drug), (n3:Disease)\n" +
                "// Extract paths following the specified metapath\n" +
                "OPTIONAL MATCH paths = (n0:Drug)-[:REGULATES]-(n1)-[:INTERACTS]-(n2)-[:ASSOCIATES]-(n3:Disease)\n" +
                "WITH\n" +
                "// reidentify the source and target nodes\n" +
                "n0 AS source,\n" +
                "n3 AS target,\n" +
                "paths,\n" +
                "// Extract the degrees along each path\n" +
                "[\n" +
                "size((n0)-[:REGULATES]-()),\n" +
                "size(()-[:REGULATES]-(n1)),\n" +
                "size((n1)-[:INTERACTS]-()),\n" +
                "size(()-[:INTERACTS]-(n2)),\n" +
                "size((n2)-[:ASSOCIATES]-()),\n" +
                "size(()-[:ASSOCIATES]-(n3))\n" +
                "] AS degrees\n" +
                "RETURN\n" +
                "// Retrieve the name of the drug and disease\n" +
                "source.name AS drug,\n" +
                "target.name AS disease,\n" +
                "// Retrieve whether the drug treats the disease\n" +
                "size((source)-[:TREATS]-(target)) AS treatment,\n" +
                "// Compute the path count\n" +
                "count(paths) AS path_count,\n" +
                "// Compute the degree-weighted path count with w = 0.5\n" +
                "sum(reduce(pdp = 1.0, d in degrees| pdp * d ^ -0.5)) AS DWPC\n" +
                "// Sort the rows\n" +
                "ORDER BY DWPC DESC",

                "// Find all drug-disease pairs\n" +
                        "MATCH (n0:Drug), (n3:Disease)\n" +
                        "// Extract paths following the specified metapath\n" +
                        "OPTIONAL MATCH paths = (n0:Drug)-[:REGULATES]-(n1)-[:INTERACTS]-(n2)-[:ASSOCIATES]-(n3:Disease)\n" +
                        "WITH\n" +
                        "// reidentify the source and target nodes\n" +
                        "  n0 AS source,\n" +
                        "  n3 AS target,\n" +
                        "  paths,\n" +
                        "// Extract the degrees along each path\n" +
                        "  [\n" +
                        "    size((n0)-[:REGULATES]-()),\n" +
                        "    size(()-[:REGULATES]-(n1)),\n" +
                        "    size((n1)-[:INTERACTS]-()),\n" +
                        "    size(()-[:INTERACTS]-(n2)),\n" +
                        "    size((n2)-[:ASSOCIATES]-()),\n" +
                        "    size(()-[:ASSOCIATES]-(n3))\n" +
                        "  ] AS degrees\n" +
                        "RETURN\n" +
                        "// Retrieve the name of the drug and disease\n" +
                        "  source.name AS drug,\n" +
                        "  target.name AS disease,\n" +
                        "// Retrieve whether the drug treats the disease\n" +
                        "  size((source)-[:TREATS]-(target)) AS treatment,\n" +
                        "// Compute the path count\n" +
                        "  count(paths) AS path_count,\n" +
                        "// Compute the degree-weighted path count with w = 0.5\n" +
                        "  sum(reduce(pdp = 1.0, d IN degrees | pdp * d ^ -0.5)) AS DWPC\n" +
                        "// Sort the rows\n" +
                        "  ORDER BY DWPC DESC");
    }

    public void testStarWarsModelQ1() {
        doTest("MATCH (s1:Set {id: \"30005-1\"})-[r1]->()<-[r2]-(d:Design)\n" +
                "MATCH (s2:Set {id: \"30272-1\"})-[r3]->()<-[r4]-(d)\n" +
                "\n" +
                "WITH DISTINCT d,\n" +
                "SUM(toInt(r1.Quantity)) AS SPEEDER_BIKE_QUANTITY,\n" +
                "SUM(toInt(r3.Quantity)) AS STARFIGHTER_QUANTITY\n" +
                "\n" +
                "RETURN SPEEDER_BIKE_QUANTITY,\n" +
                "STARFIGHTER_QUANTITY,\n" +
                "d.name AS NAME,\n" +
                "d.id AS ID",

                "MATCH (s1:Set {id: '30005-1'})-[r1]->()<-[r2]-(d:Design)\n" +
                        "MATCH (s2:Set {id: '30272-1'})-[r3]->()<-[r4]-(d)\n" +
                        "\n" +
                        "WITH DISTINCT d,\n" +
                        "              sum(toInt(r1.Quantity)) AS SPEEDER_BIKE_QUANTITY,\n" +
                        "              sum(toInt(r3.Quantity)) AS STARFIGHTER_QUANTITY\n" +
                        "\n" +
                        "RETURN SPEEDER_BIKE_QUANTITY,\n" +
                        "       STARFIGHTER_QUANTITY,\n" +
                        "       d.name AS NAME,\n" +
                        "       d.id AS ID");
    }

    public void testStarWarsModelQ2() {
        doTest("//Find pieces present in the collection in low quantity\n" +
                "MATCH (s1:Set {id: \"30272-1\"})-[r1]->()<-[r2]-(d:Design)\n" +
                "MATCH (d)-[r3]->()<-[r4]-(s2: SET {id: \"7965-1\"})\n" +
                "\n" +
                "//Exclude the minifigs\n" +
                "WHERE NOT (:Category {name: \"System: Mini Figure\"})-->(d)\n" +
                "\n" +
                "//Compute the difference between the number of pieces needed and present in the collection:\n" +
                "//The parts may have different colors but the same design. Thus multiple paths may be found\n" +
                "// between a set and the design node. Since we use 2 MATCHes, the number of row returned will\n" +
                "// be multiplied by the number of path from both matches. To find the correct quantities of\n" +
                "// parts, we need to divide the sum of quantities by this multiplicative factor.\n" +
                "WITH DISTINCT d.id AS PIECES,\n" +
                "d.name AS NAME,\n" +
                "SUM(toInt(r1.Quantity)) AS NEEDED_QUANTITY,\n" +
                "SUM(toInt(r4.Quantity)) AS AVAILABLE_QUANTITY,\n" +
                "size((s2)--()--(d)) AS MULT_FACT_NEED_QTY,\n" +
                "size((s1)--()--(d)) AS MULT_FACT_AVAIL_QTY\n" +
                "\n" +
                "WITH PIECES,\n" +
                "NAME,\n" +
                "NEEDED_QUANTITY/MULT_FACT_NEED_QTY AS NEEDED_QUANTITY,\n" +
                "AVAILABLE_QUANTITY/MULT_FACT_AVAIL_QTY AS AVAILABLE_QUANTITY,\n" +
                "(AVAILABLE_QUANTITY/MULT_FACT_AVAIL_QTY - NEEDED_QUANTITY/MULT_FACT_NEED_QTY) AS MISSING_QUANTITY\n" +
                "\n" +
                "//Select only the pieces in low quantity compared to the amount needed\n" +
                "WHERE MISSING_QUANTITY < 0\n" +
                "\n" +
                "RETURN PIECES, NAME, abs(MISSING_QUANTITY) AS NUMBER\n" +
                "ORDER BY PIECES\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "//Find unique pieces present in the A-Wing Starfighter\n" +
                "MATCH (s1:Set {id: \"30272-1\"})-[r1]->()<-[r2]-(d:Design),\n" +
                "(s2: SET {id: \"7965-1\"})\n" +
                "\n" +
                "//Exclude the minifigs and the parts from the collection\n" +
                "WHERE NOT (:Category {name: \"System: Mini Figure\"})-->(d) AND NOT (s2)-->()<--(d)\n" +
                "\n" +
                "WITH DISTINCT d.id AS PIECES,\n" +
                "d.name AS NAME,\n" +
                "SUM(toInt(r1.Quantity)) AS NEEDED_QUANTITY,\n" +
                "SUM(toInt(r1.Quantity)) AS NUMBER\n" +
                "\n" +
                "RETURN PIECES,\n" +
                "NAME,\n" +
                "NUMBER\n" +
                "\n" +
                "ORDER BY PIECES",

                "//Find pieces present in the collection in low quantity\n" +
                        "MATCH (s1:Set {id: '30272-1'})-[r1]->()<-[r2]-(d:Design)\n" +
                        "MATCH (d)-[r3]->()<-[r4]-(s2:SET {id: '7965-1'})\n" +
                        "\n" +
                        "//Exclude the minifigs\n" +
                        "  WHERE NOT (:Category {name: 'System: Mini Figure'})-->(d)\n" +
                        "\n" +
                        "//Compute the difference between the number of pieces needed and present in the collection:\n" +
                        "//The parts may have different colors but the same design. Thus multiple paths may be found\n" +
                        "// between a set and the design node. Since we use 2 MATCHes, the number of row returned will\n" +
                        "// be multiplied by the number of path from both matches. To find the correct quantities of\n" +
                        "// parts, we need to divide the sum of quantities by this multiplicative factor.\n" +
                        "WITH DISTINCT d.id AS PIECES,\n" +
                        "              d.name AS NAME,\n" +
                        "              sum(toInt(r1.Quantity)) AS NEEDED_QUANTITY,\n" +
                        "              sum(toInt(r4.Quantity)) AS AVAILABLE_QUANTITY,\n" +
                        "              size((s2)--()--(d)) AS MULT_FACT_NEED_QTY,\n" +
                        "              size((s1)--()--(d)) AS MULT_FACT_AVAIL_QTY\n" +
                        "\n" +
                        "WITH PIECES,\n" +
                        "     NAME,\n" +
                        "     NEEDED_QUANTITY / MULT_FACT_NEED_QTY AS NEEDED_QUANTITY,\n" +
                        "     AVAILABLE_QUANTITY / MULT_FACT_AVAIL_QTY AS AVAILABLE_QUANTITY,\n" +
                        "     (AVAILABLE_QUANTITY / MULT_FACT_AVAIL_QTY - NEEDED_QUANTITY / MULT_FACT_NEED_QTY) AS MISSING_QUANTITY\n" +
                        "\n" +
                        "//Select only the pieces in low quantity compared to the amount needed\n" +
                        "  WHERE MISSING_QUANTITY < 0\n" +
                        "\n" +
                        "RETURN PIECES, NAME, abs(MISSING_QUANTITY) AS NUMBER\n" +
                        "  ORDER BY PIECES\n" +
                        "\n" +
                        "UNION ALL\n" +
                        "\n" +
                        "//Find unique pieces present in the A-Wing Starfighter\n" +
                        "MATCH (s1:Set {id: '30272-1'})-[r1]->()<-[r2]-(d:Design),\n" +
                        "      (s2:SET {id: '7965-1'})\n" +
                        "\n" +
                        "//Exclude the minifigs and the parts from the collection\n" +
                        "  WHERE NOT (:Category {name: 'System: Mini Figure'})-->(d) AND NOT (s2)-->()<--(d)\n" +
                        "\n" +
                        "WITH DISTINCT d.id AS PIECES,\n" +
                        "              d.name AS NAME,\n" +
                        "              sum(toInt(r1.Quantity)) AS NEEDED_QUANTITY,\n" +
                        "              sum(toInt(r1.Quantity)) AS NUMBER\n" +
                        "\n" +
                        "RETURN PIECES,\n" +
                        "       NAME,\n" +
                        "       NUMBER\n" +
                        "\n" +
                        "  ORDER BY PIECES");
    }
}
