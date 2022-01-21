package com.test.proj.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.test.proj.test.model.IPAddressInfo;
import com.test.proj.test.model.OutputDTO;
import com.test.proj.test.model.RegInfoDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
public class RegistrationProcessorTest {

    @TestConfiguration
    static class RegistrationProcessorTestContextConfiguration {

        @Bean
        public RegistrationProcessor registrationProcessor() {
            return new RegistrationProcessor();
        }
    }

    @Autowired
    private RegistrationProcessor registrationProcessor;

    @MockBean
    private IpAPIClient ipAPIClient;

    @Before
    public void setup() {
    }

    @Test(expected = ResponseStatusException.class)
    public void shouldThrowExceptionWhenIPApiReturnsFail() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("123");
        info.setUsername("user");
        info.setPassword("Password1#");

        IPAddressInfo expected = new IPAddressInfo();
        expected.setStatus("fail");

        Mockito.when(ipAPIClient.getIPAddressInfo(info.getIpAddress())).thenReturn(expected);

        // when
        OutputDTO output = registrationProcessor.processRegistrationData(info);

        // then
        Mockito.verify(ipAPIClient, Mockito.times(1)).getIPAddressInfo(info.getIpAddress());
    }

    @Test
    public void shouldReturnOutputDTOWhenIPApiReturnsSuccess() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("123");
        info.setUsername("user");
        info.setPassword("Password1#");

        IPAddressInfo expected = new IPAddressInfo();
        expected.setStatus("success");
        expected.setCity("Ajax");

        Mockito.when(ipAPIClient.getIPAddressInfo(info.getIpAddress())).thenReturn(expected);

        // when
        OutputDTO output = registrationProcessor.processRegistrationData(info);

        // then
        assertTrue(output.getWelcomeMsg().contains(expected.getCity()));
        Mockito.verify(ipAPIClient, Mockito.times(1)).getIPAddressInfo(info.getIpAddress());
    }
}
