/**
 * 
 */
package com.khodeprasad;

import java.io.Serializable;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import com.khodeprasad.beans.CrimeBean;
import com.khodeprasad.beans.ResultBean;

/**
 * @author Prasad Khode
 *
 */
public class AppExecutor implements Serializable {

	private static final long serialVersionUID = -2595784700416857341L;

	public static void main(String args[]){
		AppExecutor executor = new AppExecutor();

		SparkConf sparkConf = new SparkConf().setAppName("Crime Analyser").setMaster("local");
		JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

		String inputFilePath = "input/input_data.csv";

		JavaRDD<CrimeBean> inputRDD = executor.loadInputFile(javaSparkContext, inputFilePath);
		System.out.println("Count : " + inputRDD.count());

		JavaRDD<ResultBean> resultRDD = executor.aggregateByYearAndCategory(javaSparkContext, inputRDD);
		System.out.println("Count : " + resultRDD.count());

		List<ResultBean> list = resultRDD.collect();

		for (ResultBean iterator : list) {
			System.out.println(iterator);
		}

		resultRDD.saveAsTextFile("output");

		inputRDD.unpersist();
		resultRDD.unpersist();

		javaSparkContext.close();
	}

	public JavaRDD<CrimeBean> loadInputFile(JavaSparkContext javaSparkContext, String inputFilePath) {
		JavaRDD<CrimeBean> inputRDD = javaSparkContext.textFile(inputFilePath).map(new Function<String, CrimeBean>() {

			private static final long serialVersionUID = -8489933086226739057L;

			@Override
			public CrimeBean call(String inputLine) throws Exception {
				String[] row = inputLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

				if ("".equals(row[17]) || "ID".equalsIgnoreCase(row[0]))
					return null;

				CrimeBean bean = new CrimeBean();
				bean.setId(row[0]);
				bean.setPrimaryType(row[5]);
				bean.setYear(Integer.parseInt(row[17]));
				return bean;
			}
		}).filter(new Function<CrimeBean, Boolean>() {

			private static final long serialVersionUID = 6676161196949643229L;

			@Override
			public Boolean call(CrimeBean bean) throws Exception {
				if (bean == null) return false;
				return true;
			}
		});

		return inputRDD;
	}

	public JavaRDD<ResultBean> aggregateByYearAndCategory(JavaSparkContext javaSparkContext, JavaRDD<CrimeBean> inputRDD) {
		SQLContext sqlContext = new SQLContext(javaSparkContext);
		
		DataFrame schema = sqlContext.createDataFrame(inputRDD, CrimeBean.class);
		schema.registerTempTable("data_table");

		StringBuilder query = new StringBuilder("SELECT year, primaryType as crime_type, count(primaryType) as total_crimes from data_table group by year, primaryType ");

		DataFrame schemaRDD = sqlContext.sql(query.toString());

		JavaRDD<ResultBean> resultRDD = schemaRDD.toJavaRDD().map(new Function<Row, ResultBean>() {

			private static final long serialVersionUID = -6646304897083597410L;

			public ResultBean call(Row row) throws Exception {
				ResultBean resultBean = new ResultBean();
				resultBean.setYear(row.getInt(0));
				resultBean.setCrimeCategory(row.getString(1));
				resultBean.setCrimeCount(row.getLong(2));
				return resultBean;
			}
		});

		schemaRDD.unpersist();
		schema.unpersist();

		return resultRDD;
	}
}
