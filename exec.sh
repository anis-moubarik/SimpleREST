if [ ! -d /data/source-simplerest/ ]; then
   echo "creating source-simplerest folder"
   mkdir /data/source-simplerest/
fi
cp -r target/simplerest.war /data/source-simplerest/
chown -R tomcat:tomcat /data/source-simplerest/
