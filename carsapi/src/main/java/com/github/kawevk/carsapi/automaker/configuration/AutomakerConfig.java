package com.github.kawevk.carsapi.automaker.configuration;

import com.github.kawevk.carsapi.automaker.Engine;
import com.github.kawevk.carsapi.automaker.EngineType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutomakerConfig {

    @Bean
    public Engine engine() {
        var engine = new Engine();
        engine.setHorsePower(120);
        engine.setCylinders(4);
        engine.setModel("XPT-0");
        engine.setFuel(2.0);
        engine.setEngineType(EngineType.ASPIRADO);
        return engine;
    }

}
