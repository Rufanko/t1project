import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
    private static String accesToken;

    private static String login = "{\n" +
            "  \"username\": \"string\",\n" +
            "  \"password\": \"string\"\n" +
            "}";
    private static String requestBody = "{\n" +
            "  \"name\": \"New Product\",\n" +
            "  \"category\": \"Electronics\",\n" +
            "  \"price\": 12.99,\n" +
            "  \"discount";

    private static String updatedName = "{\n" +
            "  \"name\": \"Updated Product Name\",\n" +
            "  \"category\": \"Electronics\",\n" +
            "  \"price\": 15.99,\n" +
            "  \"discount\": 8\n" +
            "}";

    private static String registerData = "{\n" +
            "  \"username\": \"string\",\n" +
            "  \"password\": \"string\"\n" +
            "}";

    @BeforeAll
    public static void init () {
        RestAssured.baseURI = "http://9b142cdd34e.vps.myjino.ru:49268/";
        accesToken = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(200).and().extract().body().jsonPath().getString("access_token");
    }
    //регистрация пользователя
    @Test
    @DisplayName("Register user that exists")
    public void registerUser () {
        String msg = given()
                .contentType(ContentType.JSON)
                .body(registerData)
                .when()
                .post("register")
                .then()
                .assertThat().statusCode(400).and().extract().body().jsonPath().getString("message");
        Assertions.assertEquals("User already exists", msg);


    }

    //получить список продуктов
    @Test
    @DisplayName("Get a list of products")
    public void getProductList () {
        Response response = given()
                .when()
                .get("products")
                .then()
                .log().all()
                .statusCode(200).
                        extract().
                        response();
    }

    //добавить продукт
    @Test
    @DisplayName("Add a new product")
    public void postProduct () {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("products")
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    //получить информацию о конкретном продукте
    @Test
    @DisplayName("Get information about a specific product")
    public void getSpecificProduct () {
        String productName = given()
                .when()
                .get("products/1")
                .then()
                .assertThat().statusCode(200).and().extract().body().jsonPath().getString("name");
        Assertions.assertEquals("[HP Pavilion Laptop]", productName);
    }

    //изменить информацию о продукте
    @Test
    @DisplayName("Update information about a specific product")
    public void updateInformation () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .auth()
                .oauth2(accesToken)
                .body(updatedName)
                .put("products/124")
                .then()
                .assertThat().statusCode(201).and().extract()
                .response();
    }
    //удалить продукт
    @Test
    @DisplayName("Delete a specific product")
    public void deleteProduct() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .auth()
                .oauth2(accesToken)
                .body(updatedName)
                .delete("products/124")
                .then()
                .assertThat().statusCode(201).and().extract()
                .response();
    }

    //добавить продукт в корзину
    @Test
    @DisplayName("Add a product to the user's shopping cart")
    public void addItemToCart () {
        given()
                .auth()
                .oauth2(accesToken)
                .when()
                .post("cart/2")
                .then()
                .assertThat().statusCode(200);
    }
    //получить корзину пользователя
    @Test
    @DisplayName("Get the user's shopping cart")
    public void getCart () {
        String totalPrice = given()
                .auth()
                .oauth2(accesToken)
                .when()
                .get("cart")
                .then()
                .assertThat().statusCode(200).and().extract().body().jsonPath().getString("total_price");
        Assertions.assertEquals("395.64", totalPrice);
    }

    //удалить  продукт из корзины
    @Test
    @DisplayName("delete cart item")
    public void deleteCartItem () {
        given()
                .auth()
                .oauth2(accesToken)
                .when()
                .delete("cart/1")
                .then()
                .assertThat().statusCode(200);
    }



}
