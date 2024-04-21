package org.example.springboottutorial.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featureMap.put("Department", new Feature(true, "This is Department feature"));
        featureMap.put("User", new Feature(false, "This is User feature"));
        featureMap.put("Authentication", new Feature(false, "This is Authentication feature"));
    }

    @ReadOperation
    public Map<String, Feature> getFeatures(){
        return featureMap;
    }

    @ReadOperation
    public Feature getFeature(@Selector String featureName){
        return featureMap.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
        private boolean enabled;
        private String description;
    }
}
