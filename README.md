# Veeva Assignment
This is a framework consisting of Selenium, Core Java, Cucumber, TestNG, Maven, log4j2, and RestAssured

### Execution

####Intructions
1) Since we are fetching browser type from testng.xml using @BeforeTest annotation of testNG, the execution can happen only via Maven (which inturn uses testng.xml) and TestNG (direct testng.xml), not with Cucumber Runner file (TestRunner.java).

2) To execute the scenarios parallelly with multiple browsers like chrome, edge and firefox,
- enable the entire **test** tag for the required browsers in testng.xml.
- if any new scenarios are added after closing this repo, increase the value of **data-provider-thread-count** to number of scenarios to increase the thread count (browser invocation), else existing **data-provider-thread-count="2"** would suffice.


#### Maven
&emsp;**via CMD**
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;-> mvn clean test verify -Dcucumber.options="--tags {{tags}}"

&emsp;**via IDE Run Configuration**
&emsp;&emsp;-> clean test verify -Dcucumber.options="--tags {{tags}}"

#### TestNG

Run the testNG.xml file with the required changes as mentioned in above **Instruction 2) **
<br/>
### Reports
Cucumber report can be accessed via **target/cucumber-reports/report.html**. Open in any of the browser available to view.
<br/>
### Logs
Logs can be accessed via **target/logs/log4j2.log**.
<br/>
<br/>
<br/>
<br/>