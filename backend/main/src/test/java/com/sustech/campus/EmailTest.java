package com.sustech.campus;

import com.sustech.campus.utils.EmailUtil;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void testSendEmail() {
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.sendMail("12112513@mail.sustech.edu.cn", "你好", "test for ooad");
    }
}
