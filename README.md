RESULTATS D'EXECUCIONS:
====================================

## Aquí aniré implementant els TestResults de les execucions de totes les pràctiques a partir d'ara.

* Per a l'execució d'aquests projectes, no necessariament cal empaquetar en JAR (java archives) per executar, es sol poder executar directament amb un Fn + F5.

    Però per pràcticar i familiartizar-me amb el fluxe de treball, pels projectes executaré desde la línia de comandes desde l'arrel del projecte com ara "xifratgepk" que es troba dins del directori de pràctica "07-Public_key" les següents instruccions:

* Per empaquetar i implementar bé les dependencies:
    ##### mvn package / mvn install

* Per poder generar un jar correctament si anteriorment ja s'havia compilat, pots executar prèviament del mvn install:
    ##### mvn clean

* Finalment per executar el programa que es troba dins del paquet "iticbcn.xifratge":
    ##### mvn exec:java -Dexec.mainClass="iticbcn.xifratge.Main"

### Per a la pràctica:

08-Hashes - Xifratge Unidireccional
====================================

### - Execució: desde l'IDE Visual Studio Code (Run)

*Com que ara fa les proves exactes, ja no em cal reduïr les iteracions per l'algorisme PBKDF2 alhora de l'execució, ho he deixat a 65536 iteracions.*

**Algorisme PBKDF2 amb: 65536 iteracions vs 1 iteració**
```
===========================
Algorisme: SHA-512
Hash: 6a6d2e8b5af7a59cf8f11522f184d6398ec3faf28a4b37f3ad5435819a1dee1b891e0eb843559bdaeda913e49445dd223e0a5f8897ecf13ae25bae49b4bd5ed4
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 41 millis
---------------------------

===========================
Algorisme: PBKDF2
Hash: 618f0941c18eba57fa127b25aa41203d
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 19 segons / 183 millis
---------------------------
```

**Algorisme PBKDF2 amb 1 iteració**
```
===========================
Algorisme: SHA-512
Hash: 6a6d2e8b5af7a59cf8f11522f184d6398ec3faf28a4b37f3ad5435819a1dee1b891e0eb843559bdaeda913e49445dd223e0a5f8897ecf13ae25bae49b4bd5ed4
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 47 millis
---------------------------

===========================
Algorisme: PBKDF2
Hash: ff99f324f538b86713836ffadaacc8f1
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 89 millis
---------------------------
```

**Algorisme PBKDF2 amb 10 iteracions**
```
===========================
Algorisme: SHA-512
Hash: 6a6d2e8b5af7a59cf8f11522f184d6398ec3faf28a4b37f3ad5435819a1dee1b891e0eb843559bdaeda913e49445dd223e0a5f8897ecf13ae25bae49b4bd5ed4
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 47 millis
---------------------------

===========================
Algorisme: PBKDF2
Hash: 8d58735ebea93882afd18ccf08c73bac
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 140 millis
---------------------------
```

**Algorisme PBKDF2 amb 100 iteracions**
```
===========================
Algorisme: SHA-512
Hash: 6a6d2e8b5af7a59cf8f11522f184d6398ec3faf28a4b37f3ad5435819a1dee1b891e0eb843559bdaeda913e49445dd223e0a5f8897ecf13ae25bae49b4bd5ed4
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 47 millis
---------------------------

===========================
Algorisme: PBKDF2
Hash: 0d96262be1a04f386f99a39f600277fe
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 266 millis
---------------------------
```

**Algorisme PBKDF2 amb 1000 iteracions**
```
===========================
Algorisme: SHA-512
Hash: 6a6d2e8b5af7a59cf8f11522f184d6398ec3faf28a4b37f3ad5435819a1dee1b891e0eb843559bdaeda913e49445dd223e0a5f8897ecf13ae25bae49b4bd5ed4
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 58 millis
---------------------------

===========================
Algorisme: PBKDF2
Hash: bdf489bd0d870b6bb6c0a9ba5e7b89a2
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 750 millis
---------------------------
```

**Algorisme PBKDF2 amb 10000 iteracions**
```
===========================
Algorisme: SHA-512
Hash: 6a6d2e8b5af7a59cf8f11522f184d6398ec3faf28a4b37f3ad5435819a1dee1b891e0eb843559bdaeda913e49445dd223e0a5f8897ecf13ae25bae49b4bd5ed4
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 0 segons / 54 millis
---------------------------

===========================
Algorisme: PBKDF2
Hash: 173f51b366956f700805b5c0c38ee3ce
---------------------------
-- Inici de força bruta ---
Pass : aaabF!
Provats: 845
Temps : 0 dies / 0 hores / 0 minuts / 3 segons / 833 millis
---------------------------
```

07-Public_key - Parella de claus RSA
====================================

### - Comanda d'execució: mvn exec:java -Dexec.mainClass="iticbcn.xifratge.Main"

