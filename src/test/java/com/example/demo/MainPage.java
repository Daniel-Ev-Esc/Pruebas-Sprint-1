package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {
    @FindBy(css = "input[id='matricula']")
    public WebElement inputMatricula;
    
    @FindBy(css = "button[id='boton-intro']")
    public WebElement buttonBotonIntro;
    @FindBy(css = "a[ng-reflect-router-link='reservaciones']")
    public WebElement reservaciones;

    @FindBy(css = "a[ng-reflect-router-link='inicio']")
    public WebElement home;

    @FindBy(css = "a[ng-reflect-router-link='avisos']")
    public WebElement notification;

    @FindBy(css = "h1[id='titulo-res']")
    public WebElement h1TituloRes;

    @FindBy(css = "h1[id='titulo-avisos']")
    public WebElement h1TituloAvisos;

    @FindBy(css = "h1[class^='texto-estado']")
    public WebElement h1Cerrado;

    @FindBy(css = "div[class*='btn-primary']")
    public WebElement divCambiarEstado;

    @FindBy(css = "div[id='titul-aforo']")
    public WebElement divTitulAforo;

    @FindBy(css = "p[class='saludo']")
    public WebElement pHoy;

    @FindBy(css = "p[id='fecha']")
    public WebElement pfecha;

    @FindBy(css = "p[id='hora']")
    public WebElement phora;

    
    
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
