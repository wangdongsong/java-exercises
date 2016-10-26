package com.wds.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.log4j.lf5.util.StreamUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Condition;
import java.util.stream.Stream;

/**
 * 单词统计
 * Created by wangdongsong1229@163.com on 2016/10/23.
 */
public class WordCount {

    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer str = new StringTokenizer(value.toString());
            while (str.hasMoreTokens()) {
                word.set(str.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterator<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next().get();
            }

            result.set(sum);
            context.write(key, result);

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        if (args.length < 3) {
            return;
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "WordCount");

        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.setInputPaths(job, args[0]);
        job.setJarByClass(WordCount.class);
        job.setMapOutputKeyClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setPartitionerClass(HashPartitioner.class);
        job.setReducerClass(IntSumReducer.class);
        job.setNumReduceTasks(Integer.parseInt(args[2]));
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }



}
