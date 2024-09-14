package com.hb.campaign;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CampaignApplicationTests {

    @Test
    void mainMethodTest() {
        assertDoesNotThrow(() -> CampaignApplication.main(new String[]{}));
    }
}
