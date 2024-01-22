package edu.ntnu.idatt2105.RestEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public CalculationResult evaluate(String expression) throws JsonProcessingException {
        logger.info("Evaluating expression: {}", expression);

        // Parse the JSON string
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(expression);

        // Get the expression value
        String express = json.get("expression").asText();


        // Perform calculation using exp4j
        try {
            Expression exp = new ExpressionBuilder(express).build();
            double result = exp.evaluate();
            CalculationResult calculationResult = new CalculationResult(expression, Double.toString(result));
            logger.info("Result: {}", calculationResult.getResult());
            System.out.println(expression +" = "+calculationResult.getResult());
            return calculationResult;
        } catch (Exception e) {
            logger.error("Error evaluating expression: {}", expression, e);
            throw new IllegalArgumentException("Error evaluating expression: " + expression, e);
        }
    }
}
