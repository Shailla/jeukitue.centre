@ECHO off

ECHO *************************************
ECHO * Building centre-boot
ECHO *************************************
CD centre-boot
CALL mvn clean install
CD ..

ECHO *************************************
ECHO * Building centre-gui
ECHO *************************************
CD ../centre-gui
CALL ng build --prod --aot
CD ..

ECHO *************************************
ECHO * Deploying centre-boot
ECHO *************************************
SET versionLabel=jkt-centre-%date:~6,4%-%date:~3,2%-%date:~0,2%_%time:~0,2%-%time:~3,2%-%time:~6,2%
CALL aws s3 cp ./centre-boot/target/centre-1.0-SNAPSHOT.jar s3://elasticbeanstalk-eu-west-3-924849261852/
CALL aws elasticbeanstalk create-application-version --application-name jkt-centre --version-label %versionLabel% --source-bundle S3Bucket="elasticbeanstalk-eu-west-3-924849261852",S3Key="centre-1.0-SNAPSHOT.jar"
CALL aws elasticbeanstalk update-environment --application-name jkt-centre --environment-name JktCentre-env --version-label %versionLabel%

ECHO *************************************
ECHO * Deploying centre-gui
ECHO *************************************
CALL aws s3 sync ./centre-gui/dist/ s3://jkt-centre-front2/ --delete --acl public-read

PAUSE
