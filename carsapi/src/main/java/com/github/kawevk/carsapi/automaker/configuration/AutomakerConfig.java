package com.github.kawevk.carsapi.automaker.configuration;

import com.github.kawevk.carsapi.automaker.Engine;
import com.github.kawevk.carsapi.automaker.EngineType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutomakerConfig {

    @Bean(name = "aspirado")
    public Engine aspiradoEngine() {
        var engine = new Engine();
        engine.setHorsePower(120);
        engine.setCylinders(4);
        engine.setModel("XPT-0");
        engine.setFuel(2.0);
        engine.setEngineType(EngineType.ASPIRADO);
        return engine;
    }

    @Bean(name = "eletric")
    public Engine eletricEngine() {
        var engine = new Engine();
        engine.setHorsePower(110);
        engine.setCylinders(3);
        engine.setModel("TH-40");
        engine.setFuel(1.4);
        engine.setEngineType(EngineType.ELETRIC);
        return engine;
    }

    @Bean(name = "turbo")
    public Engine turboEngine() {
        var engine = new Engine();
        engine.setHorsePower(180);
        engine.setCylinders(4);
        engine.setModel("XPTO-01");
        engine.setFuel(1.5);
        engine.setEngineType(EngineType.TURBO);
        return engine;
    }

}
