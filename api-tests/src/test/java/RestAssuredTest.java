import com.fasterxml.jackson.databind.ObjectMapper;
import dataClasses.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String CART_ENDPOINT = "/cart";
    private static final String PRODUCTS_ENDPOINT = "/products";
    private static final String REGISTER_ENDPOINT = "/register";
    private static final String LOGIN = "string";
    private static final String PASSWORD = "string";
    private static String accessToken;

    @BeforeAll
    public static void init () {
        RestAssured.baseURI = "http://9b142cdd34e.vps.myjino.ru:49268/";
    }

    public static String authorize (String username, String password) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new User(username, password))
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().as(Token.class).getAccessToken();
    }

    @BeforeEach
    public void getToken () {
        accessToken = authorize(LOGIN, PASSWORD);
    }

    //регистрация пользователя, который уже существует.
    @Test
    @DisplayName("Register user that exists")
    public void registerExistedser () {
        String msg = given()
                .contentType(ContentType.JSON)
                .body(new User("string2", "string2"))
                .when()
                .post(REGISTER_ENDPOINT)
                .then()
                .assertThat().statusCode(400).and().extract().body().jsonPath().getString("message");
        Assertions.assertEquals("User already exists", msg);
    }

    //регистрация нового пользователя.
    @Test
    @DisplayName("Register a new user")
    public void registerNewUser () {
        //генерация рандомного логина и пароля
        String userName = TestHelper.generateRandomString();
        String pwd = TestHelper.generateRandomString();
        String msg = given()
                .contentType(ContentType.JSON)
                .body(new User(userName, pwd))
                .when()
                .post(REGISTER_ENDPOINT)
                .then()
                .assertThat().statusCode(201).and().extract().body().jsonPath().getString("message");
        Assertions.assertEquals("User registered successfully", msg);
    }

    //получить список продуктов
    @Test
    @DisplayName("Get a list of products")
    public void getProductList () {
        List<ProductCard> list = given()
                .accept(ContentType.JSON)
                .get(PRODUCTS_ENDPOINT)
                .then()
                .assertThat().statusCode(200)
                .and().extract().as(new ObjectMapper().getTypeFactory().constructCollectionType(List.class, ProductCard.class));
    }

    //добавить продукт
    @Test
    @DisplayName("Add a new product")
    public void postProduct () {
        given()
                .contentType(ContentType.JSON)
                .body(new ProductCard("Electronics", "test", "23", "test", "550"))
                .when()
                .post(PRODUCTS_ENDPOINT)
                .then()
                .assertThat().statusCode(201);
    }

    //добавить продукт
    @Test
    @DisplayName("Add an empty product")
    public void postEmptyProduct () {
        given()
                .contentType(ContentType.JSON)
                .body(new ProductCard(null, null, null, null, null))
                .when()
                .post(PRODUCTS_ENDPOINT)
                .then()
                .assertThat().statusCode(404);
    }

    //получить информацию о конкретном продукте
    @Test
    @DisplayName("Get information about a specific product")
    public void getSpecificProduct () {
        given()
                .when()
                .get(PRODUCTS_ENDPOINT + "/1")
                .then()
                .assertThat().
                statusCode(200)
                .and().extract().as(ProductCard.class);
    }

    //изменить информацию о продукте
    @Test
    @DisplayName("Update information about a specific product")
    public void updateInformation () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .auth()
                .oauth2(accessToken)
                .body(new ProductCard("Electronics", "test", "1", "test", "550"))
                .put(PRODUCTS_ENDPOINT + "1")
                .then()
                .assertThat().statusCode(201).and().extract()
                .response();
    }
    //изменить информацию о продукте, передав невалидный ID
    @Test
    @DisplayName("Update information about a specific product")
    public void updateInformation404 () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .auth()
                .oauth2(accessToken)
                .body(new ProductCard("Electronics", "test", "TEST", "test", "550"))
                .put(PRODUCTS_ENDPOINT + "1")
                .then()
                .assertThat().statusCode(404).and().extract()
                .response();
    }

    //удалить продукт
    @Test
    @DisplayName("Delete a specific product")
    public void deleteProduct () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .auth()
                .oauth2(accessToken)
                .delete(PRODUCTS_ENDPOINT + "/1")
                .then()
                .assertThat().statusCode(201);
    }

    //удалить несуществующий продукт
    @Test
    @DisplayName("Delete a specific product")
    public void deleteProductNotExisted () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .auth()
                .oauth2(accessToken)
                .delete(PRODUCTS_ENDPOINT + "/1231542135")
                .then()
                .assertThat().statusCode(404);
    }

    //добавить продукт в корзину
    @Test
    @DisplayName("Add a product to the user's shopping cart")
    public void addItemToCart () {
        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .body(new Product(1, 4))
                .post(CART_ENDPOINT)
                .then()
                .assertThat().statusCode(201);
    }

    //добавить продукт в корзину неавторизованным пользователем
    @Test
    @DisplayName("Add a product to the user's shopping cart not authorized")
    public void addItemToCartNonAuthorized () {
        given()
                .contentType(ContentType.JSON)
                .body(new Product(1, 4))
                .post(CART_ENDPOINT)
                .then()
                .assertThat().statusCode(401);
    }


    //получить корзину пользователя
    //в корзине получить список товаров, сопоставить с классом.
    @Test
    @DisplayName("Get the user's shopping cart")
    public void getCart () {
        List<ProductCard> l = given()
                .auth()
                .oauth2(accessToken)
                .when()
                .get(CART_ENDPOINT)
                .then()
                .assertThat().statusCode(200).and().extract().body().jsonPath().getList("cart", ProductCard.class);
    }


    //Unrecognized field "quantity" падает тест из-за несооветсвия полей в сваггере и в реальном ответе
    @Test
    @DisplayName("Get the user's shopping cart ")
    public void getCart3 () {
        given()
                .auth()
                .oauth2(accessToken)
                .when()
                .get(CART_ENDPOINT)
                .then()
                .assertThat().statusCode(200)
                .and().extract().as(ShoppingCart.class);
    }

    //Получить корзину неавторизованным пользователем
    @Test
    @DisplayName("Get the user's shopping cart not authorized")
    public void getCart4 () {
        given()
                .when()
                .get(CART_ENDPOINT)
                .then()
                .assertThat().statusCode(401);
    }

    //удалить  продукт из корзины
    @Test
    @DisplayName("delete cart item")
    public void deleteCartItem () {
        given()
                .auth()
                .oauth2(accessToken)
                .when()
                .delete(CART_ENDPOINT + "/1")
                .then()
                .assertThat().statusCode(200);
    }

    //удалить несуществубщий продукт из корзины, ожидаем 404
    //Product not found
    @Test
    @DisplayName("delete cart item")
    public void deleteCartItem2 () {
        given()
                .auth()
                .oauth2(accessToken)
                .when()
                .delete(CART_ENDPOINT + "/1234123123")
                .then()
                .assertThat().statusCode(404);
    }

    @Test
    @DisplayName("create cart item")
    public void createCartItem () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .body(new Product(3, 1))
                .post(CART_ENDPOINT)
                .then().statusCode(201);

    }

    //в запрос подсунули другой объект, ожидаем 404
    @Test
    @DisplayName("create cart item 404")
    public void createCartItemWrongObject () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .body(new ProductCard("Electronics", "test", "28", "test", "550"))
                .post(CART_ENDPOINT)
                .then().statusCode(404);

    }


}
