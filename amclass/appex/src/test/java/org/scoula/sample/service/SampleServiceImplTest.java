package org.scoula.sample.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class SampleServiceImplTest {

    @Autowired
    private SampleService sampleService;

    @Test
    void doAdd() throws Exception {
        log.info(sampleService.doAdd("100", "200"));
    }

    @Test
    public void addError() throws Exception{

    }
}