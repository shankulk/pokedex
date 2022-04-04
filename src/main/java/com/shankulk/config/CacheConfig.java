package com.shankulk.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
public class CacheConfig {

    public static final String TRANSLATION = "translation";
    public static final String POKEMON = "pokemon";

    @Bean
    Ticker ticker() {
        return Ticker.systemTicker();
    }

    @Bean
    CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(buildCaffeineCache(ticker(), TRANSLATION),
                buildCaffeineCache(ticker(), POKEMON)));
        return cacheManager;
    }

    private CaffeineCache buildCaffeineCache(
            Ticker ticker, String cacheName) {
        final var cacheBuilder =
                Caffeine.newBuilder()
                        .expireAfterWrite(Duration.ofSeconds(3600))
                        .ticker(ticker)
                        .maximumSize(100)
                        .build();

        return new CaffeineCache(cacheName, cacheBuilder, false);
    }
}
