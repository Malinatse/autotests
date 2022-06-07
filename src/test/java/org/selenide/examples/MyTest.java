package org.selenide.examples;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.selenide.examples.testdriver.pages.LoginPage;

/**
 * Шаблон для набора тестовых кейзов
 */
public class MyTest
{
    public static LoginPage loginPage;

    @BeforeClass
    public static void setup()
    {

    }

    @Before
    public void login()
    {

    }

    @Test
    @DisplayName("Проверка суммы, позитивный кейз")
    public void testLogin(){
        Integer res = 2 + 2;

    }


    @After
    public void logout()
    {

    }

    @AfterClass
    public static void tearDown() {

    }
}
