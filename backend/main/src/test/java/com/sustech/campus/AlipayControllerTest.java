package com.sustech.campus;

import com.sustech.campus.controller.AlipayController;
import com.sustech.campus.utils.AlipayUtil;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AlipayController.class)
public class AlipayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlipayUtil alipayUtil;

    @Test
    public void testGetPayQrCode() throws Exception {
        // Mock the behavior of AlipayUtil.getPayQrCode
        Mockito.when(alipayUtil.getPayQrCode(Mockito.any(AlipayTradePrecreateModel.class)))
                .thenReturn("MockedBase64QRCode");

        // Perform the test using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/alipay/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Add your JSON request content if needed
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("MockedBase64QRCode"));
    }
}
