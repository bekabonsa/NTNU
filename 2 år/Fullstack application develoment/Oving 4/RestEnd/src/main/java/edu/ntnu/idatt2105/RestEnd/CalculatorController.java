package edu.ntnu.idatt2105.RestEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<CalculationResult> evaluate(@RequestBody String expression) throws JsonProcessingException {
        CalculationResult result = calculatorService.evaluate(expression);
        return ResponseEntity.ok(result);
    }
}
