package com.newtonschool.question;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationIntegrationTest {

    private static final String WELCOME_URL = "/welcome";
    private static final String ADD_URL = "/add";
    private static final String SUBTRACT_URL = "/subtract";
    private static final String MULTIPLY_URL = "/multiply";
    private static final String DIVIDE_URL = "/divide";
    final String ADDITION_SUCCESS = "the sum of given numbers";
    final String SUBTRACTION_SUCCESS = "the difference of given numbers";
    final String MULTIPLICATION_SUCCESS = "the product of given numbers";
    final String DIVISION_SUCCESS = "the division of given numbers";
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Welcome Message When Page Loads")
    @Order(1)
    public void shouldTestThatTheHomePageLoads() throws Exception {
        mvc.perform(get(WELCOME_URL))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Add Positive Numbers, expected the sum of given numbers")
    @Order(2)
    public void shouldAddPositiveNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 1000);
        jsonObject.put("num2", 20000);

        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(21000.0)))
                .andExpect(jsonPath("$.message", is(ADDITION_SUCCESS)));
    }

    @Test
    @DisplayName("Add Negative Numbers, expected the sum of given numbers")
    @Order(3)
    public void shouldAddNegativeNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", -1000);
        jsonObject.put("num2", -20000);

        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(-21000.0)))
                .andExpect(jsonPath("$.message", is(ADDITION_SUCCESS)));
    }

    @Test
    @DisplayName("Add Decimal Numbers, expected the sum of given numbers")
    @Order(4)
    public void shouldAddDecimalNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 1.1);
        jsonObject.put("num2", 2.1);

        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(3.2)))
                .andExpect(jsonPath("$.message", is(ADDITION_SUCCESS)));
    }

    @Test
    @DisplayName("Subtract Positive Numbers, expected the difference of given numbers")
    @Order(5)
    public void shouldSubtractPositiveNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 20000);
        jsonObject.put("num2", 1000);

        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(19000.0)))
                .andExpect(jsonPath("$.message", is(SUBTRACTION_SUCCESS)));
    }

    @Test
    @DisplayName("Subtract Negative Numbers, expected the difference of given numbers")
    @Order(6)
    public void shouldSubtractNegativeNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", -1000);
        jsonObject.put("num2", -20000);

        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(19000.0)))
                .andExpect(jsonPath("$.message", is(SUBTRACTION_SUCCESS)));
    }

    @Test
    @DisplayName("Subtract Decimal Numbers, expected the difference of given numbers")
    @Order(7)
    public void shouldSubtractDecimalNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 1.1);
        jsonObject.put("num2", 2.5);

        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(-1.4)))
                .andExpect(jsonPath("$.message", is(SUBTRACTION_SUCCESS)));
    }

    @Test
    @DisplayName("Multiply Positive Numbers, expected the product of given numbers")
    @Order(8)
    public void shouldMultiplyPositiveNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 20);
        jsonObject.put("num2", 10);

        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(200.0)))
                .andExpect(jsonPath("$.message", is(MULTIPLICATION_SUCCESS)));
    }

    @Test
    @DisplayName("Multiply Negative Numbers, expected the product of given numbers")
    @Order(9)
    public void shouldMultiplyNegativeNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", -20);
        jsonObject.put("num2", -10);

        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(200.0)))
                .andExpect(jsonPath("$.message", is(MULTIPLICATION_SUCCESS)));
    }

    @Test
    @DisplayName("Multiply Decimal Numbers, expected the product of given numbers")
    @Order(10)
    public void shouldMultiplyDecimalNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 1.1);
        jsonObject.put("num2", 2.5);

        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(2.75)))
                .andExpect(jsonPath("$.message", is(MULTIPLICATION_SUCCESS)));
    }

    @Test
    @DisplayName("Divide Positive Numbers, expected the division of given numbers")
    @Order(11)
    public void shouldDividePositiveNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 20);
        jsonObject.put("num2", 10);

        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(2.0)))
                .andExpect(jsonPath("$.message", is(DIVISION_SUCCESS)));
    }

    @Test
    @DisplayName("Divide Negative Numbers, expected the division of given numbers")
    @Order(12)
    public void shouldDivideNegativeNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", -20);
        jsonObject.put("num2", -10);

        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(2.0)))
                .andExpect(jsonPath("$.message", is(DIVISION_SUCCESS)));
    }

    @Test
    @DisplayName("Divide Decimal Numbers, expected the division of given numbers")
    @Order(13)
    public void shouldDivideDecimalNumbers() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 2.0);
        jsonObject.put("num2", 2.5);

        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(0.8)))
                .andExpect(jsonPath("$.message", is(DIVISION_SUCCESS)));
    }

    @Test
    @DisplayName("Bad Request For Invalid Body, expected invalid input")
    @Order(14)
    public void shouldGetBadRequestWhenInvalidBody() throws Exception {
        final String INVALID_INPUT_ERROR_CODE = "E006";
        final String INVALID_INPUT_MESSAGE = "invalid input";

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("num1", 2.0);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));


        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("num2", 2.0);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject2.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject2.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject2.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject2.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));


        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("num1", "2.O");
        jsonObject3.put("num2", 2.0);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject3.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject3.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject3.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject3.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));


        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("num1", 2.0);
        jsonObject4.put("num2", "2.O");
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject4.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject4.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject4.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject4.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));


        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("num1", 1000001.0);
        jsonObject5.put("num2", 2.0);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject5.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject5.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject5.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject5.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));


        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("num1", -1000001.0);
        jsonObject6.put("num2", 2.0);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject6.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject6.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject6.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject6.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));


        JSONObject jsonObject7 = new JSONObject();
        jsonObject7.put("num1", 999999.0);
        jsonObject7.put("num2", -1000001.0);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject7.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject7.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject7.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject7.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));


        JSONObject jsonObject8 = new JSONObject();
        jsonObject8.put("num1", 2.0);
        jsonObject8.put("num2", -1000001.0);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject8.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject8.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject8.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject8.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_INPUT_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_INPUT_MESSAGE)));
    }

    @Test
    @DisplayName("Bad Request For Invalid Operation, expected invalid operation")
    @Order(15)
    public void shouldGetBadRequestWhenInvalidOperation() throws Exception {
        final String INVALID_OPERATION_ERROR_CODE = "E004";
        final String INVALID_OPERATION_MESSAGE = "invalid operation";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 2.0);
        jsonObject.put("num2", 2.5);

        mvc.perform(post("/someOperation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_OPERATION_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_OPERATION_MESSAGE)));

        mvc.perform(post("/+")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_OPERATION_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_OPERATION_MESSAGE)));

        mvc.perform(post("/-")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_OPERATION_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_OPERATION_MESSAGE)));

        mvc.perform(post("/*")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_OPERATION_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_OPERATION_MESSAGE)));

        mvc.perform(post("/someOtherOperation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_OPERATION_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(INVALID_OPERATION_MESSAGE)));
    }

    @Test
    @DisplayName("Bad Request For Result More Than One Million, expected overflow")
    @Order(16)
    public void shouldGetBadRequestWhenResultIsMoreThanOneMillion() throws Exception {
        final String OVERFLOW_ERROR_CODE = "E003";
        final String OVERFLOW_MESSAGE = "overflow";

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("num1", 500001);
        jsonObject1.put("num2", 500001);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(OVERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(OVERFLOW_MESSAGE)));

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("num1", 500001);
        jsonObject2.put("num2", -500001);
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject2.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(OVERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(OVERFLOW_MESSAGE)));

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("num1", 100000);
        jsonObject3.put("num2", 100);
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject3.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(OVERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(OVERFLOW_MESSAGE)));

        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("num1", 1000000);
        jsonObject4.put("num2", 0.001);
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject4.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(OVERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(OVERFLOW_MESSAGE)));
    }

    @Test
    @DisplayName("Bad Request For Result Less Than One Million, expected underflow")
    @Order(17)
    public void shouldGetBadRequestWhenResultIsLessThanMinusOneMillion() throws Exception {
        final String UNDERFLOW_ERROR_CODE = "E002";
        final String UNDERFLOW_MESSAGE = "underflow";
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("num1", -500001);
        jsonObject1.put("num2", -500001);
        mvc.perform(post(ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(UNDERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(UNDERFLOW_MESSAGE)));

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("num1", -500001);
        jsonObject2.put("num2", 500001);
        mvc.perform(post(SUBTRACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject2.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(UNDERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(UNDERFLOW_MESSAGE)));

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("num1", -100000);
        jsonObject3.put("num2", 100);
        mvc.perform(post(MULTIPLY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject3.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(UNDERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(UNDERFLOW_MESSAGE)));

        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("num1", -1000000);
        jsonObject4.put("num2", 0.001);
        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject4.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(UNDERFLOW_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(UNDERFLOW_MESSAGE)));
    }

    @Test
    @DisplayName("Bad Request For Dividing By Zero, expected divide by zero not allowed")
    @Order(18)
    public void shouldGetBadRequestWhenDividingByZero() throws Exception {
        final String DIVIDE_BY_ZERO_ERROR_CODE = "E005";
        final String DIVIDE_BY_ZERO_MESSAGE = "divide by zero not allowed";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num1", 2.0);
        jsonObject.put("num2", 0);

        mvc.perform(post(DIVIDE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(DIVIDE_BY_ZERO_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(DIVIDE_BY_ZERO_MESSAGE)));
    }

}