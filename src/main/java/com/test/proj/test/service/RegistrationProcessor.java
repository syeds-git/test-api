package com.test.proj.test.service;

import com.test.proj.test.model.IPAddressInfo;
import com.test.proj.test.model.OutputDTO;
import com.test.proj.test.model.RegInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service for processing registration information
 */
@Service
public class RegistrationProcessor {
    public static final String MSG = "Welcome %s from the city of %s";
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationProcessor.class);
    public static final String FAIL = "fail";

    @Autowired
    private IpAPIClient ipAPIClient;

    public OutputDTO processRegistrationData(RegInfoDTO regInfoDTO) {
        LOG.info("Processing registration info: {}", regInfoDTO);
        String ipAddress = regInfoDTO.getIpAddress();
        String username = regInfoDTO.getUsername();

        IPAddressInfo info = ipAPIClient.getIPAddressInfo(ipAddress);
        if (info.getStatus().equalsIgnoreCase(FAIL)) {
            LOG.info("Failed to fetch IP info for {}", ipAddress);
            LOG.info("Failure reason: {}", info.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, info.getMessage());
        }

        LOG.info("IP info: {}", info);

        String welcomeMsg = String.format(MSG, username, info.getCity());
        return new OutputDTO(welcomeMsg);
    }
}
