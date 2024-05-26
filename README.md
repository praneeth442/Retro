# Retro Service

### Prerequisite
 * Requires java 17
 * Maven 

### Getting started
 * run mvn clean install
 * java -jar target/retro-0.0.1-SNAPSHOT.jar 
 application runs 8080 port

### Tools Used
For further reference, please consider the following sections:
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* Spring boot version 3.2.5
* h2 data base


## Services
#### Create Retro Record 
* Creates a new retrospective record and returns success message
* Accepts request body in json format
Sample 
 curl --location 'http://localhost:8080/retro/create' \
 --header 'Content-Type: application/json' \
 --data '{
 "retroName":"1",
 "date":"23/02/2002",
 "participants":["john"]
 }'

#### Search retro data
* Returns the retro data along with feed backs(if present) based on pagination parameters currentPage & pageSize
* Returns default page that is zero and ten elements if pagination parameters are null(sorted by date)
* Returns response in json  if request header is set as Accept: application/json
* Returns response in xml  if request header is set as Accept: application/xml

Sample url
curl --location 'http://localhost:8080/get/retros?currentPage=0&pageSize=1' \
--header 'Content-Type: application/xml' \
--header 'Accept: application/xml'

#### Search retro data by date
* Returns  the retro data along with feed backs(if present) based on pagination parameters currentPage & pageSize & date (accepted format dd/mm/yyyy)
* Returns  default page that is zero and ten elements if pagination parameters are null(sorted by name)
* Returns  response in json  if request header is set as Accept: application/json
* Returns  response in xml  if request header is set as Accept: application/xml

Sample url
curl --location 'http://localhost:8080/get/retros/date?currentPage=0&pageSize=3&date=23%2F02%2F2022' \
--header 'Accept: application/json'

### Feed Back Services
#### Create new FeedBack Record
* Creates a new feedback record and attach to it to retro record name and returns success message 201
* Returns 400 if either feedBackName or retroName is empty or null
* Accepts type values are positive,negative,idea,praise
* Accepts request body in json format
  Sample
  curl --location 'http://localhost:8080/feedback/create' \
  --header 'Content-Type: application/json' \
  --data '{
  "feedBackName":"feedback",
  "type":"positive",
  "retroName":"1"
  }'

#### Updates FeedBack Record
* Updates feedback record if returns success message 204 
* if feedback is not found creates new one with success message 201
* Returns 400 if either feedBackName or retroName is empty or null
* Accepts type values are positive,negative,idea,praise
* Accepts request body in json format
  Sample
  curl --location --request PUT 'http://localhost:8080/feedback/update' \
  --header 'Content-Type: application/json' \
  --data '{
  "feedBackName":"feedback",
  "type":"positive",
  "body":"sample",
  "retroName":"1"
  }'

