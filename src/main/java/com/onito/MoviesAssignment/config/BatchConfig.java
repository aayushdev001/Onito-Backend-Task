//package com.onito.MoviesAssignment.config;
//
//import com.onito.MoviesAssignment.Model.Movies;
//import jakarta.transaction.TransactionManager;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig
//{
//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    JobBuilder jobBuilder;
//
//    @Autowired
//    StepBuilder stepBuilder;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//
//
//    //reader
//    public FlatFileItemReader<Movies> reader()
//    {
//        FlatFileItemReader<Movies> reader = new FlatFileItemReader<>();
//
//        reader.setResource(new ClassPathResource("movies.csv"));
//        reader.setLineMapper(getLineMapper());
//        reader.setLinesToSkip(1);
//
//        return reader;
//    }
//
//    private LineMapper<Movies> getLineMapper()
//    {
//        DefaultLineMapper<Movies> lineMapper = new DefaultLineMapper<>();
//
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setNames(new String[]{"tconst", "titleType", "primaryTitle", "runtimeMinutes", "genres"});
//        lineTokenizer.setIncludedFields(new int[]{0,1,2,3,5});
//
//        BeanWrapperFieldSetMapper<Movies> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Movies.class);
//
//        lineMapper.setLineTokenizer(lineTokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return lineMapper;
//    }
//
//    //processor
//    @Bean
//    public MovieItemProcessor processor()
//    {
//        return new MovieItemProcessor();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Movies> writer()
//    {
//        JdbcBatchItemWriter<Movies> writer = new JdbcBatchItemWriter<>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Movies>());
//        writer.setSql("insert into movies(id, titleType, primaryTitle, runtimeMinutes, genres) values(:id, :titleType, :primaryTitle, :runtimeMinutes, :genres)");
//        writer.setDataSource(this.dataSource);
//        return writer;
//    }
//
//    @Bean
//    public Job importMoviesJob()
//    {
//        return this.jobBuilder.incrementer(new RunIdIncrementer())
//                .flow(step1())
//                .end()
//                .build();
//    }
//
//    @Bean
//    Step step1()
//    {
//        return this.stepBuilder
//                .<Movies, Movies>chunk(10, transactionManager)
//                .reader(reader())
//                .processor(processor())
//                .build();
//    }
//}