```
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< iticbcn.xifratge:xifratgepk >---------------------
[INFO] Building xifratgepk 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- exec-maven-plugin:3.5.0:java (default-cli) @ xifratgepk ---
=================================
Text xifrat: 
5F5E3B5C0BBBA9137B1D369CBBF4060A0B338363F9BF3463601CCBA4F9B34669133AA19FB67F75481364082FF015D654EA61
B4EAE0E737671AC6B99177FE08BC7011389E0620105C54674AB42E0B4FA87159327BE96E2C339AD7DE9C8CADD73DD0B4829A
6F455E22722961FE2BAECBA212E74E107B60F0ECF222CC76EBA4C363ECE7DCE2EA1D6DFE9237A0D9C936622DA14D62C76F6A
7727CD4ACE7AE50EB5D7BAC6E722080B749606F74780697751BEB0D4405674F59D1C9D7F7A43C56362FD06934EA4F1E53ACE
334F0CCADEDF650EF8FFD597DEC52B7C95F227D217F3C23BCCDE318B7B9AB625EFCB24C7323F3049EBBED42C86CFF4C7F4B3
3B77B7A7217D
=================================
Text desxifrat: Missatge de prova per xifrar áéíóú àèìòù äëïöü
=================================
Clau pública:
30820122300D06092A864886F70D01010105000382010F003082010A0282010100AE67A4BD74AAC7966892C1FE412C85A2D6
216DF8696F77FF106F5D0134E5D561FCCE1CD04EFB041503FF96B70F9C58350381DD45152FA6C5DFFC702F2F175711640F56
8805158A6FC45F39E79DD6B739674665397944FB483BE5A2B667C9074AEAC8996E913D70DDDD9F7191CB807BF76912A79C6E
BF9F2BC1B880EC3E2E36D64BA62E6E3A6459CAA0B17045B6C34CC461CA51326F1C3306513970570BF1D4D5FC5C4E8251B528
AF987757F7640A11828D9BD58AC630B6D8F8BD4A3A49358D043FF0541FCBFCAC2C2282BA201E68644964C5BA22B08B625DFF
A59434292875D99349FFA59B65576567E47D45397510F823EF9046ACCA5F0532CB3F7D374CEF970203010001
=================================
Clau privada:
308204BD020100300D06092A864886F70D0101010500048204A7308204A30201000282010100AE67A4BD74AAC7966892C1FE
412C85A2D6216DF8696F77FF106F5D0134E5D561FCCE1CD04EFB041503FF96B70F9C58350381DD45152FA6C5DFFC702F2F17
5711640F568805158A6FC45F39E79DD6B739674665397944FB483BE5A2B667C9074AEAC8996E913D70DDDD9F7191CB807BF7
6912A79C6EBF9F2BC1B880EC3E2E36D64BA62E6E3A6459CAA0B17045B6C34CC461CA51326F1C3306513970570BF1D4D5FC5C
4E8251B528AF987757F7640A11828D9BD58AC630B6D8F8BD4A3A49358D043FF0541FCBFCAC2C2282BA201E68644964C5BA22
B08B625DFFA59434292875D99349FFA59B65576567E47D45397510F823EF9046ACCA5F0532CB3F7D374CEF97020301000102
8201004BDB9DCEA003B63B4831E93A276BEF9663AFF7AEB061811E8AADFD948C4719987CC6C3C6A14BD23B37D9805B1069AE
FE240FE4C7EB0EA669F3E24F835E66B702C3F45DED5CD4C1399AFED667558272807FD8A127AE05208D0B2C7729029C705518
28F77F9DA1CD495B5FC21CA998B192EBFB66B7067EC7835747E29F9EEE2800365EA9A0F46CE2C3CB9CAD159BA45B164CE3A7
45A0425F9DF71B227FBBB3736347EF3F592A60E65C5B8BD37650A796E5EF4483F4C110FAEA1C4740C22D88AC3C585DEB54A6
1D54EE3A8386533A42C7DBFBBEB72C98BEEE51A31031A8DA9BBAB090EC1F00F6D2D7E920F41CA691A6CA67881BB4CE9BBD4B
1A867D28171E3DF23902818100E411EDBE5167BE3C633548FA53BA8E5C0E2F0A44BC05449192B37E240739C6C2D2FED6D0CD
53E54AB5A3BEF7F7382D39D03E336762F8258C6D607A859CACE8B9BE19DD962324985BFC65E66AC3700B419B30644FDCB975
AE02FB4AAB5DDAC14CD41C9C8F24BA8776EC0ABE2DD4927DB8335D3B737F3CB532FA1F7BCCD9C2376F02818100C3C34B318B
DDF7DB9D5D09BB5C681DF3B89985172F8C86B2DDCB0F901D84801668E2B0863377AF9C074CD40EE6FD717A95F2007A724E75
50730335D93FF4237AF9A7FB45AF58A520086CAF5D9BC1C8C8BF0E5FA8BC492939D422FEC184BB75E37D96E260110D55495D
8D4C0F672C02938F233A22C85C0619882F44B78D2DF6590281810080217E72C4E346E2404E4860B2B785913C3ABB472066C9
3E0BB021B6F60444A161CEE03AD09048AA6F5FBE432B35F95A989EC95C5522DA5D8979817C1D289B5AF6ED2B2B5535130FE2
BE10F47C362F6CBD56339516896F0EC30810CC33DF5967E366EBA213DD638BB152DBC37DC08B36F84C71C11283B5BD1CFEA1
1BD65F21AB028180469D338A73C1659EBA6CCD0DE08F4DFE94200C7A0894D021C50F7255B1D011C403A181BB66E54D5B22E6
FBB7446B377BD88AAAD18059FCC74035703F256A9341D0BC5F4F0BA916272FE2C47F27A8071D228DB4C3D0E7330FC10DBE87
5FB19F1CA838BCFF9A270D14A1C24E256039E7D40255DAAAA14B5087F52FD7C1BE134E410281806698CF03ABCC2F2824C9FF
9A240EBCA1BD7C585DD92C09795F5D7E4A6E860E659E37BD0358FFA8914ABEADB7C15369CA1F1A6EC506E384AF8EFA2CD893
6B920801A3596DE4722E510192A84520794CAD4CCAD9BAB0C5055AD1284A2C2DB24187215F372AFE48ED81CEB7B5C27E9A0A
3A29519911E1ECA0F306CDBF590D95FD51
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.217 s
[INFO] Finished at: 2024-11-11T23:50:46+01:00
[INFO] ------------------------------------------------------------------------
```