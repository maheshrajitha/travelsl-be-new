package com.travelsl.travelsl.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class Config {

    @Bean
    public Gson getGson(){
        return new Gson();
    }

    @Bean
    public BlobServiceClient getAzureBlobClient(){
        return new BlobServiceClientBuilder().connectionString("DefaultEndpointsProtocol=https;AccountName=travelsl;AccountKey=xF0VCFJk6X3Nc+vUAz1sfraRzhj5gcg36BSurzHDOuu18YR/iEYnId/qxwyIpTksr0znuAV/F8Y4ExeGYBQtKw==;EndpointSuffix=core.windows.net").buildClient();

    }

    @Bean
    public HttpClient getHttpClient(){
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }
}
