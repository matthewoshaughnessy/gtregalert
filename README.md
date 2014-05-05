# gtregalert
_Georgia Tech Registration Alerts_

## Features
* Checks registration numbers for class at specified intervals
* Sends SMS to specified distination when a seat opens up
* Custom logging tool to display data or pipe to output file
* Can optionally write network data to CSV file for analysis by MATLAB script

## To Run
#### Run included .jar file
```
$ java -jar gtregalert_fall14.jar crn refreshInterval(ms) phoneNumber
```
#### Compile yourself
```
$ java gtregalert crn refreshInterval(ms) phoneNumber
```

## Sample Output
![Screen capture of sample output](sampleoutput.JPG)