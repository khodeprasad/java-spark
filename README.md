## java-spark

The following are the steps to execute the application in Hadoop Eco System

**Step 1:** Create directory in Hadoop Distributed File System (HDFS) to place the input file to process using the below command.

    hadoop fs -mkdir <your_hdfs_path>/input/

**Step 2:** Copy the **input_data.csv** file in HDFS by using the below command

    hadoop fs -put /root/java-spark/input/input_data.csv <your_hdfs_path>/input/

**Step 3:** Build the project by using the following command

    cd <project_location>/java-spark/
    
    mvn clean install

**Step 4:** Execute by issuing the spark-submit command

	spark-submit --class com.khodeprasad.AppExecutor <project_location>/java-spark/target/java-spark-jar-with-dependencies.jar

**Step 5:** See the output at location **<your_hdfs_path>/output/**

    hadoop fs -ls <your_hdfs_path>/output/


The same can be run as a Java Application by following the below instructions.

**Step 1:** Build the project by using the following command

    cd <project_location>/java-spark/
    
    mvn clean install

**Step 2:** Execute by issuing the spark-submit command

	java -cp <project_location>/java-spark/target/java-spark-jar-with-dependencies.jar com.khodeprasad.AppExecutor

**Step 3:** See the output in the console


Note:  To execute the application as a Java Application you need to comment the line

    resultRDD.saveAsTextFile("output");

as it cannot save the processed output to HDFS



You can download the complete dataset from

https://data.cityofchicago.org/api/views/ijzp-q8t2/rows.csv?accessType=DOWNLOAD

or

https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-present/ijzp-q8t2