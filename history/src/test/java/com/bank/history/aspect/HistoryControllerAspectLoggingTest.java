package com.bank.history.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HistoryControllerAspectLoggingTest {

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private Signature signature;

    @Mock
    private Logger log;

    @InjectMocks
    private HistoryControllerAspectLogging historyControllerAspectLogging;

    @Test
    public void testIsRestControllerLayerWhenNoArgumentsThenVoid() {
        // Arrange
        // No arrangement for this test case

        // Act
        historyControllerAspectLogging.isRestControllerLayer();

        // Assert
        // No assertion for this test case as the method is void
    }
}