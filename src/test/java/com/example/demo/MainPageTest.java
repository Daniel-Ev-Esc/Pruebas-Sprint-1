package com.example.demo;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class MainPageTest {

    public String diaSemana() {

        int day = LocalDate.now().getDayOfWeek().getValue();

        switch (day){
            case 1:
                return "LUNES";
            case 2:
                return "MARTES";
            case 3:
                return "MIÉRCOLES";
            case 4:
                return "JUEVES";
            case 5:
                return "VIERNES";
            case 6:
                return "SABADO";
            case 7:
            return "DOMINGO";
        }
        return " ";
    }

    public String diaMes(){
        int diam = LocalDate.now().getDayOfMonth();
        int mes = LocalDate.now().getMonthValue();
        String cero = diam < 10 ? "0" : "";

        switch (mes){
            case 1:
                return cero + diam + " de enero";
            case 2:
                return cero + diam + " de febrero";
            case 3:
                return cero + diam + " de marzo";
            case 4:
                return cero + diam + " de abril";
            case 5:
                return cero + diam + " de mayo";
            case 6:
                return cero + diam + " de junio";
            case 7:
                return cero + diam + " de julio";
            case 8:
                return cero + diam + " de agosto";
            case 9:
                return cero + diam + " de septiembre";
            case 10:
                return cero + diam + " de octubre";
            case 11:
                return cero + diam + " de noviembre";
            case 12:
                return cero + diam + " de diciembre";
        }
        return " ";
    }

    public String obtenerHora(){

        int horaAct = LocalTime.now().getHour() < 12 ? LocalTime.now().getHour() - 1 : LocalTime.now().getHour() - 13;
        horaAct = horaAct == -1 ? 12 : horaAct;
        int minAct = LocalTime.now().getMinute();
        String cero = horaAct < 10 ? "0" : "";
        String ceroMin = minAct < 10 ? "0" : "";

        String a = LocalTime.now().getHour() - 1 < 12 ? "a. m." : "p. m.";

        return cero + horaAct + ":" + ceroMin + minAct + " " + a;
    }

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:4200/");

        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void alumnoFallido() {
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("A00830000");
        mainPage.buttonBotonIntro.click();

        assertEquals(driver.findElements(By.id("titul-aforo")).isEmpty(),true);

    }

    @Test
    public void alumnoExitoso(){
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("A00830337");
        mainPage.buttonBotonIntro.click();

        assertEquals(mainPage.divTitulAforo.getText(),"Aforo actual");
    }

    @Test
    public void adminFallido() {
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("L00830000");
        mainPage.buttonBotonIntro.click();

        assertEquals(driver.findElements(By.id("titul-aforo")).isEmpty(),true);
    }

    @Test
    public void adminExitoso(){
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("L00000000");
        mainPage.buttonBotonIntro.click();

        assertEquals(mainPage.divTitulAforo.getText(),"Aforo actual");
    }

    @Test
    public void navReservaciones(){
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("A00830337");
        mainPage.buttonBotonIntro.click();

        mainPage.reservaciones.click();
        assertEquals(mainPage.h1TituloRes.getText(),"Reservaciones");
    }

    @Test
    public void navAvisos(){
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("A00830337");
        mainPage.buttonBotonIntro.click();

        mainPage.notification.click();
        assertEquals(mainPage.h1TituloAvisos.getText(),"Avisos");
    }

    @Test
    public void navHome(){
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("A00830337");
        mainPage.buttonBotonIntro.click();

        mainPage.notification.click();
        mainPage.home.click();
        assertEquals(mainPage.divTitulAforo.getText(),"Aforo actual");
    }

    @Test
    public void mensajeBienvenida(){
        String mensajeEsperado = "HOY, " + diaSemana();
        String fechaEsperada = diaMes();
        String horaEsperada = obtenerHora();

        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("A00830337");
        mainPage.buttonBotonIntro.click();

        assertEquals(mainPage.pHoy.getText(),mensajeEsperado);
        assertEquals(mainPage.pfecha.getText(),fechaEsperada);
        assertEquals(mainPage.phora.getText(), horaEsperada);
    }

    @Test
    public void estadoCerrado(){
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("A00830337");
        mainPage.buttonBotonIntro.click();

        assertEquals(mainPage.h1Cerrado.getText(),"Cerrado");
    }

    @Test
    public void cambiarEstado(){
        mainPage.inputMatricula.click();
        mainPage.inputMatricula.sendKeys("L00000000");
        mainPage.buttonBotonIntro.click();

        mainPage.divCambiarEstado.click();

        assertEquals(mainPage.h1Cerrado.getText(),"Abierto");
    }
}
