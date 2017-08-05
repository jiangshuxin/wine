因为没有搭建nexus,第三方依赖先安装到本地后,再执行backend项目打包,
请在readme.txt的当前路径下执行命令:
mvn install:install-file -Dfile=aliyun-java-sdk-core-3.2.3.jar -DgroupId=aliyun -DartifactId=java-sdk-core -Dversion=3.2.3 -Dpackaging=jar
mvn install:install-file -Dfile=aliyun-java-sdk-dysmsapi-1.0.0-SANPSHOT.jar -DgroupId=aliyun -DartifactId=java-sdk-dysmsapi -Dversion=1.0.0-SNAPSHOT -Dpackaging=jar
