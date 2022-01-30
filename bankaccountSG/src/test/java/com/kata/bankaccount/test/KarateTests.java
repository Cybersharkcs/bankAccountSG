package com.kata.bankaccount.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.intuit.karate.RuntimeHook;
import com.intuit.karate.core.FeatureRuntime;
import com.intuit.karate.junit5.Karate;
import com.kata.bankaccount.api.BankAccountExposition;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT,
    properties = "spring.main.allow-bean-definition-overriding=true")
@ContextConfiguration(classes = BankAccountExposition.class)
public class KarateTests  {
	
	@Karate.Test
    Karate testAll() {
        return Karate.run().path("test").hook(new RuntimeHook() {
            @Override
            public void afterFeature(final FeatureRuntime fr) {
                RuntimeHook.super.afterFeature(fr);
            }

            @Override
            public boolean beforeFeature(final FeatureRuntime fr) {
                return RuntimeHook.super.beforeFeature(fr);
            }
        });
    }
}
