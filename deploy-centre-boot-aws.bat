set versionLabel=jkt-centre-%date:~6,4%-%date:~3,2%-%date:~0,2%_%time:~0,2%-%time:~3,2%-%time:~6,2%
aws s3 cp ./centre-boot/target/centre-1.0-SNAPSHOT.jar s3://elasticbeanstalk-eu-west-3-924849261852/
aws elasticbeanstalk create-application-version --application-name jkt-centre --version-label %versionLabel% --source-bundle S3Bucket="elasticbeanstalk-eu-west-3-924849261852",S3Key="centre-1.0-SNAPSHOT.jar"
aws elasticbeanstalk update-environment --application-name jkt-centre --environment-name JktCentre-env --version-label %versionLabel%