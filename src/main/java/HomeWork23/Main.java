package HomeWork23;
//Napraviti aplikaciju pomocu Selenium-a koji ce otvoriti sajt kupujemprodajem.com,
// izlistati sve kategorije (Stvari) sa leve strane i njihove linkove
// (kao spoken tekst “kategorija: link”), kliknuti iz te liste na Bicikli
// (bez hardkodovanja, posto imate listu, iskoristiti element iz nje da se klikne),
// kliknuti na Električni (mozete hardcodovati). Ostati na toj strani kao kraj zadatka.
// Uspavati program na 5 sekundi kako bi se video rezultat i posle toga browser zatvoriti.
//Obratiti paznju na "ad" koji kaze da se registrujete. Uzeti dugme x i kliknuti ga pre svega
// da ne bi ste imali problem da ne mozete da kliknete na kategoriju.
// Za 5+ nakon klika na kategoriju bicikli, treba izlistati sve kategorije koje pisu
// (Mountainbike, Gradski itd).

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.kupujemprodajem.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement xButton = driver.findElement(By.xpath("//*[@id=\"bodyTag\"]/div[9]/div/div[2]"));
        xButton.click();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        WebElement elementList = driver.findElement(By.id("category-tree-content-goods"));

        List<WebElement> list = elementList.findElements(By.xpath("./child::*"));
        String baseUrl = "https://www.kupujemprodajem.com";
        List<String> listArticles = new ArrayList<>();
        for (WebElement i : list) {
            listArticles.add(i.getText());
            listArticles.add(baseUrl + i.getDomAttribute("href"));
            System.out.println(i.getText() + ", " + baseUrl + i.getDomAttribute("href"));
        }

        for (int j = 0; j < listArticles.size(); j++) {
            if (listArticles.get(j).equals(("Bicikli"))) {
                driver.get(listArticles.get(j + 1));
            }
        }
        WebElement elBicycle = driver.findElement(By.xpath("//*[@id=\"groupBox1360\"]/div[1]/h2/a/span"));
        elBicycle.click();
        Thread.sleep(5000);
        driver.close();
    }
}
