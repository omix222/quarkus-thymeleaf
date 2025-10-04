package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.thymeleaf.context.Context;

@Path("/calculator")
public class CalculatorController {

    @Inject
    ThymeleafService thymeleafService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        Context context = new Context();
        context.setVariable("result", "0");
        context.setVariable("num1", "");
        context.setVariable("operator", "");
        context.setVariable("num2", "");
        return thymeleafService.process("calculator", context);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String calculate(
            @FormParam("num1") String num1,
            @FormParam("operator") String operator,
            @FormParam("num2") String num2) {

        String result = "0";
        Context context = new Context();

        // 入力値のサニタイズ
        String safeNum1 = (num1 != null && !num1.isEmpty()) ? num1 : "";
        String safeNum2 = (num2 != null && !num2.isEmpty()) ? num2 : "";
        String safeOperator = (operator != null && !operator.isEmpty()) ? operator : "";

        try {
            if (!safeNum1.isEmpty() && !safeNum2.isEmpty() && !safeOperator.isEmpty()) {

                double n1 = Double.parseDouble(safeNum1);
                double n2 = Double.parseDouble(safeNum2);
                double calcResult = 0;

                switch (safeOperator) {
                    case "+":
                        calcResult = n1 + n2;
                        break;
                    case "-":
                        calcResult = n1 - n2;
                        break;
                    case "*":
                        calcResult = n1 * n2;
                        break;
                    case "/":
                        if (n2 != 0) {
                            calcResult = n1 / n2;
                        } else {
                            result = "Error: Division by zero";
                            context.setVariable("result", result);
                            context.setVariable("num1", safeNum1);
                            context.setVariable("operator", safeOperator);
                            context.setVariable("num2", safeNum2);
                            return thymeleafService.process("calculator", context);
                        }
                        break;
                    default:
                        result = "Error: Invalid operator";
                        context.setVariable("result", result);
                        context.setVariable("num1", safeNum1);
                        context.setVariable("operator", safeOperator);
                        context.setVariable("num2", safeNum2);
                        return thymeleafService.process("calculator", context);
                }

                // 整数の場合は小数点以下を表示しない
                if (calcResult == (long) calcResult) {
                    result = String.format("%.0f", calcResult);
                } else {
                    result = String.valueOf(calcResult);
                }
            }
        } catch (NumberFormatException e) {
            result = "Error: Invalid number format";
        }

        context.setVariable("result", result);
        context.setVariable("num1", safeNum1);
        context.setVariable("operator", safeOperator);
        context.setVariable("num2", safeNum2);
        return thymeleafService.process("calculator", context);
    }
}