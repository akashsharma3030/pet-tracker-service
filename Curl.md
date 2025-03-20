### Register Dog Tracker:

curl -X 'POST' \
'http://localhost:8080/tracker/dog/register' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"dogTrackerType": "SMALL",
"ownerId": 1073741824
}'

==============
### Register Cat Tracker

curl -X 'POST' \
'http://localhost:8080/tracker/cat/register' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"catTrackerType": "SMALL",
"ownerId": 1073741824
}'

========================
### Update Cat Lost Tracker

curl -X 'PATCH' \
'http://localhost:8080/tracker/cat/lost-tracker' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"trackerId": 9007199254740991,
"lostTracker": true
}'

=============================
### Update Pet Tracker Zone Status True -> Inzone False out of Zone
curl -X 'PATCH' \
'http://localhost:8080/tracker/pet/zone?trackerId=8671135898525780999&inZone=false' \
-H 'accept: */*'

========================
### Search Tracker by Pet Type and Tracker Type

curl -X 'GET' \
'http://localhost:8080/search/pet-tracker-type?petType=DOG&trackerType=SMALL' \
-H 'accept: application/json'

=============================
### Search Tracker outside of power Zone by Pet Type and Tracker Type

curl -X 'GET' \
'http://localhost:8080/search/outside-power-zone?petType=DOG&trackerType=SMALL' \
-H 'accept: */*'

=============================

### Search Tracker By Tracker Id

curl -X 'GET' \
'http://localhost:8080/search/id?trackerId=8592233047558751045' \
-H 'accept: application/json'


