package com.tests;

import com.project.pages.AutomationPractise;
import org.testng.annotations.Test;

import java.io.IOException;

public class AutomationRunner extends AutomationPractise {
    @Test(priority = 1)
    public void verifyPagePage() throws InterruptedException {
        signAutomation();
    }
}
